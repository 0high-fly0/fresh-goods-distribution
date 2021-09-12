package cn.edu.zucc.distribution.ui;

import cn.edu.zucc.distribution.DistributionUtil;
import cn.edu.zucc.distribution.model.*;
import cn.edu.zucc.distribution.util.BaseException;
import cn.edu.zucc.distribution.util.BusinessException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class FrmOrderNotDelivery extends JDialog implements ActionListener {
    private JPanel workPane=new JPanel();
    private JPanel toolBar=new JPanel();
    private JPanel LowPane=new JPanel();

    List<orders> deliveryList=new ArrayList<orders>();
    private JButton jbtAddList=new JButton("ѡ��");
    private JButton jbtDeleteList=new JButton("ȡ��ѡ��");
    private JButton jbtDelivery=new JButton("����");
    private JLabel labelTips=new JLabel("��ǰ��ѡ�񶩵����� 0 ������� 0");
    float w=0;
    float v=0;

    List<car> CarAll =new ArrayList<car>();
    private Object tblCarTitle[]=car.tableTitles;
    private Object tblCarData[][];
    DefaultTableModel tabCarModel =new DefaultTableModel();
    private JTable dataTableCar =new JTable(tabCarModel);
    private void reloadCar(){
        try {
            CarAll = DistributionUtil.deliveryMannger.loadAllCar();
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
            return ;
        }
        tblCarData =new Object[CarAll.size()][car.tableTitles.length];
        for (int i = 0; i< CarAll.size(); i++){
            for (int j=0;j<car.tableTitles.length;j++)
                tblCarData[i][j]= CarAll.get(i).getCell(j);
        }

        tabCarModel.setDataVector(tblCarData, tblCarTitle);
        this.dataTableCar.validate();
        this.dataTableCar.repaint();

    }

    List<orders> AllOrders =null;
    private Object OrdersTitles[]=orders.tableTitlesdelivery;
    private Object tblOrdersData[][];
    DefaultTableModel tabOrdersModel =new DefaultTableModel();
    private JTable dataTableorder =new JTable(tabOrdersModel);

    private void reloadOrders(){

        tblOrdersData =new Object[AllOrders.size()][orders.tableTitlesdelivery.length];
        for (int i = 0; i< AllOrders.size(); i++){
            tblOrdersData[i][0]= AllOrders.get(i).getCell(0);
            tblOrdersData[i][1]= AllOrders.get(i).getCell(1);
            tblOrdersData[i][2]= AllOrders.get(i).getCell(2);
            tblOrdersData[i][3]= AllOrders.get(i).getCell(3);
            tblOrdersData[i][4]= AllOrders.get(i).getCell(4);
            tblOrdersData[i][5]= AllOrders.get(i).getCell(5);
            tblOrdersData[i][6]= AllOrders.get(i).getCell(6);
            tblOrdersData[i][7]= AllOrders.get(i).getCell(7);

        }

        tabOrdersModel.setDataVector(tblOrdersData, OrdersTitles);
        this.dataTableorder.validate();
        this.dataTableorder.repaint();

    }

    public FrmOrderNotDelivery(Frame f, String s, boolean b){
        super(f, s, b);
        customer.currentLoginUser=new customer();
        customer.currentLoginUser.setUserid(0);
        toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.getContentPane().add(toolBar, BorderLayout.NORTH);

        workPane.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.getContentPane().add(workPane, BorderLayout.CENTER);

        try {
            AllOrders = DistributionUtil.orderMannger.loadAllOrdersNotDelivery();
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
            return ;
        }

        this.getContentPane().add(new JScrollPane(this.dataTableorder), BorderLayout.CENTER);
        this.getContentPane().add(new JScrollPane(this.dataTableCar), BorderLayout.EAST);
        this.reloadCar();
        this.reloadOrders();

        LowPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.getContentPane().add(LowPane, BorderLayout.SOUTH);
        LowPane.add(labelTips);
        LowPane.add(jbtAddList);
        LowPane.add(jbtDeleteList);
        LowPane.add(jbtDelivery);
        jbtAddList.addActionListener(this);
        jbtDeleteList.addActionListener(this);
        jbtDelivery.addActionListener(this);

        this.setSize(1500, 700);
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.validate();

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==this.jbtAddList){
            int i= FrmOrderNotDelivery.this.dataTableorder.getSelectedRow();
            if(i<0) {
                JOptionPane.showMessageDialog(null, "��ѡ�񶩵�", "����",JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (deliveryList.size()>0){
                for (int j=0;j<deliveryList.size();j++){
                    if (deliveryList.get(j).getOrderid()==this.AllOrders.get(i).getOrderid()){
                        JOptionPane.showMessageDialog(null, "�ö�����ѡ��", "����",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    try {
                        if (DistributionUtil.orderMannger.getHouseId(this.AllOrders.get(i).getOrderid())!=DistributionUtil.orderMannger.getHouseId(deliveryList.get(j).getOrderid())){
                            throw new BusinessException("�ö�������ѡ�񶩵�����ͬһ�ֿ⣬�޷���ͬ���ͣ�");
                        }
                    } catch (BaseException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

            }
            deliveryList.add(this.AllOrders.get(i));
            w=w+this.AllOrders.get(i).getOrderweight();
            v=v+this.AllOrders.get(i).getOrderv();
            labelTips.setText("��ǰ��ѡ�񶩵����� "+w+" ������� "+v);
            JOptionPane.showMessageDialog(null, "��ӳɹ��� ", "��ʾ" , JOptionPane.PLAIN_MESSAGE);
        }
        else if (e.getSource()==this.jbtDeleteList){
            int i= FrmOrderNotDelivery.this.dataTableorder.getSelectedRow();
            if(i<0) {
                JOptionPane.showMessageDialog(null, "��ѡ�񶩵�", "����",JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (deliveryList.size()>0){
                boolean flag=false;
                for (int j=0;j<deliveryList.size();j++){
                    if (deliveryList.get(j).getOrderid()==this.AllOrders.get(i).getOrderid()){
                        flag=true;
                        break;
                    }
                }
                if (!flag) {
                    JOptionPane.showMessageDialog(null, "����δѡ��", "����", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                deliveryList.remove(this.AllOrders.get(i));
                JOptionPane.showMessageDialog(null, "ȡ��ѡ��ɹ��� ", "��ʾ" , JOptionPane.PLAIN_MESSAGE);
                w=w-this.AllOrders.get(i).getOrderweight();
                v=v-this.AllOrders.get(i).getOrderv();
                labelTips.setText("��ǰ��ѡ�񶩵����� "+w+" ������� "+v);
                return;
            }
            JOptionPane.showMessageDialog(null, "��ѡ��Ķ���", "����",JOptionPane.ERROR_MESSAGE);
        }
        else if (e.getSource()==this.jbtDelivery){
            if (deliveryList.size()==0) {
                JOptionPane.showMessageDialog(null, "��ѡ�񶩵�", "����",JOptionPane.ERROR_MESSAGE);
                return;
            }
            int j= FrmOrderNotDelivery.this.dataTableCar.getSelectedRow();
            if(j<0) {
                JOptionPane.showMessageDialog(null, "��ѡ�����ͳ���", "����",JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                DistributionUtil.deliveryMannger.deliveryOK(deliveryList,w,v,this.CarAll.get(j));
                labelTips.setText("��ǰ��ѡ�񶩵����� 0 ������� 0");
                float w=0;
                float v=0;
                List<car> CarAll =new ArrayList<car>();
                AllOrders = DistributionUtil.orderMannger.loadAllOrdersNotDelivery();
                this.reloadCar();
                this.reloadOrders();
            } catch (BaseException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
            }
        }

    }
}

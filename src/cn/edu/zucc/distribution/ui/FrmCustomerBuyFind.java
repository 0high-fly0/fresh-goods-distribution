package cn.edu.zucc.distribution.ui;

import cn.edu.zucc.distribution.DistributionUtil;
import cn.edu.zucc.distribution.model.customer;
import cn.edu.zucc.distribution.model.order_information;
import cn.edu.zucc.distribution.model.orders;
import cn.edu.zucc.distribution.model.user;
import cn.edu.zucc.distribution.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class FrmCustomerBuyFind extends JDialog implements ActionListener {
    private JPanel workPane=new JPanel();
    private JPanel toolBar=new JPanel();
    private JPanel LowPane=new JPanel();


    int num=0;
    float price=0.0f;
    float weight=0.0f;
    float v=0.0f;
    DecimalFormat decimalFormat=new DecimalFormat(".##");

    private JLabel labelInputId =new JLabel("��������Ҫ��ѯ���û���ţ�");
    private JTextField jtInputId=new JTextField(20);
    private JButton jbtFind=new JButton("��ѯ");
    private JLabel labelInform=new JLabel("�û� ��������� �������� 0 ,�ܽ�� 0 RMB�������� 0 ������� 0");

    List<order_information> OrderInformationAll =null;
    private Object tblOrderInformationTitle[]=order_information.tableTitlesCustomer;
    private Object tblOrderInformationData[][];
    DefaultTableModel tabOrderInformationModel =new DefaultTableModel();
    private JTable dataTableOrderInformation =new JTable(tabOrderInformationModel);
    private void reloadorderInformation(int orderid){
        try {
            OrderInformationAll = DistributionUtil.orderMannger.loadAllorder_information(orderid);
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
            return ;
        }
        tblOrderInformationData =new Object[OrderInformationAll.size()][order_information.tableTitlesCustomer.length];
        for (int i = 0; i< OrderInformationAll.size(); i++){
            tblOrderInformationData[i][0]= OrderInformationAll.get(i).getCell(1);
            tblOrderInformationData[i][1]= OrderInformationAll.get(i).getCell(8);
            tblOrderInformationData[i][2]= OrderInformationAll.get(i).getCell(3);
            tblOrderInformationData[i][3]= OrderInformationAll.get(i).getCell(5);
            tblOrderInformationData[i][4]= OrderInformationAll.get(i).getCell(7);
        }

        tabOrderInformationModel.setDataVector(tblOrderInformationData, tblOrderInformationTitle);
        this.dataTableOrderInformation.validate();
        this.dataTableOrderInformation.repaint();

    }

    List<orders> AllOrders =null;
    private Object OrdersTitles[]=orders.tableTitlesCustomer;
    private Object tblOrdersData[][];
    DefaultTableModel tabOrdersModel =new DefaultTableModel();
    private JTable dataTableorder =new JTable(tabOrdersModel);

    private void reloadOrders(){
        try {
            AllOrders = DistributionUtil.orderMannger.loadAllOrders();
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
            return ;
        }
        tblOrdersData =new Object[AllOrders.size()][orders.tableTitlesCustomer.length];
        for (int i = 0; i< AllOrders.size(); i++){
            tblOrdersData[i][0]= AllOrders.get(i).getCell(0);
            tblOrdersData[i][1]= AllOrders.get(i).getCell(2);
            tblOrdersData[i][2]= AllOrders.get(i).getCell(3);
            tblOrdersData[i][3]= AllOrders.get(i).getCell(4);
            tblOrdersData[i][4]= AllOrders.get(i).getCell(5);
            tblOrdersData[i][5]= AllOrders.get(i).getCell(7);
        }

        tabOrdersModel.setDataVector(tblOrdersData, OrdersTitles);
        this.dataTableorder.validate();
        this.dataTableorder.repaint();

    }

    public FrmCustomerBuyFind(Frame f, String s, boolean b){
        super(f, s, b);
        customer.currentLoginUser=new customer();
        customer.currentLoginUser.setUserid(0);
        toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.getContentPane().add(toolBar, BorderLayout.NORTH);
        toolBar.add(labelInputId);
        toolBar.add(jtInputId);
        toolBar.add(jbtFind);
        jbtFind.addActionListener(this);

        workPane.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.getContentPane().add(workPane, BorderLayout.CENTER);

        this.getContentPane().add(new JScrollPane(this.dataTableorder), BorderLayout.CENTER);
        this.dataTableorder.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e) {
                int i= FrmCustomerBuyFind.this.dataTableorder.getSelectedRow();
                if(i<0) {
                    return;
                }
                FrmCustomerBuyFind.this.reloadorderInformation(AllOrders.get(i).getOrderid());
            }

        });
        this.getContentPane().add(new JScrollPane(this.dataTableOrderInformation), BorderLayout.EAST);

        this.reloadOrders();

        LowPane.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.getContentPane().add(LowPane, BorderLayout.SOUTH);
        LowPane.add(labelInform);
        this.setSize(1300, 500);
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.validate();

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==this.jbtFind){
            try {
                if (jtInputId==null || "".equals(jtInputId.getText()))
                    throw new BusinessException("��ѯ��������Ϊ�գ�");
            } catch (BusinessException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
                return;
            }

            customer.currentLoginUser.setUserid(Integer.parseInt(jtInputId.getText()));
            try {
                FiveTuple<Integer,Float,Float,Float,Boolean> m=DistributionUtil.orderMannger.customerBuyFind();
                if (m.fifth) {
                    System.out.println("1");
                    num = m.first;
                    price = m.second;
                    weight = m.third;
                    v = m.fourth;
                    labelInform.setText("�û� "+customer.currentLoginUser.getUsername()+" ��������� �������� "+num+" ,�ܽ�� "+decimalFormat.format(price)+" RMB�������� "+decimalFormat.format(weight)+" ������� "+decimalFormat.format(v));
                    this.reloadOrders();
                }else {
                    labelInform.setText("�û� ��������� �������� 0 ,�ܽ�� 0 RMB�������� 0 ������� 0");
                    OrderInformationAll=new ArrayList<order_information>();
                    this.reloadorderInformation(-1);
                    this.reloadOrders();
                    throw new BusinessException("�û������ڻ���û���û�ж���");
                }
            } catch (BaseException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
            }
        }


    }
}

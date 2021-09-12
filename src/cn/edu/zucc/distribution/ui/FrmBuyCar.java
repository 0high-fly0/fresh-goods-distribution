package cn.edu.zucc.distribution.ui;

import cn.edu.zucc.distribution.DistributionUtil;
import cn.edu.zucc.distribution.model.customer;
import cn.edu.zucc.distribution.model.goods;
import cn.edu.zucc.distribution.model.order_information;
import cn.edu.zucc.distribution.util.BaseException;
import cn.edu.zucc.distribution.util.BusinessException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class FrmBuyCar extends JDialog implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JPanel toolBar=new JPanel();
    private JPanel workspace=new JPanel();
    private JPanel LowPane=new JPanel();

    private JLabel labeladdress=new JLabel("��ǰ��ַΪ��"+ customer.currentLoginUser.getAddress());
    private JLabel labeljwd=new JLabel("          ��ǰ���ȣ�"+customer.currentLoginUser.getCuslgt()+
            "           ��ǰγ�ȣ�"+customer.currentLoginUser.getCuslat());
    private JLabel labelphone=new JLabel("           ��ǰ��ϵ�绰��"+customer.currentLoginUser.getCusphone());
    private JButton jbtbuy=new JButton("�ύ����");
    private JButton jbtdelete=new JButton("ɾ����Ʒ");
    private JLabel labelNeedTime=new JLabel("Ҫ���ʹ�ʱ��");
    private JLabel labelAllPrice;
    private JTextField jtNeedTime=new JTextField(20);

    List<order_information> buyCarAllGoods=null;
    private Object tblbuyCarTitle[]=order_information.tableTitlesCustomer;
    private Object tblbuyCarData[][];
    DefaultTableModel tabBuyCarModel=new DefaultTableModel();
    private JTable dataTableBuyCar=new JTable(tabBuyCarModel);
    float price=0;
    private float reloadBuyCaiinform(){
        float AllPrice=0;
        try {
            buyCarAllGoods= DistributionUtil.goodsManager.LoadBuyCar();
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
            return AllPrice;
        }
        tblbuyCarData=new Object[buyCarAllGoods.size()][goods.tableTitlesCustomer.length];
        for (int i=0;i<buyCarAllGoods.size();i++){
            tblbuyCarData[i][0]=buyCarAllGoods.get(i).getCell(1);
            tblbuyCarData[i][1]=buyCarAllGoods.get(i).getCell(8);
            tblbuyCarData[i][2]=buyCarAllGoods.get(i).getCell(3);
            tblbuyCarData[i][3]=buyCarAllGoods.get(i).getCell(5);
            tblbuyCarData[i][4]=buyCarAllGoods.get(i).getCell(7);
            AllPrice+=Float.parseFloat(buyCarAllGoods.get(i).getCell(7));
        }

        tabBuyCarModel.setDataVector(tblbuyCarData,tblbuyCarTitle);
        this.dataTableBuyCar.validate();
        this.dataTableBuyCar.repaint();
        return AllPrice;

    }

    public FrmBuyCar(Frame f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.getContentPane().add(toolBar,BorderLayout.NORTH);
        toolBar.add(labeladdress);
        toolBar.add(labeljwd);
        toolBar.add(labelphone);

        LowPane.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.getContentPane().add(LowPane,BorderLayout.CENTER);

        DecimalFormat df2 = new DecimalFormat("###.0");
        this.getContentPane().add(new JScrollPane(this.dataTableBuyCar), BorderLayout.CENTER);
        price=this.reloadBuyCaiinform();
        labelAllPrice=new JLabel("  �ܼ۸�"+ df2.format(price));

        LowPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.getContentPane().add(LowPane,BorderLayout.SOUTH);
        LowPane.add(labelNeedTime);
        LowPane.add(jtNeedTime);
        LowPane.add(jbtbuy);
        LowPane.add(jbtdelete);
        LowPane.add(labelAllPrice);
        jbtbuy.addActionListener(this);
        jbtdelete.addActionListener(this);
        this.setSize(700, 500);
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int)((width - this.getWidth()) / 2), (int)((height - this.getHeight()) / 2));
        this.validate();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==this.jbtdelete){
            int i= FrmBuyCar.this.dataTableBuyCar.getSelectedRow();
            if(i<0) {
                JOptionPane.showMessageDialog(null, "��ѡ���ﳵ��Ʒ��", "����",JOptionPane.ERROR_MESSAGE);
                return;
            }
                if(JOptionPane.showConfirmDialog(this,"ȷ��ɾ���ù��ﳵ��Ϣ��","ȷ��",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                    try {
                        //(new ParkingManager()).delete(p.getParkNo(),);
                        DistributionUtil.orderMannger.DeleteBuyCar(this.buyCarAllGoods.get(i));
                        JOptionPane.showMessageDialog(null, "ɾ���ɹ�!����ʡ��һ��Ǯ�� ", "��ʾ" , JOptionPane.PLAIN_MESSAGE);
                        //ParkingManager.delete(p.getParkNo());
                        this.reloadBuyCaiinform();
                    } catch (BaseException e1) {
                        JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
                    }

                }

        }else if (e.getSource()==this.jbtbuy){
            if(JOptionPane.showConfirmDialog(this,"�����ܽ�"+price,"ȷ�϶���",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                try {
                    //(new ParkingManager()).delete(p.getParkNo(),);
                    DistributionUtil.orderMannger.CreateOrder(this.jtNeedTime.getText());
                    JOptionPane.showMessageDialog(null, "����ɹ��� ", "��ʾ" , JOptionPane.PLAIN_MESSAGE);
                    //ParkingManager.delete(p.getParkNo());
                    this.reloadBuyCaiinform();
                } catch (BaseException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
                }

            }
        }
    }


}

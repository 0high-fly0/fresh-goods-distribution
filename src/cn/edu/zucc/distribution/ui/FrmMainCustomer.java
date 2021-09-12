package cn.edu.zucc.distribution.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import cn.edu.zucc.distribution.DistributionUtil;
import cn.edu.zucc.distribution.model.*;
import cn.edu.zucc.distribution.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FrmMainCustomer extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JMenuBar menubar=new JMenuBar();
    private JMenu menu_user=new JMenu("�û�����");
    private JMenuItem menu_change_pwd=new JMenuItem("�޸�����");
    private JMenuItem menu_change_address=new JMenuItem("�޸ĵ�ַ");
    private JMenuItem menu_change_lgt=new JMenuItem("�޸ľ���");
    private JMenuItem menu_change_lat=new JMenuItem("�޸�γ��");

    private JMenu menu_sousuo=new JMenu("��Ϣ��ѯ");
    private JMenuItem menu_orders_find=new JMenuItem("��Ʒ��Ϣ��ѯ");

    private JMenu menu_buy=new JMenu("����");
    private JMenuItem menu_all_order=new JMenuItem("ȫ����Ʒ");
    private JMenuItem menu_buy_car=new JMenuItem("���ﳵ");
    private JMenuItem menu_order=new JMenuItem("�鿴��ʷ����");

    private JButton jbtAddBuycar=new JButton("���빺�ﳵ");


    DefaultTableModel tabGoodsModel=new DefaultTableModel();
    private JTable dataTableGoods=new JTable(tabGoodsModel);

    private JPanel statusBar = new JPanel();
    private JPanel statusButton=new JPanel();

    ImageIcon icon1=new ImageIcon("src/logo.jpg" );

    List<goods> allgoods=null;
    private Object tblGoodsTitle[]=goods.tableTitlesCustomer;
    private Object tblGoodData[][];
    private void reloadgoodsinform(){
        try {
            allgoods= DistributionUtil.goodsManager.loadAll();
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
            return;
        }
        tblGoodData=new Object[allgoods.size()][goods.tableTitlesCustomer.length];
        for (int i=0;i<allgoods.size();i++){
            for (int j=0;j<goods.tableTitlesCustomer.length;j++){
                tblGoodData[i][j]=allgoods.get(i).getCell(j);
            }
        }
        tabGoodsModel.setDataVector(tblGoodData,tblGoodsTitle);
        this.dataTableGoods.validate();
        this.dataTableGoods.repaint();

    }

    public FrmMainCustomer(){
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setTitle("�������ܶ���ϵͳ V1.0.0");
        this.menu_user.add(this.menu_change_pwd);
        this.menu_change_pwd.addActionListener(this);
        this.menu_user.add(this.menu_change_address);
        this.menu_user.add(this.menu_change_lgt);
        this.menu_user.add(this.menu_change_lat);
        this.menu_change_address.addActionListener(this);
        this.menu_change_lgt.addActionListener(this);
        this.menu_change_lat.addActionListener(this);

        this.menu_sousuo.add(menu_orders_find);
        this.menu_orders_find.addActionListener(this);

        this.menu_buy.add(menu_all_order);
        this.menu_buy.add(menu_buy_car);
        this.menu_buy.add(menu_order);
        this.menu_all_order.addActionListener(this);
        this.menu_buy_car.addActionListener(this);
        this.menu_order.addActionListener(this);

        menubar.add(menu_user);
        menubar.add(menu_sousuo);
        menubar.add(menu_buy);
        this.setJMenuBar(menubar);

        this.getContentPane().add(new JScrollPane(this.dataTableGoods), BorderLayout.CENTER);
        this.reloadgoodsinform();

        statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel label=new JLabel("�𾴵� " + customer.currentLoginUser.getUsername()+", ����! ");//�޸ĳ�   ���ã�+��½�û���
        statusBar.add(label);
        statusBar.add(jbtAddBuycar);
        jbtAddBuycar.addActionListener(this);

        this.getContentPane().add(statusBar,BorderLayout.SOUTH);

        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });


        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==this.menu_change_pwd){
            FrmChangePwd dlg=new FrmChangePwd(this,"�޸�����",true,"customer");
            dlg.setVisible(true);
        }
        else if (e.getSource()==this.menu_change_address){
            FrmChangeAddress dlg=new FrmChangeAddress(this,"�޸ĵ�ַ",true);
            dlg.setVisible(true);
        }else if (e.getSource()==this.menu_change_lgt){
            FrmChangeLgt dlg=new FrmChangeLgt(this,"�޸ľ���",true);
            dlg.setVisible(true);
        }else if (e.getSource()==this.menu_change_lat){
            FrmChangeLat dlg=new FrmChangeLat(this,"�޸�γ��",true);
            dlg.setVisible(true);
        }else if (e.getSource()==this.menu_all_order){
            this.reloadgoodsinform();
        }else if (e.getSource()==this.menu_orders_find){
            FrmDemandGoods dlg=new FrmDemandGoods(this,"��Ʒ��Ϣ��ѯ",true,"customer");
            dlg.setVisible(true);
        }else if (e.getSource()==this.menu_buy_car){
            FrmBuyCar dlg=new FrmBuyCar(this,"���ﳵ",true);
            dlg.setVisible(true);
            this.reloadgoodsinform();
        }else if (e.getSource()==this.menu_order){
            FrmOldOrder dlg=new FrmOldOrder(this,"��ʷ����",true);
            dlg.setVisible(true);
            this.reloadgoodsinform();
        }else if (e.getSource()==this.jbtAddBuycar){
            int i= FrmMainCustomer.this.dataTableGoods.getSelectedRow();
            if(i<0) {
                JOptionPane.showMessageDialog(null, "��ѡ����Ʒ", "����",JOptionPane.ERROR_MESSAGE);
                return;
            }
            FrmAddToCart dig=new FrmAddToCart(this,"ѡ����Ʒ����",true,this.allgoods.get(i));
            dig.setVisible(true);
            this.reloadgoodsinform();
        }

    }
}

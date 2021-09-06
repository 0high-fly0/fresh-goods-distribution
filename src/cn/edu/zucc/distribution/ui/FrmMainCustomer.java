package cn.edu.zucc.distribution.ui;

import javax.swing.*;
import java.awt.*;
import cn.edu.zucc.distribution.DistributionUtil;
import cn.edu.zucc.distribution.model.*;
import cn.edu.zucc.distribution.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import java.awt.event.ActionListener;

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
    private JMenuItem menu_warehouse_find =new JMenuItem("�ֿ���Ϣ��ѯ");

    private JMenu menu_buy=new JMenu("����");
    private JMenuItem menu_all_order=new JMenuItem("ȫ����Ʒ");
    private JMenuItem menu_buy_car=new JMenuItem("���ﳵ");
    private JMenuItem menu_order=new JMenuItem("�鿴��ʷ����");

    private JPanel statusBar = new JPanel();

    public FrmMainCustomer(){
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setTitle("������������ϵͳ V1.0.0");
        this.menu_user.add(this.menu_change_pwd);
        this.menu_user.add(this.menu_change_address);
        this.menu_user.add(this.menu_change_lgt);
        this.menu_user.add(this.menu_change_lat);

        this.menu_sousuo.add(menu_orders_find);
        this.menu_sousuo.add(menu_warehouse_find);

        this.menu_buy.add(menu_all_order);
        this.menu_buy.add(menu_buy_car);
        this.menu_buy.add(menu_order);

        menubar.add(menu_user);
        menubar.add(menu_sousuo);
        menubar.add(menu_buy);
        this.setJMenuBar(menubar);

        statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel label=new JLabel("�𾴵� " + customer.currentLoginUser.getUsername()+", ����! ");//�޸ĳ�   ���ã�+��½�û���
        statusBar.add(label);
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
    }
}

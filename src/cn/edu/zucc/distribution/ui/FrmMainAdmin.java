package cn.edu.zucc.distribution.ui;

import cn.edu.zucc.distribution.DistributionUtil;
import cn.edu.zucc.distribution.model.*;
import cn.edu.zucc.distribution.model.goods;
import cn.edu.zucc.distribution.util.BaseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class FrmMainAdmin extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    private JMenuBar menubar=new JMenuBar();
    private JMenu menu_user=new JMenu("�û�����");
    private JMenuItem menu_customer_manage =new JMenuItem("�ͻ���Ϣ����");
    private JMenuItem menu_change_pwd =new JMenuItem("�޸�����");

    private JMenu menu_ziyuan_manage =new JMenu("��Դ����");
    private JMenuItem menu_GoodsType_manage =new JMenuItem("����������");
    private JMenuItem menu_Goods_manage=new JMenuItem("��Ʒ����");
    private JMenuItem menu_house_manage=new JMenuItem("�ֿ����");
    private JMenuItem menu_Car_manage=new JMenuItem("��������");


    private JMenu menu_Order_Done =new JMenu("��������");
    private JMenuItem menu_Not_deliery_Orders =new JMenuItem("δ���Ͷ���");
    private JMenuItem menu_Orders_get =new JMenuItem("�����ʹ�");

    private JMenu menu_Find =new JMenu("��Ϣ��ѯ");
    private JMenuItem menu_buy_Find =new JMenuItem("�ɹ�����ѯ");
    private JMenuItem menu_Orders_Find =new JMenuItem("������ѯ");
    private JMenuItem menu_deliery_Find =new JMenuItem("���͵���ѯ");
    private JMenuItem menu_Goods_Find=new JMenuItem("��Ʒ��ѯ");
    private JMenuItem munu_Customer_Buy_Find=new JMenuItem("�˿����������ѯ");

    private JButton jbtBuy =new JButton("�ɹ�");


    DefaultTableModel tabGoodsModel=new DefaultTableModel();
    private JTable dataTableGoods=new JTable(tabGoodsModel);

    private JPanel statusBar = new JPanel();

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

    public FrmMainAdmin(){
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setTitle("������������ϵͳ(����Ա��) V1.0.0 ");
        this.menu_user.add(this.menu_customer_manage);
        this.menu_customer_manage.addActionListener(this);
        this.menu_user.add(this.menu_change_pwd);
        this.menu_change_pwd.addActionListener(this);

        this.menu_ziyuan_manage.add(menu_GoodsType_manage);
        this.menu_GoodsType_manage.addActionListener(this);
        this.menu_ziyuan_manage.add(menu_Goods_manage);
        this.menu_Goods_manage.addActionListener(this);
        this.menu_ziyuan_manage.add(menu_house_manage);
        this.menu_house_manage.addActionListener(this);
        this.menu_ziyuan_manage.add(menu_Car_manage);
        this.menu_Car_manage.addActionListener(this);

        this.menu_Order_Done.add(menu_Not_deliery_Orders);
        this.menu_Order_Done.add(menu_Orders_get);
        this.menu_Not_deliery_Orders.addActionListener(this);
        this.menu_Orders_get.addActionListener(this);

        this.menu_Find.add(menu_buy_Find);
        this.menu_buy_Find.addActionListener(this);
        this.menu_Find.add(menu_Orders_Find);
        this.menu_Orders_Find.addActionListener(this);
        this.menu_Find.add(menu_deliery_Find);
        this.menu_deliery_Find.addActionListener(this);
        this.menu_Find.add(menu_Goods_Find);
        this.menu_Goods_Find.addActionListener(this);
        this.menu_Find.add(munu_Customer_Buy_Find);
        this.munu_Customer_Buy_Find.addActionListener(this);

        menubar.add(menu_user);
        menubar.add(menu_ziyuan_manage);
        menubar.add(menu_Order_Done);
        menubar.add(menu_Find);
        this.setJMenuBar(menubar);

        this.getContentPane().add(new JScrollPane(this.dataTableGoods), BorderLayout.CENTER);
        this.reloadgoodsinform();

        statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel label=new JLabel("�𾴵Ĺ���Ա " + admin.currentLoginUser.getUsername()+", ����! ");//�޸ĳ�   ���ã�+��½�û���
        statusBar.add(label);
        statusBar.add(jbtBuy);
        jbtBuy.addActionListener(this);

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
            FrmChangePwd dlg=new FrmChangePwd(this,"�޸�����",true,"admin");
            dlg.setVisible(true);
        }else if (e.getSource()==this.menu_Goods_Find){
            FrmDemandGoods dlg=new FrmDemandGoods(this,"��Ʒ��Ϣ��ѯ",true,"admin");
            dlg.setVisible(true);
        }else if (e.getSource()==this.menu_customer_manage){
            FrmCustomerManage dlg=new FrmCustomerManage(this,"�ͻ���Ϣ����",true);
            dlg.setVisible(true);
        }else if (e.getSource()==this.munu_Customer_Buy_Find){
            FrmCustomerBuyFind dlg=new FrmCustomerBuyFind(this,"�ͻ����������ѯ",true);
            dlg.setVisible(true);
        }else if (e.getSource()==this.menu_Orders_Find){
            FrmCustomerOrderFind dlg=new FrmCustomerOrderFind(this,"������ѯ",true);
            dlg.setVisible(true);
        }else if (e.getSource()==this.menu_GoodsType_manage){
            FrmCommodityTypeManage dlg=new FrmCommodityTypeManage(this,"����������",true);
            dlg.setVisible(true);
        }else if (e.getSource()==this.menu_house_manage){
            FrmWareHouse dlg=new FrmWareHouse(this,"�ֿ����",true);
            dlg.setVisible(true);
        }else if (e.getSource()==this.menu_Car_manage){
            FrmCar dlg=new FrmCar(this,"��������",true);
            dlg.setVisible(true);
        }else if (e.getSource()==this.menu_Goods_manage){
            FrmGoodsManage dlg=new FrmGoodsManage(this,"��Ʒ����",true);
            dlg.setVisible(true);
            this.reloadgoodsinform();
        }else if (e.getSource()==this.jbtBuy){
            int i= FrmMainAdmin.this.dataTableGoods.getSelectedRow();
            if(i<0) {
                FrmGoodsBuyAdd dlg=new FrmGoodsBuyAdd(this,"����Ʒ�ɹ�",true);
                dlg.setVisible(true);
                this.reloadgoodsinform();
            }else {
                FrmGoodsBuyHas dlg=new FrmGoodsBuyHas(this,"��Ʒ�ɹ�",true,this.allgoods.get(i));
                dlg.setVisible(true);
                this.reloadgoodsinform();
            }
        }else if (e.getSource()==this.menu_buy_Find){
            FrmBuyFind dlg=new FrmBuyFind(this,"�ɹ�����ѯ",true);
            dlg.setVisible(true);
        }else if (e.getSource()==this.menu_Not_deliery_Orders){
            FrmOrderNotDelivery dlg=new FrmOrderNotDelivery(this,"δ���Ͷ�������",true);
            dlg.setVisible(true);
        }else if (e.getSource()==this.menu_deliery_Find){
            FrmDeliveryFind dlg=new FrmDeliveryFind(this,"���͵���ѯ",true);
            dlg.setVisible(true);
        }else if (e.getSource()==this.menu_Orders_get){
            FrmDeliveryFinish dlg=new FrmDeliveryFinish(this,"�����е����͵�",true);
            dlg.setVisible(true);
        }
    }

}

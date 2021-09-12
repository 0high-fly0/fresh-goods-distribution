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
import java.util.ArrayList;
import java.util.List;

public class FrmBuyFind extends JDialog implements ActionListener {
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JPanel LowPane=new JPanel();

    private JLabel labelType=new JLabel("��ѯ����");
    private JComboBox<String> labelDemandType=new JComboBox<String>();
    private JTextField labelDemandDate=new JTextField(20);
    private JButton jbtDemand=new JButton("��ѯ");

    List<buy> allBuy =new ArrayList<buy>();
    private Object tblBuyTitle[]=buy.tableTitles;
    private Object tblBuyData[][];
    DefaultTableModel tabBuyModel =new DefaultTableModel();
    private JTable dataBuyGoods =new JTable(tabBuyModel);
    private void reloadbuy(){
        tblBuyData =new Object[allBuy.size()][buy.tableTitles.length];
        for (int i = 0; i< allBuy.size(); i++){
            for (int j=0;j<buy.tableTitles.length;j++){
                tblBuyData[i][j]= allBuy.get(i).getCell(j);
            }
        }
        tabBuyModel.setDataVector(tblBuyData, tblBuyTitle);
        this.dataBuyGoods.validate();
        this.dataBuyGoods.repaint();

    }
    public FrmBuyFind(Frame f, String s, boolean b) {
        super(f, s, b);
        labelDemandType.addItem("�ɹ������");       // ����4������ѡ��
        labelDemandType.addItem("��Ʒ���");
        labelDemandType.addItem("�ֿ���");
        toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.getContentPane().add(toolBar, BorderLayout.NORTH);
        toolBar.add(labelType);
        toolBar.add(labelDemandType);
        toolBar.add(labelDemandDate);
        toolBar.add(jbtDemand);
        jbtDemand.addActionListener(this);

        try {
            allBuy = DistributionUtil.goodsManager.loadAllbuy();
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.getContentPane().add(new JScrollPane(this.dataBuyGoods), BorderLayout.CENTER);
        this.reloadbuy();

        this.setSize(600, 300);

        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.validate();

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==jbtDemand){
            try {
                if (labelDemandDate.getText()==null || "".equals(labelDemandDate.getText()))
                    throw new BusinessException("��ѯ���ݲ���Ϊ�գ�");
                if (labelDemandType.getSelectedItem().toString().equals("�ɹ������")){
                    try {
                        allBuy =DistributionUtil.goodsManager.loadAllFindBuyId(Integer.parseInt(labelDemandDate.getText()));
                        if  (allBuy ==null) {
                            allBuy = DistributionUtil.goodsManager.loadAllbuy();
                            this.reloadbuy();
                            throw new BaseException("δ�ҵ��ɹ���");
                        }
                        else
                            this.reloadbuy();
                    } catch (BaseException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
                    }
                }else if (labelDemandType.getSelectedItem().toString().equals("��Ʒ���")){
                    try {
                        allBuy =DistributionUtil.goodsManager.loadAllFindGoodId(Integer.parseInt(labelDemandDate.getText()));
                        if  (allBuy ==null) {
                            allBuy = DistributionUtil.goodsManager.loadAllbuy();
                            this.reloadbuy();
                            throw new BaseException("δ�ҵ��ɹ���");
                        }
                        else
                            this.reloadbuy();
                    } catch (BaseException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
                    }
                }else if (labelDemandType.getSelectedItem().toString().equals("�ֿ���")){
                    try {
                        allBuy =DistributionUtil.goodsManager.loadAllFindHouseId(Integer.parseInt(labelDemandDate.getText()));
                        if  (allBuy ==null) {
                            allBuy = DistributionUtil.goodsManager.loadAllbuy();
                            this.reloadbuy();
                            throw new BaseException("δ�ҵ��ɹ���");
                        }
                        else
                            this.reloadbuy();
                    } catch (BaseException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
                    }
                }

            } catch (BusinessException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
            }
        }


    }


}

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

public class FrmCustomerManage extends JDialog implements ActionListener {
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JPanel LowPane=new JPanel();

    private JLabel labelType=new JLabel("��ѯ����");
    private JComboBox<String> labelDemandType=new JComboBox<String>();
    private JTextField labelDemandDate=new JTextField(20);
    private JButton jbtDemand=new JButton("��ѯ");
    private JButton jbtaddCustomer =new JButton("��ӿͻ�");
    private JButton jbtChangeInformation=new JButton("�޸���Ϣ");
    private JButton jbtDeleteCustomer=new JButton("ɾ���˿�");

    List<customer> allcustomer =new ArrayList<customer>();
    private Object tblCustomerTitle[]=customer.tableTitles;
    private Object tblCustomerData[][];
    DefaultTableModel tabCustomerModel =new DefaultTableModel();
    private JTable dataTableCUstomer =new JTable(tabCustomerModel);
    private void reloadCustomer(){
        tblCustomerData =new Object[allcustomer.size()][customer.tableTitles.length];
        for (int i = 0; i< allcustomer.size(); i++){
            for (int j=0;j<customer.tableTitles.length;j++){
                tblCustomerData[i][j]= allcustomer.get(i).getCell(j);
            }
        }
        tabCustomerModel.setDataVector(tblCustomerData, tblCustomerTitle);
        this.dataTableCUstomer.validate();
        this.dataTableCUstomer.repaint();

    }
    public FrmCustomerManage(Frame f, String s, boolean b) {
        super(f, s, b);
        labelDemandType.addItem("�˿ͱ��");       // ����4������ѡ��
        labelDemandType.addItem("�˿�����");
        toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.getContentPane().add(toolBar, BorderLayout.NORTH);
        toolBar.add(labelType);
        toolBar.add(labelDemandType);
        toolBar.add(labelDemandDate);
        toolBar.add(jbtDemand);
        jbtDemand.addActionListener(this);

        try {
            allcustomer = DistributionUtil.userManager.loadAllCustomer();
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
            return;
        }

        this.getContentPane().add(new JScrollPane(this.dataTableCUstomer), BorderLayout.CENTER);
        this.reloadCustomer();


        LowPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        LowPane.add(jbtaddCustomer);
        jbtaddCustomer.addActionListener(this);
        LowPane.add(jbtChangeInformation);
        jbtChangeInformation.addActionListener(this);
        LowPane.add(jbtDeleteCustomer);
        jbtDeleteCustomer.addActionListener(this);
        this.getContentPane().add(LowPane,BorderLayout.SOUTH);

        this.setSize(1300, 500);

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
                if (labelDemandType.getSelectedItem().toString().equals("�˿ͱ��")){
                    try {
                        allcustomer =DistributionUtil.userManager.FindCustomerById(Integer.parseInt(labelDemandDate.getText()));
                        if  (allcustomer ==null) {
                            allcustomer=new ArrayList<customer>();
                            this.reloadCustomer();
                            throw new BaseException("δ�ҵ��ù˿�");
                        }
                        this.reloadCustomer();
                    } catch (BaseException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
                    }
                }else if (labelDemandType.getSelectedItem().toString().equals("�˿�����")) {
                    try {
                        allcustomer = DistributionUtil.userManager.FingCustomerByName(labelDemandDate.getText());
                        System.out.println("2");
                        if (allcustomer == null) {
                            allcustomer=new ArrayList<customer>();
                            this.reloadCustomer();
                            throw new BaseException("δ�ҵ��˿�");
                        }
                        else
                            this.reloadCustomer();
                    } catch (BaseException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
                    }
                }

            } catch (BusinessException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
            }
        }else if (e.getSource()==this.jbtaddCustomer){
            FrmRegisterCustomer dlg= new FrmRegisterCustomer(this,"�����˿�",true);
            dlg.setVisible(true);
            this.reloadCustomer();
        }else if (e.getSource()==this.jbtChangeInformation){
            int i= FrmCustomerManage.this.dataTableCUstomer.getSelectedRow();
            if(i<0) {
                JOptionPane.showMessageDialog(null, "��ѡ��ͻ���", "����",JOptionPane.ERROR_MESSAGE);
                return;
            }
            FrmChangeCustomerInformation dlg=new FrmChangeCustomerInformation(this,"�޸���Ϣ",true,this.allcustomer.get(i));
            dlg.setVisible(true);
            this.reloadCustomer();
        }else if (e.getSource()==this.jbtDeleteCustomer){
            int i= FrmCustomerManage.this.dataTableCUstomer.getSelectedRow();
            if(i<0) {
                JOptionPane.showMessageDialog(null, "��ѡ��ͻ�", "����",JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(JOptionPane.showConfirmDialog(this,"ȷ��ɾ���ÿͻ���","ȷ��",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                try {
                    DistributionUtil.userManager.deleteCustomer(this.allcustomer.get(i).getUserid());
                    JOptionPane.showMessageDialog(null, "ɾ���ɹ��� ", "��ʾ" , JOptionPane.PLAIN_MESSAGE);
                    this.reloadCustomer();
                } catch (BaseException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
                }

            }

        }


    }


}

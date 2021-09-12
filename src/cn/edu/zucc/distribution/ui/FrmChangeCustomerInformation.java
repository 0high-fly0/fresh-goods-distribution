package cn.edu.zucc.distribution.ui;

import cn.edu.zucc.distribution.DistributionUtil;
import cn.edu.zucc.distribution.model.customer;
import cn.edu.zucc.distribution.util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrmChangeCustomerInformation extends JDialog implements ActionListener {
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JButton btnOk = new JButton("ע��");
    private JButton btnCancel = new JButton("ȡ��");

    private JLabel labelUser = new JLabel("�û�������");
    private JLabel labelPwd = new JLabel("��¼���룺");
    private JLabel labelphone=new JLabel("��ϵ��ʽ��");
    private JLabel labelemail=new JLabel("�������䣺");
    private JLabel labeladdress=new JLabel("��ס��ַ��");
    private JLabel labellgt=new JLabel("���꾭�ȣ�");
    private JLabel labellat=new JLabel("����γ�ȣ�");

    private JTextField edtUserName = new JTextField(20);
    private JPasswordField edtPwd = new JPasswordField(20);
    private JTextField edtphone = new JTextField(20);
    private JTextField edtEmail = new JTextField(20);
    private JTextField edtAddress = new JTextField(20);
    private JTextField edtlgt = new JTextField(20);
    private JTextField edtlat = new JTextField(20);
    customer c=null;
    public FrmChangeCustomerInformation(Dialog f, String s, boolean b, customer c) {
        super(f, s, b);
        this.c=c;
        edtUserName = new JTextField(String.valueOf(c.getUsername()),20);
        edtUserName.setEditable(false);
        edtPwd = new JPasswordField(c.getPwd(),20);
        edtphone = new JTextField(c.getCusphone(),20);
        edtEmail = new JTextField(c.getCusemail(),20);
        edtAddress = new JTextField(c.getAddress(),20);
        edtlgt = new JTextField(String.valueOf(c.getCuslgt()),20);
        edtlat = new JTextField(String.valueOf(c.getCuslat()),20);
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(this.btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        workPane.add(labelUser);
        workPane.add(edtUserName);
        workPane.add(labelPwd);
        workPane.add(edtPwd);
        workPane.add(labelphone);
        workPane.add(edtphone);
        workPane.add(labelemail);
        workPane.add(edtEmail);
        workPane.add(labeladdress);
        workPane.add(edtAddress);
        workPane.add(labellgt);
        workPane.add(edtlgt);
        workPane.add(labellat);
        workPane.add(edtlat);
        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(330, 300);//Adjust the height
        this.btnCancel.addActionListener(this);
        this.btnOk.addActionListener(this);
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);
        this.validate();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.btnCancel)
            this.setVisible(false);
        else if(e.getSource()==this.btnOk){
            String pwd1=new String(this.edtPwd.getPassword());
            String phone=this.edtphone.getText();
            String email=this.edtEmail.getText();
            String address=this.edtAddress.getText();
            String lgtstr=this.edtlgt.getText();
            String latstr=this.edtlat.getText();
            float lgt=-1;
            if (lgtstr!=null && !"".equals(lgtstr)) lgt=new Float(lgtstr);
            float lat=-1;
            if (latstr!=null && !"".equals(latstr)) lat=new Float(latstr);
            try {
                DistributionUtil.userManager.changeCustomerInformation(c, pwd1,phone,email,address,lgt,lat);
                JOptionPane.showMessageDialog(null, "�û���Ϣ�޸ĳɹ�! ", "��ϲ" , JOptionPane.PLAIN_MESSAGE);
                this.setVisible(false);
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
                return;
            }

        }


    }


}
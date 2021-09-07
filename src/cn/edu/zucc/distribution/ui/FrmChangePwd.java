package cn.edu.zucc.distribution.ui;

import cn.edu.zucc.distribution.DistributionUtil;
import cn.edu.zucc.distribution.model.*;
import cn.edu.zucc.distribution.util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmChangePwd extends JDialog implements ActionListener {
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private Button btnOk = new Button("ȷ��");
    private Button btnCancel = new Button("ȡ��");

    private JLabel labelPwdOld = new JLabel("ԭ���룺");
    private JLabel labelPwd = new JLabel("�����룺");
    private JLabel labelPwd2 = new JLabel("�����룺");
    private JPasswordField edtPwdOld = new JPasswordField(20);
    private JPasswordField edtPwd = new JPasswordField(20);
    private JPasswordField edtPwd2 = new JPasswordField(20);
    String type_s;
    public FrmChangePwd(Frame f, String s, boolean b, String type_s) {
        super(f, s, b);
        this.type_s=type_s;
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(this.btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        workPane.add(labelPwdOld);
        workPane.add(edtPwdOld);
        workPane.add(labelPwd);
        workPane.add(edtPwd);
        workPane.add(labelPwd2);
        workPane.add(edtPwd2);
        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(330, 220);

        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.validate();

        this.btnCancel.addActionListener(this);
        this.btnOk.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.btnCancel)
            this.setVisible(false);
        else if(e.getSource()==this.btnOk){
            try {
                if (this.type_s.equals("customer")) {
                    DistributionUtil.userManager.changePwdCustomer(customer.currentLoginUser, new String(edtPwdOld.getPassword()), new String(edtPwd.getPassword()), new String(edtPwd2.getPassword()));
                }else {
                    DistributionUtil.userManager.changePwdAdmin(admin.currentLoginUser, new String(edtPwdOld.getPassword()), new String(edtPwd.getPassword()), new String(edtPwd2.getPassword()));
                }
                this.setVisible(false);
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
                return;
            }
        }


    }


}

package cn.edu.zucc.distribution.ui;

import cn.edu.zucc.distribution.DistributionUtil;
import cn.edu.zucc.distribution.model.customer;
import cn.edu.zucc.distribution.util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmChangeLgt extends JDialog implements ActionListener {
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JPanel title=new JPanel();
    private Button btnOk = new Button("ȷ��");
    private Button btnCancel = new Button("ȡ��");

    private JLabel labelOldAddress=null;
    private JLabel labelAddress = new JLabel("�¾��ȣ�");
    private JTextField edtAddress = new JPasswordField(20);
    public FrmChangeLgt(Frame f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(this.btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        labelOldAddress=new JLabel("ԭ����Ϊ��"+ customer.currentLoginUser.getCuslgt());
        title.add(labelOldAddress);
        this.getContentPane().add(title,BorderLayout.NORTH);
        workPane.add(labelAddress);
        workPane.add(edtAddress);
        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(330, 150);

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
                float lgt=-1;
                if (edtAddress.getText()!=null && !"".equals(edtAddress.getText())) lgt=new Float(edtAddress.getText());
                DistributionUtil.userManager.changeLgtCustomer(customer.currentLoginUser, lgt);
                this.setVisible(false);
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
                return;
            }
        }


    }


}

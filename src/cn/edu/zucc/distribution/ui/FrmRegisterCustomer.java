package cn.edu.zucc.distribution.ui;

import cn.edu.zucc.distribution.DistributionUtil;
import cn.edu.zucc.distribution.model.*;
import cn.edu.zucc.distribution.util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrmRegisterCustomer extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("ע��");
	private JButton btnCancel = new JButton("ȡ��");

	private JLabel labelUser = new JLabel("�û�������");
	private JLabel labelPwd = new JLabel("��¼���룺");
	private JLabel labelPwd2 = new JLabel("ȷ�����룺");
	private JLabel labelphone=new JLabel("��ϵ��ʽ��");
	private JLabel labelemail=new JLabel("�������䣺");
	private JLabel labeladdress=new JLabel("��ס��ַ��");
	private JLabel labellgt=new JLabel("���꾭�ȣ�");
	private JLabel labellat=new JLabel("����γ�ȣ�");

	private JTextField edtUserId = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);
	private JPasswordField edtPwd2 = new JPasswordField(20);
	private JTextField edtphone = new JTextField(20);
	private JTextField edtEmail = new JTextField(20);
	private JTextField edtAddress = new JTextField(20);
	private JTextField edtlgt = new JTextField(20);
	private JTextField edtlat = new JTextField(20);
	public FrmRegisterCustomer(Dialog f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelUser);
		workPane.add(edtUserId);
		workPane.add(labelPwd);
		workPane.add(edtPwd);
		workPane.add(labelPwd2);
		workPane.add(edtPwd2);
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
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			String username	=(this.edtUserId.getText());
			String pwd1=new String(this.edtPwd.getPassword());
			String pwd2=new String(this.edtPwd2.getPassword());
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
				DistributionUtil.userManager.regcustom(username, pwd1,pwd2,phone,email,address,lgt,lat);
				JOptionPane.showMessageDialog(null, "�û�ע��ɹ�! ", "��ϲ" , JOptionPane.PLAIN_MESSAGE);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
		}
			
		
	}


}

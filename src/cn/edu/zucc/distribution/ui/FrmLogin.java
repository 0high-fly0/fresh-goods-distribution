package cn.edu.zucc.distribution.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import cn.edu.zucc.distribution.DistributionUtil;
import cn.edu.zucc.distribution.model.*;
import cn.edu.zucc.distribution.util.BaseException;


public class FrmLogin extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnLogin = new JButton("��¼");
	private JButton btnCancel = new JButton("�˳�");
	private JButton btnRegister = new JButton("ע��");
	
	private JLabel labelUser = new JLabel("�˺�");
	private JLabel labelPwd = new JLabel("����");
	private JRadioButton rbcustom=new JRadioButton("�ͻ�",true);
	private JRadioButton rbadmin=new JRadioButton("����Ա");
	private JTextField edtUsername = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);


	public FrmLogin(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnRegister);
		toolBar.add(btnLogin);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelUser);
		workPane.add(edtUsername);
		workPane.add(labelPwd);
		workPane.add(edtPwd);
		ButtonGroup group = new ButtonGroup();
		group.add(rbcustom);
		group.add(rbadmin);
		workPane.add(rbcustom);
		workPane.add(rbadmin);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 180);
		// ��Ļ������ʾ
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		btnLogin.addActionListener(this);
		btnCancel.addActionListener(this);
		this.btnRegister.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnLogin) {
			String username=this.edtUsername.getText();
			String pwd=new String(this.edtPwd.getPassword());
			try {
				if (this.rbcustom.isSelected()) customer.currentLoginUser= DistributionUtil.userManager.logincustomer(username, pwd);
				else admin.currentLoginUser= DistributionUtil.userManager.loginadmin(username, pwd);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.setVisible(false);
			
		} else if (e.getSource() == this.btnCancel) {
			System.exit(0);
		} else if(e.getSource()==this.btnRegister){
			FrmRegisterType dlg=new FrmRegisterType(this,"ע��",true);
			dlg.setVisible(true);
		}
	}

}

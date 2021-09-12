package cn.edu.zucc.distribution.ui;

import cn.edu.zucc.distribution.DistributionUtil;
import cn.edu.zucc.distribution.util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmAddType extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("���");
	private JButton btnCancel = new JButton("ȡ��");

	private JLabel labelTypeName = new JLabel("������ƣ�");
	private JLabel labelTypeDescrible = new JLabel("���������");

	private JTextField edtTypeName = new JTextField(20);
	private JTextField edtTypeDescrible = new JTextField(20);
	public FrmAddType(Dialog f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelTypeName);
		workPane.add(edtTypeName);
		workPane.add(labelTypeDescrible);
		workPane.add(edtTypeDescrible);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(330, 150);//Adjust the height
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
			String TypeName	=this.edtTypeName.getText();
			String TypeDescrible=this.edtTypeDescrible.getText();
			try {
				DistributionUtil.goodsManager.createNewType(TypeName, TypeDescrible);
				JOptionPane.showMessageDialog(null, "�����ӳɹ�! ", "��ϲ" , JOptionPane.PLAIN_MESSAGE);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
				return;
			}

		}
			
		
	}


}

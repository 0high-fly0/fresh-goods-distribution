package cn.edu.zucc.distribution.ui;

import cn.edu.zucc.distribution.DistributionUtil;
import cn.edu.zucc.distribution.util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmAddCar extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("���");
	private JButton btnCancel = new JButton("ȡ��");

	private JLabel labelCarnum = new JLabel("���ƺţ�  ");
	private JLabel labelCarWeght =new JLabel("�������أ�");
	private JLabel labelCarV =new JLabel("�����ݻ���");
	private JLabel labelCarPhone =new JLabel("��ϵ�绰��");
	private JLabel labelCarCold =new JLabel("�Ƿ���أ�");
	private JComboBox<String> boxCarCold=new JComboBox<String>();

	private JTextField edtCarnum = new JTextField(20);
	private JTextField edtCarWeight = new JTextField(20);
	private JTextField edtCarV = new JTextField(20);
	private JTextField edtCarphone = new JTextField(20);
	private JTextField edtCarCold = new JTextField(20);
	public FrmAddCar(Dialog f, String s, boolean b) {
		super(f, s, b);
		boxCarCold.addItem("��س�");
		boxCarCold.addItem("����س�");
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelCarnum);
		workPane.add(edtCarnum);
		workPane.add(labelCarWeght);
		workPane.add(edtCarWeight);
		workPane.add(labelCarV);
		workPane.add(edtCarV);
		workPane.add(labelCarPhone);
		workPane.add(edtCarphone);
		workPane.add(labelCarCold);
		workPane.add(boxCarCold);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(330, 250);//Adjust the height
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
			String num	=this.edtCarnum.getText();
			String phone=this.edtCarphone.getText();
			boolean cold= boxCarCold.getSelectedItem().toString().equals("��س�");
			float weight=-1;
			if (this.edtCarWeight.getText()!=null && !"".equals(this.edtCarWeight.getText())) weight=new Float(this.edtCarWeight.getText());
			float v=-1;
			if (this.edtCarV.getText()!=null && !"".equals(this.edtCarV.getText())) v=new Float(this.edtCarV.getText());
			try {
				DistributionUtil.deliveryMannger.createNewCar(num,weight,v,phone,cold);
				JOptionPane.showMessageDialog(null, "������ӳɹ�! ", "��ϲ" , JOptionPane.PLAIN_MESSAGE);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
				return;
			}

		}
			
		
	}


}

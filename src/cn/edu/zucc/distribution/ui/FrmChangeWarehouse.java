package cn.edu.zucc.distribution.ui;

import cn.edu.zucc.distribution.DistributionUtil;
import cn.edu.zucc.distribution.model.warehouses;
import cn.edu.zucc.distribution.util.BaseException;
import cn.edu.zucc.distribution.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmChangeWarehouse extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("�޸�");
	private JButton btnCancel = new JButton("ȡ��");

	private JLabel labelWarehouId=new JLabel("�ֿ��ţ�");
	private JLabel labelname = new JLabel("�ֿ����ƣ�");
	private JLabel labeladdress =new JLabel("�ֿ��ַ��");
	private JLabel labelphone =new JLabel("��ϵ�绰��");
	private JLabel labellgt=new JLabel("���꾭�ȣ�");
	private JLabel labellat=new JLabel("����γ�ȣ�");

	private JTextField edtid =new JTextField(20);
 	private JTextField edtname = new JTextField(20);
	private JTextField edtaddress = new JTextField(20);
	private JTextField edtphone = new JTextField(20);
	private JTextField edtlgt = new JTextField(20);
	private JTextField edtlat = new JTextField(20);
	public FrmChangeWarehouse(Dialog f, String s, boolean b, warehouses c) {
		super(f, s, b);
		edtid =new JTextField(String.valueOf(c.getHouseid()),20);
		edtid.setEditable(false);
		edtname = new JTextField(c.getHousename(),20);
		edtaddress = new JTextField(c.getHouseaddress(),20);
		edtphone = new JTextField(c.getHousephone(),20);
		edtlgt = new JTextField(Float.toString(c.getHouseLgt()),20);
		edtlat = new JTextField(Float.toString(c.getHouselat()),20);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelWarehouId);
		workPane.add(edtid);
		workPane.add(labelname);
		workPane.add(edtname);
		workPane.add(labeladdress);
		workPane.add(edtaddress);
		workPane.add(labelphone);
		workPane.add(edtphone);
		workPane.add(labellgt);
		workPane.add(edtlgt);
		workPane.add(labellat);
		workPane.add(edtlat);
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
			String username	=(this.edtname.getText());
			String address=this.edtaddress.getText();
			String phone=this.edtphone.getText();
			String lgtstr=this.edtlgt.getText();
			String latstr=this.edtlat.getText();
			float lgt=-1;
			if (lgtstr!=null && !"".equals(lgtstr)) lgt=new Float(lgtstr);
			float lat=-1;
			if (latstr!=null && !"".equals(latstr)) lat=new Float(latstr);
			try {
				DistributionUtil.goodsManager.changeNewWarehouse(Integer.parseInt(edtid.getText()) ,username, address,phone,lgt,lat);
				JOptionPane.showMessageDialog(null, "�ֿ���Ϣ�޸ĳɹ�! ", "��ϲ" , JOptionPane.PLAIN_MESSAGE);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
				return;
			}

		}
			
		
	}


}

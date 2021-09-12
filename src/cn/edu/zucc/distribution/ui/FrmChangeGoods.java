package cn.edu.zucc.distribution.ui;

import cn.edu.zucc.distribution.DistributionUtil;
import cn.edu.zucc.distribution.model.*;
import cn.edu.zucc.distribution.util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmChangeGoods extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("�޸�");
	private JButton btnCancel = new JButton("ȡ��");

	private JLabel labelId = new JLabel("��Ʒ��ţ�");
	private JLabel labelhouse =new JLabel("��Ųֿ⣺");
	private JLabel labelType =new JLabel("����ţ�");
	private JLabel labelName =new JLabel("��Ʒ���ƣ�");
	private JLabel labelPrice =new JLabel("��Ʒ�۸�");
	private JLabel labelsize =new JLabel("��Ʒ���");
	private JLabel labelWeight =new JLabel("��Ʒ������");
	private JLabel labelNumber =new JLabel("��Ʒ��棺");
	private JLabel labelcold =new JLabel("�Ƿ���أ�");
	private JComboBox<String> boxCarCold=new JComboBox<String>();

	private JTextField edtId =null;
	private JTextField edtHouse = new JTextField(20);
	private JTextField edtType = new JTextField(20);
	private JTextField edtName = new JTextField(20);
	private JTextField edtprice = new JTextField(20);
	private JTextField edtsize = new JTextField(20);
	private JTextField edtweight = new JTextField(20);
	private JTextField edtNumber = new JTextField(20);

	public FrmChangeGoods(Dialog f, String s, boolean b, goods c) {
		super(f, s, b);
		edtId =new JTextField(Integer.toString(c.getGoodsid()),20);
		edtId.setEditable(false);
		edtHouse = new JTextField(Integer.toString(c.getHouseid()),20);
		edtType = new JTextField(Integer.toString(c.getTypeid()),20);
		edtName = new JTextField(c.getGoodsname(),20);
		edtprice = new JTextField(Float.toString(c.getGoodsprice()),20);
		edtsize = new JTextField(Float.toString(c.getGoodssize()),20);
		edtweight = new JTextField(Float.toString(c.getOrdersweight()),20);
		edtNumber = new JTextField(Integer.toString(c.getInventorynumber()),20);
		edtNumber.setEditable(false);

		boxCarCold.addItem("���");
		boxCarCold.addItem("�������");
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelId);
		workPane.add(edtId);
		workPane.add(labelhouse);
		workPane.add(edtHouse);
		workPane.add(labelType);
		workPane.add(edtType);
		workPane.add(labelName);
		workPane.add(edtName);
		workPane.add(labelPrice);
		workPane.add(edtprice);
		workPane.add(labelWeight);
		workPane.add(edtweight);
		workPane.add(labelsize);
		workPane.add(edtsize);
		workPane.add(labelNumber);
		workPane.add(edtNumber);
		workPane.add(labelcold);
		workPane.add(boxCarCold);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(330, 350);//Adjust the height
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
			int id = Integer.parseInt(this.edtId.getText());
			int house=Integer.parseInt(this.edtHouse.getText());
			int type=Integer.parseInt(this.edtType.getText());
			String name=this.edtName.getText();
			float price=Float.parseFloat(this.edtprice.getText());
			float size=Float.parseFloat(this.edtsize.getText());
			float weight=Float.parseFloat(this.edtweight.getText());
			boolean cold= boxCarCold.getSelectedItem().toString().equals("���");
			try {
				DistributionUtil.goodsManager.changeGood(id,house,type,name,price,size,weight,cold);
				JOptionPane.showMessageDialog(null, "��Ʒ��Ϣ�޸ĳɹ�! ", "��ϲ" , JOptionPane.PLAIN_MESSAGE);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
				return;
			}

		}
			
		
	}


}

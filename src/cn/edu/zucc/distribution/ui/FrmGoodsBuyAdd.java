package cn.edu.zucc.distribution.ui;

import cn.edu.zucc.distribution.DistributionUtil;
import cn.edu.zucc.distribution.model.goods;
import cn.edu.zucc.distribution.util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmGoodsBuyAdd extends JDialog implements ActionListener {
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JButton btnOk = new JButton("�ɹ�");
    private JButton btnCancel = new JButton("ȡ��");

    private JLabel labelhouse =new JLabel("��Ųֿ⣺");
    private JLabel labelType =new JLabel("����ţ�");
    private JLabel labelName =new JLabel("��Ʒ���ƣ�");
    private JLabel labelPrice =new JLabel("��Ʒ�۸�");
    private JLabel labelsize =new JLabel("��Ʒ���");
    private JLabel labelWeight =new JLabel("��Ʒ������");
    private JLabel labelNumber =new JLabel("�ɹ���棺");
    private JLabel labelcold =new JLabel("�Ƿ���أ�");
    private JComboBox<String> boxCarCold=new JComboBox<String>();

    private JTextField edtHouse = new JTextField(20);
    private JTextField edtType = new JTextField(20);
    private JTextField edtName = new JTextField(20);
    private JTextField edtprice = new JTextField(20);
    private JTextField edtsize = new JTextField(20);
    private JTextField edtweight = new JTextField(20);
    private JTextField edtNumber = new JTextField(20);

    public FrmGoodsBuyAdd(Frame f, String s, boolean b) {
        super(f, s, b);

        boxCarCold.addItem("���");
        boxCarCold.addItem("�������");
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(this.btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        workPane.add(labelName);
        workPane.add(edtName);
        workPane.add(labelhouse);
        workPane.add(edtHouse);
        workPane.add(labelType);
        workPane.add(edtType);
        workPane.add(labelPrice);
        workPane.add(edtprice);
        workPane.add(labelsize);
        workPane.add(edtsize);
        workPane.add(labelWeight);
        workPane.add(edtweight);
        workPane.add(labelNumber);
        workPane.add(edtNumber);
        workPane.add(labelcold);
        workPane.add(boxCarCold);
        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(330, 320);//Adjust the height
        this.btnCancel.addActionListener(this);
        this.btnOk.addActionListener(this);
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);
        this.validate();
    }

    public FrmGoodsBuyAdd(Dialog f, String s, boolean b) {
        super(f, s, b);

        boxCarCold.addItem("���");
        boxCarCold.addItem("�������");
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(this.btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        workPane.add(labelName);
        workPane.add(edtName);
        workPane.add(labelhouse);
        workPane.add(edtHouse);
        workPane.add(labelType);
        workPane.add(edtType);
        workPane.add(labelPrice);
        workPane.add(edtprice);
        workPane.add(labelsize);
        workPane.add(edtsize);
        workPane.add(labelWeight);
        workPane.add(edtweight);
        workPane.add(labelNumber);
        workPane.add(edtNumber);
        workPane.add(labelcold);
        workPane.add(boxCarCold);
        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(330, 320);//Adjust the height
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
        if (e.getSource() == this.btnCancel)
            this.setVisible(false);
        else if (e.getSource() == this.btnOk) {
            int house=this.edtHouse.getText()==null || "".equals(this.edtHouse.getText())?-1:Integer.parseInt(this.edtHouse.getText());
            int type=this.edtType.getText()==null || "".equals(this.edtType.getText())?-1:Integer.parseInt(this.edtType.getText());
            String name=this.edtName.getText();
            float price=this.edtprice.getText()==null || "".equals(this.edtprice.getText())?0:Float.parseFloat(this.edtprice.getText());
            float size=this.edtsize.getText()==null || "".equals(this.edtsize.getText())?0:Float.parseFloat(this.edtsize.getText());
            float weight=this.edtweight.getText()==null || "".equals(this.edtweight.getText())?0:Float.parseFloat(this.edtweight.getText());
            int number=this.edtNumber.getText()==null || "".equals(this.edtNumber.getText())?-1:Integer.parseInt(this.edtNumber.getText());
            boolean cold= boxCarCold.getSelectedItem().toString().equals("���");
            try {
                DistributionUtil.goodsManager.CreateNewGood(house,type,name,price,size,weight,cold,number);
                JOptionPane.showMessageDialog(null, "��Ʒ�ɹ������ɳɹ�! ", "��ϲ" , JOptionPane.PLAIN_MESSAGE);
                JOptionPane.showMessageDialog(null, "��Ʒ�ɹ���ɣ� ", "��ϲ" , JOptionPane.PLAIN_MESSAGE);
                this.setVisible(false);
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
                return;
            }

        }


    }
}

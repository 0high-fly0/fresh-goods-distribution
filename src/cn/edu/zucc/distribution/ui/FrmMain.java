package cn.edu.zucc.distribution.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import cn.edu.zucc.distribution.DistributionUtil;
import cn.edu.zucc.distribution.model.*;
import cn.edu.zucc.distribution.util.*;

public class FrmMain extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private FrmLogin dlgLogin=null;
    private FrmMainCustomer dlgMainCustomer=null;
    private FrmMainAdmin dlgMainAdmin=null;
    public FrmMain(){
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setTitle("��������ϵͳ");
        dlgLogin=new FrmLogin(this,"��½",true);
        dlgLogin.setVisible(true);
        if (user.currentLoginUser.getUsertype().equals("�ͻ�")) {
            dlgMainCustomer=new FrmMainCustomer();
            dlgMainCustomer.setVisible(true);
        }else {
            dlgMainAdmin=new FrmMainAdmin();
            dlgMainAdmin.setVisible(true);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {}
}

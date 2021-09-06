package cn.edu.zucc.distribution.comtrol.example;

import cn.edu.zucc.distribution.itf.IUserManager;
import cn.edu.zucc.distribution.model.*;
import cn.edu.zucc.distribution.util.BaseException;
import cn.edu.zucc.distribution.util.BusinessException;
import cn.edu.zucc.distribution.util.DBUtil;
import cn.edu.zucc.distribution.util.DbException;
import javax.swing.JOptionPane;

import java.util.Date;
import java.sql.*;

public class ExampleUserManager implements IUserManager {

	@Override
	public void regadmin(String username, String pwd,String pwd2) throws BaseException {
		if (username==null || "".equals(username)) throw new BusinessException("��������Ϊ��");
		if (pwd==null || "".equals(pwd)) throw new BusinessException("���벻��Ϊ��");
		if (!pwd.equals(pwd2)) throw new BusinessException("������������벻һ��");
		Connection conn=null;
		try {
			conn= DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="select user_name\n" +
					"from user\n" +
					"where user_name=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,username);
			ResultSet rs=pst.executeQuery();
			if (rs.next()){
				rs.close();
				pst.close();
				throw new BusinessException("��ǰ�����Ѿ���ע�ᣡ");
			}
			rs.close();
			pst.close();

			sql="select max(user_id)\n" +
					"from user";
			int order=1;
			pst= conn.prepareStatement(sql);
			rs= pst.executeQuery();
			if (rs.next()){
				order=rs.getInt(1)+1;
			}
			rs.close();
			pst.close();

			sql="INSERT INTO user(user_id,user_name,password,user_type)\n" +
					"VALUES (?,?,?,'����Ա')\n";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,order);
			pst.setString(2,username);
			pst.setString(3,pwd);
			pst.execute();
			pst.close();

			sql="INSERT INTO admin(user_id,user_name,password,user_type)\n" +
					"VALUES (?,?,?,'����Ա')\n";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,order);
			pst.setString(2,username);
			pst.setString(3,pwd);
			pst.execute();
			pst.close();

			conn.commit();
			JOptionPane.showMessageDialog(null, "�û�ע��ɹ�! ", "��ϲ" , JOptionPane.PLAIN_MESSAGE);
		}catch (SQLException ex){
			throw new DbException(ex);
		}finally {
			if (conn!=null){
				try {
					conn.rollback();
					conn.close();
				}catch (SQLException e){
					e.printStackTrace();
				}
			}
		}
	}


	public void regcustom(String username, String pwd,String pwd2,String phone,String email,String address,float lgt,float lat) throws BaseException {
		if (username==null || "".equals(username)) throw new BusinessException("��������Ϊ��");
		if (pwd==null || "".equals(pwd)) throw new BusinessException("���벻��Ϊ��");
		if (!pwd.equals(pwd2)) throw new BusinessException("������������벻һ��");
		if (phone==null || "".equals(phone)) throw new BusinessException("��ϵ��ʽ����Ϊ��");
		if (address==null || "".equals(address)) throw new BusinessException("��ס��ַ����Ϊ��");
		if (lgt==-1) throw new BusinessException("���꾭�Ȳ���Ϊ��");
		if (lat==-1) throw new BusinessException("����γ�Ȳ���Ϊ��");
		Connection conn=null;
		try {
			conn= DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="select user_name\n" +
					"from user\n" +
					"where user_name=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,username);
			ResultSet rs=pst.executeQuery();
			if (rs.next()){
				rs.close();
				pst.close();
				throw new BusinessException("��ǰ�����Ѿ���ע�ᣡ");
			}
			rs.close();
			pst.close();

			sql="select max(user_id)\n" +
					"from user";
			int order=1;
			pst= conn.prepareStatement(sql);
			rs= pst.executeQuery();
			if (rs.next()){
				order=rs.getInt(1)+1;
			}
			rs.close();
			pst.close();

			sql="INSERT INTO user(user_id,user_name,password,user_type)\n" +
					"VALUES (?,?,?,'�ͻ�')\n";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,order);
			pst.setString(2,username);
			pst.setString(3,pwd);
			pst.execute();
			pst.close();

			sql="INSERT INTO customer(user_id,user_name,password,user_type,cus_phone,cus_email,cus_address,cus_time,cus_longitude,cus_latitude)\n" +
					"VALUES (?,?,?,'�ͻ�',?,?,?,now(),?,?)\n";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,order);
			pst.setString(2,username);
			pst.setString(3,pwd);
			pst.setString(4,phone);
			pst.setString(5,email);
			pst.setString(6,address);
			pst.setFloat(7,lgt);
			pst.setFloat(8,lat);
			pst.execute();
			pst.close();

			conn.commit();
		}catch (SQLException ex){
			throw new DbException(ex);
		}finally {
			if (conn!=null){
				try {
					conn.rollback();
					conn.close();
				}catch (SQLException e){
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public admin loginadmin(String username, String pwd) throws BaseException {
		if (username==null || "".equals(username)) throw new BusinessException("��������Ϊ��");
		if (pwd==null || "".equals(pwd)) throw new BusinessException("���벻��Ϊ��");
		Connection conn=null;
		admin user=null;
		try {
			conn= DBUtil.getConnection();
			String sql="select user_id from admin where user_name=? and password=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,username);
			pst.setString(2,pwd);
			ResultSet rs=pst.executeQuery();
			if (rs.next()){
				user=new admin();
				user.setUserid(rs.getInt(1));
				user.setUsername(username);
				user.setPwd(pwd);
				user.setUsertype("����Ա");
				return user;
			}else {
				throw new BusinessException("�������������");
			}
		}catch (SQLException ex){
			throw new DbException(ex);
		}finally {
			if (conn!=null){
				try {
					conn.close();
				}catch (SQLException e){
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public customer logincustomer(String username, String pwd) throws BaseException {
		if (username==null || "".equals(username)) throw new BusinessException("��������Ϊ��");
		if (pwd==null || "".equals(pwd)) throw new BusinessException("���벻��Ϊ��");
		Connection conn=null;
		customer user=null;
		try {
			conn= DBUtil.getConnection();
			String sql="select user_id,cus_phone,cus_email,cus_address,cus_time,cus_longitude,cus_latitude\n" +
					"from customer where user_name=? and password=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,username);
			pst.setString(2,pwd);
			ResultSet rs=pst.executeQuery();
			if (rs.next()){
				user=new customer();
				user.setUserid(rs.getInt(1));
				user.setUsername(username);
				user.setPwd(pwd);
				user.setUsertype("�ͻ�");
				user.setCusphone(rs.getString(2));
				user.setCusemail(rs.getString(3));
				user.setAddress(rs.getString(4));
				user.setCustime(rs.getDate(5));
				user.setCuslgt(rs.getFloat(6));
				user.setCuslat(rs.getFloat(7));
				return user;
			}else {
				throw new BusinessException("�������������");
			}
		}catch (SQLException ex){
			throw new DbException(ex);
		}finally {
			if (conn!=null){
				try {
					conn.close();
				}catch (SQLException e){
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void changePwd(BeanUser user, String oldPwd, String newPwd,
			String newPwd2) throws BaseException {
		// TODO Auto-generated method stub
		if (oldPwd==null || "".equals(oldPwd)) throw new BusinessException("??????????");
		Connection conn=null;
		try {
			conn= DBUtil.getConnection();
			String sql="SELECT user_pwd\n" +
					"FROM tbl_user \n" +
					"WHERE user_id=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,user.getUser_id());
			ResultSet rs=pst.executeQuery();
			if (rs.next()){
				if (!oldPwd.equals(rs.getString(1))) {
					rs.close();
					pst.close();
					throw new BusinessException("????????????");
				}
			}
			rs.close();
			pst.close();
			if (newPwd==null || "".equals(newPwd)) throw new BusinessException("????????????");
			if (!newPwd.equals(newPwd2)) throw new BusinessException("????????????????");
			sql="UPDATE tbl_user SET user_pwd=? WHERE user_id=?\n";
			pst=conn.prepareStatement(sql);
			pst.setString(1,newPwd);
			pst.setString(2,user.getUser_id());
			pst.execute();
			pst.close();
			JOptionPane.showMessageDialog(null, "success! ", "???" , JOptionPane.PLAIN_MESSAGE);
		}catch (SQLException ex){
			throw new DbException(ex);
		}finally {
			if (conn!=null){
				try {
					conn.close();
				}catch (SQLException e){
					e.printStackTrace();
				}
			}
		}
	}

}





package cn.edu.zucc.distribution.util;

public class DbException extends BaseException {
	public DbException(Throwable ex){
		super("���ݿ��������:"+ex.getMessage());
	}
}

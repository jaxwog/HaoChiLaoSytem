package android.hcl.dao;

import java.util.List;

import page.Pager;

import android.hcl.bean.Business;
import android.hcl.bean.Customer;

public interface CustomerAct {
	public abstract boolean checklogin(String username, String password);//��֤�û����������Ƿ���ȷ

	public abstract boolean checkRegister(String username);//ע��ʱ��֤�û����Ƿ����

	public abstract List querycustomer(String username);//�����û�����ѯ�û���Ϣ

	public abstract void saveCus(Customer c);//�洢�û���Ϣ

	public abstract void updateCus(Customer c);//�����û���Ϣ

	public abstract void deleteCus(Customer c);//ɾ���û�

	public abstract void deleteCus(String Customername);//�����û���ɾ���û�

	public abstract List queryAllcustomer();//��ѯ�����û���Ϣ

	public abstract List queryAllcustomer(Pager pager);//��ѯ�����û���Ϣ����ҳ

	public abstract int getTotalRows();//�õ��û�����������Ϣ������

	public Customer findUserById(Integer id);//����id��ѯ�û�

}

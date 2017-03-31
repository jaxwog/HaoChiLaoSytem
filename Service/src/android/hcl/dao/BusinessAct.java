package android.hcl.dao;

import java.util.List;

import page.Pager;
import android.hcl.bean.Business;
import android.hcl.bean.Customer;

public interface BusinessAct {
	public abstract void saveBuss(Business b);// �洢�̼���Ϣ

	public abstract void updateBuss(Business b);// �����̼���Ϣ

	public Business findBusById(Integer id);// �����̼�id�õ���ص��̼ҵĶ���

	public abstract void deleteBuss(Business b);// ɾ���̼�

	public abstract void deleteBuss(String Busunessname);// �����̼���ɾ����Ӧ���̼�

	public abstract int getTotalRows();// �õ��̼ұ��е�������

	public abstract List queryAllbusiness();// ��ѯ�����̼���Ϣ

	public abstract List queryAllbusiness(Pager pager);// �÷�ҳ�ķ�ʽ��ѯ�����̼ҵ���Ϣ

	public abstract boolean checklogin(String bussinessname);// ��ѯ�̼ұ��е��̼����Ƿ��Ѵ���

	public abstract boolean checkname(String bususername);// ��ѯ�̼ұ��е��̼��Ñ����Ƿ��Ѵ���

	public abstract List queryBus(String s);// �����̼�����ѯ�̼���Ϣ

	public abstract boolean checklogin(String username, String password);//��ѯ�̼����������Ƿ���ȷ

}

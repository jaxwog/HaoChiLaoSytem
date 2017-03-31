package android.hcl.dao;

import java.util.List;

import page.Pager;

import android.hcl.bean.Business;
import android.hcl.bean.Customer;
import android.hcl.bean.Dish;

public interface DishAct {
	public abstract List queryAllDish();//��ѯ���в�Ʒ��Ϣ

	public abstract List queryAllDish(Pager pager);//��ѯ���в�Ʒ��Ϣ����ҳ

	public abstract List queryOwnDish(Pager pager, Integer bussinessid);//����id��ѯ���̼ҵĲ�Ʒ��Ϣ����ҳ

	public abstract List queryOwnDish(Integer bussinessid);//����id��ѯ���̼ҵĲ�Ʒ��Ϣ

	public abstract void deleteDish(String dishName);//���ݲ���ɾ����Ʒ

	public abstract void save(Dish dish);//�洢��Ʒ

	public Dish findUserById(Integer id);//����id�õ��ò�Ʒ����

	public abstract void delete(Dish dish);//ɾ���ò�Ʒ

	public abstract int getTotalRows();//�õ���Ʒ����������Ϣ������

	public abstract boolean checklogin(String dishname);//��֤�����Ƿ��Ѿ���ע��

	public abstract List queryDish(String dishname);//���ݲ�����ѯ�ò�Ʒ��Ϣ

	public abstract List queryDishByType(String type, String id);//�����̼�id�����Ͳ�ѯ��Ʒ��Ϣ

	public abstract void updateDish(Dish dish);//���²�Ʒ��Ϣ
}

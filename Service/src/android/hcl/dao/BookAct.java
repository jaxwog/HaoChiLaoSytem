package android.hcl.dao;

import java.util.List;

import android.hcl.bean.Book;
import android.hcl.bean.Business;
import android.hcl.bean.Customer;

import page.Pager;

public interface BookAct {
	public abstract void saveBook(Book book);// �洢����

	public abstract void deleteBoo(Book b);// ɾ������

	public Book findbooById(Integer id);// ���ݶ���id��ѯ��صĶ���

	public abstract void updateBook(Book b);//���¶�����Ϣ

	public abstract List queryAllBook();// ��ѯ���ж�����Ϣ

	public abstract List queryAllBook(Pager pager);// ��ѯ���ж�������Ϣ����ҳ

	public abstract int getTotalRows();// �õ��������е�������

	public abstract List queryBook(String s);//�����û�����ѯ����
	
	public abstract List queryBookbyDish(String s);//���ݲ�����ѯ����
	
	public abstract int getRows(String s);// �õ��������е�����

	public abstract List queryownBook(String s, Pager pager);//��ѯ�û�������Ϣ����ҳ
	
	
	public abstract List queryBookby(String s,String d);//�����û�����ѯ����
}

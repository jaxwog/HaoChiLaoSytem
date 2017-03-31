package android.hcl.dao;

import java.util.List;

import android.hcl.bean.Book;
import android.hcl.bean.Business;
import android.hcl.bean.Customer;

import page.Pager;

public interface BookAct {
	public abstract void saveBook(Book book);// 存储订单

	public abstract void deleteBoo(Book b);// 删除订单

	public Book findbooById(Integer id);// 根据订单id查询相关的订单

	public abstract void updateBook(Book b);//更新订单信息

	public abstract List queryAllBook();// 查询所有订单信息

	public abstract List queryAllBook(Pager pager);// 查询所有订单的信息并分页

	public abstract int getTotalRows();// 得到订单表中的总行数

	public abstract List queryBook(String s);//根据用户名查询订单
	
	public abstract List queryBookbyDish(String s);//根据菜名查询订单
	
	public abstract int getRows(String s);// 得到订单表中的行数

	public abstract List queryownBook(String s, Pager pager);//查询用户订单信息并分页
	
	
	public abstract List queryBookby(String s,String d);//根据用户名查询订单
}

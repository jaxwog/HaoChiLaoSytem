package android.hcl.dao;

import java.util.List;

import page.Pager;

import android.hcl.bean.Business;
import android.hcl.bean.Customer;

public interface CustomerAct {
	public abstract boolean checklogin(String username, String password);//验证用户名和密码是否正确

	public abstract boolean checkRegister(String username);//注册时验证用户名是否存在

	public abstract List querycustomer(String username);//根据用户名查询用户信息

	public abstract void saveCus(Customer c);//存储用户信息

	public abstract void updateCus(Customer c);//更新用户信息

	public abstract void deleteCus(Customer c);//删除用户

	public abstract void deleteCus(String Customername);//根据用户名删除用户

	public abstract List queryAllcustomer();//查询所有用户信息

	public abstract List queryAllcustomer(Pager pager);//查询所有用户信息并分页

	public abstract int getTotalRows();//得到用户表中所有信息的行数

	public Customer findUserById(Integer id);//根据id查询用户

}

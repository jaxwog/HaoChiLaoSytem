package android.hcl.dao;

import java.util.List;

import page.Pager;
import android.hcl.bean.Business;
import android.hcl.bean.Customer;

public interface BusinessAct {
	public abstract void saveBuss(Business b);// 存储商家信息

	public abstract void updateBuss(Business b);// 更改商家信息

	public Business findBusById(Integer id);// 根据商家id得到相关的商家的对象

	public abstract void deleteBuss(Business b);// 删除商家

	public abstract void deleteBuss(String Busunessname);// 根据商家名删除相应的商家

	public abstract int getTotalRows();// 得到商家表中的总行数

	public abstract List queryAllbusiness();// 查询所有商家信息

	public abstract List queryAllbusiness(Pager pager);// 用分页的方式查询所有商家的信息

	public abstract boolean checklogin(String bussinessname);// 查询商家表中的商家名是否已存在

	public abstract boolean checkname(String bususername);// 查询商家表中的商家用戶名是否已存在

	public abstract List queryBus(String s);// 根据商家名查询商家信息

	public abstract boolean checklogin(String username, String password);//查询商家名和密码是否正确

}

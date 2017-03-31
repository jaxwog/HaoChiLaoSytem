package android.hcl.dao;

import java.util.List;

import page.Pager;

import android.hcl.bean.Business;
import android.hcl.bean.Customer;
import android.hcl.bean.Dish;

public interface DishAct {
	public abstract List queryAllDish();//查询所有菜品信息

	public abstract List queryAllDish(Pager pager);//查询所有菜品信息并分页

	public abstract List queryOwnDish(Pager pager, Integer bussinessid);//根据id查询该商家的菜品信息并分页

	public abstract List queryOwnDish(Integer bussinessid);//根据id查询该商家的菜品信息

	public abstract void deleteDish(String dishName);//根据菜名删除菜品

	public abstract void save(Dish dish);//存储菜品

	public Dish findUserById(Integer id);//根据id得到该菜品对象

	public abstract void delete(Dish dish);//删除该菜品

	public abstract int getTotalRows();//得到菜品表中所有信息的行数

	public abstract boolean checklogin(String dishname);//验证菜名是否已经被注册

	public abstract List queryDish(String dishname);//根据菜名查询该菜品信息

	public abstract List queryDishByType(String type, String id);//根据商家id和类型查询菜品信息

	public abstract void updateDish(Dish dish);//更新菜品信息
}

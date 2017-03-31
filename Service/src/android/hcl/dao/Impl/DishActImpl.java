package android.hcl.dao.Impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import page.Pager;

import android.hcl.bean.Customer;
import android.hcl.bean.Dish;
import android.hcl.dao.DishAct;

public class DishActImpl implements DishAct {

	public DishActImpl() {

	}

	public List queryAllDish() {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		String strHql = "from Dish";
		List myList = session.createQuery(strHql).list();
		session.getTransaction().commit();
		session.close();
		sf.close();
		return myList;
	}

	public void save(Dish dish) {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(dish);
		session.getTransaction().commit();
		session.close();
		sf.close();
	}

	public void delete(Dish dish) {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.delete(dish);
		session.getTransaction().commit();
		session.close();
		sf.close();

	}

	@Override
	public List queryAllDish(Pager pager) {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		List list = session.createQuery("from Dish")
				.setFirstResult(pager.getStartRow())
				.setMaxResults(pager.getPageSize()).list();
		session.close();
		sf.close();
		return list;
	}

	public void deleteDish(String dishName) {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		String hql = " delete from Dish s where dishName='" + dishName + "' ";
		Query queryupdate = session.createQuery(hql);
		int ret = queryupdate.executeUpdate();
		session.getTransaction().commit();
		session.close();
		sf.close();
	}

	public int getTotalRows() {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		int totalRows = 0;
		String strHql = "select count(*) from Dish";
		Object obj = session.createQuery(strHql).list().iterator().next();
		if (obj != null)
			totalRows = Integer.parseInt(obj.toString());
		session.close();
		sf.close();
		return totalRows;
	}

	public Dish findUserById(Integer id) {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		Dish d = (Dish) session.get(Dish.class, id);
		session.getTransaction().commit();
		session.close();
		sf.close();
		return d;
	}

	public boolean checklogin(String dishname) {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		boolean b = false;
		String strHql = "select count(*) from Dish d where d.dishName=:m";
		Long count = (Long) session.createQuery(strHql)
				.setParameter("m", dishname).iterate().next();
		if (count.longValue() > 0L)
			b = true;
		session.getTransaction().commit();
		session.close();
		sf.close();
		return b;
	}

	public List queryDish(String dishname) {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		String strHql = "from Dish where dishName= '" + dishname + "' ";
		List myList = session.createQuery(strHql).list();
		session.beginTransaction().commit();
		session.close();
		sf.close();
		return myList;
	}

	public void updateDish(Dish dish) {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(dish);
		session.getTransaction().commit();
		session.close();
		sf.close();

	}

	public List queryOwnDish(Pager pager, Integer bussinessid) {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		String strHql = "from Dish where business= '" + bussinessid + "' ";
		List list = session.createQuery(strHql)
				.setFirstResult(pager.getStartRow())
				.setMaxResults(pager.getPageSize()).list();
		session.close();
		sf.close();
		return list;
	}

	public List queryOwnDish(Integer bussinessid) {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		String strHql = "from Dish where business= '" + bussinessid + "' ";
		List list = session.createQuery(strHql).list();
		session.close();
		sf.close();
		return list;
	}

	public List queryDishByType(String type, String id) {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		String strHql = "from Dish where dishtype= '" + type
				+ "' and business= '" + id + "' ";
		List myList = session.createQuery(strHql).list();
		session.beginTransaction().commit();
		session.close();
		sf.close();
		return myList;
	}

}

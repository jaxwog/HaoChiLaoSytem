package android.hcl.dao.Impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import page.Pager;
import android.hcl.bean.Business;
import android.hcl.bean.Customer;
import android.hcl.dao.BusinessAct;

public class BusinessActImpl implements BusinessAct {
	public BusinessActImpl() {

	}

	public void saveBuss(Business b) {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(b);
		session.getTransaction().commit();
		session.close();
		sf.close();

	}

	public void updateBuss(Business b) {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(b);
		session.getTransaction().commit();
		session.close();
		sf.close();

	}

	public void deleteBuss(Business b) {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.delete(b);
		session.getTransaction().commit();
		session.close();
		sf.close();

	}

	public List queryAllbusiness(Pager pager) {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		List list = session.createQuery("from Business")
				.setFirstResult(pager.getStartRow())
				.setMaxResults(pager.getPageSize()).list();
		session.close();
		sf.close();
		return list;
	}

	public int getTotalRows() {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		int totalRows = 0;
		String strHql = "select count(*) from Business";
		Object obj = session.createQuery(strHql).list().iterator().next();
		if (obj != null)
			totalRows = Integer.parseInt(obj.toString());
		session.close();
		sf.close();
		return totalRows;
	}

	public void deleteBuss(String Busunessname) {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		String hql = " delete from Business s where Busunessname='"
				+ Busunessname + "' ";
		Query queryupdate = session.createQuery(hql);
		int ret = queryupdate.executeUpdate();
		session.getTransaction().commit();
		session.close();
		sf.close();
	}

	public List queryAllbusiness() {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		String strHql = "from Business";
		List myList = session.createQuery(strHql).list();
		session.getTransaction().commit();
		session.close();
		sf.close();
		return myList;
	}

	public Business findBusById(Integer id) {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		Business b = (Business) session.get(Business.class, id);
		session.getTransaction().commit();
		session.close();
		sf.close();
		return b;
	}

	public boolean checklogin(String bussinessname) {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		boolean b = false;
		String strHql = "select count(*) from Business b where b.busunessname=:m";
		Long count = (Long) session.createQuery(strHql)
				.setParameter("m", bussinessname).iterate().next();
		if (count.longValue() > 0L)
			b = true;
		session.getTransaction().commit();
		session.close();
		sf.close();
		return b;
	}

	public List queryBus(String bususername) {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		String strHql = "from Business  a where a.bususername=:m";
		List myList = session.createQuery(strHql)
				.setParameter("m", bususername).list();
		session.beginTransaction().commit();
		session.close();
		sf.close();
		return myList;
	}

	public boolean checklogin(String username, String password) {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		boolean b = false;
		String strHql = "select count(*) from Business a where a.bususername=:m and a.buspassword=:n";
		Long count = (Long) session.createQuery(strHql)
				.setParameter("m", username).setParameter("n", password)
				.iterate().next();
		if (count.longValue() > 0L)
			b = true;
		session.getTransaction().commit();
		session.close();
		sf.close();
		return b;
	}

	public boolean checkname(String bususername) {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		boolean b = false;
		String strHql = "select count(*) from Business b where b.bususername=:m";
		Long count = (Long) session.createQuery(strHql)
				.setParameter("m", bususername).iterate().next();
		if (count.longValue() > 0L)
			b = true;
		session.getTransaction().commit();
		session.close();
		sf.close();
		return b;
	}

}

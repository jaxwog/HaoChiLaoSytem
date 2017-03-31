package android.hcl.dao.Impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import page.Pager;
import android.hcl.bean.Customer;
import android.hcl.dao.CustomerAct;

public class CustomerActImpl implements CustomerAct {

	public CustomerActImpl() {

	}

	public boolean checklogin(String username, String password) {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		boolean b = false;
		String strHql = "select count(*) from Customer a where a.username=:m and a.password=:n";
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

	public void saveCus(Customer c) {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(c);
		session.getTransaction().commit();
		session.close();
		sf.close();

	}

	public void updateCus(Customer c) {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(c);
		session.getTransaction().commit();
		session.close();
		sf.close();

	}

	public List queryAllcustomer(Pager pager) {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		List list = session.createQuery("from Customer")
				.setFirstResult(pager.getStartRow())
				.setMaxResults(pager.getPageSize()).list();
		session.close();
		sf.close();
		return list;
	}

	public List queryAllcustomer() {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		String strHql = "from Customer";
		List myList = session.createQuery(strHql).list();
		session.getTransaction().commit();
		session.close();
		sf.close();
		return myList;
	}

	public int getTotalRows() {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		int totalRows = 0;
		String strHql = "select count(*) from Customer";
		Object obj = session.createQuery(strHql).list().iterator().next();
		if (obj != null)
			totalRows = Integer.parseInt(obj.toString());
		session.close();
		sf.close();
		return totalRows;
	}

	public Customer findUserById(Integer id) {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		Customer c = (Customer) session.get(Customer.class, id);
		session.getTransaction().commit();
		session.close();
		sf.close();
		return c;
	}

	public void deleteCus(Customer c) {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.delete(c);
		session.getTransaction().commit();
		session.close();
		sf.close();

	}

	public void deleteCus(String Customername) {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		String hql = " delete from Customer s where s.username = '"
				+ Customername + "' ";
		Query queryupdate = session.createQuery(hql);
		int ret = queryupdate.executeUpdate();
		session.getTransaction().commit();
		session.close();
		sf.close();
	}

	public boolean checkRegister(String username) {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		boolean b = false;
		String strHql = "select count(*) from Customer a where a.username=:m ";
		Long count = (Long) session.createQuery(strHql)
				.setParameter("m", username).iterate().next();
		if (count.longValue() > 0L)
			b = true;
		session.getTransaction().commit();
		session.close();
		sf.close();
		return b;
	}

	public List querycustomer(String username) {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		String strHql = "from Customer  a where a.username=:m";
		List myList = session.createQuery(strHql).setParameter("m", username)
				.list();
		session.getTransaction().commit();
		session.close();
		sf.close();
		return myList;
	}
}

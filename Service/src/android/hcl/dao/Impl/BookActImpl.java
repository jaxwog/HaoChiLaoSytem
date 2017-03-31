package android.hcl.dao.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import page.Pager;
import android.hcl.bean.Book;
import android.hcl.bean.Business;
import android.hcl.bean.Customer;
import android.hcl.dao.BookAct;

public class BookActImpl implements BookAct {

	public BookActImpl() {

	}

	public void saveBook(Book book) {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(book);
		session.getTransaction().commit();
		session.close();
		sf.close();

	}

	public List queryAllBook(Pager pager) {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		List list = session.createQuery("from Book")
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
		String strHql = "select count(*) from Book";
		Object obj = session.createQuery(strHql).list().iterator().next();
		if (obj != null)
			totalRows = Integer.parseInt(obj.toString());
		session.close();
		sf.close();
		return totalRows;
	}

	public List queryAllBook() {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		String strHql = "from Book";
		List myList = session.createQuery(strHql).list();
		session.getTransaction().commit();
		session.close();
		sf.close();
		return myList;
	}

	public void updateBook(Book b) {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(b);
		session.getTransaction().commit();
		session.close();
		sf.close();
	}

	public List queryBook(String s) {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		String strHql = "from Book where username= '" + s + "' ";
		List myList = session.createQuery(strHql).list();
		session.beginTransaction().commit();
		session.close();
		sf.close();
		return myList;
	}

	public List queryownBook(String s, Pager pager) {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		String strHql = "from Book where busunessname= '" + s + "' ";
		List myList = session.createQuery(strHql)
				.setFirstResult(pager.getStartRow())
				.setMaxResults(pager.getPageSize()).list();
		session.beginTransaction().commit();
		session.close();
		sf.close();
		return myList;
	}

	public int getRows(String s) {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		int totalRows = 0;
		String strHql = "select count(*) from Book where busunessname= '" + s
				+ "' ";
		Object obj = session.createQuery(strHql).list().iterator().next();
		if (obj != null)
			totalRows = Integer.parseInt(obj.toString());
		session.close();
		sf.close();
		return totalRows;
	}

	public Book findbooById(Integer id) {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		Book b = (Book) session.get(Book.class, id);
		session.getTransaction().commit();
		session.close();
		sf.close();
		return b;
	}

	public void deleteBoo(Book b) {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.delete(b);
		session.getTransaction().commit();
		session.close();
		sf.close();
	}

	public List queryBookbyDish(String s) {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		String strHql = "from Book where dishName= '" + s + "' ";
		List myList = session.createQuery(strHql).list();
		session.beginTransaction().commit();
		session.close();
		sf.close();
		return myList;
	}

	@Override
	public List queryBookby(String d, String s) {
		Configuration cfg = (new Configuration()).configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		String strHql = "from Book where dishName= '" + d + "' and username= '"+s+ "'";
		List myList = session.createQuery(strHql).list();
		session.beginTransaction().commit();
		session.close();
		sf.close();
		return myList;
	}
}

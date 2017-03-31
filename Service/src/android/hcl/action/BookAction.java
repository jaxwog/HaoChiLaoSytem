package android.hcl.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import page.Pager;
import page.PagerHelper;
import android.hcl.bean.Book;
import android.hcl.dao.Impl.BookActImpl;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BookAction extends ActionSupport {
	private Book book;
	private BookActImpl bookImpl;
	private List userList;
	private Integer finish;//订单完成状态

	public BookAction() {
		this.book = new Book();
		this.bookImpl = new BookActImpl();
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public BookActImpl getBookImpl() {
		return bookImpl;
	}

	public void setBookImpl(BookActImpl bookImpl) {
		this.bookImpl = bookImpl;
	}

	public List getUserList() {
		return userList;
	}

	public void setUserList(List userList) {
		this.userList = userList;
	}

	public Integer getFinish() {
		return finish;
	}

	public void setFinish(Integer finish) {
		this.finish = finish;
	}
	//调用BookActImpl中的方法得到数据库中的信息并调用分页的方法让数据已分页的方式显示在jsp页面上
	public String listAllBook() throws Exception {
		int totalRows = 0;
		Pager pager = null;
		int pageSize = 5;
		HttpServletRequest request = ServletActionContext.getRequest();
		totalRows = bookImpl.getTotalRows();
		pager = PagerHelper.getPager(request, totalRows, pageSize);
		pager.setLinkUrl("listAllBook.action?");
		request.setAttribute("pb", pager);
		List userList = bookImpl.queryAllBook(pager);
		request.setAttribute("userList", userList);
		return "success";
	}
	//得到jsp页面传输的用户名 ，调用BookActImpl中的方法，传输改用户的订单信息传输到jsp页面上
	public String updateBoo() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String dishName = new String(request.getParameter("dishName").getBytes(
				"iso8859-1"), "GBK");
		String username = new String(request.getParameter("username").getBytes(
				"iso8859-1"), "GBK");
		userList = bookImpl.queryBookby(dishName, username);
		request.setAttribute("update", userList);
		return "success";
	}
// 更改订单状态并保存在数据库中
	public String booupdate() throws Exception {
		book.setBookId(book.getBookId());
		if (finish.intValue() == 1) {
			book.setBookFinish("未完成");
		} else if (finish.intValue() == 2) {
			book.setBookFinish("店家已确认");
		}else if (finish.intValue() == 3) {
			book.setBookFinish("店家已派送");
		}
		bookImpl.updateBook(book);
		userList = bookImpl.queryBook(book.getUsername());
		ActionContext.getContext().getSession()
				.put("username", book.getUsername());
		return "success";
	}
//根据商家名查询该商家的订单信息并传输到jsp页面
	public String listOwnBook() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Map session = ActionContext.getContext().getSession();
		String bususername = session.get("busunessname").toString();
		int totalRows = 0;
		Pager pager = null;
		int pageSize = 5;
		totalRows = bookImpl.getRows(bususername);
		pager = PagerHelper.getPager(request, totalRows, pageSize);
		pager.setLinkUrl("listOwnBook.action?");
		request.setAttribute("pb", pager);
		List userList = bookImpl.queryownBook(bususername,pager);
		request.setAttribute("userList", userList);
		return "success";
	}
}

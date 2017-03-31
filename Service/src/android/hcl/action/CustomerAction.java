package android.hcl.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import page.Pager;
import page.PagerHelper;
import android.hcl.bean.Customer;
import android.hcl.dao.Impl.CustomerActImpl;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CustomerAction extends ActionSupport {
	private Customer cus;
	private CustomerActImpl cusImpl;

	public CustomerAction() {
		cusImpl = new CustomerActImpl();
	}

	public Customer getCus() {
		return cus;
	}

	public void setCus(Customer cus) {
		this.cus = cus;
	}

	public CustomerActImpl getCusImpl() {
		return cusImpl;
	}

	public void setCusImpl(CustomerActImpl cusImpl) {
		this.cusImpl = cusImpl;
	}
//将所有用户信息和分页信息传输到jsp页面上
	public String listAllCustomer() throws Exception {
		int totalRows = 0;
		Pager pager = null;
		int pageSize = 5;
		HttpServletRequest request = ServletActionContext.getRequest();
		totalRows = cusImpl.getTotalRows();
		pager = PagerHelper.getPager(request, totalRows, pageSize);
		pager.setLinkUrl("listAllCustomer.action?");
		request.setAttribute("pb", pager);
		List userList = cusImpl.queryAllcustomer(pager);
		request.setAttribute("userList", userList);
		return "success";
	}

	public String cusDelete() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = new String(request.getParameter("username").getBytes(
				"iso8859-1"), "GBK");
		cusImpl.deleteCus(username);
		listAllCustomer();
		return "success";
	}
//根据id批量删除用户
	public String delChecked() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String uids[] = request.getParameterValues("ids");
		String as[];
		int j = (as = uids).length;
		for (int i = 0; i < j; i++) {
			String uid = as[i];
			android.hcl.bean.Customer c = cusImpl.findUserById(Integer
					.valueOf(Integer.parseInt(uid)));
			System.out.print(Integer.parseInt(uid));
			if (c != null) {
				cusImpl.deleteCus(c);
			}
		}

		return listAllCustomer();
	}
}

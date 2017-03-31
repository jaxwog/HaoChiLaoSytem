package android.hcl.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import page.Pager;
import page.PagerHelper;
import android.hcl.bean.Business;
import android.hcl.bean.Dish;
import android.hcl.dao.Impl.BusinessActImpl;
import android.hcl.dao.Impl.DishActImpl;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BusinessAction extends ActionSupport {
	private List userList;
	private Business bus;
	private Dish dish;
	private DishActImpl dishImpl;
	private BusinessActImpl busImpl;

	public BusinessAction() {
		this.bus = new Business();
		this.busImpl = new BusinessActImpl();
	}

	public Business getBus() {
		return bus;
	}

	public void setBus(Business bus) {
		this.bus = bus;
	}

	public BusinessActImpl getBusImpl() {
		return busImpl;
	}

	public void setBusImpl(BusinessActImpl busImpl) {
		this.busImpl = busImpl;
	}

	public List getUserList() {
		return userList;
	}

	public void setUserList(List userList) {
		this.userList = userList;
	}

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	public DishActImpl getDishImpl() {
		return dishImpl;
	}

	public void setDishImpl(DishActImpl dishImpl) {
		this.dishImpl = dishImpl;
	}
//把分页信息，所有商家信息传输到jsp页面
	public String listAllBusiness() throws Exception {
		int totalRows = 0;
		Pager pager = null;
		int pageSize = 5;
		HttpServletRequest request = ServletActionContext.getRequest();
		totalRows = busImpl.getTotalRows();
		pager = PagerHelper.getPager(request, totalRows, pageSize);
		pager.setLinkUrl("listAllBusiness.action?");
		request.setAttribute("pb", pager);
		List userList = busImpl.queryAllbusiness(pager);
		request.setAttribute("userList", userList);
		return "success";
	}
//根据商家名删除该商家
	public String busDelete() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String busunessname = new String(request.getParameter("busunessname")
				.getBytes("iso8859-1"), "GBK");
		System.out.print(busunessname);
		busImpl.deleteBuss(busunessname);
		listAllBusiness();
		return "success";
	}
//根据id批量删除商家
	public String delChecked() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String uids[] = request.getParameterValues("ids");
		String as[];
		int j = (as = uids).length;
		for (int i = 0; i < j; i++) {
			String uid = as[i];
			android.hcl.bean.Business b = busImpl.findBusById(Integer
					.valueOf(Integer.parseInt(uid)));
			if (b != null) {
				busImpl.deleteBuss(b);
			}
		}

		return listAllBusiness();
	}
//根据商家名将商家信息传输到jsp页面
	public String updateBus() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String bususername = new String(request.getParameter("bususername").getBytes(
				"iso8859-1"), "GBK");
		userList = busImpl.queryBus(bususername);
		request.setAttribute("update", userList);
		return "success";
	}
//获得jsp页面更改的商家信息并提交到数据库
	public String busupdate() throws Exception {
		bus.setBusunessid(bus.getBusunessid());
		busImpl.updateBuss(bus);
		userList = busImpl.queryBus(bus.getBususername());
		ActionContext.getContext().getSession()
				.put("bususername",bus.getBususername());
		return "success";
	}
//把businessid封装在session上
	public String saveDish() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String busunessid = request.getParameter("busunessid");
		ActionContext.getContext().getSession().put("id", busunessid);
		return "success";
	}
	//把businessid封装在session上
	public String listDish() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String busunessid = request.getParameter("busunessid");
		ActionContext.getContext().getSession().put("id", busunessid);
		return "success";
	}
}

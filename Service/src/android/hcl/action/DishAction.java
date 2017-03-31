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

public class DishAction extends ActionSupport {
	private Integer searchType;//菜品类型
	private List userList;
	private Business bus;
	private Dish dish;
	private DishActImpl dishImpl;
	private BusinessActImpl busImpl;

	public DishAction() {
		this.bus = new Business();
		this.busImpl = new BusinessActImpl();
		this.dish = new Dish();
		this.dishImpl = new DishActImpl();
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

	public Integer getSearchType() {
		return searchType;
	}

	public void setSearchType(Integer searchType) {
		this.searchType = searchType;
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

	// 分页显示所有菜品信息
	public String listAllDish() throws Exception {
		int totalRows = 0;
		Pager pager = null;
		int pageSize = 5;
		HttpServletRequest request = ServletActionContext.getRequest();
		totalRows = dishImpl.getTotalRows();
		pager = PagerHelper.getPager(request, totalRows, pageSize);
		pager.setLinkUrl("listAllDish.action?");
		request.setAttribute("pb", pager);
		List userList = dishImpl.queryAllDish(pager);
		request.setAttribute("userList", userList);
		return "success";
	}

	// 根据商家id将所有该商家的菜品信息传输到jsp页面
	public String listOwnDish() throws Exception {
		int totalRows = 0;
		Pager pager = null;
		int pageSize = 5;
		HttpServletRequest request = ServletActionContext.getRequest();
		Map session = ActionContext.getContext().getSession();
		String id = session.get("id").toString();
		totalRows = dishImpl.getTotalRows();
		pager = PagerHelper.getPager(request, totalRows, pageSize);
		pager.setLinkUrl("listOwnDish.action?");
		request.setAttribute("pb", pager);
		List userList = dishImpl.queryOwnDish(pager, Integer.parseInt(id));
		request.setAttribute("userList", userList);
		return "success";
	}

	// 根据菜品名称删除菜品
	public String dishDelete() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String dishName = new String(request.getParameter("dishName").getBytes(
				"iso8859-1"), "GBK");
		dishImpl.deleteDish(dishName);
		listAllDish();
		return "success";
	}

	// 根据id批量删除菜品信息
	public String delChecked() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String uids[] = request.getParameterValues("ids");
		String as[];
		int j = (as = uids).length;
		for (int i = 0; i < j; i++) {
			String uid = as[i];
			android.hcl.bean.Dish d = dishImpl.findUserById(Integer
					.valueOf(Integer.parseInt(uid)));
			if (d != null) {
				dishImpl.deleteDish(d.getDishName());
			}
		}

		return listAllDish();
	}

	// 得到jsp页面的菜品信息并保存在数据库中
	public String dishSave() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Map session = ActionContext.getContext().getSession();
		String id = session.get("id").toString();
		if (dishImpl.checklogin(dish.getDishName()) == false) {
			if (searchType.intValue() == 1) {
				dish.setDishtype("饭食类");
				bus = busImpl.findBusById(Integer.parseInt(id));
				dish.setBusiness(bus);
				dishImpl.save(dish);
			} else if (searchType.intValue() == 2) {
				dish.setDishtype("面点类");
				bus = busImpl.findBusById(Integer.parseInt(id));
				dish.setBusiness(bus);
				dishImpl.save(dish);
			} else if (searchType.intValue() == 3) {
				dish.setDishtype("面条类");
				bus = busImpl.findBusById(Integer.parseInt(id));
				dish.setBusiness(bus);
				dishImpl.save(dish);
			}
		} else {
			addActionError("菜品已被注册！");
			return "input";
		}
		return "success";
	}
}

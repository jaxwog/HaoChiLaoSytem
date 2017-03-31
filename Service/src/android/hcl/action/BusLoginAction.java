package android.hcl.action;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import page.Pager;
import page.PagerHelper;

import android.hcl.bean.Business;
import android.hcl.dao.Impl.BookActImpl;
import android.hcl.dao.Impl.BusinessActImpl;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BusLoginAction extends ActionSupport {
	private String bususername;//商家用户名
	private String buspassword;//商家密码
	private Business bus;
	private BusinessActImpl buslmpl;
	private String validateCode;//验证码

	public BusLoginAction() {
		this.buslmpl = new BusinessActImpl();
	}

	public String getBususername() {
		return bususername;
	}

	public void setBususername(String bususername) {
		this.bususername = bususername;
	}

	public String getBuspassword() {
		return buspassword;
	}

	public void setBuspassword(String buspassword) {
		this.buspassword = buspassword;
	}

	public Business getBus() {
		return bus;
	}

	public void setBus(Business bus) {
		this.bus = bus;
	}

	public BusinessActImpl getBuslmpl() {
		return buslmpl;
	}

	public void setBuslmpl(BusinessActImpl buslmpl) {
		this.buslmpl = buslmpl;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}
//验证验证码是否正确，验证商家的用户名和密码是否正确
	public String buslogin() throws Exception {
		Object code = ActionContext.getContext().getSession().get("code");
		String strCode = "";
		if (code != null)
			strCode = code.toString();
		if (validateCode.toUpperCase().equals(strCode)) {
			if (buslmpl
					.checklogin(this.getBususername(), this.getBuspassword()) == true) {
				List userList = buslmpl.queryBus(this.getBususername());
				for (Iterator iterator1 = userList.iterator(); iterator1
						.hasNext(); ActionContext.getContext().getSession()
						.put("bususername", bus.getBususername())) {
					Object o = iterator1.next();
					bus = (Business) o;
					ActionContext.getContext().getSession()
							.put("busunessname", bus.getBusunessname());
					ActionContext.getContext().getSession()
							.put("id", bus.getBusunessid());
				}
				return "success";
			} else {
				addActionError("您还没有注册或您的密码或帐号错误！");
				return "input";
			}
		} else {
			addActionError("验证码错误");
			return "input";
		}
	}
//根据商家用户名显示商家自己的信息
	public String listownBusiness() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Map session = ActionContext.getContext().getSession();
		String bususername = session.get("bususername").toString();
		List userList = buslmpl.queryBus(bususername);
		request.setAttribute("userList", userList);
		return "success";
	}
//商家注册
	public String busregister() throws Exception {
		if (buslmpl.checkname(this.getBususername()) == false) {
			if (buslmpl.checklogin(this.bus.getBusunessname()) == false) {
				buslmpl.saveBuss(bus);
				return "success";
			} else {
				addActionError("店名已被注册！");
				return "input";
			}
		} else {
			addActionError("用户名已被注册！");
			return "input";
		}
	}

}

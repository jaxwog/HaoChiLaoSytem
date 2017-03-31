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
	private String bususername;//�̼��û���
	private String buspassword;//�̼�����
	private Business bus;
	private BusinessActImpl buslmpl;
	private String validateCode;//��֤��

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
//��֤��֤���Ƿ���ȷ����֤�̼ҵ��û����������Ƿ���ȷ
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
				addActionError("����û��ע�������������ʺŴ���");
				return "input";
			}
		} else {
			addActionError("��֤�����");
			return "input";
		}
	}
//�����̼��û�����ʾ�̼��Լ�����Ϣ
	public String listownBusiness() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Map session = ActionContext.getContext().getSession();
		String bususername = session.get("bususername").toString();
		List userList = buslmpl.queryBus(bususername);
		request.setAttribute("userList", userList);
		return "success";
	}
//�̼�ע��
	public String busregister() throws Exception {
		if (buslmpl.checkname(this.getBususername()) == false) {
			if (buslmpl.checklogin(this.bus.getBusunessname()) == false) {
				buslmpl.saveBuss(bus);
				return "success";
			} else {
				addActionError("�����ѱ�ע�ᣡ");
				return "input";
			}
		} else {
			addActionError("�û����ѱ�ע�ᣡ");
			return "input";
		}
	}

}

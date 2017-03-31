package android.hcl.action;

import android.hcl.bean.Customer;
import android.hcl.dao.Impl.CustomerActImpl;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	private String AdminName;
	private String AdminPassword;
	private String validateCode;

	public String getAdminName() {
		return AdminName;
	}

	public void setAdminName(String adminName) {
		AdminName = adminName;
	}

	public String getAdminPassword() {
		return AdminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		AdminPassword = adminPassword;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}
   //管理员登录验证
	public String adminlogin() throws Exception {
		Object code = ActionContext.getContext().getSession().get("code");
		String strCode = "";
		if (code != null)
			strCode = code.toString();
		if (validateCode.toUpperCase().equals(strCode)) {
			if ("admin".equals(getAdminName())
					&& "admin".equals(getAdminPassword())) {
				return "success";
			} else {
				addActionError("登录失败!");
				return "input";
			}
		} else {
			addActionError("验证码错误");
			return "input";
		}
	}
	
	
}

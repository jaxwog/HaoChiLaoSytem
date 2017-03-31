package android.hcl.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

import android.hcl.bean.Customer;
import android.hcl.dao.Impl.CustomerActImpl;

public class CusJsonAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {
	private CustomerActImpl cusImpl;
	HttpServletRequest request;
	HttpServletResponse response;

	public CusJsonAction() {
		cusImpl = new CustomerActImpl();
	}

	public CustomerActImpl getCusImpl() {
		return cusImpl;
	}

	public void setCusImpl(CustomerActImpl cusImpl) {
		this.cusImpl = cusImpl;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
//��֤�ͻ����û�������û����������Ƿ���ȷ����������Ϣ
	public void Cuslogin() {
		try {
			this.response.setContentType("text/html;charset=utf-8");
			this.response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			String username = this.request.getParameter("username");
			String password = this.request.getParameter("password");
			CustomerActImpl cus = new CustomerActImpl();
			boolean b = cus.checklogin(username, password);
			if (b == true) {
				out.write("true");
			} else if (b == false) {
				out.write("false");
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//��֤�ͻ����û�ע��ʱ�û����Ƿ��ѱ�ע��
	public void check() {
		try {
			this.response.setContentType("text/html;charset=utf-8");
			this.response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			String username = this.request.getParameter("username");
			CustomerActImpl cus = new CustomerActImpl();
			boolean b = cus.checkRegister(username);
			if (b == true) {// �û����Ѵ���
				String a = "true";
				out.write(a);
			} else if (b == false) {// �û���������
				String c = "false";
				out.write(c);
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//�õ��ͻ��˴�����û�ע��json���ݣ���������洢�����ݿ�
	public void Cusregister() {
		try {
			this.response.setContentType("text/html;charset=utf-8");
			this.response.setCharacterEncoding("UTF-8");
			this.request.setCharacterEncoding("gbk");
			PrintWriter out = response.getWriter();
			String jsondata = this.request.getParameter("jsonstring");
			String json = jsondata.substring(1, jsondata.length() - 1);
			JSONArray js = new JSONArray();
			js.add(json);
			JSONObject jsonObject = js.getJSONObject(0);
			String username = jsonObject.getString("username");
			String password = jsonObject.getString("password");
			String userPhone = jsonObject.getString("userPhone");
			String userEmail = jsonObject.getString("userEmail");
			String userAddress = jsonObject.getString("userAddress");
			Customer cus = new Customer(username, password, userEmail,
					userPhone, userAddress);
			this.cusImpl.saveCus(cus);
			out.write("t");
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
//���ݿͻ��˴�����û�����ѯ�û���Ϣ��װΪjson���ݺ��䵽�ͻ���
	public void Cusupdate() {
		try {
			this.response.setContentType("text/json;charset=UTF-8");
			this.response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			String username = this.request.getParameter("username");
			CustomerActImpl cus = new CustomerActImpl();
			List listc = cus.querycustomer(username);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("listc", listc);
			JSONObject jobjc = JSONObject.fromObject(map);
			out.write(jobjc.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//�õ��ͻ����û����ĵ���Ϣ���洢�����ݿ�
	public void updateCus() {
		try {
			this.response.setContentType("text/html;charset=utf-8");
			this.response.setCharacterEncoding("UTF-8");
			this.request.setCharacterEncoding("gbk");
			PrintWriter out = response.getWriter();
			String jsondata = this.request.getParameter("jsonstring");
			System.out.println(jsondata);
			String json = jsondata.substring(1, jsondata.length() - 1);
			JSONArray js = new JSONArray();
			js.add(json);
			JSONObject jsonObject = js.getJSONObject(0);
			String userid = jsonObject.getString("userid");
			String userPhone = jsonObject.getString("userPhone");
			String userEmail = jsonObject.getString("userEmail");
			String userAddress = jsonObject.getString("userAddress");
			Customer cus = this.cusImpl.findUserById(Integer.parseInt(userid));
			cus.setUserPhone(userPhone);
			cus.setUserEmail(userEmail);
			cus.setUserAddress(userAddress);
			this.cusImpl.updateCus(cus);
			out.write("t");
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

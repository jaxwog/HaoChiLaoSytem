package android.hcl.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import android.hcl.bean.Dish;
import android.hcl.dao.Impl.BusinessActImpl;
import android.hcl.dao.Impl.DishActImpl;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BusJsonAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {
	HttpServletRequest request;
	HttpServletResponse response;
	BusinessActImpl busImpl;
	DishActImpl disImpl;

	public BusinessActImpl getBusImpl() {
		return busImpl;
	}

	public void setBusImpl(BusinessActImpl busImpl) {
		this.busImpl = busImpl;
	}

	public BusJsonAction() {
		this.busImpl = new BusinessActImpl();
		this.disImpl = new DishActImpl();
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public DishActImpl getDisImpl() {
		return disImpl;
	}

	public void setDisImpl(DishActImpl disImpl) {
		this.disImpl = disImpl;
	}
	//查询所有商家信息，封装成json数据传输到客户端
	public void BusQuery() {
		List list = this.busImpl.queryAllbusiness();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		JSONObject jobj = JSONObject.fromObject(map);
		try {
			this.response.setContentType("text/html;charset=utf-8");
			this.response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write(gb2312ToUtf8(jobj.toString()));
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//根据商家id查询该商家的菜品，封装成json数据传输到客户端
	public void DisQuery() {
		try {
			this.response.setContentType("text/html;charset=utf-8");
			this.response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			String businessid = this.request.getParameter("businessid");
			List listd = disImpl.queryOwnDish(Integer.parseInt(businessid));
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("listd", listd);
			JSONObject jobj = JSONObject.fromObject(map);
			out.write(gb2312ToUtf8(jobj.toString()));
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//根据商家id和菜品类型查询菜品，封装成json数据传输到客户端
	public void DisQueryByType() {
		try {
			this.response.setContentType("text/html;charset=utf-8");
			this.response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			String type = this.request.getParameter("type");
			String businessid = this.request.getParameter("businessid");
			System.out.print(type+businessid);
			List listd = disImpl.queryDishByType(type, businessid);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("listd", listd);
			JSONObject jobj = JSONObject.fromObject(map);
			out.write(gb2312ToUtf8(jobj.toString()));
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String gb2312ToUtf8(String str) throws UnsupportedEncodingException {
		String urlEncode = "";
		try {
			urlEncode = URLEncoder.encode(str, "UTF-8");

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
		return URLDecoder.decode(urlEncode, "utf-8");
	}
}

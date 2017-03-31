package android.hcl.action;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import android.hcl.bean.Book;
import android.hcl.bean.Business;
import android.hcl.dao.Impl.BookActImpl;
import android.hcl.dao.Impl.BusinessActImpl;

import com.opensymphony.xwork2.ActionSupport;

public class booJsonAction extends ActionSupport implements
	ServletRequestAware, ServletResponseAware {
	HttpServletRequest request;
	HttpServletResponse response;
	BookActImpl bookActImpl;
	BusinessActImpl busActImpl;

	public booJsonAction() {
		this.bookActImpl = new BookActImpl();
		this.busActImpl = new BusinessActImpl();
	}

	public BookActImpl getBookActImpl() {
		return bookActImpl;
	}

	public void setBookActImpl(BookActImpl bookActImpl) {
		this.bookActImpl = bookActImpl;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void orderbook() {//���մӿͻ��˴�����json���ݣ�������洢�����ݿ���
		try {
			this.response.setContentType("text/html;charset=utf-8");
			this.response.setCharacterEncoding("UTF-8");
			this.request.setCharacterEncoding("gbk");
			PrintWriter out = response.getWriter();
			String jsondata = request.getParameter("jsonstring");
			String json = jsondata.substring(1, jsondata.length() - 1);
			System.out.println(json);
			JSONArray js = new JSONArray();
			js.add(jsondata);
			System.out.print(js.size());
			JSONArray array = js.getJSONArray(0);
			for (int i = 0; i < array.size(); i++) {
				JSONObject jsonObject = array.getJSONObject(i);
				Book boo = new Book();
				boo.setUsername(jsonObject.getString("username"));
				boo.setPhone(jsonObject.getString("phone"));
				boo.setAddress(jsonObject.getString("address"));
				String businessid = jsonObject.getString("businessid");
				Business bus = busActImpl.findBusById(Integer
						.parseInt(businessid));
				boo.setBusunessname(bus.getBusunessname());
				boo.setDishName(jsonObject.getString("dish_name"));
				boo.setNumber(Integer.parseInt(jsonObject.getString("number")));
				boo.setPrice(Float.parseFloat(jsonObject.getString("price")));
				boo.setTotalconsumption(Float.parseFloat(jsonObject
						.getString("totalconsumption")));
				boo.setBookTime(new Timestamp(new Date().getTime()));
				boo.setBookFinish("δ���");
				this.bookActImpl.saveBook(boo);
			}
			out.write("t");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void BookQuery() {//�����û�����ѯ������Ϣ����װ��json�����ͻ���
		try {
			this.response.setContentType("text/html;charset=utf-8");
			this.response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			String username = request.getParameter("username");
			List list = this.bookActImpl.queryBook(username);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", list);
			JsonConfig config = new JsonConfig();
			config.registerJsonValueProcessor(Timestamp.class,
					new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
			JSONObject jobj = JSONObject.fromObject(map, config);
			out.write(jobj.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delorder() {//���ݿͻ��˵õ��Ĳ���ɾ��������Ϣ
		try {
			this.response.setContentType("text/html;charset=utf-8");
			this.response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			int bookid = 0;
			String dishName = request.getParameter("dishName");
			List list = this.bookActImpl.queryBookbyDish(dishName);
			for (Object o : list) {
				Book book = (Book) o;
				bookid = book.getBookId();
			}
			Book or = this.bookActImpl.findbooById(bookid);
			this.bookActImpl.deleteBoo(or);
			out.write("true");
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void orderfinish() {//�û����������ɰ�ť�����Ķ��������Ϣ
		try {
			this.response.setContentType("text/html;charset=utf-8");
			this.response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			int bookid = 0;
			String dishName = request.getParameter("dishName");
			List list = this.bookActImpl.queryBookbyDish(dishName);
			for (Object o : list) {
				Book book = (Book) o;
				bookid = book.getBookId();
			}
			Book or = this.bookActImpl.findbooById(bookid);
	        or.setBookFinish("���");
	        this.bookActImpl.updateBook(or);
			out.write("true");
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

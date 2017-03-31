package com.example.operation;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.quanju.MyApp;

import android.net.ParseException;

public class OrderOperation {
	private static final String orderuri = MyApp.uri + "orderbook.action";
	private static final String queryuri = MyApp.uri + "BookQuery.action";
	private static final String deleteuri = MyApp.uri + "deleteQuery.action";
	private static final String orderfinish = MyApp.uri + "orderfinish.action";

	public String disOrder(String jsonString) {// 预定订单
		String result = null;
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		NameValuePair nvp = new BasicNameValuePair("jsonstring", jsonString);
		list.add(nvp);
		HttpPost httpPost = new HttpPost(orderuri);
		try {
			HttpEntity entity = new UrlEncodedFormEntity(list, "GBK");
			// 此句必须加上否则传到客户端的中文将是乱码
			httpPost.setEntity(entity);
			HttpClient client = new DefaultHttpClient();
			HttpResponse httpResponse = client.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(httpResponse.getEntity(), "GBK");
				System.out.println("resu" + result);
			} else {
				result = httpResponse.getStatusLine().getStatusCode() + "+预定失败";
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static List<Map<String, Object>> QueryOwnOrder(String username) {// 查询所有订单信息
		List<Map<String, Object>> lsadmin = new ArrayList<Map<String, Object>>();
		String result = forclient.sendPost(queryuri);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", username));
		try {
			HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
			HttpPost httpPost = new HttpPost(queryuri);
			httpPost.setEntity(entity);
			HttpClient client = new DefaultHttpClient();
			HttpResponse httpResponse = client.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result = EntityUtils
						.toString(httpResponse.getEntity(), "utf-8");
			} else {
				result = httpResponse.getStatusLine().getStatusCode() + "+查询失败";
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			// 获取服务器返回的json格式的数据
			JSONObject jobj = new JSONObject(result);
			// 获取json格式中的lsad主键集合内容
			JSONArray jarray = jobj.getJSONArray("list");
			for (int i = 0; i < jarray.length(); i++) {
				// 逐行读取json格式的数据
				JSONObject jo = (JSONObject) jarray.opt(i);
				// 通过主键获取值信息
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("bookId", jo.getString("bookId"));
				map.put("username", jo.getString("username"));
				map.put("busunessname", jo.getString("busunessname"));
				map.put("dishName", jo.getString("dishName"));
				map.put("totalconsumption", jo.getString("totalconsumption"));
				map.put("bookTime", jo.getString("bookTime"));
				map.put("bookFinish", jo.getString("bookFinish"));
				map.put("price", jo.getString("price"));
				map.put("phone", jo.getString("phone"));
				map.put("address", jo.getString("address"));
				map.put("number", jo.getString("number"));
				lsadmin.add(map);
			}
			return lsadmin;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return lsadmin;
	}

	public String delOrder(String dishName) {//要删除的订单的菜名传给服务器，在服务器端删除
		String result = null;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("dishName", dishName));
		try {
			HttpEntity entity = new UrlEncodedFormEntity(params, "GBK");
			HttpPost httpPost = new HttpPost(deleteuri);
			httpPost.setEntity(entity);
			HttpClient client = new DefaultHttpClient();
			HttpResponse httpResponse = client.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result = EntityUtils.toString(httpResponse.getEntity(), "gbk");
				System.out.println("resu" + result);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String orderFinish(String dishName) {//将要更改订单状态的订单的菜名传给服务器，由服务器端更改订单状态
		String result = null;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("dishName", dishName));
		try {
			HttpEntity entity = new UrlEncodedFormEntity(params, "GBK");
			HttpPost httpPost = new HttpPost(orderfinish);
			httpPost.setEntity(entity);
			HttpClient client = new DefaultHttpClient();
			HttpResponse httpResponse = client.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result = EntityUtils.toString(httpResponse.getEntity(), "gbk");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}

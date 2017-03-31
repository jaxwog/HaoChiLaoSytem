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

import com.example.haochilao.R;
import com.quanju.MyApp;

import android.net.ParseException;

public class BusinessOperation {
	private static int[] images = { R.drawable.m1_c, R.drawable.m2_c,
			R.drawable.m3_c, R.drawable.m4_c, R.drawable.m5_c,
			R.drawable.m6_c, R.drawable.m7_c, R.drawable.m8_c,
			R.drawable.m9_c, R.drawable.m10_c, R.drawable.m11_c};
	private static final String queryuri = MyApp.uri + "BusQuery.action";
	private static final String querydis = MyApp.uri + "DisQuery.action";
	private static final String querydisbyType = MyApp.uri
			+ "DisQueryByType.action";

	public static List<Map<String, Object>> QueryAll() {// 查询所有商家信息
		List<Map<String, Object>> lsadmin = new ArrayList<Map<String, Object>>();
		String result = forclient.sendPost(queryuri);
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
				map.put("images", images[i]);
				map.put("busunessid", jo.getString("busunessid"));
				map.put("busunessname", jo.getString("busunessname"));
				map.put("busunessPhone", jo.getString("busunessPhone"));
				map.put("busunessAddress", jo.getString("busunessAddress"));
				System.out.print(jo.getString("busunessname"));
				lsadmin.add(map);
			}
			return lsadmin;
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return lsadmin;
	}

	public List<Map<String, Object>> disQuery(String businessid) {// 根据商家id查询这个商家的菜品
		List<Map<String, Object>> lsadmin = new ArrayList<Map<String, Object>>();
		String result = forclient.sendPost(querydis);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("businessid", businessid));
		try {
			HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
			HttpPost httpPost = new HttpPost(querydis);
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
				map.put("dishId", jo.getString("dishId"));
				map.put("dishtype", jo.getString("dishtype"));
				map.put("price", jo.getString("price"));
				map.put("dishName", jo.getString("dishName"));
				map.put("dishMessage", jo.getString("dishMessage"));
				lsadmin.add(map);
			}
			return lsadmin;
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return lsadmin;
	}
//把菜品类型和商家id传输给客户端，并接收客户端发送的json数据并解析
	public List<Map<String, Object>> QuerydishbyType(String type,
			String businessid) {
		List<Map<String, Object>> lsadmin = new ArrayList<Map<String, Object>>();
		String result = forclient.sendPost(querydisbyType);

		 List<NameValuePair> params = new ArrayList<NameValuePair>();
		 params.add(new BasicNameValuePair("type", type));
		 params.add(new BasicNameValuePair("businessid", businessid));
		 try {
		 HttpEntity entity = new UrlEncodedFormEntity(params, "gbk");
		 HttpPost httpPost = new HttpPost(querydisbyType);
		 httpPost.setEntity(entity);
		 HttpClient client = new DefaultHttpClient();
		 HttpResponse httpResponse = client.execute(httpPost);
		 if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
		 {
		 result = EntityUtils.toString(httpResponse.getEntity(), "gbk");
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
			JSONArray jarray = jobj.getJSONArray("listd");
			for (int i = 0; i < jarray.length(); i++) {
				// 逐行读取json格式的数据
				JSONObject jo = (JSONObject) jarray.opt(i);
				// 通过主键获取值信息
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("dishId", jo.getString("dishId"));
				map.put("price", jo.getString("price"));
				map.put("dishName", jo.getString("dishName"));
				map.put("dishMessage", jo.getString("dishMessage"));
				lsadmin.add(map);
			}
			return lsadmin;
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return lsadmin;
	}

}

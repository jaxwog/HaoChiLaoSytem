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

	public String disOrder(String jsonString) {// Ԥ������
		String result = null;
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		NameValuePair nvp = new BasicNameValuePair("jsonstring", jsonString);
		list.add(nvp);
		HttpPost httpPost = new HttpPost(orderuri);
		try {
			HttpEntity entity = new UrlEncodedFormEntity(list, "GBK");
			// �˾������Ϸ��򴫵��ͻ��˵����Ľ�������
			httpPost.setEntity(entity);
			HttpClient client = new DefaultHttpClient();
			HttpResponse httpResponse = client.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(httpResponse.getEntity(), "GBK");
				System.out.println("resu" + result);
			} else {
				result = httpResponse.getStatusLine().getStatusCode() + "+Ԥ��ʧ��";
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

	public static List<Map<String, Object>> QueryOwnOrder(String username) {// ��ѯ���ж�����Ϣ
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
				result = httpResponse.getStatusLine().getStatusCode() + "+��ѯʧ��";
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
			// ��ȡ���������ص�json��ʽ������
			JSONObject jobj = new JSONObject(result);
			// ��ȡjson��ʽ�е�lsad������������
			JSONArray jarray = jobj.getJSONArray("list");
			for (int i = 0; i < jarray.length(); i++) {
				// ���ж�ȡjson��ʽ������
				JSONObject jo = (JSONObject) jarray.opt(i);
				// ͨ��������ȡֵ��Ϣ
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

	public String delOrder(String dishName) {//Ҫɾ���Ķ����Ĳ����������������ڷ�������ɾ��
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

	public String orderFinish(String dishName) {//��Ҫ���Ķ���״̬�Ķ����Ĳ����������������ɷ������˸��Ķ���״̬
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

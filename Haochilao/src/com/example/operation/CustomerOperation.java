package com.example.operation;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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

public class CustomerOperation {
	private static final String loginuri = MyApp.uri+"Cuslogin.action";
	private static final String registeruri = MyApp.uri+"Cusregister.action";
	private static final String checkuri = MyApp.uri+"check.action";
	private static final String updateuri = MyApp.uri+"Cusupdate.action";
	private static final String update = MyApp.uri+"updateCus.action";

	public String Cuslogin(String username, String password) {// ��½
		String result = null;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("password", password));
		try {
			HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
			HttpPost httpPost = new HttpPost(loginuri);
			httpPost.setEntity(entity);
			HttpClient client = new DefaultHttpClient();
			HttpResponse httpResponse = client.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result = EntityUtils
						.toString(httpResponse.getEntity(), "utf-8");
			} else {
				result = httpResponse.getStatusLine().getStatusCode() + "+��¼ʧ��";
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

	public String checkusername(String username) {// ע��ʱ��֤�û����Ƿ�ע��
		String result = null;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", username));
		try {
			HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
			HttpPost httpPost = new HttpPost(checkuri);
			httpPost.setEntity(entity);
			HttpClient client = new DefaultHttpClient();
			HttpResponse httpResponse = client.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result = EntityUtils
						.toString(httpResponse.getEntity(), "utf-8");
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

	public String register(String jsonString) {// ��ע����Ϣ��װ��json���䵽������
		String result = null;
		List<NameValuePair> list = new ArrayList<NameValuePair>();

		NameValuePair nvp = new BasicNameValuePair("jsonstring", jsonString);
		list.add(nvp);
		HttpPost httpPost = new HttpPost(registeruri);
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
				result = httpResponse.getStatusLine().getStatusCode() + "+ע��ʧ��";
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

	public List<Map<String, Object>> updateAdd(String username) {// �����û����õ�����û�����Ϣ
		List<Map<String, Object>> lsadmin = new ArrayList<Map<String, Object>>();
		String result = forclient.sendPost(updateuri);
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", username));
		HttpPost httpPost = new HttpPost(updateuri);
		try {
			HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
			httpPost.setEntity(entity);
			HttpClient client = new DefaultHttpClient();
			HttpResponse httpResponse = client.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result = EntityUtils
						.toString(httpResponse.getEntity(), "utf-8");
			} else {
				result = httpResponse.getStatusLine().getStatusCode() + "+��¼ʧ��";
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
			JSONArray jarray = jobj.getJSONArray("listc");
			for (int i = 0; i < jarray.length(); i++) {
				// ���ж�ȡjson��ʽ������
				JSONObject jo = (JSONObject) jarray.opt(i);
				// ͨ��������ȡֵ��Ϣ
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userid", jo.getString("userid"));
				map.put("username", jo.getString("username"));
				map.put("password", jo.getString("password"));
				map.put("userEmail", jo.getString("userEmail"));
				map.put("userPhone", jo.getString("userPhone"));
				map.put("userAddress", jo.getString("userAddress"));
				lsadmin.add(map);
			}
			return lsadmin;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return lsadmin;
	}

	public String update(String jsonString) {// ��ע����Ϣ��װ��json���䵽������
		String result = null;
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		NameValuePair nvp = new BasicNameValuePair("jsonstring", jsonString);
		list.add(nvp);
		HttpPost httpPost = new HttpPost(update);
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
				result = httpResponse.getStatusLine().getStatusCode() + "+ע��ʧ��";
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

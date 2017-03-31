package com.quanju;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Application;

public class MyApp extends Application {
	public static String b = null;// 判断用户是否登陆

	public static String username = null;// 存储用户名
	public static String phone = null;
	public static String address = null;
	public static String userid = null;// 存储用户id
	public static String uri = "http://192.168.191.1:8080/Service/";

	public static String rice = "饭食类";
	public static String noodle = "面条类";
	public static String bread = "面点类";

	public static String businessid = null;
    public static Map<String, Object> map=new HashMap<String, Object>();
    public static Map<String, Object> map2=new HashMap<String, Object>();
	 
	
	public static String getB() {
		return b;
	}


	public static void setB(String b) {
		MyApp.b = b;
	}


	public static String getUsername() {
		return username;
	}


	public static void setUsername(String username) {
		MyApp.username = username;
	}


	public static String getPhone() {
		return phone;
	}


	public static void setPhone(String phone) {
		MyApp.phone = phone;
	}


	public static String getAddress() {
		return address;
	}


	public static void setAddress(String address) {
		MyApp.address = address;
	}


	public static String getUserid() {
		return userid;
	}


	public static void setUserid(String userid) {
		MyApp.userid = userid;
	}


	public static String getBusinessid() {
		return businessid;
	}


	public static void setBusinessid(String businessid) {
		MyApp.businessid = businessid;
	}





	public static Map<String, Object> getMap() {
		return map;
	}


	public static void setMap(Map<String, Object> map) {
		MyApp.map = map;
	}


	public static Map<String, Object> getMap2() {
		return map2;
	}


	public static void setMap2(Map<String, Object> map2) {
		MyApp.map2 = map2;
	}


	@Override
	public void onCreate() {

		super.onCreate();
	}
}
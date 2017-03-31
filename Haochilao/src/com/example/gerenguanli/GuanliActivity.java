package com.example.gerenguanli;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.bean.Customer;
import com.example.haochilao.MeFragment;
import com.example.haochilao.R;
import com.example.json.WriteJson;
import com.example.operation.CustomerOperation;
import com.quanju.MyApp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class GuanliActivity extends Activity {
	private EditText etusername;
	private EditText etpassword;
	private EditText etemail;
	private EditText etphone;
	private EditText etAddress;
	private Button submit;
	String str = null;
	String jsonString = null;
	ProgressDialog dialog;
	private static final int REQUEST_EX = 1;
	String username = null;
	String password = null;
	String email = null;
	String phone = null;
	String Address = null;
	private ImageView button;
	private List<Map<String, Object>> lsadmin;
	private CustomerOperation operaton = new CustomerOperation();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update);
		button = (ImageView) findViewById(R.id.back_image_button);
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				GuanliActivity.this.finish();
			}
		});
		init();
		new Thread(runnable).start();
		this.etusername.setOnFocusChangeListener(new EtusernameOnFocusChange());
		this.submit.setOnClickListener(new SubmitOnclick());
	}

	private void init() {
		this.submit = (Button) findViewById(R.id.submit);
		this.etusername = (EditText) findViewById(R.id.etusername);
		this.etpassword = (EditText) findViewById(R.id.etpassword);
		this.etemail = (EditText) findViewById(R.id.etemail);
		this.etphone = (EditText) findViewById(R.id.etphone);
		this.etAddress = (EditText) findViewById(R.id.etAddress);
		this.dialog = new ProgressDialog(GuanliActivity.this);
		this.dialog.setTitle("上传数据中");
		this.dialog.setMessage("请稍等...");
	}

	private class EtusernameOnFocusChange implements OnFocusChangeListener {
		public void onFocusChange(View v, boolean hasFocus) {//对用户名进行判断
			if (!etusername.hasFocus()) {
				str = etusername.getText().toString().trim();
				if (str == null || str.length() <= 0) {
					etusername.setError("用户名不能为空");
				}
			}

		}
	}

	private class SubmitOnclick implements OnClickListener {

		public void onClick(View v) {

			username = etusername.getText().toString().trim();
			password = etpassword.getText().toString().trim();
			//对邮箱格式进行判断
			email = etemail.getText().toString().trim();
			String emilRegex="^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$"; 
			Pattern pattern1 = Pattern.compile(emilRegex);
			Matcher matcher1 = pattern1.matcher(email);
			boolean flag1 = matcher1.matches();
			if (etemail == null || etemail.length() <= 0) {
				etemail.requestFocus();
				etemail.setError("邮箱不能为空");
				return;
			}else if (flag1 == false){
				etemail.requestFocus();
				etemail.setError("邮箱格式不正确！");
				return;
			}
			//对电话格式进行判断
			phone = etphone.getText().toString().trim();
			String telRegex = "^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$";
			Pattern pattern = Pattern.compile(telRegex);
			Matcher matcher= pattern.matcher(phone);
			boolean flag = matcher.matches();
			if (etphone == null || etphone.length() <= 0) {
				etphone.requestFocus();
				etphone.setError("电话不能为空");
				return;
			}else if (flag == false){
				etphone.requestFocus();
				etphone.setError("电话格式不正确！");
				return;
			}
			
			Address = etAddress.getText().toString().trim();
			if (etAddress == null || etAddress.length() <= 0) {
				etAddress.requestFocus();
				etAddress.setError("地址不能为空");
				return;
			}
			dialog.show();
			new Thread(new Runnable() {
				public void run() {
					Customer cus = new Customer();
					// 构造一个user对象
					cus.setUserid(Integer.parseInt(MyApp.userid));
					cus.setUsername(username);
					cus.setPassword(password);
					cus.setUserPhone(phone);
					cus.setUserEmail(email);
					cus.setUserAddress(Address);
					List list = new ArrayList();
					list.add(cus);
					WriteJson writeJson = new WriteJson();
					// 将user对象写出json形式字符串
					jsonString = writeJson.getJsonData(list);
					System.out.println(jsonString);
					String result = operaton.update(jsonString);
					Message msg = new Message();
					System.out.println("result---->" + result);
					msg.obj = result;
					handler1.sendMessage(msg);
				}
			}).start();
		}
	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			lsadmin = (List<Map<String, Object>>) msg.obj;
			for (int i = 0; i < lsadmin.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map = lsadmin.get(i);
				MyApp.userid = map.get("userid").toString();
				etusername.setText(map.get("username").toString());
				etpassword.setText(map.get("password").toString());
				etphone.setText(map.get("userPhone").toString());
				etemail.setText(map.get("userEmail").toString());
				etAddress.setText(map.get("userAddress").toString());
			}
			super.handleMessage(msg);
		}
	};

	Handler handler1 = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			dialog.dismiss();
			String msgobj = msg.obj.toString();
			if (msgobj.equals("t")) {
				Toast.makeText(GuanliActivity.this, "修改成功", 0).show();
				GuanliActivity.this.finish();
			} else {
				Toast.makeText(GuanliActivity.this, "修改失败", 0).show();
			}
			super.handleMessage(msg);
		}
	};
	Runnable runnable = new Runnable() {
		@Override
		public void run() {
			List<Map<String, Object>> list = operaton.updateAdd(MyApp.username);
			Message msg = new Message();
			msg.obj = list;
			handler.sendMessage(msg);
		}
	};
}

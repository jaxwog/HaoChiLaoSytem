package com.example.gerenguanli;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.bean.Customer;
import com.example.haochilao.R;
import com.example.json.WriteJson;
import com.example.operation.CustomerOperation;

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

public class registerActivity extends Activity {
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
	private  CustomerOperation operaton = new CustomerOperation();
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		button=(ImageView)findViewById(R.id.zhuce_back);
	       button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				registerActivity.this.finish();
			}
		});
		init();
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
		this.dialog = new ProgressDialog(registerActivity.this);
		this.dialog.setTitle("上传数据中");
		this.dialog.setMessage("请稍等...");
	}

	private class EtusernameOnFocusChange implements OnFocusChangeListener {
		public void onFocusChange(View v, boolean hasFocus) {
			if (!etusername.hasFocus()) {
				str = etusername.getText().toString().trim();
				if (str == null || str.length() <= 0) {
					etusername.setError("用户名不能为空");
				} else {
					new Thread(new Runnable() {
						//如果用户名不为空，那么将用户名提交到服务器上进行验证，看用户名是否存在，就像JavaEE中利用
						// ajax一样，虽然你看不到但是它却偷偷摸摸做了很多
						public void run() {
							String result = operaton.checkusername(str);
							System.out.println("result:" + result);
							Message message = new Message();
							message.obj = result;
							handler.sendMessage(message);
						}
					}).start();
				}
			}
		}
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			String msgobj = msg.obj.toString();
			if (msgobj.equals("true")) {
				etusername.requestFocus();
				etusername.setError("用户名" + str + "已存在");
			} else {
				etpassword.requestFocus();
			}
			super.handleMessage(msg);
		}
	};
	private class SubmitOnclick implements OnClickListener {

		public void onClick(View v) {
			username = etusername.getText().toString().trim();
			password = etpassword.getText().toString().trim();
			//对邮箱格式进项判断
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
				
	//对电话格式进项判断
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
		//对地址信息进行判断
			Address = etAddress.getText().toString().trim();
			if (etAddress == null || etAddress.length() <= 0) {
				etAddress.requestFocus();
				etAddress.setError("地址不能为空");
				return;
			}
			dialog.show();
			new Thread(new Runnable() {

				public void run() {

					CustomerOperation operaton = new CustomerOperation();
	                Customer cus = new Customer(username, password, email, phone, Address);
					// 构造一个user对象
					List list = new ArrayList();
					list.add(cus);
					WriteJson writeJson = new WriteJson();
					// 将user对象写出json形式字符串
					jsonString = writeJson.getJsonData(list);
					System.out.println(jsonString);
					String result = operaton.register( jsonString);
					Message msg = new Message();
					System.out.println("result---->" + result);
					msg.obj = result;
					handler1.sendMessage(msg);
				}
			}).start();

		}
	}
	Handler handler1=new Handler()
	{
		@Override
		public void handleMessage(Message msg) {
			dialog.dismiss();
			String msgobj=msg.obj.toString();
			if(msgobj.equals("t"))
			{
				Toast.makeText(registerActivity.this, "注册成功", 0).show();
				registerActivity.this.finish();
			}
			else {
				Toast.makeText(registerActivity.this, "注册失败", 0).show();
			}
			super.handleMessage(msg);
		}	
	};
}

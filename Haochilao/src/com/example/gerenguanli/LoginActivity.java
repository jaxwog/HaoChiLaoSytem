package com.example.gerenguanli;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.haochilao.R;
import com.example.operation.CustomerOperation;
import com.quanju.MyApp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private static final String Filename = "Cus";
	private EditText etusername;
	private EditText etpassword;
	private Button login;
	private Button register;
	String username;
	String password;
	ProgressDialog p;
	private ImageView button;
	private List<Map<String, Object>> list;
	private CustomerOperation operaton = new CustomerOperation();

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_login);
		init();
		button = (ImageView) findViewById(R.id.back_image_button);
		login.setOnClickListener(new LoginOnclick());
		button.setOnClickListener(new OnClickListeneri());
	}

	private void init() {//对话框信息
		etusername = (EditText) findViewById(R.id.etusername);
		etpassword = (EditText) findViewById(R.id.etpassword);
		login = (Button) findViewById(R.id.login);
		register = (Button) findViewById(R.id.register);
		p = new ProgressDialog(LoginActivity.this);
		p.setTitle("登录中");
		p.setMessage("登录中，马上就好");
	}
//用户登录时对用户输入的密码和用户名进行判断
	private class LoginOnclick implements OnClickListener {

		public void onClick(View arg0) {
			username = etusername.getText().toString().trim();
			if (username == null || username.length() <= 0) {
				etusername.requestFocus();
				etusername.setError("对不起，用户名不能为空");
				return;
			} else {
				username = etusername.getText().toString().trim();
			}
			password = etpassword.getText().toString().trim();
			if (password == null || password.length() <= 0) {
				etpassword.requestFocus();
				etpassword.setError("对不起，密码不能为空");
				return;
			} else {
				password = etpassword.getText().toString().trim();
			}
			p.show();
			new Thread(new Runnable() {

				public void run() {
					List<Map<String, Object>> aa = operaton.updateAdd(username);
					String result = operaton.Cuslogin(username, password);
					String result2 = operaton.checkusername(username);
					Message msg = new Message();
					Message msg1 = new Message();
					Message msg2 = new Message();
					msg.obj = result;
					msg1.obj = aa;
					msg2.obj = result2;
					handler.sendMessage(msg);
					handlerc.sendMessage(msg1);
					handlerch.sendMessage(msg2);
				}
			}).start();
		}

	}

	private class OnClickListeneri implements OnClickListener {

		@Override
		public void onClick(View v) {
			LoginActivity.this.finish();
		}

	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			String string = (String) msg.obj;
			p.dismiss();
			if (string.equals("true")) {
				Toast.makeText(LoginActivity.this, "登陆成功", 0).show();
				LoginActivity.this.finish();
				MyApp.b = "succ";
				button.setVisibility(View.GONE);
				button.setVisibility(View.VISIBLE);
				MyApp.username = username;
			} else {
				Toast.makeText(LoginActivity.this, "用户名或密码错误", 0).show();
			}
			super.handleMessage(msg);
		}
	};
	Handler handlerc = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			list = (List<Map<String, Object>>) msg.obj;
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map = list.get(i);
				MyApp.address = map.get("userAddress").toString();
				MyApp.phone = map.get("userPhone").toString();
			}
			super.handleMessage(msg);
		}
	};
	Handler handlerch = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			String msgobj = msg.obj.toString();
			if (msgobj.equals("false")) {
				etusername.requestFocus();
				etusername.setError("用户名" + username + "未注册");
				etpassword.requestFocus();
			}
			super.handleMessage(msg);
		}
	};
}

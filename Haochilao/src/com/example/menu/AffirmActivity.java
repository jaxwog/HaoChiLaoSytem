package com.example.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.bean.Book;
import com.example.bean.Customer;
import com.example.gerenguanli.registerActivity;
import com.example.haochilao.R;
import com.example.json.WriteJson;
import com.example.operation.CustomerOperation;
import com.example.operation.OrderOperation;
import com.quanju.MyApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class AffirmActivity extends Activity {
	private TextView adress_affirm, phone_affirm, bottomportion, bottommoney;
	private ListView affirm_listview = null;
	private ImageButton affirm_back_image_button;
	// private Button affirm_bottombutton;
	private ImageButton plus_button_information;
	private Button bottombutton;
	private String dishname[];
	private String number[];
	private String price[];
	private String dishId[];
	String jsonString = null;
	private int totalprice;
	private int num;
	private List<Map<String, String>> affirm_list = new ArrayList<Map<String, String>>();
	private SimpleAdapter simpleAdapter = null;
	private OrderOperation oo = new OrderOperation();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.affirm_indent);
		this.affirm_listview = (ListView) findViewById(R.id.affirm_listview);
		this.adress_affirm = (TextView) findViewById(R.id.adress_affirm);
		this.phone_affirm = (TextView) findViewById(R.id.phone_affirm);
		this.bottommoney = (TextView) findViewById(R.id.bottommoney);
		this.bottomportion = (TextView) findViewById(R.id.bottomportion);
		this.bottombutton = (Button) findViewById(R.id.affirm_bottombutton);
		this.affirm_back_image_button = (ImageButton) findViewById(R.id.affirm_back_image_button);
		this.adress_affirm.setText(MyApp.address);
		this.phone_affirm.setText(MyApp.phone);
		this.affirm_back_image_button
				.setOnClickListener(new OnClickListenerback());
		this.bottombutton.setOnClickListener(new OnClickListenersub());
		setValue();
		this.bottommoney.setText(totalprice + "");
		this.bottomportion.setText(num + "");
		for (int i = 0; i < MyApp.map.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("dish_name", this.dishname[i]);
			map.put("number", this.number[i]);
			map.put("price", this.price[i]);
			map.put("username", MyApp.username);
			map.put("phone", MyApp.phone);
			map.put("address", MyApp.address);
			map.put("businessid", MyApp.businessid);
			map.put("totalconsumption", totalprice + "");
			this.affirm_list.add(map);
		}
		this.simpleAdapter = new SimpleAdapter(this, this.affirm_list,
				R.layout.list_affirm, new String[] { "dish_name", "number",
						"price" }, new int[] { R.id.afdishname, R.id.afnumber,
						R.id.afprice });
		this.affirm_listview.setAdapter(this.simpleAdapter);
	}

	public class OnClickListenerback implements OnClickListener {//返回按钮点击事件
		@Override
		public void onClick(View v) {

			Intent intent = new Intent(AffirmActivity.this, MenuActivity.class);
			AffirmActivity.this.startActivity(intent);
		}
	}

	public void setValue() {

		this.dishname = new String[MyApp.map2.size()];
		this.price = new String[MyApp.map2.size()];
		this.number = new String[MyApp.map.size()];
		this.dishId = new String[MyApp.map.size()];
		Set<String> sets1 = MyApp.map.keySet();
		Set<String> sets2 = MyApp.map2.keySet();

		Object o1[] = sets1.toArray(new Object[0]);
		Object o2[] = sets2.toArray(new Object[0]);
		for (int i = 0; i < o1.length; i++) {
			this.dishId[i] = o1[i].toString();
		}
		for (int i = 0; i < o1.length; i++) {
			this.number[i] = MyApp.map.get(this.dishId[i]).toString();
		}
		for (int i = 0; i < o2.length; i++) {
			this.dishname[i] = o2[i].toString();
		}
		for (int i = 0; i < o2.length; i++) {
			this.price[i] = MyApp.map2.get(this.dishname[i]).toString();
		}
		for (int i = 0; i < o2.length; i++) {
			totalprice = totalprice + Integer.parseInt(this.price[i])
					* Integer.parseInt(this.number[i]);
		}
		for (int i = 0; i < o2.length; i++) {
			num = num + Integer.parseInt(this.number[i]);
		}

	}

	public class OnClickListenersub implements OnClickListener {

		public void onClick(View v) {
			setValue();
			new Thread(new Runnable() {
				public void run() {
					WriteJson writeJson = new WriteJson();
					jsonString = writeJson.getJsonData(affirm_list);
					System.out.println(jsonString);
					String result = oo.disOrder(jsonString);
					Message msg = new Message();
					
					msg.obj = result;
					handler1.sendMessage(msg);
				}
			}).start();
			// }

		}

	}
//对下单状态进行判断
	Handler handler1 = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			String msgobj = msg.obj.toString();
			if (msgobj.equals("t")) {
				Toast.makeText(AffirmActivity.this, "下单成功", 0).show();
				AffirmActivity.this.finish();

			} else {
				Toast.makeText(AffirmActivity.this, "下单失败", 0).show();
			}
			super.handleMessage(msg);
		}
	};
}
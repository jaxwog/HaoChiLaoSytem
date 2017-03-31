package com.example.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.menu.AffirmActivity;
import com.example.menu.MenuActivity;
import com.example.haochilao.MainActivity;
import com.example.haochilao.R;
import com.quanju.MyApp;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MenuActivity extends Activity {
	private TextView text = null, bottomportion = null, bottommoney = null;

	private ImageButton back_image_button;
	private ImageButton plus_button, sub_button;
	private Button buttonbottom ;
	private int portion = 0;
	public static MenuActivity activity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_main_menu);
		
		this.text = (TextView) super.findViewById(R.id.storename);
		this.bottomportion = (TextView) super.findViewById(R.id.bottomportion);
		this.bottommoney = (TextView) super.findViewById(R.id.bottommoney);

		activity = this;
		this.buttonbottom = (Button)super.findViewById(R.id.bottombutton);
		this.buttonbottom.setOnClickListener(new OnClickListener() {
			//点击下单按钮判断用户是否登录
			@Override
			public void onClick(View v) {
				if (MyApp.b == null) {
					Toast.makeText(MenuActivity.this, "还没有登录", Toast.LENGTH_SHORT).show();
					MenuActivity.this.finish();
				} else {
					if(MyApp.map.size()==0){
						Toast.makeText(MenuActivity.this, "还选择菜品", Toast.LENGTH_SHORT).show();
					}else{
						   Intent intent = new Intent(MenuActivity.this,AffirmActivity.class);
						   MenuActivity.this.startActivity(intent);
					}

				}
			}
		});
		this.back_image_button = (ImageButton) findViewById(R.id.back_image_button);
		//返回按钮事件
		this.back_image_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MenuActivity.this,
						MainActivity.class);
				MenuActivity.this.startActivity(intent);

			}
		});
		Intent intent = super.getIntent();
		String info = intent.getStringExtra("name");
		this.text.setText(info);
	}
	public void setNumber(int price, String num) {
		//从map中获取
		int totalPrice =0;
		String  priceText = this.bottommoney.getText().toString();
		if(priceText!=null && !priceText.equals("")){
			totalPrice = Integer.parseInt(priceText);
		}
		totalPrice = totalPrice +price;
		this.bottommoney.setText(totalPrice + "");
		int totalNum = 0;
		String numText  = this.bottomportion.getText().toString();
		if(numText!=null && !numText.equals("")){
			totalNum = Integer.parseInt(numText);
		}
		totalNum  = totalNum +Integer.parseInt(num);
		this.bottomportion.setText(totalNum+"");
	}
}

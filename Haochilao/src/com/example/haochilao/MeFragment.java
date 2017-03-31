package com.example.haochilao;

import com.example.gerenguanli.AboutActivity;
import com.example.gerenguanli.GuanliActivity;
import com.example.gerenguanli.LoginActivity;
import com.example.gerenguanli.registerActivity;
import com.example.haochilao.R;
import com.quanju.MyApp;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MeFragment extends Fragment {
	private Context context;
	private Button denglu, zhuxiao, zhuce;
	private TextView show_text;
	private ImageView person_image;
//初始化信息
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		context = getActivity().getApplicationContext();

		View view = inflater.inflate(R.layout.login_activity_main, container,
				false);
		denglu = (Button) view.findViewById(R.id.button1);
		zhuxiao = (Button) view.findViewById(R.id.zhuxiao);
		denglu.setOnClickListener(new OnClickListenera());
		zhuce = (Button) view.findViewById(R.id.register);
		show_text = (TextView) view.findViewById(R.id.show_text);
		person_image=(ImageView)view.findViewById(R.id.person_image);
		zhuce.setOnClickListener(new OnClickListenera());
		TextView textview = (TextView) view.findViewById(R.id.TextView03);
		textview.setOnClickListener(new OnClickListenera());
		TextView textview1 = (TextView) view.findViewById(R.id.TextView02);
		textview1.setOnClickListener(new OnClickListenera());
		TextView textview2 = (TextView) view.findViewById(R.id.TextView04);
		textview2.setOnClickListener(new OnClickListenera());
      zhuxiao.setOnClickListener(new OnClickListener() {
    	  
    	  //注销功能实现
		public void onClick(View v) {
			Toast.makeText(getActivity(), "你已经成功注销了",
					Toast.LENGTH_SHORT).show();
			MyApp.b=null;
			MyApp.userid=null;
			MyApp.username=null;
			show_text.setVisibility(View.GONE);
			person_image.setVisibility(View.GONE);
			denglu.setVisibility(View.VISIBLE);
			zhuce.setVisibility(View.VISIBLE);
			zhuxiao.setVisibility(View.GONE);
		}
	});
		return view;
	}

	public void showMsg(String value) {
		AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
		dialog.setTitle("工作时间:每天9:00-22:00");
		dialog.setMessage(value);
		dialog.setPositiveButton("取消", null);
		dialog.setNeutralButton("确定", null);
		dialog.show();
	}

	private class OnClickListenera implements View.OnClickListener {

		@Override
		public void onClick(View v) {

			switch (v.getId()) {
			case R.id.button1:
				Intent intent = new Intent(context, LoginActivity.class);
				startActivity(intent);
				break;
			case R.id.register:
				Intent intent1 = new Intent(context, registerActivity.class);
				startActivity(intent1);
				break;
			case R.id.TextView02:
				if (MyApp.b == null) {
					Toast.makeText(context, "还没有登录", Toast.LENGTH_SHORT).show();
				} else {
					Intent intent2 = new Intent(context, GuanliActivity.class);
					startActivity(intent2);
				}
				break;
			case R.id.TextView03:
				showMsg("呼叫400-660-5335");
				break;
			case R.id.TextView04:
				Intent intent2 = new Intent(context, AboutActivity.class);
				startActivity(intent2);
				break;
			}
		}
	}

	@Override
	public void onResume() {
		if (MyApp.b != null) {
			show_text.setVisibility(View.VISIBLE);
			show_text.setText(MyApp.username);
			person_image.setVisibility(View.VISIBLE);
			denglu.setVisibility(View.GONE);
			zhuce.setVisibility(View.GONE);
			zhuxiao.setVisibility(View.VISIBLE);
		}
		super.onResume();
	}
}

package com.example.haochilao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.menu.MenuActivity;
import com.example.operation.BusinessOperation;
import com.example.haochilao.R;
import com.quanju.MyApp;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.os.StrictMode;

import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class StoreFragment extends Fragment {

	//从数据库获取商家信息
	private SimpleAdapter simpleAdapter;
	private Context context;
	private List<Map<String, Object>> lsadmin;
	View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		context = getActivity().getApplicationContext();
		view = inflater.inflate(R.layout.webchat, container, false);

		new Thread(runnable).start();
		return view;
	}

	public void getChatList(View view) {
		ListView storeList = (ListView) view.findViewById(R.id.chatlist);
		this.simpleAdapter = new SimpleAdapter(getActivity(), lsadmin,
				R.layout.takelist, new String[] {"images", "busunessname" },
				new int[] {R.id.images, R.id.name });
		storeList.setAdapter(simpleAdapter);
		storeList.setOnItemClickListener(new OnItemClickListenerlist());

	}
//点击点击列表，进入菜品信息栏
	private class OnItemClickListenerlist implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {

			Map<String, String> map = (Map<String, String>) StoreFragment.this.simpleAdapter
					.getItem(position);
			
			System.out.println("id---" + id);
			String name = map.get("busunessname");
			String bid = map.get("busunessid");
			MyApp.businessid = bid;
			Intent intent = new Intent(context, MenuActivity.class);
			intent.putExtra("name", name);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent);

		}

	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			lsadmin = (List<Map<String, Object>>) msg.obj;
			getChatList(view);
			super.handleMessage(msg);
		}
	};

	Runnable runnable = new Runnable() {
		@Override
		public void run() {

			List<Map<String, Object>> aa = BusinessOperation.QueryAll();
			Message msg = new Message();
			msg.obj = aa;
			handler.sendMessage(msg);
		}
	};

}

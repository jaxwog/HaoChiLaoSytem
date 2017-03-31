package com.example.haochilao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.gerenguanli.LoginActivity;
import com.example.haochilao.R;

import com.example.operation.CustomerOperation;
import com.example.operation.OrderOperation;
import com.quanju.MyApp;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class AddressFragment extends Fragment {

	private ListView order_management_listview = null;
	private Context context;
	private OrderOperation oo = new OrderOperation();
	private List<Map<String, Object>> list;
	private View view;
	MyOrderBaseAdapter moba;
	ProgressDialog p;
	Button affirm,cancel;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override//订单管理
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		context = getActivity().getApplicationContext();
		view = inflater
				.inflate(R.layout.order_management_yes, container, false);
		this.order_management_listview = (ListView) view
				.findViewById(R.id.order_management_listview);
		new Thread(runnablex).start();
		return view;
	}
//从数据库获得订单信息
	class MyOrderBaseAdapter extends BaseAdapter {

		private LayoutInflater mInflater;
		private Context context;
		private List<Map<String, Object>> list;
		OrderOperation oo = new OrderOperation();

		public final class ListItemView {
			public TextView busunessname, username, dishName, number, price,
					totalconsumption, phone, address, booktime, bookFinish;
		}

		public MyOrderBaseAdapter(Context context,
				List<Map<String, Object>> list) {
			this.context = context;
			this.mInflater = LayoutInflater.from(context);
			this.list = list;

		}

		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		//获取订单信息
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			ListItemView listitemview = null;

			if (convertView == null) {
				listitemview = new ListItemView();
				convertView = mInflater.inflate(
						R.layout.list_data_order_management, null);
				listitemview.busunessname = (TextView) convertView
						.findViewById(R.id.busunessname);
				listitemview.bookFinish = (TextView) convertView
						.findViewById(R.id.bookFinish);
				listitemview.username = (TextView) convertView
						.findViewById(R.id.username);
				listitemview.dishName = (TextView) convertView
						.findViewById(R.id.dish_name);
				listitemview.number = (TextView) convertView
						.findViewById(R.id.dish_num);
				listitemview.price = (TextView) convertView
						.findViewById(R.id.dish_price);
				listitemview.totalconsumption = (TextView) convertView
						.findViewById(R.id.totalconsumption);
				listitemview.phone = (TextView) convertView
						.findViewById(R.id.phone);
				listitemview.address = (TextView) convertView
						.findViewById(R.id.adress);
				listitemview.booktime = (TextView) convertView
						.findViewById(R.id.time);
				convertView.setTag(listitemview);
			} else {

				listitemview = (ListItemView) convertView.getTag();
			}
			listitemview.busunessname.setText((String) list.get(position).get(
					"busunessname"));
			listitemview.username.setText((String) list.get(position).get(
					"username"));
			listitemview.bookFinish.setText((String) list.get(position).get(
					"bookFinish"));

			listitemview.dishName.setText((String) list.get(position).get(
					"dishName"));
			listitemview.number.setText(list.get(position).get("number") + "");
			listitemview.price.setText(list.get(position).get("price") + "");
			listitemview.totalconsumption.setText(list.get(position).get(
					"totalconsumption")
					+ "");
			listitemview.phone.setText(list.get(position).get("phone") + "");
			listitemview.address
					.setText(list.get(position).get("address") + "");
			listitemview.booktime.setText(list.get(position).get("bookTime")
					+ "");
			 cancel = (Button) convertView.findViewById(R.id.cancel);
			 affirm = (Button) convertView.findViewById(R.id.affirm);
			cancel.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					new Thread(new Runnable() {
						public void run() {
							String result = oo.delOrder(list.get(position)
									.get("dishName").toString());
							Message msg = new Message();
							msg.obj = result;
							handlerd.sendMessage(msg);
							setText(position);
						}
					}).start();
				}
			});
			affirm.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					
					new Thread(new Runnable() {
						
						public void run() {
							String result = oo.orderFinish(list.get(position)
									.get("dishName").toString());
							Message msg = new Message();
							msg.obj = result;
							handlerf.sendMessage(msg);
							List<Map<String, Object>> aa = oo.QueryOwnOrder(MyApp.username);
							Message msg2 = new Message();
							msg2.obj = aa;
							handlerx.sendMessage(msg2);
						}
					}).start();
				}
			});
			return convertView;
		}
	}

	public void setText(int index) {
		// list reomve 掉一个
		list.remove(index);
	}

	Handler handlerx = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			list = (List<Map<String, Object>>) msg.obj;
			moba = new MyOrderBaseAdapter(context, list);
			order_management_listview.setAdapter(moba);
			super.handleMessage(msg);
		}
	};
	Handler handlerd = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			String string = (String) msg.obj;

			if (string.equals("true")) {
				Toast.makeText(getActivity(), "取消订单成功", 0).show();
				moba.notifyDataSetChanged();
			} else {
				Toast.makeText(getActivity(), "取消订单失败", 0).show();
			}
			super.handleMessage(msg);
		}
	};
	Handler handlerf = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			String string = (String) msg.obj;
			if (string.equals("true")) {
				Toast.makeText(getActivity(), "提交成功", 0).show();
				moba.notifyDataSetChanged();
			} else {
				Toast.makeText(getActivity(), "提交失败", 0).show();
			}
			super.handleMessage(msg);
		}
	};
	Runnable runnablex = new Runnable() {
		@Override
		public void run() {
			List<Map<String, Object>> aa = oo.QueryOwnOrder(MyApp.username);
			Message msg = new Message();
			msg.obj = aa;
			handlerx.sendMessage(msg);
		}
	};

}

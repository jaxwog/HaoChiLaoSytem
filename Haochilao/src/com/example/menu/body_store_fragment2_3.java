package com.example.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.bean.Dish;
import com.example.haochilao.R;
import com.example.operation.BusinessOperation;
import com.quanju.MyApp;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class body_store_fragment2_3 extends Fragment {
	private View view = null;
	private Context context;
	private ListView menu_listview1;
	private String data[] = null;
	private List<Map<String, Object>> list;
	private SimpleAdapter simpleAdapter = null;
	private TextView t1, t2;
	private TextView bottomNumber;
	private LinearLayout linear;
	private int width;
	private int screen1_3;
	MyBaseAdapter mba;
	private static String Num;
	private static String price;
	private BusinessOperation bo = new BusinessOperation();

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		context = getActivity().getApplicationContext();

		view = inflater.inflate(R.layout.body_store__fragment2_3, null);
		this.menu_listview1 = (ListView) view.findViewById(R.id.menu_listview3);
		bottomNumber = (TextView) view.findViewById(R.id.bottomportion);

		LayoutInflater li = LayoutInflater.from(context);
		View v = li.inflate(R.layout.list_data_right, null);

		t1 = (TextView) v.findViewById(R.id.bottomportion);
		t2 = (TextView) v.findViewById(R.id.price_menu);

		new Thread(runnable3).start();
		return view;

	}

	public final class ListItemView {
		public TextView dishName_mune, price_mune, addText;
		public ImageButton plus, sub;
	}

	class MyBaseAdapter extends BaseAdapter {
		private LayoutInflater mInflater;
		private Context context;
		private List<Map<String, Object>> list;

		public MyBaseAdapter(Context context, List<Map<String, Object>> list) {
			this.context = context;
			this.mInflater = LayoutInflater.from(context);
			this.list = list;

		}

		@Override
		public int getCount() {
			
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			
			return position;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			ListItemView listitemview = null;
			if (convertView == null) {
				listitemview = new ListItemView();
				convertView = mInflater.inflate(R.layout.list_data_right, null);
				listitemview.dishName_mune = (TextView) convertView
						.findViewById(R.id.dishName_menu);
				listitemview.price_mune = (TextView) convertView
						.findViewById(R.id.price_menu);
				convertView.setTag(listitemview);
			}else{
	            listitemview=(ListItemView)convertView.getTag();
	        }
			listitemview.dishName_mune.setText((String)list.get(position).get("dishName"));
			listitemview.price_mune.setText((String)list.get(position).get("price"));
			 TextView tv = (TextView) convertView.findViewById(R.id.text);
			 price = listitemview.price_mune.getText().toString();
			 final ImageButton plus = (ImageButton) convertView
			 .findViewById(R.id.plus_button);
			
			 final TextView addText = (TextView) convertView
			 .findViewById(R.id.textView3);
			
			 final ImageButton sub = (ImageButton) convertView
			 .findViewById(R.id.sub_button);

			plus.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					String addNum = addText.getText().toString();
					int a = Integer.parseInt(addNum) + 1;
					Num = a + "";
				     /**
				      * �����ж� myapp.maps�Ƿ���ͬ������Ʒ��Ϣ
				      * ����ͬ���� ��map�ж�ȡ��ǰ���ܸ����͵�ǰ�ܼ۸�
				      * ����û����ͬ����Ʒ��new map ����Ʒ�ļ۸����ƣ����� �ŵ�map��
				      */
					if(a>0){
						sub.setEnabled(true);
					}
					//MyApp.maps.add(map);
					addText.setText(a + "");
					setText(position,1+"",list.get(position).get("price").toString());
					
				}
			});
			sub.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					String addNum = addText.getText().toString();
					int a = Integer.parseInt(addNum);
					if (a <= 0) {
						sub.setEnabled(false);
					}else{
						a=a-1;
					}
					//���ǵ�ǰ��Ʒ��Ϣ����map�У��޷����м�
					//������map�к�����Ʒ��Ϣ���������ͼ۸���м�
					Num = a + "";
					addText.setText(a + "");
					setText(position,-1+"","-"+list.get(position).get("price").toString());
					

				}
			});
			return convertView;
		}

	}

	public void setText(int index,String a,String price) {
		MyApp.map.put(list.get(index).get("dishId").toString(), Num+"");
		MyApp.map2.put(list.get(index).get("dishName").toString(), list.get(index).get("price").toString());
		MenuActivity.activity.setNumber(Integer.parseInt(price), a);

	}

	Handler handler3 = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			list= (List<Map<String, Object>>) msg.obj;
			mba = new MyBaseAdapter(context, list);
			menu_listview1.setAdapter(mba);
			super.handleMessage(msg);
		}
	};

	Runnable runnable3 = new Runnable() {
		@Override
		public void run() {
			List<Map<String, Object>> aa = bo.QuerydishbyType(MyApp.bread,
					MyApp.businessid);
			Message msg = new Message();
			msg.obj = aa;
			handler3.sendMessage(msg);
		}
	};
}

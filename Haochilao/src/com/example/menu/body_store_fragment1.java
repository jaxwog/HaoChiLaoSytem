package com.example.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.haochilao.R;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class body_store_fragment1 extends Fragment {
	private ListView listview1 = null;
	private View view;
	private Context context;
	private String data[] = { "饭食类", "面条类", "面点类" };
	private List<Map<String, String>> storelist = new ArrayList<Map<String, String>>();
	private SimpleAdapter simpleAdapter1 = null;
	private FragmentManager fm = null;
	private FragmentTransaction ft = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.body_store__fragment1, null);
		context = getActivity().getApplicationContext();
		this.listview1 = (ListView) view.findViewById(R.id.listview1);

		for (int i = 0; i < data.length; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("text", data[i]);
			this.storelist.add(map);
		}
		this.simpleAdapter1 = new SimpleAdapter(context, this.storelist,
				R.layout.list_data, new String[] { "text" },
				new int[] { R.id.text });

		this.listview1.setAdapter(this.simpleAdapter1);

		fm = getFragmentManager();
		ft = fm.beginTransaction();
		ft.replace(R.id.content, new body_store_fragment2_1());
		ft.commit();
		this.listview1.setOnItemClickListener(new OnItemClickListenerlist());
		return view;
	}

	class OnItemClickListenerlist implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {

			Map<String, String> map = (Map<String, String>) body_store_fragment1.this.simpleAdapter1
					.getItem(position);
			String text = map.get("text");
			if (text.equals(body_store_fragment1.this.data[0])) { 
				fm = getFragmentManager();
				ft = fm.beginTransaction();
				ft.replace(R.id.content, new body_store_fragment2_1());
				ft.commit();
			} else if (text.equals(body_store_fragment1.this.data[1])) {
				fm = getFragmentManager();
				ft = fm.beginTransaction();
				ft.replace(R.id.content, new body_store_fragment2_2());
				ft.commit();
			} else if (text.equals(body_store_fragment1.this.data[2])) {
				fm = getFragmentManager();
				ft = fm.beginTransaction();
				ft.replace(R.id.content, new body_store_fragment2_3());
				ft.commit();
			}
		}

	}
}

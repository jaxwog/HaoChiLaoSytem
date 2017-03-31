package com.example.haochilao;

import com.example.haochilao.R;
import com.quanju.MyApp;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private RadioButton ra_weixin_bt, ra_tongxunlu_bt, ra_wo_bt;
	private Fragment talkFragment, addressFragment;
	private Fragment meFragment;
	FragmentManager fgManager;
	private int i = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		fgManager = getFragmentManager();
		init();
		ra_weixin_bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				talkFragment = new StoreFragment();
				changeFrament(talkFragment, null, "talkFragment");
				changeRadioButtonImage(v.getId());
			}
		});
		ra_tongxunlu_bt.setOnClickListener(new OnClickListener()

		{

			@Override//确定是否登录
			public void onClick(View v) {
				if (MyApp.b == null) {
					Toast.makeText(MainActivity.this, "还没有登录",
							Toast.LENGTH_SHORT).show();
				} else {
					addressFragment = new AddressFragment();
					changeFrament(addressFragment, null, "addressFragment");
					changeRadioButtonImage(v.getId());
				}
			}
		});

		ra_wo_bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				meFragment = new MeFragment();
				changeFrament(meFragment, null, "meFragment");
				changeRadioButtonImage(v.getId());
			}
		});

	}

	// 初始化信息
	public void init() {
		ra_weixin_bt = (RadioButton) findViewById(R.id.ra_weixin_bt);
		ra_tongxunlu_bt = (RadioButton) findViewById(R.id.ra_tongxunlu_bt);
		ra_wo_bt = (RadioButton) findViewById(R.id.ra_wo_bt);
		talkFragment = new StoreFragment();
		addressFragment = new AddressFragment();

		meFragment = new MeFragment();
		changeFrament(talkFragment, null, "talkFragment");
		// 更换 android:drawableTop图pain资源
		ra_weixin_bt.setCompoundDrawablesWithIntrinsicBounds(0,
				R.drawable.n_find_l, 0, 0);
	}

	// 切界面
	public void changeFrament(Fragment fragment, Bundle bundle, String tag) {

		for (int i = 0, count = fgManager.getBackStackEntryCount(); i < count; i++) {
			fgManager.popBackStack();
		}
		FragmentTransaction fg = fgManager.beginTransaction();
		fragment.setArguments(bundle);
		fg.add(R.id.fragmentRoot, fragment, tag);
		fg.addToBackStack(tag);
		fg.commit();

	}

	// 更换RadioButton图片
	public void changeRadioButtonImage(int btids) {
		int[] imageh = { R.drawable.n_address_h, R.drawable.n_find_h,
				R.drawable.n_me_h };
		int[] imagel = { R.drawable.n_address_l, R.drawable.n_find_l,
				R.drawable.n_me_l };
		int[] rabt = { R.id.ra_tongxunlu_bt, R.id.ra_weixin_bt, R.id.ra_wo_bt };
		switch (btids) {
		case R.id.ra_tongxunlu_bt:
			changeImage(imageh, imagel, rabt, 0);
			break;
		case R.id.ra_weixin_bt:
			changeImage(imageh, imagel, rabt, 1);
			break;
		case R.id.ra_wo_bt:
			changeImage(imageh, imagel, rabt, 2);
			break;

		default:
			break;
		}
	}

	@Override//退出程序
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (i == 0) {
				Toast.makeText(this, "再点击一次将退出程序", Toast.LENGTH_SHORT).show();
				i++;
			} else {
				this.finish();
			}
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	public void changeImage(int[] image1, int[] image2, int[] rabtid, int index) {
		for (int i = 0; i < image1.length; i++) {
			if (i != index) {
				((RadioButton) findViewById(rabtid[i]))
						.setCompoundDrawablesWithIntrinsicBounds(0, image1[i],
								0, 0);
			} else {
				((RadioButton) findViewById(rabtid[i]))
						.setCompoundDrawablesWithIntrinsicBounds(0, image2[i],
								0, 0);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if

		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

package com.example.gerenguanli;

import com.example.haochilao.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutActivity extends Activity {
	private ImageView button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		button = (ImageView) findViewById(R.id.button2);
		button.setOnClickListener(new OnClickListenera());
	}

	private final class OnClickListenera implements View.OnClickListener {

		@Override
		public void onClick(View v) {
				AboutActivity.this.finish();

		}
	}
}

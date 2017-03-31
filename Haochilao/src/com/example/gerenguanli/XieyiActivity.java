package com.example.gerenguanli;



import com.example.haochilao.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class XieyiActivity extends Activity {
	
	private ImageView button;
 @Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.xieyi);
	button=(ImageView)findViewById(R.id.back_image_button);
	 button.setOnClickListener(new OnClickListenera());
}
 private final class OnClickListenera implements View.OnClickListener{

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent1=new Intent(XieyiActivity.this,AboutActivity.class);
		 startActivity(intent1);
	}
 }
}

package com.example.gerenguanli;
import com.example.haochilao.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class UpdateActivity extends Activity {
	
	private ImageView button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.update);
    	
    	button=(ImageView)findViewById(R.id.back_image_button);
    	 button.setOnClickListener(new OnClickListeneri());
    }
    private class OnClickListeneri implements OnClickListener{

		@Override
		public void onClick(View v) {
			UpdateActivity.this.finish();
		}
		
	}
}

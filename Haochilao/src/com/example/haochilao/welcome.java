
package com.example.haochilao;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;


public class welcome extends Activity {
    
    @Override//欢迎引导界面
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view = View.inflate(this, R.layout.welcome, null);
		setContentView(view);	
		AlphaAnimation ff = new AlphaAnimation(0.3f,1.0f);
		ff.setDuration(2000);
		view.startAnimation(ff);
		ff.setAnimationListener(new AnimationListener()
		{
			//@Override
			public void onAnimationEnd(Animation arg0) {
				redirectTo();
			}
			//@Override
			public void onAnimationRepeat(Animation animation) {}
			//@Override
			public void onAnimationStart(Animation animation) {}
			
		});
		
    }
 
    private void redirectTo(){        
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
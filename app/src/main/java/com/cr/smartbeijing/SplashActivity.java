package com.cr.smartbeijing;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;
import com.cr.smartbeijing.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;


public class SplashActivity extends Activity {
	@ViewInject(R.id.rl_splash)
	private RelativeLayout rl_splash;
	private SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sp = getSharedPreferences("isGuide",Activity.MODE_PRIVATE);
		InitUi();
	}
	private void InitUi() {
		setContentView(R.layout.activity_splash);
		ViewUtils.inject(this);
		AnimationSet set = new AnimationSet(false);
		RotateAnimation  anim = new RotateAnimation(0f,360f,Animation.RELATIVE_TO_SELF, 
				0.5f,Animation.RELATIVE_TO_SELF,0.5f); 
		anim.setDuration(1000);
		anim.setFillAfter(true);
		ScaleAnimation scale = new ScaleAnimation(0.0f, 1f, 0.0f, 1f, 
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f); 
		scale.setDuration(1000);
		scale.setFillAfter(true);
		AlphaAnimation alpha = new AlphaAnimation(0, 1);
		alpha.setDuration(1000);
		alpha.setFillAfter(true);
		set.addAnimation(anim);
		set.addAnimation(scale);
		set.addAnimation(alpha);
		
		set.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				if (sp.getInt("isFirst", 0) == 0) {
					startActivity(new Intent(SplashActivity.this, GuideActivity.class));

					finish();
				} else {
					startActivity(new Intent(SplashActivity.this, MainActivity.class));

					finish();
				}
			}
		});
		rl_splash.startAnimation(set);
	}
}

package com.cr.smartbeijing;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

import com.cr.smartbeijing.Fragement.Content_Fragment;
import com.cr.smartbeijing.Fragement.Menu_Fragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
//Demo12
public class MainActivity extends SlidingFragmentActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initUi();
	}

	private void initUi() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		setBehindContentView(R.layout.menu_left);
		SlidingMenu slidingMenu = getSlidingMenu();// 获取侧边栏对象
	//	slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);// 设置全屏触摸
	//	slidingMenu.setSecondaryMenu(R.layout.menu_right);// 设置右侧边栏
	//	slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);// 设置展现模式
		slidingMenu.setBehindOffset(400);// 设置预留屏幕的宽度
		initFragment();
	}
	private void initFragment() {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		FragmentTransaction menu_fragment = transaction.replace(R.id.menu_left,
				new Menu_Fragment(), "menu_fragment");
		FragmentTransaction content_fragment = transaction.replace(R.id.content_view,
				new Content_Fragment(), "content_fragment");
		transaction.commit();
	}
	// 获取侧边栏fragment
	public Menu_Fragment getLeftMenuFragment() {
		FragmentManager fm = getSupportFragmentManager();
		Menu_Fragment fragment = (Menu_Fragment) fm
				.findFragmentByTag("menu_fragment");

		return fragment;
	}

	// 获取主页面fragment
	public Content_Fragment getContentFragment() {
		FragmentManager fm = getSupportFragmentManager();
		Content_Fragment fragment = (Content_Fragment) fm
				.findFragmentByTag("content_fragment");

		return fragment;
	}
}

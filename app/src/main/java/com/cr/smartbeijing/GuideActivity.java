package com.cr.smartbeijing;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.cr.smartbeijing.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GuideActivity extends Activity {
	@ViewInject(R.id.vp_guide)
	private ViewPager vpGuide;
	@ViewInject(R.id.ll_point_group)
	private LinearLayout ll_point_group;
	@ViewInject(R.id.enter_home)
	private Button Enter_home;
	private static final int[] mImageIds = new int[] { R.drawable.guide_1,
			R.drawable.guide_2, R.drawable.guide_3 };
	private ArrayList<ImageView> mImageViewList;
	private ArrayList<View> mPointList;
	private SharedPreferences sp;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initUi();
        initData();
	}

    private void initData() {
		sp = getSharedPreferences("isGuide",Activity.MODE_PRIVATE);
		sp.edit().putInt("isFirst",1).commit();
        vpGuide.setAdapter(new GuideAdapter());
		GuideOnPageListener listener = new GuideOnPageListener();
		vpGuide.setOnPageChangeListener(listener);
		Enter_home.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(GuideActivity.this,MainActivity.class));
				finish();
			}
		});
    }

    private void initUi() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题
		setContentView(R.layout.activity_guide);
		ViewUtils.inject(this);
		initViews();

	}

	/**
	 * 初始化界面
	 */
	private void initViews() {
		mImageViewList = new ArrayList<ImageView>();
		mPointList = new ArrayList<View>();

		// 初始化引导页的3个页面
		for (int i = 0; i < mImageIds.length; i++) {
			ImageView image = new ImageView(this);
			image.setBackgroundResource(mImageIds[i]);// 设置引导页背景
			mImageViewList.add(image);
		}
		//初始化小圆点
		for (int i = 0; i < mImageIds.length; i++) {
			View point = new View(this);
			point.setBackgroundResource(R.drawable.shape_point_gray);// 设置引导页背景
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20,20);

			if(i>0){
				params.leftMargin = 20;
			}
			point.setLayoutParams(params);
			ll_point_group.addView(point);
		}
	}
		/**
		 * ViewPager数据适配器
		 *
		 * @author Kevin
		 *
		 */
		class GuideAdapter extends PagerAdapter {

			@Override
			public int getCount() {
				return mImageIds.length;
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				container.addView(mImageViewList.get(position));
				return mImageViewList.get(position);
			}

			@Override
			public void destroyItem(ViewGroup container, int position, Object object) {
				container.removeView((View) object);
			}
		}
	class GuideOnPageListener implements ViewPager.OnPageChangeListener{

		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

		}

		@Override
		public void onPageSelected(int position) {
			if(position == mImageIds.length-1){//显示开始体验的按钮
				Enter_home.setVisibility(View.VISIBLE);
			}else
				Enter_home.setVisibility(View.INVISIBLE);

		}

		@Override
		public void onPageScrollStateChanged(int state) {

		}
	}
}

package com.cr.smartbeijing.Base.menudetail;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cr.smartbeijing.R;
import com.cr.smartbeijing.Base.BaseMenuDetailPager;
import com.cr.smartbeijing.Base.TabDetailPager;
import com.cr.smartbeijing.Domain.NewsData.NewsTabData;

/**
 * 菜单详情页-新闻
 * 
 * @author demo
 * 
 */
public class NewsMenuDetailPager extends BaseMenuDetailPager {

	public NewsMenuDetailPager(Activity activity) {

		super(activity);
	}

	@Override
	public View initViews() {
		TextView tv_content = new TextView(mActivity);
		tv_content.setText("设置");
		tv_content.setTextSize(25);
		tv_content.setTextColor(Color.RED);
		tv_content.setGravity(Gravity.CENTER);
		return tv_content;
	}
}

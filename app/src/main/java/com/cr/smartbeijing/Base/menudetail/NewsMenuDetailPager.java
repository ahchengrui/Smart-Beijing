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

	private ArrayList<TabDetailPager> mPageList;
	private ArrayList<NewsTabData> mNewsData;
	private ViewPager mViewPager;
	public NewsMenuDetailPager(Activity activity,ArrayList<NewsTabData> children) {
		super(activity);
		mNewsData = children;
	}

	@Override
	public View initViews() {
		View view = View.inflate(mActivity,R.layout.news_menu_detail,null);
		mViewPager = (ViewPager) view.findViewById(R.id.vp_menu_detail);
		return view;
	}

	@Override
	public void initData() {
		mPageList = new ArrayList<TabDetailPager>();
		for(int i= 0;i< mNewsData.size();i++){
			TabDetailPager pager = new TabDetailPager(mActivity,mNewsData.get(i));
			mPageList.add(pager);
		}
		mViewPager.setAdapter(new MenuDetailAdapter());
	}
	public class MenuDetailAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return mPageList.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public void destroyItem(ViewGroup view, int position, Object object) {
			view.removeView((View)object);
		}

		@Override
		public Object instantiateItem(ViewGroup view, int position) {
			TabDetailPager pager = mPageList.get(position);
			view.addView(pager.mRootView);
			pager.initData();
			return pager.mRootView ;
		}
	}


}
package com.cr.smartbeijing.Base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cr.smartbeijing.Base.BaseMenuDetailPager;
import com.cr.smartbeijing.Base.menudetail.InteractMenuDetailPager;
import com.cr.smartbeijing.Base.menudetail.NewsMenuDetailPager;
import com.cr.smartbeijing.Base.menudetail.PhotoMenuDetailPager;
import com.cr.smartbeijing.Base.menudetail.TopicMenuDetailPager;
import com.cr.smartbeijing.Domain.NewsData;
import com.cr.smartbeijing.Fragement.Menu_Fragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.cr.smartbeijing.Base.BasePager;
import com.cr.smartbeijing.Global.GlobalContants;
import com.cr.smartbeijing.MainActivity;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;
import java.util.Objects;

/**
 * ===========================================
 * 版权所有CEIT
 * 作者：程瑞 on 15-11-8 11:03
 * 邮箱：ahchengrui@126.com
 * ==========================================
 */
public class NewsCenterPager extends BasePager {
    private ArrayList<BaseMenuDetailPager> mPagers;// 4个菜单详情页的集合

    ArrayList<BaseMenuDetailPager> pagers;
    private NewsData newsData;

    public NewsCenterPager(Activity activity) {
        super(activity);
    }
    @Override
    public void initData(){
       tv_title.setText("新闻中心");

       /*  TextView tv_content = new TextView(mActivity);
        tv_content.setText("新闻中心");
        tv_content.setTextSize(25);
        tv_content.setTextColor(Color.RED);
        tv_content.setGravity(Gravity.CENTER);
        fl_content.addView(tv_content);
        Log.e("chengrui","获取数据");*/
        setSlidingMenuEnable(true);
        getDataFromServer();
    }
    /**
     * 从服务器获取数据
     */

    private void getDataFromServer() {
        Log.e("chengrui","获取数据");
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, GlobalContants.CATEGORIES_URL, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = (String) responseInfo.result;
                Log.e("chengrui", result);
                parseData(result);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(mActivity, s, 0).show();
            }
        });
    }

    private void parseData(String result) {
        Gson gson = new Gson();

        newsData = gson.fromJson(result, NewsData.class);
        Log.e("chengrui", newsData.toString());
        MainActivity mainUi = (MainActivity)mActivity;
        Menu_Fragment leftMenuFragment = mainUi.getLeftMenuFragment();
        leftMenuFragment.setMenuData(newsData);

        pagers = new ArrayList<BaseMenuDetailPager>();
        pagers.add(new NewsMenuDetailPager(mActivity,newsData.data.get(0).children));
        pagers.add(new TopicMenuDetailPager(mActivity));
        pagers.add(new PhotoMenuDetailPager(mActivity));
        pagers.add(new InteractMenuDetailPager(mActivity));

        setCurrentMenuDetailPager(0);


    }
    /*
    * 设置当前菜单详情页
    * */
    public void setCurrentMenuDetailPager(int position){
        BaseMenuDetailPager pager = pagers.get(position);
        fl_content.removeAllViews();
        fl_content.addView(pager.mRootView);

        NewsData.NewsMenuData menuData = newsData.data.get(position);
        tv_title.setText(menuData.title);
        pager.initData();

    }

}
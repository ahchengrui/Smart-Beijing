package com.cr.smartbeijing.Base.impl;

import android.app.Activity;
import android.graphics.Color;
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

/**
 * ===========================================
 * 版权所有CEIT
 * 作者：程瑞 on 15-11-8 11:03
 * 邮箱：ahchengrui@126.com
 * ==========================================
 */
public class NewsCenterPager extends BasePager {
    private ArrayList<BaseMenuDetailPager> mPagers;// 4个菜单详情页的集合
    private NewsData mNewsData;
    public NewsCenterPager(Activity activity) {
        super(activity);
    }
    @Override
    public void initData(){
        tv_title.setText("新闻中心");
        setSlidingMenuEnable(true);
        TextView tv_content = new TextView(mActivity);
        tv_content.setText("新闻中心");
        tv_content.setTextSize(25);
        tv_content.setTextColor(Color.RED);
        tv_content.setGravity(Gravity.CENTER);
        fl_content.addView(tv_content);
        getDataFromServer();
    }

    /**
     * 从服务器获取数据
     */
    private void getDataFromServer() {
        HttpUtils utils = new HttpUtils();

        // 使用xutils发送请求
        utils.send(HttpRequest.HttpMethod.GET, GlobalContants.CATEGORIES_URL,
                new RequestCallBack<String>() {
                    // 访问成功
                    @Override
                    public void onSuccess(ResponseInfo responseInfo) {
                        String result = (String) responseInfo.result;
                        Log.e("smart", result);
                        parseData(result);
                    }

                    // 访问失败
                    @Override
                    public void onFailure(HttpException error, String msg) {
                        Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT)
                                .show();
                        Log.e("smart", msg);
                        error.printStackTrace();
                    }
                });
    }

    private void parseData(String result) {
        Gson gson = new Gson();
        NewsData data= gson.fromJson(result, NewsData.class);
        //刷新侧边栏的数据
        MainActivity mainUi = (MainActivity)mActivity;
        Menu_Fragment fragment = mainUi.getLeftMenuFragment();
        fragment.setMenuData(data);
        //准备四个菜单详情页
        mPagers = new ArrayList<BaseMenuDetailPager>();
        mPagers.add(new NewsMenuDetailPager(mActivity));
        mPagers.add(new TopicMenuDetailPager(mActivity));
        mPagers.add(new PhotoMenuDetailPager(mActivity));
        mPagers.add(new InteractMenuDetailPager(mActivity));
    }
    //设置当前菜单详情页
    public void setCurrentMenuDetailPager(int position){
        BaseMenuDetailPager pager = mPagers.get(position);
        fl_content.removeAllViews();
        fl_content.addView(pager.mRootView);
    }
}
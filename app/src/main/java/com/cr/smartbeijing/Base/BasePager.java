package com.cr.smartbeijing.Base;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cr.smartbeijing.MainActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.cr.smartbeijing.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * ===========================================
 * 版权所有CEIT
 * 作者：程瑞 on 15-11-8 10:40
 * 邮箱：ahchengrui@126.com
 * ==========================================
 */
public abstract  class BasePager {
    public  Activity mActivity;
    public  View mRootView;

    public TextView tv_title;
    public ImageButton ibtn_menu;
    public FrameLayout fl_content;

    public BasePager(Activity activity) {
        mActivity = activity;
        initUi();
        initData();
    }
    public void initUi(){
        mRootView = View.inflate(mActivity, R.layout.basepager,null);
        tv_title = (TextView) mRootView.findViewById(R.id.tv_title);
        fl_content = (FrameLayout) mRootView.findViewById(R.id.fl_content);
        ibtn_menu = (ImageButton) mRootView.findViewById(R.id.ibtn_menu);
        ibtn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleSlidingMenu();
            }
        });
    }
    protected void toggleSlidingMenu() {
        MainActivity mainUi = (MainActivity) mActivity;
        SlidingMenu slidingMenu = mainUi.getSlidingMenu();
        slidingMenu.toggle();// 切换状态, 显示时隐藏, 隐藏时显示
    }
    public abstract void initData();
    public void setSlidingMenuEnable(boolean enable) {
        MainActivity mainUi = (MainActivity) mActivity;

        SlidingMenu slidingMenu = mainUi.getSlidingMenu();

        if (enable) {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        } else {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }
    }
}

package com.cr.smartbeijing.Base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.cr.smartbeijing.Base.BasePager;

/**
 * ===========================================
 * 版权所有CEIT
 * 作者：程瑞 on 15-11-8 11:03
 * 邮箱：ahchengrui@126.com
 * ==========================================
 */
public class SettingPager extends BasePager {
    public SettingPager(Activity activity) {
        super(activity);
    }
    @Override
    public void initData(){
        ibtn_menu.setVisibility(View.INVISIBLE);
        setSlidingMenuEnable(false);
        tv_title.setText("设置");
        TextView tv_content = new TextView(mActivity);
        tv_content.setText("设置");
        tv_content.setTextSize(25);
        tv_content.setTextColor(Color.RED);
        tv_content.setGravity(Gravity.CENTER);
        fl_content.addView(tv_content);

    }

}

package com.cr.smartbeijing.Fragement;

import android.view.View;
import android.widget.RadioGroup;

import com.cr.smartbeijing.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * Created by Demo on 2015/11/7.
 */
public class Content_Fragment extends BaseFragement{
    @ViewInject(R.id.rg_group)
    private RadioGroup rg_group;
    @Override
    public View initUi() {

        View view = View.inflate(mActivity, R.layout.content_fragment,null);
        ViewUtils.inject(this,view);
        return view;
    }
    public void initData(){
        rg_group.check(R.id.rb_home);
    }
}

package com.cr.smartbeijing.Fragement;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.cr.smartbeijing.Base.BasePager;
import com.cr.smartbeijing.Base.impl.GovAffairsPager;
import com.cr.smartbeijing.Base.impl.HomePager;
import com.cr.smartbeijing.Base.impl.NewsCenterPager;
import com.cr.smartbeijing.Base.impl.SettingPager;
import com.cr.smartbeijing.Base.impl.SmartServicePager;
import com.cr.smartbeijing.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by Demo on 2015/11/7.
 */
public class Content_Fragment extends BaseFragement{
    @ViewInject(R.id.rg_group)
    private RadioGroup rg_group;
    @ViewInject(R.id.vp_content)
    private ViewPager vp_content;
    private ArrayList<BasePager> array_Pager;
    @Override
    public View initUi() {
        View view = View.inflate(mActivity, R.layout.content_fragment,null);
        ViewUtils.inject(this, view);
        return view;
    }
    public void initData(){
        rg_group.check(R.id.rb_home);
        array_Pager = new ArrayList<BasePager>();
        array_Pager.add(new HomePager(mActivity));
        array_Pager.add(new NewsCenterPager(mActivity));
        array_Pager.add(new SmartServicePager(mActivity));
        array_Pager.add(new GovAffairsPager(mActivity));
        array_Pager.add(new SettingPager(mActivity));
        vp_content.setAdapter(new ContentAdapter());
        rg_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        vp_content.setCurrentItem(0, false);// 去掉切换页面的动画
                        break;
                    case R.id.rb_news:
                        vp_content.setCurrentItem(1, false);// 设置当前页面
                        break;
                    case R.id.rb_smart:
                        vp_content.setCurrentItem(2, false);// 设置当前页面
                        break;
                    case R.id.rb_gov:
                        vp_content.setCurrentItem(3, false);// 设置当前页面
                        break;
                    case R.id.rb_setting:
                        vp_content.setCurrentItem(4, false);// 设置当前页面
                        break;

                    default:
                        break;
                }
            }
        });
        vp_content.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                array_Pager.get(position).initData();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        array_Pager.get(0).initData();
    }

    class ContentAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return array_Pager.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager pager = array_Pager.get(position);
            container.addView(pager.mRootView);
            return pager.mRootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }
    public NewsCenterPager getNewsCenterPager() {
        return (NewsCenterPager) array_Pager.get(1);
    }
}

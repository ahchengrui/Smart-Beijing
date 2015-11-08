package com.cr.smartbeijing.Fragement;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cr.smartbeijing.Base.impl.NewsCenterPager;
import com.cr.smartbeijing.Domain.NewsData;
import com.cr.smartbeijing.MainActivity;
import com.cr.smartbeijing.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by Demo on 2015/11/7.
 */
public class Menu_Fragment extends BaseFragement{
    @ViewInject(R.id.lv_list)
    private ListView lvList;
    private ArrayList<NewsData.NewsMenuData> mMenuList;

    private int mCurrentPos;// 当前被点击的菜单项
    private MenuAdapter mAdapter;
    @Override
    public View initUi() {
        View view = View.inflate(mActivity, R.layout.menu_fragment,null);
        ViewUtils.inject(this, view);
        return view;
    }
    @Override
    public void initData() {
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCurrentPos = position;
                mAdapter.notifyDataSetChanged();
                setCurrentMenuDetailPager(position);
            }
        });
    }

    private void setCurrentMenuDetailPager(int position) {
        MainActivity mainUi = (MainActivity) mActivity;
        Content_Fragment fragment = mainUi.getContentFragment();
        NewsCenterPager pager = fragment.getNewsCenterPager();
        pager.setCurrentMenuDetailPager(position);
    }

    public void setMenuData(NewsData data ){

        mMenuList = data.data;
        mAdapter = new MenuAdapter();
        lvList.setAdapter(new MenuAdapter());


    }

    /**
     * 侧边栏数据适配器
     *
     * @author Kevin
     *
     */
    class MenuAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mMenuList.size();
        }

        @Override
        public NewsData.NewsMenuData getItem(int position) {
            return mMenuList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(mActivity, R.layout.list_menu_item, null);
            TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
            NewsData.NewsMenuData newsMenuData = getItem(position);
            tvTitle.setText(newsMenuData.title);
            if(mCurrentPos == position){
                Log.e("smart","mCurrentPos="+mCurrentPos);
                tvTitle.setEnabled(true);
            }else{
                tvTitle.setEnabled(false);
            }
            return view;
        }

    }

}

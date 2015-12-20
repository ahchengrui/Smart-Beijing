package com.cr.smartbeijing.Fragement;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
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
import com.cr.smartbeijing.domain.NewData;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import static android.graphics.Color.BLUE;
import static android.graphics.Color.GRAY;

/**
 * Created by Demo on 2015/11/7.
 */
public class Menu_Fragment extends BaseFragement{
    NewsData data;
    ArrayList<NewsData.NewsMenuData> menudata;
    @ViewInject(R.id.lv_list)
    public ListView lv_list;
    private int myCurrentPos;
    MenuAdapter adapter;

    @Override
    public View initUi() {
        View view = View.inflate(mActivity,R.layout.menu_fragment,null);
        ViewUtils.inject(this, view);

        return view;
    }
    @Override
    public void initData(){
        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                myCurrentPos = position;

                adapter.notifyDataSetChanged();
                setCurrentMenuDetailPager(position);
                toggleSlideMenu();//隐藏侧边栏
            }
        });

    }

    private void toggleSlideMenu() {
        MainActivity mainUi = (MainActivity)mActivity;
        SlidingMenu slidingMenu = mainUi.getSlidingMenu();
        slidingMenu.toggle();
    }

    private void setCurrentMenuDetailPager(int position) {
        MainActivity mainUi = (MainActivity)mActivity;
        Content_Fragment fragment = mainUi.getContentFragment();//获取主页面fragment
        NewsCenterPager centerPager = fragment.getNewsCenterPager();//获取新闻中心pager
        centerPager.setCurrentMenuDetailPager(position);
    }

    public void setMenuData(NewsData data){//通过mainactivity得到数据
        System.out.println(data.toString());
        menudata = data.data;
        adapter = new MenuAdapter();
        lv_list.setAdapter(adapter);

    }
    class MenuAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return menudata.size();
        }

        @Override
        public NewsData.NewsMenuData getItem(int position) {
            return menudata.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(mActivity,R.layout.list_menu_item,null);
            TextView tv_title =(TextView)view.findViewById(R.id.tv_title);
            NewsData.NewsMenuData data = getItem(position);
            tv_title.setText(data.title);
            if(myCurrentPos == position){
                tv_title.setEnabled(true);
            }else
                tv_title.setEnabled(false);
            return view;
        }
    };
}

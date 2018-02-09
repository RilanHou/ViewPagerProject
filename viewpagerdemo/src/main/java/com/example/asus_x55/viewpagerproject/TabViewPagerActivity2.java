package com.example.asus_x55.viewpagerproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Fragment配合ViewPager
 * Created by ASUS-X55 on 2018/1/11.
 */

public class TabViewPagerActivity2 extends AppCompatActivity implements TabHost.TabContentFactory{

    private TabHost mTabHost;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_tab_viewpager2);

        //初始化总布局
        mTabHost = findViewById (R.id.tab_host);
        mTabHost.setup ();

        //对三个Tab 做处理
        //1. init data
        int[] titleIDs = {
                R.string.home,
                R.string.message,
                R.string.me
        };
        int[] drawableIDs = {
                R.drawable.main_tab_icon_home,
                R.drawable.main_tab_icon_msg,
                R.drawable.main_tab_icon_me
        };

        // data --> view （将数据填充到布局里形成视图）
        for (int i = 0; i < titleIDs.length; i++) {
            View view = getLayoutInflater ().inflate (R.layout.main_tab_layout, null, false);

            //获取控件对象、视图对象
            ImageView icon = view.findViewById (R.id.main_tab_icon);
            TextView title = view.findViewById (R.id.main_tab_text);
            View tab = view.findViewById (R.id.tab_bg);

            //将数据（图片、文本）填充到控件上
            icon.setImageResource (drawableIDs[i]);
            title.setText (titleIDs[i]);

            //为视图（View tab）设置背景色
            tab.setBackgroundColor (getResources ().getColor (R.color.white));

            //添加选项卡
            mTabHost.addTab (mTabHost.newTabSpec (getString (titleIDs[i]))
            .setIndicator (view)
            .setContent (this)
            );
        }

        //三个fragment组成的viewpager


        final Fragment[] fragments = new Fragment[]{
                TestFragment2.newInstance2 ("home"),
                TestFragment2.newInstance2 ("massage"),
                TestFragment2.newInstance2 ("me")
        };

        final ViewPager viewPager = findViewById (R.id.view_pager2);

        viewPager.setAdapter (new FragmentPagerAdapter (getSupportFragmentManager ()) {
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }
        });

        viewPager.setOnPageChangeListener (new ViewPager.OnPageChangeListener () {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //当用户滑动界面时要选一个tab
            @Override
            public void onPageSelected(int position) {
                if (mTabHost != null) {
                    mTabHost.setCurrentTab (position); //设置当前选项卡
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //当tab的状态为“动”的时候，viewpager的状态也为“动”
        mTabHost.setOnTabChangedListener (new TabHost.OnTabChangeListener () {
            @Override
            public void onTabChanged(String tabId) {
                if (mTabHost != null) {
                    int position = mTabHost.getCurrentTab (); //获取当前tab的位置
                    viewPager.setCurrentItem (position); //设置当前选中的页面
                }

            }
        });
    }

    @Override
    public View createTabContent(String tag) {
        View view = new View (this);
        view.setMinimumHeight (0);
        view.setMinimumWidth (0);
        return view;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate (R.menu.main_menu,menu);
        return true; //返回true，允许菜单显示出来
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId ()) {
            case R.id.add_item:
                Toast.makeText (TabViewPagerActivity2.this,"Add",Toast.LENGTH_SHORT).show ();
                break;
            case R.id.add_remove:
                Toast.makeText (TabViewPagerActivity2.this,"Remove",Toast.LENGTH_SHORT).show ();
                break;
        }
        return super.onOptionsItemSelected (item);
    }
}

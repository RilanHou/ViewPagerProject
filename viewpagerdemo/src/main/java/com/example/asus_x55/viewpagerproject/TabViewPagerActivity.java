package com.example.asus_x55.viewpagerproject;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Fragment配合ViewPager
 * Created by ASUS-X55 on 2018/1/11.
 */

public class TabViewPagerActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_tab_viewpager);

        ViewPager viewPager = findViewById (R.id.view_pager1);

        viewPager.setAdapter (new FragmentPagerAdapter (getSupportFragmentManager ()) {
            @Override
            public Fragment getItem(int position) {
                return TestFragment.newInstance (position);
            }

            @Override
            public int getCount() {
                return 4;
            }
        });
    }

//    @Override
//    public int getCount() {
//        return 0;
//    }
//
//    @Override
//    public boolean isViewFromObject(View view, Object object) {
//        return false;
//    }
}

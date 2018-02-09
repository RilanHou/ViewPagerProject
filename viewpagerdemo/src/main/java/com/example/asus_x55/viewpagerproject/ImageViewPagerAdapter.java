package com.example.asus_x55.viewpagerproject;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用ViewPager实现APP引导页
 */

public class ImageViewPagerAdapter extends AppCompatActivity {

    public static final int INIT_POSTION = 0;
    private ViewPager viewPager;

    private int[] mLayoutIDs = {
            R.layout.view_first,
            R.layout.view_second,
            R.layout.view_thrid
    };
    private List<View> mViews;

    private ViewGroup mDotViewGroup;

    private List<ImageView> mDotViews = new ArrayList<> (); //存放“点”的集合


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_image_view_adapter);

        viewPager = findViewById (R.id.view_pager);
        mDotViewGroup = findViewById (R.id.dot_layout);

        //初始化数据
//        mViews = new ArrayList<> ();
//        for (int i = 0; i < mLayoutIDs.length; i++) {
//            final View view = getLayoutInflater ().inflate (mLayoutIDs[i], null);
//            mViews.add (view);
//        }

        //初始化数据
        mViews = new ArrayList<> ();
        for (int i = 0; i < mLayoutIDs.length; i++) {
            //设置页面显示的图片
            ImageView imageView = new ImageView (this);
            imageView.setImageResource (R.mipmap.ic_launcher);
            mViews.add (imageView);

            //设置滑动下标“点”
            ImageView dot = new ImageView (this); //生成“点”的对象
            dot.setImageResource (R.mipmap.ic_launcher); //设置“点”的图片
            dot.setMaxWidth (100); //限制“点”最大宽度 为100
            dot.setMaxHeight (100); //限制“点”最大高度 为100

            //为“点”指定宽高（将宽高封装成一个layoutParams对象）
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams (20, 20);
            layoutParams.leftMargin = 20; //每个“点”距离左边20dp
            dot.setLayoutParams (layoutParams); //将封装好的宽高对象设置在“点上”
            dot.setEnabled (false); //设置“点”不可用
            mDotViewGroup.addView (dot); //将“点”视图添加到控件ViewGroup上
            mDotViews.add (dot); //将处理好的“点”添加进专门存放“点”的集合mDotViews

        }


        //设置适配器
        viewPager.setAdapter (mPagerAdapter);

        //缓存视图（防止被销毁）
        viewPager.setOffscreenPageLimit (4);

        //设置当前选中的页面为第0页（从第几页开始都可以）
        viewPager.setCurrentItem (INIT_POSTION);
        //从第0页开始遍历（从第几页开始都可以）
        setDotViews(INIT_POSTION);

        viewPager.addOnPageChangeListener (new ViewPager.OnPageChangeListener () {
            //当页面开始滚动
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //当页面被完全选中（滚动状态 or 未滚动，但已被完全选中）
            @Override
            public void onPageSelected(int position) {
                setDotViews (position);
            }

            //页面状态 ：滚动 or 未滚动
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //当页面完全被选中时调用此方法
    private void setDotViews(int position) {
        for (int i = 0; i < mLayoutIDs.length; i++) {
            mDotViews.get (i).setImageResource (position == i ? R.mipmap.diglett : R.mipmap.ic_launcher);
        }
    }

    PagerAdapter mPagerAdapter = new PagerAdapter () {
        //数据总和
        @Override
        public int getCount() {
            return mLayoutIDs.length;
        }

        //判断instantiateItem方法返回的Object对象是否与isViewFromObject中的object对象一致
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object; //一致
        }

        //把每一次的视图添加进去
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View child = mViews.get (position); //拿到当前child视图
            container.addView (child); //添加视图
            return child; //将视图返回出去
        }

        //销毁视图（此方法可防止内存泄漏）
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView (mViews.get (position)); //销毁视图
        }
    };
}
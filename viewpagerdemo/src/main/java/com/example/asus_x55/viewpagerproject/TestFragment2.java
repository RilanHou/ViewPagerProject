package com.example.asus_x55.viewpagerproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Fragment
 * Created by ASUS-X55 on 2018/1/11.
 */

public class TestFragment2 extends Fragment {

    public static final String TITLE = "title";
    private String mTitle;

    public static TestFragment2 newInstance2(String title) {
        TestFragment2 testFragment = new TestFragment2 ();
        Bundle bundle = new Bundle ();
        bundle.putString (TITLE,title);
        testFragment.setArguments (bundle); //传递参数
        return testFragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        if (getArguments () != null) {
            mTitle = getArguments ().getString (TITLE); //获取参数
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate (R.layout.fragment_test,null);
        TextView textView = view.findViewById (R.id.text_view);
        textView.setText (mTitle);
        return view;
    }
}

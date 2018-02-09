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

public class TestFragment extends Fragment {

    public static final String POSITION = "position";
    private String mPosition;

    public static TestFragment newInstance(int position) {
        TestFragment testFragment = new TestFragment ();
        Bundle bundle = new Bundle ();
        bundle.putInt (POSITION,position);
        testFragment.setArguments (bundle); //传递参数
        return testFragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        if (getArguments () != null) {
            mPosition = String.valueOf (getArguments ().getInt (POSITION)); //获取参数
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate (R.layout.fragment_test,null);
        TextView textView = view.findViewById (R.id.text_view);
        textView.setText (mPosition);
        return view;
    }
}

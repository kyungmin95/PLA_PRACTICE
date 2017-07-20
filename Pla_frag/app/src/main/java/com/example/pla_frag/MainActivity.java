package com.example.pla_frag;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity {

    FragmentTabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabHost = (FragmentTabHost)findViewById(R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        //tabHost.addTab(tabHost.newTabSpec("month").setIndicator("",getResources().getDrawable(R.drawable.month)), DayFragment.class, null);
        //tabHost.addTab(tabHost.newTabSpec("week").setIndicator("",getResources().getDrawable(R.drawable.week)), DayFragment.class, null);
        //tabHost.addTab(tabHost.newTabSpec("day").setIndicator("",getResources().getDrawable(R.drawable.day)), DayFragment.class, null);
        //tabHost.addTab(tabHost.newTabSpec("memo").setIndicator("",getResources().getDrawable(R.drawable.memo)), DayFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("month").setIndicator(getTabIndicator(tabHost.getContext(), R.drawable.month)), FragmentOne.class, null);
        tabHost.addTab(tabHost.newTabSpec("week").setIndicator(getTabIndicator(tabHost.getContext(), R.drawable.week)), FragmentOne.class, null);
        tabHost.addTab(tabHost.newTabSpec("day").setIndicator(getTabIndicator(tabHost.getContext(), R.drawable.day)), DayFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("memo").setIndicator(getTabIndicator(tabHost.getContext(), R.drawable.memo)), FragmentOne.class, null);
    }

    private View getTabIndicator(Context context, int icon) {   //탭의 디자인을 지정하기 위해 탭 디자인 설정 layout과 연결시키는 함수
        View view = LayoutInflater.from(context).inflate(R.layout.tab_layout, null);
        ImageView iv = (ImageView) view.findViewById(R.id.imageView);
        iv.setImageResource(icon);
        return view;
    }
}
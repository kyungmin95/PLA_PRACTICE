package com.example.pla_frag;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

    FragmentTabHost tabHost;
    TabHost mTab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //tabHost = (FragmentTabHost)findViewById(R.id.tabhost);
        //tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        //tabHost.addTab(tabHost.newTabSpec("month").setIndicator(getTabIndicator(tabHost.getContext(), R.drawable.month)), FragmentOne.class, null);
        //tabHost.addTab(tabHost.newTabSpec("week").setIndicator(getTabIndicator(tabHost.getContext(), R.drawable.week)), FragmentOne.class, null);
        //tabHost.addTab(tabHost.newTabSpec("day").setIndicator(getTabIndicator(tabHost.getContext(), R.drawable.day)), DayActivity.class, null);
        //tabHost.addTab(tabHost.newTabSpec("memo").setIndicator(getTabIndicator(tabHost.getContext(), R.drawable.memo)), FragmentOne.class, null);
        TabHost mTab = getTabHost();
        LayoutInflater.from(this).inflate(R.layout.activity_main, mTab.getTabContentView(), true);

        mTab.addTab(mTab.newTabSpec("month").setIndicator(getTabIndicator(mTab.getContext(), R.drawable.month))
                .setContent(new Intent(this, FragmentOne.class)));
        mTab.addTab(mTab.newTabSpec("week").setIndicator(getTabIndicator(mTab.getContext(), R.drawable.week))
                .setContent(new Intent(this, FragmentOne.class)));
        mTab.addTab(mTab.newTabSpec("day").setIndicator(getTabIndicator(mTab.getContext(), R.drawable.day))
                .setContent(new Intent(this, DayActivity.class)));
        mTab.addTab(mTab.newTabSpec("memo").setIndicator(getTabIndicator(mTab.getContext(), R.drawable.memo))
                .setContent(new Intent(this, FragmentOne.class)));
    }

    private View getTabIndicator(Context context, int icon) {   //탭의 디자인을 지정하기 위해 탭 디자인 설정 layout과 연결시키는 함수
        View view = LayoutInflater.from(context).inflate(R.layout.tab_layout, null);
        ImageView iv = (ImageView) view.findViewById(R.id.imageView);
        iv.setImageResource(icon);
        return view;
    }
}
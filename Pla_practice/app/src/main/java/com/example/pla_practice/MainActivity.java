package com.example.pla_practice;

import android.app.TabActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;

public class MainActivity extends TabActivity {
    TabHost mTab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        TabHost mTab = getTabHost();
        TabHost.TabSpec spec;
        LayoutInflater.from(this).inflate(R.layout.activity_main, mTab.getTabContentView(), true);

        spec = mTab.newTabSpec("home").setIndicator("", getResources().getDrawable(R.drawable.home)).setContent(R.id.tv1);
        mTab.addTab(spec);
        spec = mTab.newTabSpec("month").setIndicator("", getResources().getDrawable(R.drawable.month)).setContent(R.id.tv1);
        mTab.addTab(spec);
        spec = mTab.newTabSpec("week").setIndicator("", getResources().getDrawable(R.drawable.week)).setContent(R.id.tv1);
        mTab.addTab(spec);
        spec = mTab.newTabSpec("day").setIndicator("", getResources().getDrawable(R.drawable.day)).setContent(R.id.tv1);
        mTab.addTab(spec);
        spec = mTab.newTabSpec("memo").setIndicator("", getResources().getDrawable(R.drawable.memo)).setContent(R.id.tv1);
        mTab.addTab(spec);
        spec = mTab.newTabSpec("pen").setIndicator("", getResources().getDrawable(R.drawable.pen)).setContent(R.id.tv1);
        mTab.addTab(spec);
        //for(int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
        //    tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("7FA3CF"));
       // }
    }
}
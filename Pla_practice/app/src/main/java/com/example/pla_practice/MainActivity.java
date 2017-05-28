package com.example.pla_practice;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBarActivity;

public class MainActivity extends ActionBarActivity {

    FragmentTabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabHost = (FragmentTabHost)findViewById(R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        tabHost.addTab(tabHost.newTabSpec("home").setIndicator("",getResources().getDrawable(R.drawable.home)), FragmentOne.class, null);
        tabHost.addTab(tabHost.newTabSpec("month").setIndicator("",getResources().getDrawable(R.drawable.month)), FragmentOne.class, null);
        tabHost.addTab(tabHost.newTabSpec("week").setIndicator("",getResources().getDrawable(R.drawable.week)), FragmentOne.class, null);
        tabHost.addTab(tabHost.newTabSpec("day").setIndicator("",getResources().getDrawable(R.drawable.day)), FragmentOne.class, null);
        tabHost.addTab(tabHost.newTabSpec("memo").setIndicator("",getResources().getDrawable(R.drawable.memo)), FragmentOne.class, null);
        tabHost.addTab(tabHost.newTabSpec("pen").setIndicator("",getResources().getDrawable(R.drawable.pen)), FragmentOne.class, null);

        //for(int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
        //    tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("7FA3CF"));
       // }
    }
}
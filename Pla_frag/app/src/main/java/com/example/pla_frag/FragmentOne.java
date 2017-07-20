package com.example.pla_frag;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 김경민 on 2017-06-17.
 */

public class FragmentOne extends Activity {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        return inflater.inflate(R.layout.fragment_layout, container, false);
    }
    //@Override
    //public void onViewCreated(View view, Bundle saveInstanceState) {
        //super.onViewCreated(view, saveInstanceState);
        //TextView tv = (TextView)view.findViewById(R.id.textView1);
        //tv.setText("Hello");
    //}
}

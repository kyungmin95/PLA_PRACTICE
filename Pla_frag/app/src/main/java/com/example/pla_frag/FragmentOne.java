package com.example.pla_frag;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by 김경민 on 2017-06-17.
 */

public class FragmentOne extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        return inflater.inflate(R.layout.fragment_layout, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);
        TextView tv = (TextView)view.findViewById(R.id.textView1);
        tv.setText("Hello");
    }
}

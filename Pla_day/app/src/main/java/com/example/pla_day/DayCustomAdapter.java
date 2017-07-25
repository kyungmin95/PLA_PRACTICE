package com.example.pla_day;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class DayCustomAdapter extends BaseAdapter {
    Context con;
    LayoutInflater inflater;
    ArrayList<DayListView> AL;
    int layout;

    public DayCustomAdapter(Context context, int alayout, ArrayList<DayListView> aAL) { //생성자 구현
        con = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        AL = aAL;
        layout = alayout;
    }

    @Override
    public int getCount() {
        return AL.size();
    }
    @Override
    public Object getItem(int position) {
        return AL.get(position).content;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    //각 항목의 뷰 생성
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = inflater.inflate(layout, parent, false );
        }
        ImageView img = (ImageView)convertView.findViewById(R.id.daytodo_cir);
        img.setImageResource(AL.get(position).icon);
        TextView txtv = (TextView)convertView.findViewById(R.id.daytodo_cont);
        txtv.setText(AL.get(position).content);
        return convertView;
    }
}

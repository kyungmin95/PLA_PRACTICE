package com.example.pla_day_exc;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class DayEditAdapter extends BaseAdapter{
    private ArrayList<DayEditList> myList = new ArrayList<>();

    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public DayEditList getItem(int position) {
        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Context context = parent.getContext();

        //day_todo_edit 를 inflate 하여서 convertView 구함
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.day_todo_edit, parent, false);
        }

        //해당 textview 와 imagebutton 을 가져와서 데이터를 넣음
        TextView edit = (TextView)convertView.findViewById(R.id.daytodoe_cont);
        ImageButton imgb = (ImageButton)convertView.findViewById(R.id.daytodoe_but) ;

        DayEditList mList = getItem(position);

        edit.setText(mList.getContent());
        imgb.setImageDrawable(mList.getButton());

        return convertView;
    }

    public void addItem(String cont, Drawable but) { //myList에 원하는 데이터 가진 리스트를 넣는 함수
        DayEditList myItem = new DayEditList();

        myItem.setContent(cont);
        myItem.setButton(but);

        myList.add(myItem);
    }

    public  void clearItem() {
        myList.clear();
    }
}

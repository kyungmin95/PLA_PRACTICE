package com.example.pla_day;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

//day 의 todolist 수정 화면에서 뜨는 리스트뷰에 대한 클래스와 어댑터 생성

class DayEdTodoItem {  //필요한 데이터 저장하는 클래스 생성
    private long eid;
    private String content_ed;

    public void setContent_ed(String cont) {content_ed = cont;}
    public String getContent_ed() {return content_ed;}
    public void setEid(long iid) {eid = iid;}
    public long getEid() {return eid;}
}

public class DayEdTodoAD extends BaseAdapter{ //리스트뷰 연결 위한 어댑터 생성
    private ArrayList<DayEdTodoItem> edList = new ArrayList<>();
    DayDB helper;

    @Override
    public int getCount() {
        return edList.size();
    }

    @Override
    public DayEdTodoItem getItem(int position) {
        return edList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Context context = parent.getContext();

        //eidt의 list 를 inflate 하여서 convertView 구함
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.day_todoed_list, parent, false);
        }

        //해당 TextView 를 가져와서 데이터를 넣음
        TextView txtv = (TextView)convertView.findViewById(R.id.daytodoe_cont);
        DayEdTodoItem medList = getItem(position);
        txtv.setText(medList.getContent_ed());

        return convertView;
    }

    public void addItem(String cont) { //myList에 원하는 데이터 가진 리스트를 넣는 함수
        DayEdTodoItem myItem = new DayEdTodoItem();

        myItem.setContent_ed(cont);

        edList.add(myItem);
    }

    public void clearItem() {
        edList.clear();
    }
}

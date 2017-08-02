package com.example.pla_day;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
//새로운 형태의 리스트를 만들기 위해 어댑터 생성
class DayListView {
    private String content;

    public void setContent(String con) {
        content = con;
    }
    public String getContent() { return content; }
}

public class DayAdapter extends BaseAdapter {
    private ArrayList<DayListView> myList = new ArrayList<>();

    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public DayListView getItem(int position) {
        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Context context = parent.getContext();

        //day_todo_list 를 inflate 하여서 convertView 구함
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.day_todo_list, parent, false);
        }

        //해당 ImageView 와 TextView 를 가져와서 데이터를 넣음
        TextView txtv = (TextView)convertView.findViewById(R.id.daytodo_cont);

        DayListView mList = getItem(position);
        txtv.setText(mList.getContent());

        return convertView;
    }

    public void addItem(String cont) { //myList에 원하는 데이터 가진 리스트를 넣는 함수
        DayListView myItem = new DayListView();
        myItem.setContent(cont);
        myList.add(myItem);
    }

    public void clearItem() {
        myList.clear();
    }
}

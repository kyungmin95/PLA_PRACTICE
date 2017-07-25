package com.example.pla_day;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
//새로운 형태의 리스트를 만들기 위해 어댑터 생성
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
        ImageView img = (ImageView)convertView.findViewById(R.id.daytodo_cir);
        TextView txtv = (TextView)convertView.findViewById(R.id.daytodo_cont);

        DayListView mList = getItem(position);

        img.setImageDrawable(mList.getCircle());
        txtv.setText(mList.getContent());

        return convertView;
    }

    public void addItem(Drawable cir, String cont) { //myList에 원하는 데이터 가진 리스트를 넣는 함수
        DayListView myItem = new DayListView();

        myItem.setCircle(cir);
        myItem.setContent(cont);

        myList.add(myItem);
    }
}

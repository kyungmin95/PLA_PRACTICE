package com.example.pla_memo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class MemoListItem {
    private int mId;
    private String title;
    private String cont;
    private String date;

    public void setmId(int id) {mId = id;}
    public int getmId() {return mId;}
    public void setTitle(String t) {title = t;}
    public String getTitle() {return title;}
    public void setCont(String co) {cont = co;}
    public String getCont() {return cont;}
    public void setDate(String dd) {date = dd;}
    public String getDate() {return date;}
}

public class MemoListAdapter extends BaseAdapter { //리스트뷰 연결 위한 어댑터 생성
    private ArrayList<MemoListItem> edList = new ArrayList<>();
    MemoListItem medList;

    @Override
    public int getCount() {
        return edList.size();
    }

    @Override
    public MemoListItem getItem(int position) {
        return edList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.memo_list, parent, false);
        }

        medList = getItem(pos);
        TextView title = (TextView)convertView.findViewById(R.id.memol_title);
        title.setText(medList.getTitle());
        TextView cont = (TextView)convertView.findViewById(R.id.memol_cont);
        cont.setText(medList.getCont());
        TextView day = (TextView)convertView.findViewById(R.id.memol_date);
        day.setText(medList.getDate());

        return convertView;
    }

    public void addItem(int id, String date, String tit, String cont) { //edList에 원하는 데이터 가진 리스트를 넣는 함수
        MemoListItem myItem = new MemoListItem();

        myItem.setmId(id);
        myItem.setTitle(tit);
        myItem.setCont(cont);
        myItem.setDate(date);

        edList.add(myItem);
    }

    public void clearItem() {
        edList.clear();
    } //리스트 내용을 전부 삭제
}

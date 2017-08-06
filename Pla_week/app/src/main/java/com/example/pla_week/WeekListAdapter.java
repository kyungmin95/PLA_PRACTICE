package com.example.pla_week;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
//week 화면에 보이는 리스트를 관리하기 위한 정보들 관리
class WeekListItem {//필요한 데이터를 저장하는 클래스
    private int dId;
    private String content;
    private boolean chcond;

    public void setDId(int dd) {dId = dd;}
    public int getDId() {return dId;}
    public void setContent(String con) {
        content = con;
    }
    public String getContent() { return content; }
    public void setChcond(boolean cc) { chcond = cc;}
    public boolean getChcond() { return chcond;}
}

public class WeekListAdapter extends BaseAdapter { //리스트와 연결 위한 어댑터 생성
    private ArrayList<WeekListItem> myList = new ArrayList<>();

    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public WeekListItem getItem(int position) {
        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Context context = parent.getContext();
        final TextView txtv; final int pos = position;

        //day_todo_list 를 inflate 하여서 convertView 구함
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.week_list, parent, false);
        }

        //해당 ImageView 와 TextView 를 가져와서 데이터를 넣음
        txtv = (TextView)convertView.findViewById(R.id.weektodo_cont);

        WeekListItem mList = getItem(position);
        txtv.setText(mList.getContent());

        CheckBox chb = (CheckBox)convertView.findViewById(R.id.weektodo_chb);

        //DB에서 checked가 T이면 체크박스를 체크하고 내용에 중앙선 삽입
        if(getItem(position).getChcond() == true) {
            chb.setChecked(true);
            txtv.setPaintFlags(txtv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        else {
            chb.setChecked(false);
        }

        //체크박스 체크 상태에 따라 DB에 checked 내용을 update 함
        chb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true) {
                    WeekListItem mList = getItem(pos);
                    mList.setChcond(true);
                    myList.set(pos, mList);
                    WeekDB helper = new WeekDB(context);
                    SQLiteDatabase db = helper.getWritableDatabase();
                    String queryupd = String.format("update %s set checked='%s' where _id = %d;", "plaweektodo_ex", "T", mList.getDId());
                    db.execSQL(queryupd);
                    db.close();
                    txtv.setPaintFlags(txtv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    WeekListItem mList = getItem(pos);
                    mList.setChcond(false);
                    myList.set(pos, mList);
                    WeekDB helper = new WeekDB(context);
                    SQLiteDatabase db = helper.getWritableDatabase();
                    String queryupd = String.format("update %s set checked='%s' where _id = %d;", "plaweektodo_ex", "F", mList.getDId());
                    db.execSQL(queryupd);
                    db.close();
                    txtv.setPaintFlags(txtv.getPaintFlags() ^ Paint.STRIKE_THRU_TEXT_FLAG);
                }
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    public void addItem(int dd, String cont, String cc) { //myList에 원하는 데이터 가진 리스트를 넣는 함수
        WeekListItem myItem = new WeekListItem();
        boolean cd;
        myItem.setContent(cont);
        myItem.setDId(dd);
        if(cc.equals("T")) cd = true;
        else cd = false;
        myItem.setChcond(cd);
        myList.add(myItem);
    }

    public void clearItem() {
        myList.clear();
    }
}
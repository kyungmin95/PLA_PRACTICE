package com.example.pla_day_exc;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText editCont;
    ListView LV;
    DBHelper helper;
    AaaDay aDay;
    List<String> ds;
    DayEditAdapter todolistAA;
    SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editCont = (EditText)findViewById(R.id.day_edit_cont);
        LV = (ListView)findViewById(R.id.day_edit_list);
        helper = new DBHelper(this);
        ds = new ArrayList<String>();
        todolistAA = new DayEditAdapter();
        makeList();
        //todolistAA.addItem("은행 들려서 돈 입금", ContextCompat.getDrawable(getApplicationContext(), R.drawable.minus));
        //todolistAA.addItem("기차표 예매",ContextCompat.getDrawable(getApplicationContext(), R.drawable.minus));
        //todolistAA.addItem("시계 주문하기",ContextCompat.getDrawable(getApplicationContext(), R.drawable.minus));
        //ListView listview = (ListView)findViewById(R.id.day_edit_list);
        LV.setAdapter(todolistAA);
        LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                btnEnabled(true);
            }
        });
    }

    public void onClick(View v) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        String str_cont;
        switch(v.getId()) {
            case R.id.day_todo_add:
                str_cont = editCont.getText().toString();
                if (str_cont.length() == 0)
                    break;
                String queryadd = String.format("insert into %s values(null, '%s', '%s', '%s');", "aaday", "20170727", "todolist", str_cont);
                db.execSQL(queryadd);
                break;
            case R.id.day_todo_edit:
                str_cont = editCont.getText().toString();
                if (str_cont.length() == 0)
                    break;
                String queryupd = String.format("update %s set content='%s' where ");
                break;
        }
        editCont.setText("");
        makeList();
        btnEnabled(false);
        todolistAA.notifyDataSetChanged();
        helper.close();
        db.close();
    }

    public void makeList() {
        todolistAA.clearItem();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery("select content from aaday;", null);
        while(c.moveToNext()) {
            todolistAA.addItem(c.getString(0), ContextCompat.getDrawable(getApplicationContext(), R.drawable.minus));
        }
        c.close();
        db.close();
    }

    public void btnEnabled(boolean enabled) {
        ((Button)findViewById(R.id.day_todo_edit)).setEnabled(enabled);
    }
}

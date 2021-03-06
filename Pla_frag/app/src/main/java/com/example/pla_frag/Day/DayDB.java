package com.example.pla_frag.Day;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//Day에 필요한 DB를 생성
public class DayDB extends SQLiteOpenHelper {
    public DayDB(Context context) {
        super(context, "pladay_e.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { //id, 날짜, 내용, 체크박스 체크 여부 저장하는 테이블 생성
        db.execSQL("create table pladaytodo_e " +
                "(_id integer primary key autoincrement, date text not null, content text not null, checked text not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists pladaytodo_e;");
        onCreate(db);
    }
}

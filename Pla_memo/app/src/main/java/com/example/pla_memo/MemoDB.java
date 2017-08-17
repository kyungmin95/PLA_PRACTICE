package com.example.pla_memo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//메모 관련 데이터를 관리하기 위한 DB 생성
public class MemoDB extends SQLiteOpenHelper {
    public MemoDB(Context context) {
        super(context, "plamemo_exc.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table plamemo_exc " +
                "(_id integer primary key autoincrement, date text not null, time text not null, title text, content text);");
    }
    //id, 날짜(date), 내용(content) 저장하는 테이블 생성

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists plamemo_exc;");
        onCreate(db);
    }
}
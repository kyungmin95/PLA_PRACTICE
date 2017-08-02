package com.example.pla_day;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//Day Memo에 필요한 DB 생성
public class DayMemoDB extends SQLiteOpenHelper {
    public DayMemoDB(Context context) {
        super(context, "pladayme_ex.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table pladaymemo_ex " +
                "(_id integer primary key autoincrement, date text not null, memocont text not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists pladaymemo_ex;");
        onCreate(db);
    }
}
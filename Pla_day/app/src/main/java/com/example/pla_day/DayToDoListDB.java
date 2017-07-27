package com.example.pla_day;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DayToDoListDB extends SQLiteOpenHelper {
    public DayToDoListDB(Context context, String tabname) {
        super(context, tabname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table pladaytodo_ex " +
                "(_id integer primary key autoincrement, date text not null, content text not null);");
        db.execSQL("create table pladaymemo_ex " +
                "(_id integer primary key autoincrement, date text not null, memocont text not null");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists pladaytodo_ex;");
        db.execSQL("drop table if exists pladaymemo_ex;");
        onCreate(db);
    }
}

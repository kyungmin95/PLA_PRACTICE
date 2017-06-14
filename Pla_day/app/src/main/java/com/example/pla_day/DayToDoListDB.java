package com.example.pla_day;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 김경민 on 2017-06-15.
 */

public class DayToDoListDB extends SQLiteOpenHelper {
    public DayToDoListDB(Context context) {
        super(context, "pladaytodo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table pladaytodo " +
                "(_id integer primary key autoincrement, eng text not null, han text not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table pladaytodo;");
        onCreate(db);
    }
}

package com.example.pla_frag.Day;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DayDiaryDB extends SQLiteOpenHelper {
    public DayDiaryDB(Context context) {
        super(context, "pladaydi_ex.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table pladaydi_ex " +
                "(_id integer primary key autoincrement, date text not null, dicont text not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists pladaydi_ex;");
        onCreate(db);
    }
}

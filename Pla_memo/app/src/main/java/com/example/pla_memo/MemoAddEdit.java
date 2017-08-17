package com.example.pla_memo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MemoAddEdit extends Activity {
    Intent maeIntent;
    int con, mid;
    int year, month, day;
    String dd, sd;
    EditText title, cont;
    String t, c;
    MemoDB mhelper;
    Calendar cal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_addedit);

        maeIntent = getIntent();
        con = maeIntent.getExtras().getInt("CON");
        title = (EditText)findViewById(R.id.memoae_title);
        cont = (EditText)findViewById(R.id.memoae_cont);
        mhelper = new MemoDB(this);

        cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH)+1;
        day = cal.get(Calendar.DAY_OF_MONTH);
        dd = Integer.toString(year) + "." + Integer.toString(month) + "." + Integer.toString(day);

        if(con == 1) {
            title.setText("");
            cont.setText("");
        }
        else {
            mid = maeIntent.getExtras().getInt("id");
            SQLiteDatabase db = mhelper.getReadableDatabase();
            Cursor c = db.rawQuery("select title, content from plamemo_exc where _id = '"+ mid + "';", null);
            while(c.moveToNext()) {
                title.setText(c.getString(0));
                cont.setText(c.getString(1));
            }
            c.close();
            db.close();
        }

        findViewById(R.id.memoae_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault());
                Date d = new Date();
                sd = df.format(d);
                SQLiteDatabase db = mhelper.getWritableDatabase();
                t = title.getText().toString();
                c = cont.getText().toString();
                if(t.length() == 0 && c.length() == 0) {
                    if(con == 2) {
                        db.delete("plamemo_exc", "_id=" + mid, null);
                        maeIntent.putExtra("Del", 1);
                        //setResult(RESULT_OK, maeIntent);
                    }
                    else ;

                }
                else {
                    if(con == 1) {
                        String queryadd = String.format("insert into %s values(null, '%s', '%s','%s', '%s');", "plamemo_exc", dd, sd, t, c);
                        db.execSQL(queryadd);
                    }
                    else {
                        String queryupd = String.format("update %s set title='%s', content = '%s', date = '%s', time = '%s'" +
                                "where _id = %d;", "plamemo_exc", t, c, dd, sd, mid);
                        db.execSQL(queryupd);
                        maeIntent.putExtra("Del", 0);
                        //setResult(RESULT_OK, maeIntent);
                    }
                }
                setResult(RESULT_OK, maeIntent);
                db.close();
                finish();
            }
        });
    }
}

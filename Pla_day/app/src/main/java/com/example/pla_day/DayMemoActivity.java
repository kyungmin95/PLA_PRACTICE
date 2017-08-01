package com.example.pla_day;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class DayMemoActivity extends Activity {
    int year, month, day;
    Intent mintent;
    TextView dayDate;
    String dd, division;
    DayMemoDB mHelper;
    EditText memoCont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_memo);

        mintent = getIntent();
        year = mintent.getExtras().getInt("year");
        month = mintent.getExtras().getInt("month");
        day = mintent.getExtras().getInt("day");
        mHelper = new DayMemoDB(this);
        division = "memo";
        setDayDate();
        memoCont = (EditText)findViewById(R.id.daymemo_cont);
        makeMemo();

        findViewById(R.id.daymemo_can).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mintent.putExtra("cmYear", year);
                mintent.putExtra("cmMonth", month);
                mintent.putExtra("cmDay", day);
                setResult(RESULT_OK, mintent);
                finish();
            }
        });

    }

    public void editMemo(View v) {  // 체크 모양을 누르면 메모 내용을 DB에 추가하거나 수정하고 intent를 종료함
        mintent.putExtra("cmYear", year);
        mintent.putExtra("cmMonth", month);
        mintent.putExtra("cmDay", day);
        setResult(RESULT_OK, mintent);
        SQLiteDatabase db = mHelper.getReadableDatabase();
        String mecont = memoCont.getText().toString();
        int mId = 0; int cnt = 0;
        Cursor c = db.rawQuery("select _id, memocont from pladaymemo_ex where date = '"+ dd +"';", null);
        while(c.moveToNext()) {
            mId = c.getInt(0);
            cnt++;
        }
        c.close();
        db = mHelper.getWritableDatabase();
        if(cnt == 0) {
            if(mecont.length() != 0) {
                String queryadd = String.format("insert into %s values(null, '%s', '%s');", "pladaymemo_ex", dd, mecont);
                db.execSQL(queryadd);
            }
        }
        else {
            if(mecont.length() != 0) {
                String queryupd = String.format("update %s set memocont='%s' where _id = %d;", "pladaymemo_ex", mecont, mId);
                db.execSQL(queryupd);
            }
        }
        db.close();
        mintent.putExtra("cYear", year);
        mintent.putExtra("cMonth", month);
        mintent.putExtra("cDay", day);
        setResult(RESULT_OK, mintent);
        finish();
    }

    public void setDayDate() {  //TextView에 년, 월, 일을 입력하는 함수. 데이터베이스에 사용하기 위해 String 타입으로 dd도 지정.
        dayDate = (TextView) findViewById(R.id.daymemo_date);
        dayDate.setText(year+"년 "+month+"월 "+day+"일");
        int syear = year; int smonth = month; int sday = day;
        dd = Integer.toString(syear) + Integer.toString(smonth) + Integer.toString(sday);
    }

    public void memoChangeDate(View v){
        DatePickerDialog dpf = new DatePickerDialog(this, listener, year, month-1, day);
        dpf.show();
    }

    //Picker를 사용해 선택한 날짜로 year, month, day를 갱신, 바꾼 날짜에 따라 리스트도 갱신
    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int cyear, int cmonth, int cdayOfMonth) {
            year = cyear;
            month = cmonth+1;
            day = cdayOfMonth;
            setDayDate();
            makeMemo();
        }
    };

    public void makeMemo() {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor c = db.rawQuery("select memocont from pladaymemo_ex where date = '"+ dd +"';", null);
        while(c.moveToNext()) {
            memoCont.setText(c.getString(0));
        }
        c.close();
        db.close();
    }
}

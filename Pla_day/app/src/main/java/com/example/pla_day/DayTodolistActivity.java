package com.example.pla_day;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DayTodolistActivity extends Activity {
    int year, month, day;
    int syear, smonth, sday;
    Button dayDate;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.day_add_todolist);

        Intent intent = getIntent();
        year = intent.getExtras().getInt("year");
        month = intent.getExtras().getInt("month");
        day = intent.getExtras().getInt("day");
        setDayDate();

        findViewById(R.id.day_todo_can).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.day_todo_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void setDayDate() {  //TextView에 년, 월, 일을 입력하는 함수
        dayDate = (Button) findViewById(R.id.todolist_date);
        dayDate.setText(year+"년 "+month+"월 "+day+"일");
    }
}

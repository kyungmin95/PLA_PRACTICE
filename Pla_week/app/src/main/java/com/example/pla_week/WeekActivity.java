package com.example.pla_week;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

public class WeekActivity extends Activity {
    int year, month, day, dow; //해당 년,월,일,요일을 저장하는 변수
    int MDate, SDate; //해당 주의 월요일, 일요일의 일을 저장하는 변수
    int Mmonth, Smonth; //해당 주의 월요일, 일요일의 월을 저장하는 변수
    int WYear; //주에 따른 년도를 저장하는 변수
    String wd; //DB 검색을 위한 해당 주의 월요일을 저장하는 변수
    TextView weekp;
    Calendar c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.week_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {    // + 버튼을 누르면 일정 추가 화면으로 넘어감
                Intent addTodoIntent = new Intent(WeekActivity.this, WeekAddTodoActivity.class);
                startActivity(addTodoIntent);
                //startActivityForResult(addTodoIntent, 1);
            }
        });

        weekp = (TextView)findViewById(R.id.week_date);
        getCurrentWeek();
        getFEDate();
        setWeekP();

        //전주로 이동하는 함수
        findViewById(R.id.week_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cday = day - 7;
                if(cday < 1) { //일주일 전으로 이동했을 때 전달로 넘어가야 하는 날짜가 되면 month를 하나 줄이고, 그에 맞게 day 값도 수정
                    switch (month) {
                        case 5:case 7:case 10:case 12:
                            day = 30 + cday; break;
                        case 3:
                            if((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) day = 29 + cday;
                            else day = 28 + cday;
                            break;
                        default:
                            day = 31 + cday; break;
                    }
                    month -= 1;
                }
                else day -= 7;
                if(month == 0) { //만약 1월에서 전달로 가 month가 0이 되면 년도를 작년으로 수정하고, month 를 12월로 지정.
                    year -= 1;
                    month = 12;
                }
                getFEDate();
                setWeekP();
            }
        });

        //다음주로 이동하는 함수
        findViewById(R.id.week_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cday = day + 7;
                switch (month) { //일주일 뒤로 이동했을 때 다음달로 넘어가야 하는 날짜가 되면 month를 하나 증가시키고, day 알맞게 수정
                    case 4:case 6:case 9:case 11:
                        if(cday > 30) {
                            day = cday - 30; month += 1;
                        }
                        else day += 7;
                        break;
                    case 2:
                        if((year % 4 == 0 && year % 100 != 0) || year % 400 == 0){
                            if(cday > 29) { day = cday - 29; month += 1;}
                            else day += 7;
                        }
                        if(cday > 28) { day = cday - 28; month += 1;}
                        else day += 7;
                        break;
                    default:
                        if(cday > 31) {
                            day = cday - 31; month += 1;
                        }
                        else day += 7;
                        break;
                }
                if(month > 12) { //만약 month가 12월이 넘어가면 년도를 다음해로 수정하고, month를 1월로 지정.
                    year += 1; month = 1;
                }
                getFEDate();
                setWeekP();
            }
        });

        //오늘 버튼을 누르면 오늘이 있는 주로 이동
        findViewById(R.id.week_today).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrentWeek();
                getFEDate();
                setWeekP();
            }
        });
    }

    public void getFEDate() {
        MDate = day; SDate = day;
        Mmonth = month; Smonth = month;
        WYear = year;
        for(int i = dow; i > 2; i--) {
            MDate--;
            if(MDate < 1) { //1일 이전으로 넘어가면 경우에 따라 MDate 와 Mmonth를 재 설정
                switch (month) {
                    case 5:case 7:case 10:case 12:
                        MDate = 30; break;
                    case 3:
                        if((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) MDate = 29;
                        else MDate = 28;
                        break;
                    default:
                        MDate = 31; break;
                }
                Mmonth -= 1;
                if(Mmonth == 0) { //만약 1월에서 전달로 가 Mmonth가 0이 되면 Mmonth 를 12월로 지정.
                    WYear -= 1; Mmonth = 12;
                }
            }
        }
        for(int i = dow; i <= 7; i++) {
            SDate++;
            switch (month) { //29,30,31일을 넘어가면 경우에 따라 SDate, Smonth를 재설정
                case 4:case 6:case 9:case 11:
                    if(SDate == 31) {
                        SDate = 1; Smonth += 1;
                    }
                    break;
                case 2:
                    if((year % 4 == 0 && year % 100 != 0) || year % 400 == 0){
                        if(SDate == 30) SDate = 1;
                    }
                    if(SDate == 29) SDate = 1;
                    if(SDate == 1) Smonth += 1;
                    break;
                default:
                    if(SDate == 32) {
                        SDate = 1; Smonth += 1;
                    }
                    break;
            }
            if(Smonth > 12) { //만약 Smonth가 12월이 넘어가면 Smonth를 1월로 지정.
                Smonth = 1;
            }
        }
    }

    public void getCurrentWeek() {  //오늘 날짜와 요일을 가져와 변수에 저장
        c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH)+1;
        day = c.get(Calendar.DAY_OF_MONTH);
        dow = c.get(Calendar.DAY_OF_WEEK);
    }

    public void setWeekP() {    //주의 월요일과 일요일을 보여주기 위해 textView 에 set 하고, DB에 사용하기 위해 해당 월요일을 wd 에 저장
        weekp.setText(WYear + "   " + Mmonth + "/" + MDate + " ~ " + Smonth + "/" + SDate);
        int syear = WYear; int smonth = Mmonth; int sdate = MDate;
        wd = Integer.toString(syear) + Integer.toString(smonth) + Integer.toString(sdate);
    }
}

package com.example.pla_day;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    int year, month, day;
    TextView dayDate;
    Calendar c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getCurrentDate();
        setDayDate();  //getCurrentDate로 오늘 날짜를 가져온 뒤, 그 값을 dayDate에 입력(아무 버튼도 누르지 않은 기본 상태)

        findViewById(R.id.day_left).setOnClickListener(new View.OnClickListener() { // '<' 버튼 눌렀을 때. 전날로 날짜 이동
            @Override
            public void onClick(View v) {
                day -= 1;
                if(day == 0) { //전달로 넘어가야 하는 날짜가 되면 month를 하나 줄이고, 그에 맞게 day 값도 수정
                    switch (month) {
                        case 5:case 7:case 10:case 12:
                            day = 30; break;
                        case 3:
                            if((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) day = 29;
                            else day = 28;
                            break;
                        default:
                            day = 31; break;
                    }
                    month -= 1;
                }
                if(month == 0) { //만약 1월에서 전달로 가 month가 0이 되면 년도를 작년으로 수정하고, month 를 12월로 지정.
                    year -= 1;
                    month = 12;
                }
                setDayDate();
            }
        });

        findViewById(R.id.day_right).setOnClickListener(new View.OnClickListener() {  // '>' 버튼 눌렀을 때. 다음날로 날짜 이동
            @Override
            public void onClick(View v) {
                day += 1;
                switch (month) { //다음달로 넘어가야 하는 날짜가 되면 month를 하나 증가시키고, day 도 1일로 수정.
                    case 4:case 6:case 9:case 11:
                        if(day == 31) {
                            day = 1; month += 1;
                        }
                        break;
                    case 2:
                        if((year % 4 == 0 && year % 100 != 0) || year % 400 == 0){
                            if(day == 30) day = 1;
                        }
                        if(day == 29) day = 1;
                        if(day == 1) month += 1;
                        break;
                    default:
                        if(day == 32) {
                            day = 1; month += 1;
                        }
                        break;
                }
                if(month > 12) { //만약 month가 12월이 넘어가면 년도를 다음해로 수정하고, month를 1월로 지정.
                    year += 1; month = 1;
                }
                setDayDate();
            }
        });

        findViewById(R.id.day_today).setOnClickListener(new View.OnClickListener() {  // '오늘' 버튼 눌렀을 때. 오늘로 날짜 이동
            @Override
            public void onClick(View v) {
                getCurrentDate();
                setDayDate();
            }
        });

        //리스트를 직접 구성한 이미지로 보이게 하기 위해 새로 만든 어댑터와 연결
        DayAdapter todolistAA = new DayAdapter();
        todolistAA.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.cir),"은행 들려서 돈 입금");
        todolistAA.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.cir),"기차표 예매");
        todolistAA.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.cir),"시계 주문하기");
        ListView listview = (ListView)findViewById(R.id.day_list);
        listview.setAdapter(todolistAA);

        //to do list의 배경을 선택하면 to do list 추가 화면으로 넘어가는 intent 설정
        LinearLayout todolistLL = (LinearLayout)findViewById(R.id.day_todolist);
        todolistLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent todolistIntent = new Intent(MainActivity.this, DayTodolistActivity.class);
                todolistIntent.putExtra("year", year);
                todolistIntent.putExtra("month", month);
                todolistIntent.putExtra("day", day);
                startActivity(todolistIntent);
            }
        });
        //to do list의 리스트를 선택하면 to do list 추가 화면으로 넘어가는 intent 설정
        ListView todolistLV = (ListView)findViewById(R.id.day_list);
        todolistLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent todolistIntent = new Intent(MainActivity.this, DayTodolistActivity.class);
                todolistIntent.putExtra("year", year);
                todolistIntent.putExtra("month", month);
                todolistIntent.putExtra("day", day);
                startActivity(todolistIntent);
            }
        });

    }

    public void getCurrentDate() {  //오늘 날짜를 가져오는 함수. Calendar 사용.
        c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH)+1;
        day = c.get(Calendar.DAY_OF_MONTH);
    }

    public void setDayDate() {  //TextView에 년, 월, 일을 입력하는 함수
        dayDate = (TextView)findViewById(R.id.day_date);
        dayDate.setText(year+"년 "+month+"월 "+day+"일");
    }
}

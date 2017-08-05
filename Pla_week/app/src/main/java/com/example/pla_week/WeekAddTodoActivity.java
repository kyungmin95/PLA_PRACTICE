package com.example.pla_week;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WeekAddTodoActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.week_add_todo);
        Intent i = getIntent();

        findViewById(R.id.weektodoa_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent.putExtra("cYear", year);
                //intent.putExtra("cMonth", month);
                //intent.putExtra("cDay", day);
                //setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}

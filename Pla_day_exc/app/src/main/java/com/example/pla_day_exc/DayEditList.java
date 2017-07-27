package com.example.pla_day_exc;

import android.graphics.drawable.Drawable;

/**
 * Created by 김경민 on 2017-07-26.
 */

class DayEditList {
    private String content;
    private Drawable button;

    public void setButton(Drawable but) {
        button = but;
    }

    public Drawable getButton(){
        return button;
    }

    public void setContent(String con) {
        content = con;
    }

    public String getContent() {
        return content;
    }
}

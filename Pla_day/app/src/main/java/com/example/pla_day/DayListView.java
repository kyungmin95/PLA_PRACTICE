package com.example.pla_day;

import android.graphics.drawable.Drawable;
//리스트 구성 데이터 설정
class DayListView {
    private Drawable circle;
    private String content;

    public void setCircle(Drawable cir) {
        circle = cir;
    }

    public Drawable getCircle(){
        return circle;
    }

    public void setContent(String con) {
        content = con;
    }

    public String getContent() {
        return content;
    }
}

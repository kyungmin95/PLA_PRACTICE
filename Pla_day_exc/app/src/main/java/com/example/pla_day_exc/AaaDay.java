package com.example.pla_day_exc;

/**
 * Created by 김경민 on 2017-07-26.
 */

public class AaaDay {
    private long id;
    private String date;
    private String division;
    private String content;

    public AaaDay(long id, String date, String division, String content) {
        this.id =id;
        this.date = date;
        this.division = division;
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public String getDivision() {
        return division;
    }

    public String getContent() {
        return content;
    }

}

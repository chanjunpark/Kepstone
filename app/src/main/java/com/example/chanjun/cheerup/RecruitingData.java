package com.example.chanjun.cheerup;

/**
 * Created by chanjun on 2017. 6. 12..
 */

public class RecruitingData {
    private String name;
    private String date;
    private String location;
    private String type;
    public RecruitingData() { }

    public RecruitingData(String name, String date, String location, String type) {
        this.name = name;
        this.date = date;
        this.location = location;
        this.type = type;
    }

    public String getCorporationName() {
        return name;
    }

    public void setCorporationName(String userName) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }
}

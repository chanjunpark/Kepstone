package com.example.chanjun.cheerup;

/**
 * Created by chanjun on 2017. 6. 12..
 */

public class RecruitingData {
    private String corporationName;
    private String date;
    private String location;
    private String type;
    public RecruitingData() { }

    public RecruitingData(String name, String date, String location, String type) {
        this.corporationName = name;
        this.date = date;
        this.location = location;
        this.type = type;
    }

    public String getCorporationName() {
        return corporationName;
    }

    public void setCorporationName(String name) {
        this.corporationName = name;
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

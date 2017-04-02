package com.example.android.bloodbankapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shubham on 3/4/17.
 */

public class Date {

    @SerializedName("day")
    private int day;

    @SerializedName("month")
    private int month;

    @SerializedName("year")
    private int year;

    public void setDay(int day) { this.day = day; }

    public void setMonth(int month) { this.month = month; }

    public void setYear(int year) { this.year = year; }

    public int getDay() { return day; }

    public int getMonth() { return month; }

    public int getYear() { return year; }


}

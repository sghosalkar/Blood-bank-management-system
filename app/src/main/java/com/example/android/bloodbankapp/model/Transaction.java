package com.example.android.bloodbankapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shubham on 2/4/17.
 */

public class Transaction {

    @SerializedName("bloodbank_id")
    private int bloodbankId;

    @SerializedName("name")
    private String name;

    @SerializedName("contact_no")
    private String contactNo;

    @SerializedName("type")
    private String type;

    @SerializedName("blood_group")
    private String bloodGroup;

    @SerializedName("quantity")
    private int quantity;

    @SerializedName("price")
    private int price;

    @SerializedName("day")
    private int day;

    @SerializedName("month")
    private int month;

    @SerializedName("year")
    private int year;

    //Setters
    public void setBloodbankId(int bloodbankId) { this.bloodbankId = bloodbankId; }

    public void setName(String name) { this.name = name; }

    public void setContactNo(String contactNo) { this.contactNo = contactNo; }

    public void setType(String type) { this.type = type; }

    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public void setPrice(int price) { this.price = price; }

    public void setDay(int day) { this.day = day; }

    public void setMonth(int month) { this.month = month; }

    public void setYear(int year) { this.year = year; }

    //Getters
    public int getBloodbankId() { return bloodbankId; }

    public String getName() { return name; }

    public String getContactNo() { return contactNo; }

    public String getType() { return type; }

    public String getBloodGroup() { return bloodGroup; }

    public int getQuantity() { return quantity; }

    public int getPrice() { return price; }

    public int getDay() { return day; }

    public int getMonth() { return month; }

    public int getYear() { return year; }

}

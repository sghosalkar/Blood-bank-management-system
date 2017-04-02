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

    public void setBloodbankId(int bloodbankId) { this.bloodbankId = bloodbankId; }

    public void setName(String name) { this.name = name; }

    public void setContactNo(String contactNo) { this.contactNo = contactNo; }

    public void setType(String type) { this.type = type; }

    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public void setPrice(int price) { this.price = price; }

    public int getBloodbankId() { return bloodbankId; }

    public String getName() { return name; }

    public String getContactNo() { return contactNo; }

    public String getType() { return type; }

    public String getBloodGroup() { return bloodGroup; }

    public int getQuantity() { return quantity; }

    public int getPrice() { return price; }

}

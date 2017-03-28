package com.example.android.bloodbankapp.Utility;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.android.bloodbankapp.data.BloodBankContract.DateEntry;
import com.example.android.bloodbankapp.data.BloodBankContract.DonorEntry;
import com.example.android.bloodbankapp.data.BloodBankContract.ReceiverEntry;

/**
 * Created by shubham on 28/3/17.
 */

public class InsertionQueries {

    SQLiteDatabase sqLiteDatabase;

    public  void addDateEntry(int id, int day,String month,int year){
        ContentValues values=new ContentValues();
        values.put("id",id);
        values.put("day",day);
        values.put("month",month);
        values.put("year",year);
        sqLiteDatabase.insert(DateEntry.TABLE_NAME,null,values);
        Log.d("BB","INSERTED");

    }
    public void addDonorEntry(int id,int datekey,String name,String contactno,String bldgrp,String quantity,int price){

        ContentValues values=new ContentValues();
        values.put("id",id);
        values.put("datekey",datekey);
        values.put("name",name);
        values.put("contact",contactno);
        values.put("bldgrp",bldgrp);
        values.put("quantity",quantity);
        values.put("price",price);


        sqLiteDatabase.insert(DonorEntry.TABLE_NAME,null,values);
        Log.d("BB","Inserted");

    }


    public void addReceiverEntry(int id,int datekey,String name,String contactno,String bldgrp,String quantity,int price){

        ContentValues values=new ContentValues();
        values.put("id",id);
        values.put("datekey",datekey);
        values.put("name",name);
        values.put("contact",contactno);
        values.put("bldgrp",bldgrp);
        values.put("quantity",quantity);
        values.put("price",price);


        sqLiteDatabase.insert(ReceiverEntry.TABLE_NAME,null,values);
        Log.d("BB","Inserted");



    }

}

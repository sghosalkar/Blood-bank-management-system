package com.example.android.bloodbankapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.bloodbankapp.StatisticsReportsFragment;
import com.example.android.bloodbankapp.data.BloodBankContract.DonorEntry;
//import com.example.android.bloodbankapp.data.BloodBankContract.DonorTransactionEntry;
import com.example.android.bloodbankapp.data.BloodBankContract.ReceiverEntry;
import com.example.android.bloodbankapp.data.BloodBankContract.DateTable;
//import com.example.android.bloodbankapp.data.BloodBankContract.ReceiverTransactionEntry;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
/**
 * Created by shubham on 17/3/17.
 */

public class BloodBankDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "bloodBank.db";
    SQLiteDatabase db;
    Context context;

    public BloodBankDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
        db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_DATE_TABLE="CREATE TABLE"+ DateTable.TABLE_NAME +" ("+ DateTable._ID +"INTEGER PRIMARY KEY," +
                DateTable.COLUMN_DATE_DATENO +"INTEGER NOT NULL," + DateTable.COLUMN_DATE_MONTH +" TEXT NOT NULL," +
                DateTable.COLUMN_DATE_YEAR +"INTEGER NOT NULL);";


        final String SQL_CREATE_DONOR_TABLE = "CREATE TABLE " + DonorEntry.TABLE_NAME + " (" +
                DonorEntry._ID + " INTEGER PRIMARY KEY, " +
                DonorEntry.COLUMN_DATE_ID +" INTEGER NOT NULL ,"+
                DonorEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                DonorEntry.COLUMN_CONTACT_NO + " TEXT UNIQUE NOT NULL ," +
                DonorEntry.COLUMN_BLOOD_GROUP + " TEXT NOT NULL , " +
                DonorEntry.COLUMN_QUANTITY + " TEXT NOT NULL , " +
                DonorEntry.COLUMN_PRICE + " INTEGER NOT NULL ," +
                "FOREIGN KEY ("+ DonorEntry.COLUMN_DATE_ID +" ) REFERENCES " +
                DateTable.TABLE_NAME + "(" + DateTable._ID + ") ON CONFLICT REPLACE;";

        final String SQL_CREATE_RECEIVER_TABLE = "CREATE TABLE " + ReceiverEntry.TABLE_NAME + " (" +
                ReceiverEntry._ID + " INTEGER PRIMARY KEY, " +
                ReceiverEntry.COLUMN_DATE_ID + "INTEGER PRIMARY KEY" +
                ReceiverEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                ReceiverEntry.COLUMN_CONTACT_NO + " TEXT UNIQUE NOT NULL ," +
                ReceiverEntry.COLUMN_BLOOD_GROUP + " TEXT NOT NULL ," +
                ReceiverEntry.COLUMN_QUANTITY + " TEXT NOT NULL, " +
                DonorEntry.COLUMN_PRICE + " INTEGER NOT NULL ," + "FOREIGN KEY (" + ReceiverEntry.COLUMN_DATE_ID + ")REFERENCES " +
                DateTable.TABLE_NAME + "(" + DateTable._ID +") ON CONFLICT REPLACE);";
/*
        final String SQL_CREATE_DONOR_TRANSACTION_TABLE = "CREATE TABLE " + DonorTransactionEntry.TABLE_NAME + " (" +
                DonorTransactionEntry._ID + " INTEGER PRIMARY KEY, " +
                DonorTransactionEntry.COLUMN_DONOR_KEY + " INTEGER NOT NULL, " +
                DonorTransactionEntry.COLUMN_BLOOD_GROUP + " TEXT NOT NULL, " +
                DonorTransactionEntry.COLUMN_QUANTITY + " INTEGER NOT NULL, " +
                DonorTransactionEntry.COLUMN_PRICE + " INTEGER NOT NULL, " +
                DonorTransactionEntry.COLUMN_TRANSACTION_DATE + " INTEGER NOT NULL, " +
                " FOREIGN KEY (" + DonorTransactionEntry.COLUMN_DONOR_KEY + ") REFERENCES " +
                DonorEntry.TABLE_NAME + " (" + DonorEntry._ID + ") ON CONFLICT REPLACE);";

        final String SQL_CREATE_RECEIVER_TRANSACTION_TABLE = "CREATE TABLE " + ReceiverTransactionEntry.TABLE_NAME + " (" +
                ReceiverTransactionEntry._ID + " INTEGER PRIMARY KEY, " +
                ReceiverTransactionEntry.COLUMN_RECEIVER_KEY + " INTEGER NOT NULL, " +
                ReceiverTransactionEntry.COLUMN_BLOOD_GROUP + " TEXT NOT NULL, " +
                ReceiverTransactionEntry.COLUMN_QUANTITY + " INTEGER NOT NULL, " +
                ReceiverTransactionEntry.COLUMN_PRICE + " INTEGER NOT NULL, " +
                ReceiverTransactionEntry.COLUMN_TRANSACTION_DATE + " INTEGER NOT NULL, " +
                " FOREIGN KEY (" + ReceiverTransactionEntry.COLUMN_RECEIVER_KEY + ") REFERENCES " +
                ReceiverEntry.TABLE_NAME + " (" + ReceiverEntry._ID + ") ON CONFLICT REPLACE);";
*/
        db.execSQL(SQL_CREATE_DATE_TABLE);
        db.execSQL(SQL_CREATE_DONOR_TABLE);
        db.execSQL(SQL_CREATE_RECEIVER_TABLE);
        /*
        sqLiteDatabase.execSQL(SQL_CREATE_DONOR_TRANSACTION_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_RECEIVER_TRANSACTION_TABLE);*/
    }
    public  void addDateEntry(int id, int day,String month,int year){
        ContentValues values=new ContentValues();
        values.put("id",id);
        values.put("day",day);
        values.put("month",month);
        values.put("year",year);
        db.insert(DateTable.TABLE_NAME,null,values);
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


        db.insert(DonorEntry.TABLE_NAME,null,values);
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


        db.insert(ReceiverEntry.TABLE_NAME,null,values);
        Log.d("BB","Inserted");



    }




    /*
    public void addDonorEntry(int id,String name, String contactno, String bloodgroup, int quantity) {

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MONTH-YYYY");
        String currentDate = df.format(c.getTime());
        String current[] = currentDate.split("-");
        int currentDay = Integer.parseInt(current[0]);
        String currentMonth = current[1];

        //Date d=new Date();
                try {p
            db.execSQL("Insert into DonorEntry.TABLE_NAME( DonorEntry._ID ,DonorEntry.COLUMN_NAME ," +
                    "DonorEntry.COLUMN_CONTACT_NO ,DonorEntry.COLUMN_BLOOD_GROUP "+
                    ",DonorEntry.COLUMN_QUANTITY ,DonorEntry.COLUMN_PRICE ,DonorEntry.COLUMN_DATE )" +
                    "values('" + id + "','" + name + "','" + contactno + "','" + bloodgroup + "','" + quantity + "','"
                    + (quantity * 10) + "','" + currentMonth + "');");
            Log.d("DBB", "Inserted");
        } catch (SQLiteConstraintException e) {
            Log.d("DBB", "Insert Issue");
        }

/*
        db.execSQL("Insert into DonorEntry.TABLE_NAME" +
                "(DonorTransactionEntry.COLUMN_DONOR_NMAE,DonorTransactionEntry.COLUMN_QUANTITY ," +
                " DonorTransactionEntry.COLUMN_PRICE, DonorTransactionEntry.COLUMN_TRANSACTION_DATE )" +
                "values('"+id+"','"+quantity+"','"+(quantity*10)+"','"+date+"')");

       /* if(rid<0)
        {Log.d("DB123","Insert Issue");}
        else
        {Log.d("DB123","Inserted");}
/*
        db.execSQL("Insert into DonorTransactionEntry.TABLE_NAME" +
                "(DonorTransactionEntry.COLUMN_DONOR_KEY,DonorTransactionEntry.COLUMN_QUANTITY ," +
                " DonorTransactionEntry.COLUMN_PRICE, DonorTransactionEntry.COLUMN_TRANSACTION_DATE )" +
                "values('"+id+"','"+quantity+"','"+(quantity*10)+"','"+date+"')");
                }*/

    /*

    public void addReceiverEntry(int id,String name, String contactno, String bloodgroup, int quantity) {
        // ContentValues values=new ContentValues();
        //values.put("id",id);
        //values.put("name",name);
        //values.put("contactno",contactno);
        //values.put("bloodgroup",bloodgroup);
        //values.put("quantity",quantity);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MONTH-YYYY");
        String currentDate = df.format(c.getTime());
        String current[] = currentDate.split("-");
        int currentDay = Integer.parseInt(current[0]);
        String currentMonth = current[1];

        try {
            db.execSQL("Insert into ReceiverEntry.TABLE_NAME(ReceiverEntry._ID,ReceiverEntry.COLUMN_NAME ," +
                    "ReceiverEntry.COLUMN_CONTACT_NO ,ReceiverEntry.COLUMN_BLOOD_GROUP " +
                    ",ReceiverEntry.COLUMN_QUANTITY ,ReceiverEntry.COLUMN_PRICE ,ReceiverEntry.COLUMN_DATE )" +
                    "values('" + id + "','" + name + "','" + contactno + "','" + bloodgroup + "','"
                    + quantity + "','" + (quantity * 20) + "','" + currentMonth + "');");
            Log.d("DB", "Inserted");
        } catch (SQLiteConstraintException e) {
            Log.d("DB", "Insert Issue");
        }
    }


*/


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DonorEntry.TABLE_NAME);
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DonorTransactionEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ReceiverEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + DateTable.TABLE_NAME);
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ReceiverTransactionEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}

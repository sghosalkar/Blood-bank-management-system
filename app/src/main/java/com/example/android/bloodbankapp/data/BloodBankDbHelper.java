package com.example.android.bloodbankapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.bloodbankapp.data.BloodBankContract.DateEntry;
import com.example.android.bloodbankapp.data.BloodBankContract.DonorEntry;
import com.example.android.bloodbankapp.data.BloodBankContract.ReceiverEntry;
/**
 * Created by shubham on 17/3/17.
 */

public class BloodBankDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "bloodBank.db";

    public BloodBankDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_DATE_TABLE = "CREATE TABLE " + DateEntry.TABLE_NAME + " (" +
                DateEntry._ID + " INTEGER PRIMARY KEY, " +
                DateEntry.COLUMN_DAY + " INTEGER NOT NULL, " +
                DateEntry.COLUMN_MONTH + " INTEGER NOT NULL, " +
                DateEntry.COLUMN_YEAR + " INTEGER NOT NULL " +
                " );";


        final String SQL_CREATE_DONOR_TABLE = "CREATE TABLE " + DonorEntry.TABLE_NAME + " (" +
                DonorEntry._ID + " INTEGER PRIMARY KEY, " +
                DonorEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                DonorEntry.COLUMN_CONTACT_NO + " TEXT UNIQUE NOT NULL, " +
                DonorEntry.COLUMN_BLOOD_GROUP + " TEXT NOT NULL, " +
                DonorEntry.COLUMN_QUANTITY + " INTEGER NOT NULL, " +
                DonorEntry.COLUMN_PRICE + " INTEGER NOT NULL, " +
                DonorEntry.COLUMN_DATE_KEY + " INTEGER NOT NULL, " +
                " FOREIGN KEY (" + DonorEntry.COLUMN_DATE_KEY + ") REFERENCES " +
                DateEntry.TABLE_NAME + " (" + DateEntry._ID + "));";

        final String SQL_CREATE_RECEIVER_TABLE = "CREATE TABLE " + ReceiverEntry.TABLE_NAME + " (" +
                ReceiverEntry._ID + " INTEGER PRIMARY KEY, " +
                ReceiverEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                ReceiverEntry.COLUMN_CONTACT_NO + " TEXT UNIQUE NOT NULL, " +
                ReceiverEntry.COLUMN_BLOOD_GROUP + " TEXT NOT NULL, " +
                ReceiverEntry.COLUMN_QUANTITY + " INTEGER NOT NULL, " +
                ReceiverEntry.COLUMN_PRICE + " INTEGER NOT NULL, " +
                ReceiverEntry.COLUMN_DATE_KEY + " INTEGER NOT NULL, " +
                " FOREIGN KEY (" + ReceiverEntry.COLUMN_DATE_KEY + ") REFERENCES " +
                DateEntry.TABLE_NAME + " (" + DateEntry._ID + "));";

        sqLiteDatabase.execSQL(SQL_CREATE_DATE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_DONOR_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_RECEIVER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DonorEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ReceiverEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + DateEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}

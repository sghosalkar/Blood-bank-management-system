package com.example.android.bloodbankapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.bloodbankapp.data.BloodBankContract.DonorEntry;
import com.example.android.bloodbankapp.data.BloodBankContract.DonorTransactionEntry;
import com.example.android.bloodbankapp.data.BloodBankContract.ReceiverEntry;
import com.example.android.bloodbankapp.data.BloodBankContract.ReceiverTransactionEntry;

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
        final String SQL_CREATE_DONOR_TABLE = "CREATE TABLE " + DonorEntry.TABLE_NAME + " (" +
                DonorEntry._ID + " INTEGER PRIMARY KEY, " +
                DonorEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                DonorEntry.COLUMN_CONTACT_NO + " TEXT UNIQUE NOT NULL, " +
                DonorEntry.COLUMN_BLOOD_GROUP + " TEXT NOT NULL " +
                " );";

        final String SQL_CREATE_RECEIVER_TABLE = "CREATE TABLE " + ReceiverEntry.TABLE_NAME + " (" +
                ReceiverEntry._ID + " INTEGER PRIMARY KEY, " +
                ReceiverEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                ReceiverEntry.COLUMN_CONTACT_NO + " TEXT UNIQUE NOT NULL " +
                " );";

        final String SQL_CREATE_DONOR_TRANSACTION_TABLE = "CREATE TABLE " + DonorTransactionEntry.TABLE_NAME + " (" +
                DonorTransactionEntry._ID + " INTEGER PRIMARY KEY, " +
                DonorTransactionEntry.COLUMN_DONOR_KEY + " INTEGER NOT NULL, " +
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

        sqLiteDatabase.execSQL(SQL_CREATE_DONOR_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_RECEIVER_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_DONOR_TRANSACTION_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_RECEIVER_TRANSACTION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DonorEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DonorTransactionEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ReceiverEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ReceiverTransactionEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}

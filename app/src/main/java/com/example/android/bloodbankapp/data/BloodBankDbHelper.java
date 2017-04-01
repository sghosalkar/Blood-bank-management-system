package com.example.android.bloodbankapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.bloodbankapp.data.BloodBankContract.DateEntry;
import com.example.android.bloodbankapp.data.BloodBankContract.TransactionEntry;

/**
 * Created by shubham on 17/3/17.
 */

public class BloodBankDbHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = BloodBankDbHelper.class.getSimpleName();

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

        final String SQL_CREATE_TRANSACTION_TABLE = "CREATE TABLE " + TransactionEntry.TABLE_NAME + " (" +
                TransactionEntry._ID + " INTEGER PRIMARY KEY, " +
                TransactionEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                TransactionEntry.COLUMN_CONTACT_NO + " TEXT NOT NULL, " +
                TransactionEntry.COLUMN_TYPE + " TEXT NOT NULL, " +
                TransactionEntry.COLUMN_BLOOD_GROUP + " TEXT NOT NULL, " +
                TransactionEntry.COLUMN_QUANTITY + " INTEGER NOT NULL, " +
                TransactionEntry.COLUMN_PRICE + " INTEGER NOT NULL, " +
                TransactionEntry.COLUMN_DATE_KEY + " INTEGER NOT NULL, " +
                " FOREIGN KEY (" + TransactionEntry.COLUMN_DATE_KEY + ") REFERENCES " +
                DateEntry.TABLE_NAME + " (" + DateEntry._ID + "));";

        sqLiteDatabase.execSQL(SQL_CREATE_DATE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_TRANSACTION_TABLE);
        Log.d(LOG_TAG ,"Tables created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TransactionEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DateEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}

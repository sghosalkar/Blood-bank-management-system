package com.example.android.bloodbankapp.transactions;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.bloodbankapp.R;
import com.example.android.bloodbankapp.data.BloodBankContract;
import com.example.android.bloodbankapp.data.BloodBankDbHelper;
import com.example.android.bloodbankapp.data.TestUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TransactionDetailActivity extends AppCompatActivity {

    SQLiteDatabase mDb;
    Cursor mCursor;
    private static final String sTransactionSelection =
            BloodBankContract.TransactionEntry.TABLE_NAME +
            "." + BloodBankContract.TransactionEntry._ID + " = ? ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_detail);
        Intent intent = getIntent();
        int transactionId = intent.getIntExtra(getString(R.string.id_at_position), 0);

        BloodBankDbHelper dbHelper = new BloodBankDbHelper(this);
        mDb = dbHelper.getReadableDatabase();
        mCursor = getTransactionDetails(transactionId);
        if(mCursor.moveToFirst()) {
            TextView textView = (TextView) findViewById(R.id.transaction_name_textview);
            String name = mCursor.getString(mCursor.getColumnIndex(BloodBankContract.TransactionEntry.COLUMN_NAME));
            String date = getTransactionDate(mCursor.getString(mCursor.getColumnIndex(BloodBankContract.TransactionEntry.COLUMN_DATE_KEY)));
            textView.setText(name + " " + date);
        }
    }

    private Cursor getTransactionDetails(int transactionId) {
        return mDb.query(
                BloodBankContract.TransactionEntry.TABLE_NAME,
                null,
                sTransactionSelection,
                new String[]{String.valueOf(transactionId)},
                null,
                null,
                null
        );
    }

    private String getTransactionDate(String dateId) {

        final String sTransactionDateSelection =
                BloodBankContract.DateEntry.TABLE_NAME +
                        "." + BloodBankContract.DateEntry._ID + " = ? ";

        mCursor = mDb.query(BloodBankContract.DateEntry.TABLE_NAME,
                null,
                sTransactionDateSelection,
                new String[] {dateId},
                null,
                null,
                null,
                null
                );
        mCursor.moveToFirst();
        String transactionDate =
                mCursor.getString(mCursor.getColumnIndex(BloodBankContract.DateEntry.COLUMN_DAY)) + "-" +
                        mCursor.getString(mCursor.getColumnIndex(BloodBankContract.DateEntry.COLUMN_MONTH)) + "-" +
                        mCursor.getString(mCursor.getColumnIndex(BloodBankContract.DateEntry.COLUMN_YEAR));
        return transactionDate;
    }
}

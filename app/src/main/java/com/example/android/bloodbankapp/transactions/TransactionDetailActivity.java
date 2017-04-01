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
            textView.setText(mCursor.getString(mCursor.getColumnIndex(BloodBankContract.TransactionEntry.COLUMN_NAME)));
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
}

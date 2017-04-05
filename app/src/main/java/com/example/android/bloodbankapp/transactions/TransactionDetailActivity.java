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

        TextView nameTextView = (TextView) findViewById(R.id.name_textview);
        TextView contactTextView = (TextView) findViewById(R.id.contact_no_textview);
        TextView typeAndDateTextView = (TextView) findViewById(R.id.type_and_date_textview);
        TextView bloodGroupTextView = (TextView) findViewById(R.id.blood_group_textview);
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_textview);
        TextView priceTextView = (TextView) findViewById(R.id.price_textview);

        BloodBankDbHelper dbHelper = new BloodBankDbHelper(this);
        mDb = dbHelper.getReadableDatabase();
        mCursor = getTransactionDetails(transactionId);
        if(mCursor.moveToFirst()) {
            nameTextView.setText(mCursor.getString(mCursor.getColumnIndex(BloodBankContract.TransactionEntry.COLUMN_NAME)));
            contactTextView.setText(mCursor.getString(mCursor.getColumnIndex(BloodBankContract.TransactionEntry.COLUMN_CONTACT_NO)));
            bloodGroupTextView.setText(mCursor.getString(mCursor.getColumnIndex(BloodBankContract.TransactionEntry.COLUMN_BLOOD_GROUP)));
            String quantityText = "Quantity: " + mCursor.getString(mCursor.getColumnIndex(BloodBankContract.TransactionEntry.COLUMN_QUANTITY));
            quantityTextView.setText(quantityText);
            String priceText = "Amount associated: " + mCursor.getString(mCursor.getColumnIndex(BloodBankContract.TransactionEntry.COLUMN_PRICE));
            priceTextView.setText(priceText);
            if(mCursor.getString(mCursor.getColumnIndex(BloodBankContract.TransactionEntry.COLUMN_TYPE)).equals("Donor")){
                String donorDateText = "Donated on " +
                        getTransactionDate(mCursor.getString(mCursor.getColumnIndex(BloodBankContract.TransactionEntry.COLUMN_DATE_KEY)));
                typeAndDateTextView.setText(donorDateText);
            } else if (mCursor.getString(mCursor.getColumnIndex(BloodBankContract.TransactionEntry.COLUMN_TYPE)).equals("Receiver")) {
                String receiverDateText = "Received on " +
                        getTransactionDate(mCursor.getString(mCursor.getColumnIndex(BloodBankContract.TransactionEntry.COLUMN_DATE_KEY)));
                typeAndDateTextView.setText(receiverDateText);
            } else {
                typeAndDateTextView.setText(
                        mCursor.getString(mCursor.getColumnIndex(BloodBankContract.TransactionEntry.COLUMN_TYPE)) + "    " +
                                getTransactionDate(mCursor.getString(mCursor.getColumnIndex(BloodBankContract.TransactionEntry.COLUMN_DATE_KEY)))
                );
            }

            //TODO: Complete detail view of transaction.
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

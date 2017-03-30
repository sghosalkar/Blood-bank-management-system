package com.example.android.bloodbankapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.android.bloodbankapp.TransactionAdapter;

/**
 * Created by shubham on 28/3/17.
 */

public class TestUtils {

    private static final String LOG_TAG = TestUtils.class.getSimpleName();

    public static void insertFakeData(SQLiteDatabase db) {

        if(db == null) {
            Log.d(LOG_TAG, "No database found");
            return;
        }
        ContentValues contentValues = new ContentValues();

        String[][] dateFakeData = new String[][]{
                {"28", "03", "2017"},
                {"29", "03", "2017"},
                {"30", "03", "2017"}
        };
        String[][] transactionFakeData = new String[][]{
                {"Amar", "9999999999", "donor", "O+", "300", "300", "0"},
                {"Akbar", "2229999999", "donor", "AB+", "300", "300", "1"},
                {"Anthony", "7777779999", "receiver", "A+", "400", "400", "1"},
                {"Jai", "9999888888", "donor", "B+", "300", "200", "2"},
                {"Viru", "0000000000", "receiver", "O-", "400", "400", "2"},
                {"Mogambo", "9999999977", "receiver", "O+", "300", "300", "0"},
                {"Gabbar", "2229999955", "donor", "AB+", "300", "300", "1"},
                {"Pasha", "7777779922", "donor", "A+", "400", "400", "1"},
                {"Don", "9999888833", "receiver", "B+", "300", "200", "2"},
                {"Jaadu", "0000000011", "receiver", "O-", "400", "400", "2"}
        };

        try {
            db.beginTransaction();
            db.delete(BloodBankContract.DateEntry.TABLE_NAME, null, null);
            db.delete(BloodBankContract.TransactionEntry.TABLE_NAME, null, null);
            for(int i = 0; i < 3; i++) {
                contentValues.put(BloodBankContract.DateEntry.COLUMN_DAY, Integer.parseInt(dateFakeData[i][0]));
                contentValues.put(BloodBankContract.DateEntry.COLUMN_MONTH, Integer.parseInt(dateFakeData[i][1]));
                contentValues.put(BloodBankContract.DateEntry.COLUMN_YEAR, Integer.parseInt(dateFakeData[i][2]));
                db.insert(BloodBankContract.DateEntry.TABLE_NAME, null, contentValues);
                contentValues.clear();
            }
            for(int i = 0; i < 10; i++) {
                contentValues.put(BloodBankContract.TransactionEntry.COLUMN_NAME, transactionFakeData[i][0]);
                contentValues.put(BloodBankContract.TransactionEntry.COLUMN_CONTACT_NO, transactionFakeData[i][1]);
                contentValues.put(BloodBankContract.TransactionEntry.COLUMN_TYPE, transactionFakeData[i][2]);
                contentValues.put(BloodBankContract.TransactionEntry.COLUMN_BLOOD_GROUP, transactionFakeData[i][3]);
                contentValues.put(BloodBankContract.TransactionEntry.COLUMN_QUANTITY, Integer.parseInt(transactionFakeData[i][4]));
                contentValues.put(BloodBankContract.TransactionEntry.COLUMN_PRICE, Integer.parseInt(transactionFakeData[i][5]));
                contentValues.put(BloodBankContract.TransactionEntry.COLUMN_DATE_KEY, Integer.parseInt(transactionFakeData[i][6]));
                db.insert(BloodBankContract.TransactionEntry.TABLE_NAME, null, contentValues);
                contentValues.clear();
            }
            db.setTransactionSuccessful();
        }
        catch (SQLException e) {
            Log.d(LOG_TAG, "Check TestUtils file.");
        }
        finally {
            db.endTransaction();
        }
    }

}

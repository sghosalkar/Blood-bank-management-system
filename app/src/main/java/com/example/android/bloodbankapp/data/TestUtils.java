package com.example.android.bloodbankapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by shubham on 28/3/17.
 */

public class TestUtils {

    public static void insertFakeData(SQLiteDatabase db) {

        if(db == null) {
            return;
        }
        ContentValues contentValues = new ContentValues();

        String[][] dateFakeData = new String[][]{
                {"28", "03", "2017"},
                {"29", "03", "2017"},
                {"30", "03", "2017"}
        };
        String[][] donorFakeData = new String[][]{
                {"Amar", "9999999999", "O+", "300", "300", "0"},
                {"Akbar", "2229999999", "AB+", "300", "300", "1"},
                {"Anthony", "7777779999", "A+", "400", "400", "1"},
                {"Mogambo", "9999888888", "B+", "300", "200", "2"},
                {"Pasha", "0000000000", "O-", "400", "400", "2"}
        };
        String[][] receiverFakeData = new String[][]{
                {"Amar", "9999999999", "O+", "300", "300", "0"},
                {"Akbar", "2229999999", "AB+", "300", "300", "1"},
                {"Anthony", "7777779999", "A+", "400", "400", "1"},
                {"Mogambo", "9999888888", "B+", "300", "200", "2"},
                {"Pasha", "0000000000", "O-", "400", "400", "2"}
        };

        try {
            db.beginTransaction();
            db.delete(BloodBankContract.DateEntry.TABLE_NAME, null, null);
            for(int i = 0; i < 3; i++) {
                contentValues.put(BloodBankContract.DateEntry.COLUMN_DAY, dateFakeData[i][0]);
                contentValues.put(BloodBankContract.DateEntry.COLUMN_DAY, dateFakeData[i][1]);
                contentValues.put(BloodBankContract.DateEntry.COLUMN_DAY, dateFakeData[i][2]);
                db.insert(BloodBankContract.DateEntry.TABLE_NAME, null, contentValues);
            }
            for(int i = 0; i < 5; i++) {
                contentValues.put(BloodBankContract.DonorEntry.COLUMN_NAME, donorFakeData[i][0]);
                contentValues.put(BloodBankContract.DonorEntry.COLUMN_CONTACT_NO, donorFakeData[i][1]);
                contentValues.put(BloodBankContract.DonorEntry.COLUMN_BLOOD_GROUP, donorFakeData[i][2]);
                contentValues.put(BloodBankContract.DonorEntry.COLUMN_QUANTITY, donorFakeData[i][3]);
                contentValues.put(BloodBankContract.DonorEntry.COLUMN_PRICE, donorFakeData[i][4]);
                contentValues.put(BloodBankContract.DonorEntry.COLUMN_DATE_KEY, donorFakeData[i][5]);
                db.insert(BloodBankContract.DonorEntry.TABLE_NAME, null, contentValues);
            }

            for(int i = 0; i < 5; i++) {
                contentValues.put(BloodBankContract.ReceiverEntry.COLUMN_NAME, receiverFakeData[i][0]);
                contentValues.put(BloodBankContract.ReceiverEntry.COLUMN_CONTACT_NO, receiverFakeData[i][1]);
                contentValues.put(BloodBankContract.ReceiverEntry.COLUMN_BLOOD_GROUP, receiverFakeData[i][2]);
                contentValues.put(BloodBankContract.ReceiverEntry.COLUMN_QUANTITY, receiverFakeData[i][3]);
                contentValues.put(BloodBankContract.ReceiverEntry.COLUMN_PRICE, receiverFakeData[i][4]);
                contentValues.put(BloodBankContract.ReceiverEntry.COLUMN_DATE_KEY, receiverFakeData[i][5]);
                db.insert(BloodBankContract.ReceiverEntry.TABLE_NAME, null, contentValues);
            }
            db.setTransactionSuccessful();
        }
        catch (SQLException e) {

        }
        finally {
            db.endTransaction();
        }




    }

}

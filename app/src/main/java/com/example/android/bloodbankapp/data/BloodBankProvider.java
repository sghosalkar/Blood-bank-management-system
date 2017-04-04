package com.example.android.bloodbankapp.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.android.bloodbankapp.Constants;
import com.example.android.bloodbankapp.R;
import com.example.android.bloodbankapp.Utility;
import com.example.android.bloodbankapp.apiService.BloodDataInsertionApi;
import com.example.android.bloodbankapp.apiService.TransactionFetchApi;
import com.example.android.bloodbankapp.model.Transaction;
import com.example.android.bloodbankapp.transactions.TransactionsFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shubham on 17/3/17.
 */

public class BloodBankProvider{

    public static final String LOG_TAG = BloodBankProvider.class.getSimpleName();

    /**
     * @param bloodBankId Set blood bank ID here
     *                    Add an option for setting blood bank id in app options menu for testing purpose
     */
    public static final int bloodBankId = 1;

    SQLiteDatabase mDb;
    Context mContext;

    public BloodBankProvider(Context context) {
        mContext = context;
        BloodBankDbHelper dbHelper = new BloodBankDbHelper(mContext);
        mDb = dbHelper.getWritableDatabase();
    }

    public void addTransactionInDb(ContentValues values, String[] date) {

        final String sDateSelection =
                BloodBankContract.DateEntry.TABLE_NAME +
                        "." + BloodBankContract.DateEntry.COLUMN_DAY + " = ? AND " +
                        BloodBankContract.DateEntry.TABLE_NAME +
                        "." + BloodBankContract.DateEntry.COLUMN_MONTH + " = ? AND " +
                        BloodBankContract.DateEntry.TABLE_NAME +
                        "." + BloodBankContract.DateEntry.COLUMN_YEAR + " = ?";

        String[] sDateSelectionArgs = date;

        Cursor dateCursor = mDb.query(BloodBankContract.DateEntry.TABLE_NAME,
                new String[] {BloodBankContract.DateEntry._ID},
                sDateSelection,
                sDateSelectionArgs,
                null,
                null,
                null
        );
        long dateId;
        if (!dateCursor.moveToFirst()) {
            ContentValues dateValues=new ContentValues();
            dateValues.put(BloodBankContract.DateEntry.COLUMN_DAY, sDateSelectionArgs[0]);
            dateValues.put(BloodBankContract.DateEntry.COLUMN_MONTH, sDateSelectionArgs[1]);
            dateValues.put(BloodBankContract.DateEntry.COLUMN_YEAR, sDateSelectionArgs[2]);
            dateId = mDb.insert(BloodBankContract.DateEntry.TABLE_NAME, null, dateValues);
        } else {
            dateId = dateCursor.getInt(dateCursor.getColumnIndex(BloodBankContract.DateEntry._ID));
        }
        values.put(BloodBankContract.TransactionEntry.COLUMN_DATE_KEY, dateId);
        long rowId = mDb.insert(BloodBankContract.TransactionEntry.TABLE_NAME, null, values);
    }

    public Cursor getAllTransactionsFromDb() {

        return mDb.query(
                BloodBankContract.TransactionEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                BloodBankContract.TransactionEntry._ID + " DESC"
        );
    }

    public void sendTransactionDataToServer(ContentValues contentValues) {

        //Specify your own ip address here only while testing.
        //Revert to this IP back before committing.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://" + Constants.IP_ADDRESS + ":" + Constants.PORT_NUMBER + "/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BloodDataInsertionApi bloodDataInsertionApi = retrofit.create(BloodDataInsertionApi.class);

        Transaction transaction = new Transaction();
        transaction.setBloodbankId(bloodBankId);
        transaction.setName(contentValues.getAsString(BloodBankContract.TransactionEntry.COLUMN_NAME));
        transaction.setContactNo(contentValues.getAsString(BloodBankContract.TransactionEntry.COLUMN_CONTACT_NO));
        transaction.setType(contentValues.getAsString(BloodBankContract.TransactionEntry.COLUMN_TYPE));
        transaction.setBloodGroup(contentValues.getAsString(BloodBankContract.TransactionEntry.COLUMN_BLOOD_GROUP));
        transaction.setQuantity(contentValues.getAsInteger(BloodBankContract.TransactionEntry.COLUMN_QUANTITY));
        transaction.setPrice(contentValues.getAsInteger(BloodBankContract.TransactionEntry.COLUMN_PRICE));

        String[] dateString = Utility.getCurrentDate();
        transaction.setDay(Integer.parseInt(dateString[0]));
        transaction.setMonth(Integer.parseInt(dateString[1]));
        transaction.setYear(Integer.parseInt(dateString[2]));

        Call<Transaction> transactionCall = bloodDataInsertionApi.setTransaction(
                transaction.getBloodbankId(),
                transaction.getName(),
                transaction.getContactNo(),
                transaction.getType(),
                transaction.getBloodGroup(),
                transaction.getQuantity(),
                transaction.getPrice(),
                transaction.getDay(),
                transaction.getMonth(),
                transaction.getYear()
        );

        transactionCall.enqueue(new Callback<Transaction>() {
            @Override
            public void onResponse(Call<Transaction> call, Response<Transaction> response) {
                Log.d("onResponse", "" + response.code() +
                        "  response body "  + response.body() +
                        " responseError " + response.errorBody() + " responseMessage " +
                        response.message());
            }

            @Override
            public void onFailure(Call<Transaction> call, Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });
    }

    public void getAllTransactionsFromServer() {

        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://" + Constants.IP_ADDRESS + ":" + Constants.PORT_NUMBER + "/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            TransactionFetchApi transactionFetchApi = retrofit.create(TransactionFetchApi.class);

            Call<List<Transaction>> transactionListCall = transactionFetchApi.getAllTransactions(bloodBankId);

            transactionListCall.enqueue(new Callback<List<Transaction>>() {
                @Override
                public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                    List<Transaction> transactionList = response.body();
                    Log.d("JSON", response.body().toString());
                    try {
                        deleteAllTransactionsFromDb();
                        for (Transaction transaction: transactionList) {
                            if(transaction.getName().equals("close")) {
                                break;
                            }
                            ContentValues contentValues = new ContentValues();
                            contentValues.put(BloodBankContract.TransactionEntry.COLUMN_NAME, transaction.getName());
                            contentValues.put(BloodBankContract.TransactionEntry.COLUMN_CONTACT_NO, transaction.getContactNo());
                            contentValues.put(BloodBankContract.TransactionEntry.COLUMN_TYPE, transaction.getType());
                            contentValues.put(BloodBankContract.TransactionEntry.COLUMN_BLOOD_GROUP, transaction.getBloodGroup());
                            contentValues.put(BloodBankContract.TransactionEntry.COLUMN_QUANTITY, transaction.getQuantity());
                            contentValues.put(BloodBankContract.TransactionEntry.COLUMN_PRICE, transaction.getPrice());
                            String[] date = new String[] {
                                    String.valueOf(transaction.getDay()),
                                    String.valueOf(transaction.getMonth()),
                                    String.valueOf(transaction.getYear())
                            };
                            addTransactionInDb(contentValues, date);
                        }
                    } catch (Exception e) {
                        Log.d("Server response:", "No data found");
                    }
                }

                @Override
                public void onFailure(Call<List<Transaction>> call, Throwable t) {
                    deleteAllTransactionsFromDb();
                    Log.d("onFailure", t.toString());
                }
            });

        } catch (Exception e) {
            Log.d(LOG_TAG, e.getMessage());
        }
    }

    //Danger! Never use this!
    public void deleteAllTransactionsFromDb() {
        mDb.delete(BloodBankContract.TransactionEntry.TABLE_NAME, null, null);
    }

    //Danger! Never use this!
    public void deleteAllDatesFromDb() {
        mDb.delete(BloodBankContract.DateEntry.TABLE_NAME, null, null);
    }


}

package com.example.android.bloodbankapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.bloodbankapp.data.BloodBankContract;
import com.example.android.bloodbankapp.data.BloodBankDbHelper;
import com.example.android.bloodbankapp.data.TestUtils;

public class TransactionsFragment extends Fragment implements TransactionAdapter.ListItemClickListener {

    public static final String LOG_TAG = TransactionsFragment.class.getSimpleName();
    private TransactionAdapter mTransactionAdapter;
    private RecyclerView mTransactionList;

    SQLiteDatabase mDb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_transactions, container, false);

        mTransactionList = (RecyclerView) rootView.findViewById(R.id.recyclerview_transactions);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        mTransactionList.setLayoutManager(layoutManager);
        mTransactionList.setHasFixedSize(true);

        //Fetch data from database
        BloodBankDbHelper dbHelper = new BloodBankDbHelper(this.getContext());
        mDb = dbHelper.getWritableDatabase();
        TestUtils.insertFakeData(mDb);
        Cursor cursor = getAllTransactions();
        mTransactionAdapter = new TransactionAdapter(cursor, this);
        mTransactionList.setAdapter(mTransactionAdapter);
        return rootView;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        //handler for item click
        Intent intent = new Intent(getActivity(), TransactionDetailActivity.class);
        startActivity(intent);
    }

    private Cursor getAllTransactions() {
        //Query for retrieving all transactions (modification required)
        return mDb.query(
                BloodBankContract.DonorEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }
}

package com.example.android.bloodbankapp.transactions;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.bloodbankapp.R;
import com.example.android.bloodbankapp.apiService.BloodDataInsertionApi;
import com.example.android.bloodbankapp.apiService.TransactionFetchApi;
import com.example.android.bloodbankapp.data.BloodBankContract;
import com.example.android.bloodbankapp.data.BloodBankDbHelper;
import com.example.android.bloodbankapp.data.BloodBankProvider;
import com.example.android.bloodbankapp.model.Transaction;
import com.example.android.bloodbankapp.model.TransactionList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TransactionsFragment extends Fragment implements TransactionAdapter.ListItemClickListener {

    public static final String LOG_TAG = TransactionsFragment.class.getSimpleName();
    private TransactionAdapter mTransactionAdapter;
    private RecyclerView mTransactionList;
    Cursor mCursor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_transactions, container, false);

        mTransactionList = (RecyclerView) rootView.findViewById(R.id.recyclerview_transactions);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        mTransactionList.setLayoutManager(layoutManager);
        mTransactionList.setHasFixedSize(true);

        //Fetch data from database
        BloodBankProvider bloodBankProvider = new BloodBankProvider(getContext());
        //TestUtils.insertFakeData(mDb);
        mCursor = bloodBankProvider.getAllTransactionsFromDb();
        bloodBankProvider.getAllTransactionsFromServer();
        mTransactionAdapter = new TransactionAdapter(mCursor, this);
        mTransactionList.setAdapter(mTransactionAdapter);
        return rootView;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        //handler for item click
        Intent intent = new Intent(getActivity(), TransactionDetailActivity.class);
        mCursor.moveToPosition(clickedItemIndex);
        int idAtPosition = mCursor.getInt(mCursor.getColumnIndex(BloodBankContract.TransactionEntry._ID));
        intent.putExtra(getString(R.string.id_at_position), idAtPosition);
        startActivity(intent);
    }
}

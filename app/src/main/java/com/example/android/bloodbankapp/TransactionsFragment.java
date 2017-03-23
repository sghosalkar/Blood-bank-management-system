package com.example.android.bloodbankapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TransactionsFragment extends Fragment {

    public static final String LOG_TAG = TransactionsFragment.class.getSimpleName();
    private static final int TRANS_LIST_ITEMS = 100;
    private TransactionAdapter mTransactionAdapter;
    private RecyclerView mTransactionList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_transactions, container, false);

        mTransactionList = (RecyclerView) rootView.findViewById(R.id.recyclerview_transactions);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        mTransactionList.setLayoutManager(layoutManager);
        mTransactionList.setHasFixedSize(true);
        mTransactionAdapter = new TransactionAdapter(TRANS_LIST_ITEMS);
        mTransactionList.setAdapter(mTransactionAdapter);
//        mTransactionAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast toast = Toast.makeText(getContext(), "Here", Toast.LENGTH_SHORT);
//                toast.show();
//            }
//        });
        return rootView;
    }
}

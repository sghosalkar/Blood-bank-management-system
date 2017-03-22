package com.example.android.bloodbankapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class TransactionsFragment extends Fragment {

    public static final String LOG_TAG = TransactionsFragment.class.getSimpleName();
    private TransactionAdapter mTransactionAdapter;
    private ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mTransactionAdapter = new TransactionAdapter(getActivity(), null, 0);

        View rootView = inflater.inflate(R.layout.fragment_transactions, container, false);

        mListView = (ListView) rootView.findViewById(R.id.listview_transactions);
        mListView.setAdapter(mTransactionAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast toast = Toast.makeText(getContext(), "Here", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        return rootView;
    }
}

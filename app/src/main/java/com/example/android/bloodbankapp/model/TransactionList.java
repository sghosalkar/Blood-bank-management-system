package com.example.android.bloodbankapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by shubham on 3/4/17.
 */

public class TransactionList {

    @SerializedName("transactionData")
    private List<Transaction> transactions;

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}

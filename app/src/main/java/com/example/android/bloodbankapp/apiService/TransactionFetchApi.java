package com.example.android.bloodbankapp.apiService;

import com.example.android.bloodbankapp.model.Transaction;
import com.example.android.bloodbankapp.model.TransactionList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by shubham on 3/4/17.
 */

public interface TransactionFetchApi {

    //TODO: Implement fetching data from server

    @FormUrlEncoded
    @POST("fetchAllTransactions.php")
    Call<List<Transaction>> getAllTransactions(
            @Field("bloodBankId") int bloodBankId
    );

}

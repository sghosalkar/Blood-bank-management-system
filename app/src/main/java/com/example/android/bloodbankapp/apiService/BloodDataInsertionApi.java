package com.example.android.bloodbankapp.apiService;

import com.example.android.bloodbankapp.model.Transaction;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by shubham on 2/4/17.
 */

public interface BloodDataInsertionApi {

    @FormUrlEncoded
    @POST("insertTransaction.php")
    Call<Transaction> setTransaction(@Field("bloodBankId") int bloodBankId,
                                     @Field("name") String name,
                                     @Field("contactNo") String contactNo,
                                     @Field("type") String type,
                                     @Field("bloodGroup") String bloodGroup,
                                     @Field("quantity") int quantity,
                                     @Field("price") int price,
                                     @Field("day") int day,
                                     @Field("month") int month,
                                     @Field("year") int year
    );

}

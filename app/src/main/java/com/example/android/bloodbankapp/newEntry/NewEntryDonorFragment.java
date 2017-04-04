package com.example.android.bloodbankapp.newEntry;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.bloodbankapp.R;
import com.example.android.bloodbankapp.Utility;
import com.example.android.bloodbankapp.apiService.BloodDataInsertionApi;
import com.example.android.bloodbankapp.data.BloodBankContract;
import com.example.android.bloodbankapp.data.BloodBankDbHelper;
import com.example.android.bloodbankapp.data.BloodBankProvider;
import com.example.android.bloodbankapp.data.TestUtils;
import com.example.android.bloodbankapp.model.Transaction;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jaihi on 3/27/2017.
 */

public class NewEntryDonorFragment extends Fragment {

    EditText editTextDonorName, editTextContactNo, editTextQuantity, editTextPrice;
    Spinner spinnerBloodGroup;
    Button buttonSave;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_new_entry_donor, container, false);
        editTextDonorName = (EditText) view.findViewById(R.id.et_dname);
        editTextContactNo = (EditText) view.findViewById(R.id.et_dphone);
        editTextQuantity = (EditText) view.findViewById(R.id.et_dquantity);
        editTextPrice = (EditText) view.findViewById(R.id.et_dprice);
        spinnerBloodGroup = (Spinner) view.findViewById(R.id.sp_bldgrp);
        buttonSave = (Button) view.findViewById(R.id.btn_save);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String name = editTextDonorName.getText().toString();
                    String contactNo = editTextContactNo.getText().toString();
                    Integer quantity = Integer.parseInt(editTextQuantity.getText().toString());
                    Integer price = Integer.parseInt(editTextPrice.getText().toString());
                    String bloodGroup = spinnerBloodGroup.getSelectedItem().toString();

                    ContentValues values = new ContentValues();
                    values.put(BloodBankContract.TransactionEntry.COLUMN_NAME, name);
                    values.put(BloodBankContract.TransactionEntry.COLUMN_CONTACT_NO, contactNo);
                    values.put(BloodBankContract.TransactionEntry.COLUMN_TYPE, getString(R.string.actor_type_donor));
                    values.put(BloodBankContract.TransactionEntry.COLUMN_BLOOD_GROUP, bloodGroup);
                    values.put(BloodBankContract.TransactionEntry.COLUMN_QUANTITY, quantity);
                    values.put(BloodBankContract.TransactionEntry.COLUMN_PRICE, price);
                    String[] currentDate = Utility.getCurrentDate();
                    BloodBankProvider bloodBankProvider = new BloodBankProvider(getContext());
                    bloodBankProvider.addTransactionInDb(values, currentDate);
                    bloodBankProvider.sendTransactionDataToServer(values);
                    Toast.makeText(getContext(), "Data Saved", Toast.LENGTH_SHORT).show();
                } catch (Exception ex) {
                    Toast.makeText(getContext(), "Please Enter Proper Details", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    //TODO: Switch to AsyncTask or Service.

}
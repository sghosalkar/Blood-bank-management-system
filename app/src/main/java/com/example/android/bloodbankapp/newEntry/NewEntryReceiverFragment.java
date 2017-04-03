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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.bloodbankapp.R;
import com.example.android.bloodbankapp.Utility;
import com.example.android.bloodbankapp.data.BloodBankContract;
import com.example.android.bloodbankapp.data.BloodBankDbHelper;
import com.example.android.bloodbankapp.data.BloodBankProvider;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by jaihi on 3/27/2017.
 */

public class NewEntryReceiverFragment extends Fragment {

    EditText et_rname, et_rphoneno,et_rquantity,et_rprice;
    Spinner sp_rdbloodgroup;
    Button btn_rsave;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_new_entry_receiver, container, false);
        // Inflate the layout for this fragment
        et_rname= (EditText) view.findViewById(R.id.et_rname);
        et_rphoneno= (EditText) view.findViewById(R.id.et_rphone);
        et_rquantity= (EditText) view.findViewById(R.id.et_rquantity);
        et_rprice= (EditText) view.findViewById(R.id.et_rprice);
        sp_rdbloodgroup= (Spinner) view.findViewById(R.id.sp_rbldgrp);
        btn_rsave= (Button) view.findViewById(R.id.btn_rsave);
        btn_rsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String rname = et_rname.getText().toString();
                    String rphoneno = et_rphoneno.getText().toString();
                    Integer quantity = Integer.parseInt(et_rquantity.getText().toString());
                    Integer price = Integer.parseInt(et_rprice.getText().toString());
                    String bldgrp = sp_rdbloodgroup.getSelectedItem().toString();
                    ContentValues values = new ContentValues();
                    values.put(BloodBankContract.TransactionEntry.COLUMN_NAME, rname);
                    values.put(BloodBankContract.TransactionEntry.COLUMN_CONTACT_NO, rphoneno);
                    values.put(BloodBankContract.TransactionEntry.COLUMN_TYPE, getString(R.string.actor_type_receiver));
                    values.put(BloodBankContract.TransactionEntry.COLUMN_BLOOD_GROUP, bldgrp);
                    values.put(BloodBankContract.TransactionEntry.COLUMN_QUANTITY, quantity);
                    values.put(BloodBankContract.TransactionEntry.COLUMN_PRICE, price);
                    String[] currentDate = Utility.getCurrentDate();
                    BloodBankProvider bloodBankProvider = new BloodBankProvider(getContext());
                    bloodBankProvider.addTransactionInDb(values, currentDate);
                    bloodBankProvider.sendTransactionDataToServer(values);
                    Toast.makeText(getContext(), "Data Saved", Toast.LENGTH_SHORT).show();
                } catch (Exception ex) {
                    Toast.makeText(getContext(), "Please Enter Details", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    //TODO: Switch to AsyncTask or Service.
}
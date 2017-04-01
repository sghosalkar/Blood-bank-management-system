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
import com.example.android.bloodbankapp.data.BloodBankContract;
import com.example.android.bloodbankapp.data.BloodBankDbHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by jaihi on 3/27/2017.
 */

public class NewEntryDonorFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    EditText et_dname, et_dphoneno,et_dquantity,et_price;
    Spinner sp_dbloodgroup;
    Button btn_save;

    SQLiteDatabase mdb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_new_entry_donor, container, false);
        et_dname= (EditText) view.findViewById(R.id.et_dname);
        et_dphoneno= (EditText) view.findViewById(R.id.et_dphone);
        et_dquantity= (EditText) view.findViewById(R.id.et_dquantity);
        et_price= (EditText) view.findViewById(R.id.et_dprice);
        sp_dbloodgroup= (Spinner) view.findViewById(R.id.sp_bldgrp);
        btn_save= (Button) view.findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String dname = et_dname.getText().toString();
                    String dphoneno = et_dphoneno.getText().toString();
                    Integer quantity = Integer.parseInt(et_dquantity.getText().toString());
                    Integer price = Integer.parseInt(et_price.getText().toString());
                    String bldgrp = sp_dbloodgroup.getSelectedItem().toString();
                    BloodBankDbHelper db=new BloodBankDbHelper(getActivity());
                    mdb=db.getWritableDatabase();
                    addDonor(dname,dphoneno,quantity,price,getString(R.string.actor_type_donor),bldgrp);
                    Toast.makeText(getContext(), "Data Saved", Toast.LENGTH_SHORT).show();
                } catch (Exception ex) {
                    Toast.makeText(getContext(), "Please Enter Proper Details", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    private void addDonor(String dname, String dphoneno, Integer quantity, Integer price, String type, String bldgrp) {

        String Query=" Select "+ BloodBankContract.DateEntry._ID + " , " + BloodBankContract.DateEntry.COLUMN_DAY
                + " , "+BloodBankContract.DateEntry.COLUMN_MONTH + " , " + BloodBankContract.DateEntry.COLUMN_YEAR +
                " from "+BloodBankContract.DateEntry.TABLE_NAME +" ; ";

        Cursor c=mdb.rawQuery(Query,null);
        c.moveToFirst();
        int a = -1;
        int dateid = 0;
        if (c.getCount() > 0 && c!=null){
            Calendar calender = Calendar.getInstance();
            SimpleDateFormat df=new SimpleDateFormat("dd-MM-yyyy");
            String currentDate = df.format(calender.getTime());
            String current[] = currentDate.split("-");
            int currentDay = Integer.parseInt(current[0]);
            int currentMonth = Integer.parseInt(current[1]);
            int currentYear=Integer.parseInt(current[2]);
            for(int i=0;i<c.getCount();i++) {
                if (c.getInt(1) == currentDay && c.getInt(2) == currentMonth && c.getInt(3) == currentYear) {
                    a = c.getInt(0);
                }
                c.moveToNext();
            }
            if(a == -1){
                ContentValues val=new ContentValues();
                val.put(BloodBankContract.DateEntry.COLUMN_DAY,currentDay);
                val.put(BloodBankContract.DateEntry.COLUMN_MONTH,currentMonth);
                val.put(BloodBankContract.DateEntry.COLUMN_YEAR,currentYear);
                mdb.insert(BloodBankContract.DateEntry.TABLE_NAME,null,val);
                String q="Select "+BloodBankContract.DateEntry._ID + " from " + BloodBankContract.DateEntry.TABLE_NAME +
                        " where "+BloodBankContract.DateEntry.COLUMN_DAY+" = "+currentDay +
                        " and "+BloodBankContract.DateEntry.COLUMN_MONTH+" = "+currentMonth+
                        " and "+BloodBankContract.DateEntry.COLUMN_YEAR+" = "+currentYear+" ; ";
                Cursor cdate=mdb.rawQuery(q,null);
                dateid=cdate.getInt(0);
                Log.d("bb",dateid+"datemilgaya");
            }
            else
                dateid=a;
        }
        ContentValues values=new ContentValues();
        values.put(BloodBankContract.TransactionEntry.COLUMN_NAME,dname);
        values.put(BloodBankContract.TransactionEntry.COLUMN_CONTACT_NO,dphoneno);
        values.put(BloodBankContract.TransactionEntry.COLUMN_TYPE,type);
        values.put(BloodBankContract.TransactionEntry.COLUMN_BLOOD_GROUP,bldgrp);
        values.put(BloodBankContract.TransactionEntry.COLUMN_QUANTITY,quantity);
        values.put(BloodBankContract.TransactionEntry.COLUMN_PRICE,price);
        values.put(BloodBankContract.TransactionEntry.COLUMN_DATE_KEY,dateid);
        long rid = mdb.insert(BloodBankContract.TransactionEntry.TABLE_NAME,null,values);
        if (rid<0)
            Log.d("bb","issue");
        else{
            Log.d("bb","inserted");
            Log.d("bb",dateid+"bjm");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
package com.example.android.bloodbankapp.statistics;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.android.bloodbankapp.R;
import com.example.android.bloodbankapp.data.BloodBankContract;
import com.example.android.bloodbankapp.data.BloodBankDbHelper;

public class BloodReportDetails extends AppCompatActivity {

    TextView tv_apos,tv_aposin,tv_aposout,tv_aneg,tv_anegin,tv_anegout;
    TextView tv_bpos,tv_bposin,tv_bposout,tv_bneg,tv_bnegin,tv_bnegout,tv_abpos,tv_abposin,tv_abposout,tv_abneg,tv_abnegin,tv_abnegout,tv_opos,tv_oposin,tv_oposout,tv_oneg,tv_onegin,tv_onegout;
    int month= 3;
    SQLiteDatabase mdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_report_details);

        tv_apos= (TextView) findViewById(R.id.tv_apos);
        tv_aposin= (TextView) findViewById(R.id.tv_aposin);
        tv_aposout= (TextView) findViewById(R.id.tv_aposout);
        tv_aneg= (TextView) findViewById(R.id.tv_aneg);
        tv_anegin= (TextView) findViewById(R.id.tv_anegin);
        tv_anegout= (TextView) findViewById(R.id.tv_anegout);

        tv_bpos= (TextView) findViewById(R.id.tv_bpos);
        tv_bposin= (TextView) findViewById(R.id.tv_bposin);
        tv_bposout= (TextView) findViewById(R.id.tv_bposout);
        tv_bneg= (TextView) findViewById(R.id.tv_bneg);
        tv_bnegin= (TextView) findViewById(R.id.tv_bnegin);
        tv_bnegout= (TextView) findViewById(R.id.tv_bnegout);

        tv_abpos= (TextView) findViewById(R.id.tv_abpos);
        tv_abposin= (TextView) findViewById(R.id.tv_abposin);
        tv_abposout= (TextView) findViewById(R.id.tv_abposout);
        tv_abneg= (TextView) findViewById(R.id.tv_abneg);
        tv_abnegin= (TextView) findViewById(R.id.tv_abnegin);
        tv_abnegout= (TextView) findViewById(R.id.tv_abnegout);

        tv_opos= (TextView) findViewById(R.id.tv_opos);
        tv_oposin= (TextView) findViewById(R.id.tv_oposin);
        tv_oposout= (TextView) findViewById(R.id.tv_oposout);
        tv_oneg= (TextView) findViewById(R.id.tv_oneg);
        tv_onegin= (TextView) findViewById(R.id.tv_onegin);
        tv_onegout= (TextView) findViewById(R.id.tv_onegout);


        Intent i=getIntent();
        //month=i.getExtras("Month",month);

        BloodBankDbHelper db=new BloodBankDbHelper(getApplicationContext());
        mdb=db.getWritableDatabase();
        Cursor c=getBloodGroupDetails();
        c.moveToFirst();
        int bldinapos=0,bldinaneg=0,bldinbpos=0,bldinbneg=0,bldinabpos=0,bldinabneg=0,bldinopos=0,bldinoneg=0;
        int bldoutapos=0,bldoutaneg=0,bldoutbpos=0,bldoutbneg=0,bldoutabpos=0,bldoutabneg=0,bldoutopos=0,bldoutoneg=0;
        for (int j=0; j<c.getCount();j++){
            if (c.getString(2) == "donor")   {
                if(c.getString(0) == "A+")
                    bldinapos=bldinapos+c.getInt(1);
                else if(c.getString(0) == "A-")
                    bldinaneg=bldinaneg+c.getInt(1);
                if(c.getString(0) == "B+")
                    bldinbpos=bldinbpos+c.getInt(1);
                else if(c.getString(0) == "B-")
                    bldinbneg=bldinbneg+c.getInt(1);
                if(c.getString(0) == "AB+")
                    bldinabpos=bldinabpos+c.getInt(1);
                else if(c.getString(0) == "AB-")
                    bldinabneg=bldinabneg+c.getInt(1);
                if(c.getString(0) == "O+")
                    bldinopos=bldinopos+c.getInt(1);
                else if(c.getString(0) == "O-")
                    bldinoneg=bldinoneg+c.getInt(1);

            }else{
                if(c.getString(0) == "A+")
                    bldoutapos=bldoutapos+c.getInt(1);
                else if(c.getString(0) == "A-")
                    bldoutaneg=bldoutaneg+c.getInt(1);
                if(c.getString(0) == "B+")
                    bldoutbpos=bldoutbpos+c.getInt(1);
                else if(c.getString(0) == "B-")
                    bldoutbneg=bldoutbneg+c.getInt(1);
                if(c.getString(0) == "AB+")
                    bldoutabpos=bldoutabpos+c.getInt(1);
                else if(c.getString(0) == "AB-")
                    bldoutabneg=bldoutabneg+c.getInt(1);
                if(c.getString(0) == "O+")
                    bldoutopos=bldoutopos+c.getInt(1);
                else if(c.getString(0) == "O-")
                    bldoutoneg=bldoutoneg+c.getInt(1);

            }

            Log.d("bb",bldinabneg+"hi");
            Log.d("bb",bldinabpos+"hi");

            Log.d("bb",bldinaneg+"hi");
            Log.d("bb",bldinapos+"hi");
            Log.d("bb",bldinbneg+"hi");
            Log.d("bb",bldinbpos+"hi");
            Log.d("bb",bldinoneg+"hi");
            Log.d("bb",bldinopos+"hi");




        }


        tv_aposin.setText(bldinapos);
        tv_aposout.setText(bldinapos);
        tv_bposin.setText(bldinbpos);
        tv_bposout.setText(bldoutbpos);
        tv_abposin.setText(bldinabpos);
        tv_abposout.setText(bldoutabpos);
        tv_oposin.setText(bldinopos);
        tv_oposout.setText(bldoutopos);
        tv_anegin.setText(bldinaneg);
        tv_anegout.setText(bldinaneg);
        tv_bnegin.setText(bldinbneg);
        tv_bnegout.setText(bldoutbneg);
        tv_abnegin.setText(bldinabneg);
        tv_abnegout.setText(bldoutabneg);
        tv_onegin.setText(bldinoneg);
        tv_onegout.setText(bldoutoneg);



    }

    private Cursor getBloodGroupDetails(){

        String q=" Select "+BloodBankContract.TransactionEntry.COLUMN_BLOOD_GROUP
                +" , "+BloodBankContract.TransactionEntry.COLUMN_QUANTITY
                +" , "+BloodBankContract.TransactionEntry.COLUMN_TYPE
                +" from "+BloodBankContract.TransactionEntry.TABLE_NAME+" where "
                +BloodBankContract.TransactionEntry.COLUMN_DATE_KEY+" IN ( Select "+BloodBankContract.DateEntry._ID + " FROM "
                +BloodBankContract.DateEntry.TABLE_NAME+" where "+BloodBankContract.DateEntry.COLUMN_MONTH+"  = "+month+") ; ";

        Cursor c=mdb.rawQuery(q,null);

        return  c;
    }
}

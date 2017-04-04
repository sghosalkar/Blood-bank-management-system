package com.example.android.bloodbankapp.statistics;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.bloodbankapp.R;
import com.example.android.bloodbankapp.data.BloodBankContract;
import com.example.android.bloodbankapp.data.BloodBankDbHelper;

import java.util.Locale;

public class BloodReportDetails extends AppCompatActivity {

    TextView tv_apos,tv_aposin,tv_aposout,tv_aneg,tv_anegin,tv_anegout;
    TextView tv_bpos,tv_bposin,tv_bposout,tv_bneg,tv_bnegin,tv_bnegout,tv_abpos,tv_abposin,tv_abposout,tv_abneg,tv_abnegin,tv_abnegout,tv_opos,tv_oposin,tv_oposout,tv_oneg,tv_onegin,tv_onegout;
    int month1;
    String month;
    TextToSpeech tts;
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
        month=i.getStringExtra("Month");
        Toast.makeText(this, month+" Blood Group Details", Toast.LENGTH_SHORT).show();
        tts =new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if ( i != TextToSpeech.ERROR)
                    tts.setLanguage(Locale.UK);
            }
        });
        tts.speak(month,TextToSpeech.QUEUE_FLUSH,null);
        if (month.equals("January"))
            month1=1;
         else if (month.equals("February"))
             month1=2;
        else if (month.equals("March"))
            month1=3;
        else if (month.equals("April"))
            month1=4;
        else if (month.equals("May"))
            month1=5;
        else if (month.equals("June"))
            month1=6;
        else if (month.equals("July"))
            month1=7;
        else if (month.equals("August"))
            month1=8;
        else if (month.equals("September"))
            month1=9;
        else if(month.equals("October"))
            month1=10;
        else if (month.equals("November"))
            month1=11;
        else if (month.equals("December"))
            month1=12;

        BloodBankDbHelper db=new BloodBankDbHelper(getApplicationContext());
        mdb=db.getWritableDatabase();
        Cursor c=getBloodGroupDetails();
        c.moveToFirst();
        int bldinapos=0,bldinaneg=0,bldinbpos=0,bldinbneg=0,bldinabpos=0,bldinabneg=0,bldinopos=0,bldinoneg=0;
        int bldoutapos=0,bldoutaneg=0,bldoutbpos=0,bldoutbneg=0,bldoutabpos=0,bldoutabneg=0,bldoutopos=0,bldoutoneg=0;
        for (int j=0; j<c.getCount();j++) {
            if (c.getString(2).equals("Donor")) {
                Log.d("bb",c.getInt(1)+"quantity");
                Log.d("bb",c.getString(0));
                if (c.getString(0).equals("A+"))
                    bldinapos = bldinapos + c.getInt(1);
                else if (c.getString(0).equals( "A-"))
                    bldinaneg = bldinaneg + c.getInt(1);
                if (c.getString(0).equals( "B+")){
                    bldinbpos = bldinbpos + c.getInt(1);
                Log.d("bb",bldinbpos+"inbpos");
                }
                else if (c.getString(0).equals( "B-"))
                    bldinbneg = bldinbneg + c.getInt(1);
                if (c.getString(0).equals( "AB+"))
                    bldinabpos = bldinabpos + c.getInt(1);
                else if (c.getString(0).equals( "AB-"))
                    bldinabneg = bldinabneg + c.getInt(1);
                if (c.getString(0).equals( "O+")){
                    bldinopos = bldinopos + c.getInt(1);
                Log.d("bb",bldinopos+"inopos");
                }
                else if (c.getString(0).equals( "O-"))
                    bldinoneg = bldinoneg + c.getInt(1);
            } else {
                if (c.getString(0).equals( "A+"))
                    bldoutapos = bldoutapos + c.getInt(1);
                else if (c.getString(0).equals( "A-"))
                    bldoutaneg = bldoutaneg + c.getInt(1);
                if (c.getString(0).equals( "B+"))
                    bldoutbpos = bldoutbpos + c.getInt(1);
                else if (c.getString(0).equals( "B-"))
                    bldoutbneg = bldoutbneg + c.getInt(1);
                if (c.getString(0).equals( "AB+"))
                    bldoutabpos = bldoutabpos + c.getInt(1);
                else if (c.getString(0).equals( "AB-"))
                    bldoutabneg = bldoutabneg + c.getInt(1);
                if (c.getString(0).equals( "O+"))
                    bldoutopos = bldoutopos + c.getInt(1);
                else if (c.getString(0).equals( "O-"))
                    bldoutoneg = bldoutoneg + c.getInt(1);
            }
            c.moveToNext();
        }
        tv_aposin.setText(String.valueOf(bldinapos));
        tv_aposout.setText(String.valueOf(bldoutapos));
        tv_bposin.setText(String.valueOf(bldinbpos));
        tv_bposout.setText(String.valueOf(bldoutbpos));
        tv_abposin.setText(String.valueOf(bldinabpos));
        tv_abposout.setText(String.valueOf(bldoutabpos));
        tv_oposin.setText(String.valueOf(bldinopos));
        tv_oposout.setText(String.valueOf(bldoutopos));
        tv_anegin.setText(String.valueOf(bldinaneg));
        tv_anegout.setText(String.valueOf(bldoutaneg));
        tv_bnegin.setText(String.valueOf(bldinbneg));
        tv_bnegout.setText(String.valueOf(bldoutbneg));
        tv_abnegin.setText(String.valueOf(bldinabneg));
        tv_abnegout.setText(String.valueOf(bldoutabneg));
        tv_onegin.setText(String.valueOf(bldinoneg));
        tv_onegout.setText(String.valueOf(bldoutoneg));
    }

    private Cursor getBloodGroupDetails(){
        Log.d("bb",month1+"fewwwwww");
        String q=" Select "+BloodBankContract.TransactionEntry.COLUMN_BLOOD_GROUP
                +" , "+BloodBankContract.TransactionEntry.COLUMN_QUANTITY
                +" , "+BloodBankContract.TransactionEntry.COLUMN_TYPE
                +" from "+BloodBankContract.TransactionEntry.TABLE_NAME+" where "
                +BloodBankContract.TransactionEntry.COLUMN_DATE_KEY+" IN ( Select "+BloodBankContract.DateEntry._ID + " FROM "
                +BloodBankContract.DateEntry.TABLE_NAME+" where "+BloodBankContract.DateEntry.COLUMN_MONTH+"  = "+month1+") ; ";
        Cursor c=mdb.rawQuery(q,null);

        /*c.moveToFirst();
        for (int i=0;i<c.getCount();i++)
        {
            Log.d("bb",c.getString(0));
            Log.d("bb",c.getInt(1)+"quant");
            Log.d("bb",c.getString(2));
            c.moveToNext();

        }
        */

        return  c;
    }
}

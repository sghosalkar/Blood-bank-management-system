package com.example.android.bloodbankapp.statistics;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.bloodbankapp.data.BloodBankContract;

import com.example.android.bloodbankapp.R;
import com.example.android.bloodbankapp.data.BloodBankDbHelper;
import com.example.android.bloodbankapp.statistics.BloodReportDetails;
import com.example.android.bloodbankapp.statistics.Report;
import com.example.android.bloodbankapp.statistics.ReportAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StatisticsReportsFragment extends Fragment {
    ListView listview;
    int up=R.drawable.up;
    int down= R.drawable.down;
    SQLiteDatabase mdb;
    //String month[]={"December","January","February","MArch"};
    //String bldqtyin[]={"123 units","578 units","456 units","698 units"};
    //String bldqtyout[]={"123 units","578 units","456 units","698 units"};
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_statistics_reports, container, false);
        listview= (ListView) view.findViewById(R.id.lv_report);
        ReportAdapter ra=new ReportAdapter(getContext(),R.layout.row_layout);


        BloodBankDbHelper dbh=new BloodBankDbHelper(getActivity());
        mdb = dbh.getWritableDatabase();
        Cursor c = getReport1();
        c.moveToFirst();
        int bldin1 =0,bldin2 =0,bldin3 =0,bldin4 =0,bldin5 =0,bldin6 =0,bldin7 =0,bldin8 =0,bldin9 =0,bldin10 =0,bldin11 =0,bldin12 =0;
        int bldout1 =0,bldout2 =0,bldout3 =0,bldout4 =0,bldout5 =0,bldout6 =0,bldout7 =0,bldout8 =0,bldout9 =0,bldout10 =0,bldout11 =0,bldout12 =0;
        for (int i=0;i<c.getCount();i++){

            Log.d("bb",c.getInt(0)+"quant");
            Log.d("bb",c.getString(1)+"type");
            Log.d("bb",c.getString(2)+"month");
            Log.d("bb",c.getString(3)+"name");
            Log.d("bb,",c.getInt(4)+"id");
            c.moveToNext();
        }

        c.moveToFirst();
        String bldqtyin[]=new String[12];
        String bldqtyout[]=new String[12];
        String month[]=new String[12];
        Log.d("bb",c.getCount()+"fd");
        if ((c.getCount())>0 && c != null){
            for (int i = 0; i < c.getCount(); i++) {

                if (c.getString(1).equals("Donor")) {
                    Log.d("bb",c.getInt(0)+"Ddd");
                    if (c.getInt(2) == 01)
                        bldin1 = bldin1 + c.getInt(0);
                    if (c.getInt(2) == 02){
                        bldin2 = bldin2 + c.getInt(0);
                        Log.d("bb",bldin2+"valueinfeb");
                    }
                    if (c.getInt(2) == 03){
                        bldin3 = bldin3 + c.getInt(0);
                        Log.d("bb",bldin3+"valuein");
                        Log.d("bb",c.getString(3));
                    }
                    if (c.getInt(2) == 04)
                        bldin4 = bldin4 + c.getInt(0);
                    if (c.getInt(2) == 05)
                        bldin5 = bldin5 + c.getInt(0);
                    if (c.getInt(2) == 06)
                        bldin6 = bldin6 + c.getInt(0);
                    if (c.getInt(2) == 07)
                        bldin7 = bldin7 + c.getInt(0);
                    if (8 == c.getInt(2))
                        bldin8 = bldin8 + c.getInt(0);
                    if (c.getInt(2) == 9)
                        bldin9 = bldin9 + c.getInt(0);
                    if (c.getInt(2) == 10)
                        bldin10 = bldin10 + c.getInt(0);
                    if (c.getInt(2) == 11)
                        bldin11 = bldin11 + c.getInt(0);
                    if (c.getInt(2) == 12)
                        bldin12 = bldin12 + c.getInt(0);
                }
                if (c.getString(1).equals("Receiver")){
                    Log.d("bb",c.getInt(0)+"Ddd");
                    if (c.getInt(2) == 01)
                        bldout1 = bldout1 + c.getInt(0);
                    if (c.getInt(2) == 02){
                        bldout2 = bldout2 + c.getInt(0);
                        Log.d("bb",bldout2+"valueoutfeb");
                    }
                    if (c.getInt(2) == 03){
                        bldout3 = bldout3 + c.getInt(0);
                        Log.d("bb",bldout3+"valueout");
                        Log.d("bb",c.getString(3));
                    }
                    if (c.getInt(2) == 04)
                        bldout4 = bldout4 + c.getInt(0);
                    if (c.getInt(2) == 05)
                        bldout5 = bldout5 + c.getInt(0);
                    if (c.getInt(2) == 06)
                        bldout6 = bldout6 + c.getInt(0);
                    if (c.getInt(2) == 07)
                        bldout7 = bldout7 + c.getInt(0);
                    if (8 == c.getInt(2))
                        bldout8 = bldout8 + c.getInt(0);
                    if (c.getInt(2) == 9)
                        bldout9 = bldout9 + c.getInt(0);
                    if (c.getInt(2) == 10)
                        bldout10 = bldout10 + c.getInt(0);
                    if (c.getInt(2) == 11)
                        bldout11 = bldout11 + c.getInt(0);
                    if (c.getInt(2) == 12)
                        bldout12 = bldout12 + c.getInt(0);
                }
                c.moveToNext();
            }


            bldqtyin[11]=Integer.toString(bldin12);
            bldqtyin[10]=Integer.toString(bldin11);
            bldqtyin[9]=Integer.toString(bldin10);
            bldqtyin[8]=Integer.toString(bldin9);
            bldqtyin[7]=Integer.toString(bldin8);
            bldqtyin[6]=Integer.toString(bldin7);
            bldqtyin[5]=Integer.toString(bldin6);
            bldqtyin[4]=Integer.toString(bldin5);
            bldqtyin[3]=Integer.toString(bldin4);
            bldqtyin[2]=Integer.toString(bldin3);
            bldqtyin[1]=Integer.toString(bldin2);
            bldqtyin[0]=Integer.toString(bldin1);

         /* if (currentMonth == 1)
              month[0]="January";
            else  if (currentMonth == 2)
              month[0]="Frbruary";
          else if (currentMonth == 3)
              month[0]="March";
          else if (currentMonth == 4)
              month[0]="April";
          else if (currentMonth == 5)
              month[0]="May";
          else if (currentMonth == 6)
              month[0]="June";
          else if (currentMonth == 7)
              month[0]="July";
          else if (currentMonth == 8)
              month[0]="August";
          else if (currentMonth == 9)
              month[0]="September";
          else if (currentMonth == 10)
              month[0]="October";
          else if (currentMonth == 11)
              month[0]="November";
          else if (currentMonth == 12)
              month[0]="December";
*/
            Calendar calender = Calendar.getInstance();
            SimpleDateFormat df=new SimpleDateFormat("MMMM");
            String currentDate = df.format(calender.getTime());
            String current[] = currentDate.split("-");
            String currentMonth= current[0];


            try {
                calender.setTime(df.parse(currentMonth));
                for(int i=0;i<12;i++){
                    String monthname=df.format(calender.getTime());
                    month[i]=monthname;
                    calender.add(Calendar.MONTH,-1);


                }
            } catch (ParseException e) {
                e.printStackTrace();
            }



            bldqtyout[11]=Integer.toString(bldout12);
            bldqtyout[10]=Integer.toString(bldout11);
            bldqtyout[9]=Integer.toString(bldout10);
            bldqtyout[8]=Integer.toString(bldout9);
            bldqtyout[7]=Integer.toString(bldout8);
            bldqtyout[6]=Integer.toString(bldout7);
            bldqtyout[5]=Integer.toString(bldout6);
            bldqtyout[4]=Integer.toString(bldout5);
            bldqtyout[3]=Integer.toString(bldout4);
            bldqtyout[2]=Integer.toString(bldout3);
            bldqtyout[1]=Integer.toString(bldout2);
            bldqtyout[0]=Integer.toString(bldout1);
            for (int i=0;i<12;i++){
                Log.d("bb",bldqtyin[i]);
                Log.d("bb",bldqtyout[i]);
                Log.d("bb",month[i]+"hee");
            }
        }
        Report r = null;
        for(String trace:month){
            Log.d("bb",trace+"kk");
            if (trace.equals("January")){
                Log.d("bb",trace+"hkj");
                r=new Report(trace,down,bldqtyin[0],up,bldqtyout[0]);

            }
            if (trace.equals("February")){
                Log.d("bb",trace+"jii");
                r=new Report(trace,down,bldqtyin[1],up,bldqtyout[1]);

            }
            if (trace.equals("March")){
                Log.d("bb",trace+"lk");
                r=new Report(trace,down,bldqtyin[2],up,bldqtyout[2]);
            }
            if (trace.equals("April")){
                r=new Report(trace,down,bldqtyin[3],up,bldqtyout[3]);

            }
            if (trace.equals("May")){
                r=new Report(trace,down,bldqtyin[4],up,bldqtyout[4]);

            }
            if (trace.equals("June")){
                r=new Report(trace,down,bldqtyin[5],up,bldqtyout[5]);

            }
            if (trace.equals("July")){
                r=new Report(trace,down,bldqtyin[6],up,bldqtyout[6]);

            }
            if (trace.equals("August")){
                r=new Report(trace,down,bldqtyin[7],up,bldqtyout[7]);

            }
            if (trace.equals("September")){
                r=new Report(trace,down,bldqtyin[8],up,bldqtyout[8]);

            }
            if (trace.equals("October")){
                r=new Report(trace,down,bldqtyin[9],up,bldqtyout[9]);

            }
            if (trace.equals("November")){
                r=new Report(trace,down,bldqtyin[10],up,bldqtyout[10]);
            }
            if (trace.equals("December")){
                r=new Report(trace,down,bldqtyin[11],up,bldqtyout[11]);

            }

            ra.add(r);

        }


        listview.setAdapter(ra);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(),BloodReportDetails.class);
                //   Toast.makeText(getContext(),listview.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
                intent.putExtra("Month",listview.getItemAtPosition(i).toString());
                startActivity(intent);
            }
        });

        // Inflate the layout for this fragment
        return view;

    }

    private Cursor getReport1(){
        String Query1 = " Select t."+ BloodBankContract.TransactionEntry.COLUMN_QUANTITY
                + " , t." + BloodBankContract.TransactionEntry.COLUMN_TYPE +
                " , d." + BloodBankContract.DateEntry.COLUMN_MONTH +" , t." +BloodBankContract.TransactionEntry.COLUMN_NAME+
                " , d."+BloodBankContract.DateEntry._ID
                + " from " + BloodBankContract.TransactionEntry.TABLE_NAME +" t INNER JOIN " +
                BloodBankContract.DateEntry.TABLE_NAME + " d  ON d." + BloodBankContract.DateEntry._ID
                +" = t." + BloodBankContract.TransactionEntry.COLUMN_DATE_KEY +" ; ";
        Cursor c=mdb.rawQuery(Query1,null);

        return c;
    }

}


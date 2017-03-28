package com.example.android.bloodbankapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.android.bloodbankapp.data.BloodBankDbHelper;


public class StatisticsReportsFragment extends Fragment {
    ListView listview;
    int up=R.drawable.up;
    int down=R.drawable.down;
    String month[]={"December","January","February","MArch"};
    String bldqtyin[]={"123 units","578 units","456 units","698 units"};
    String bldqtyout[]={"123 units","578 units","456 units","698 units"};
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_statistics_reports, container, false);
        listview= (ListView) view.findViewById(R.id.lv_report);
        ReportAdapter ra=new ReportAdapter(getContext(),R.layout.row_layout);


        BloodBankDbHelper dbh=new BloodBankDbHelper(getActivity());
        dbh.addDateEntry(1,20,"DECEMBER",2017);
        dbh.addDonorEntry(1,1,"Sonia","6789087654","B+","100 ml",100);
        dbh.addDateEntry(2,11,"DECEMBER",2016);

        dbh.addDonorEntry(2,2,"Sonia","6789087654","B+","100 ml",100);
        dbh.addDateEntry(3,20,"DECEMBER",2016);
        dbh.addDonorEntry(3,7,"Sonia","6789087654","B+","100 ml",100);
        dbh.addDateEntry(4,25,"DECEMBER",2016);
        dbh.addDonorEntry(4,3,"Sonia","6789087654","B+","100 ml",100);
        dbh.addDateEntry(5,12,"JANUARY",2017);
        dbh.addDonorEntry(5,4,"Sonia","6789087654","B+","100 ml",100);
        dbh.addDateEntry(6,20,"JANUARY",2017);
        dbh.addDonorEntry(6,5,"Sonia","6789087654","B+","100 ml",100);
        dbh.addDateEntry(7,20,"MAY",2016);
        dbh.addDonorEntry(7,6,"Sonia","6789087654","B+","100 ml",100);
        dbh.addDateEntry(8,20,"JUNE",2016);
        dbh.addDateEntry(9,12,"MARCH",2017);
        dbh.addDonorEntry(13,9,"Sonia","6789087654","B+","100 ml",100);
        dbh.addDonorEntry(14,9,"Sonia","6789087654","B+","100 ml",100);
        dbh.addDonorEntry(15,9,"Sonia","6789087654","B+","100 ml",100);
        dbh.addDonorEntry(16,9,"Sonia","6789087654","B+","100 ml",100);

        dbh.addDonorEntry(8,8,"Sonia","6789087654","B+","100 ml",100);
        dbh.addDonorEntry(9,8,"Sonia","3789087654","B+","100 ml",100);
        dbh.addDonorEntry(10,8,"abc","6749087654","B+","300 ml",300);
        dbh.addDonorEntry(11,8,"Sa","6743087654","B+","200 ml",200);
        dbh.addDonorEntry(12,8,"Sre","61239087654","B+","150 ml",150);

        dbh.addReceiverEntry(1,1,"abc","6749087654","B+","300 ml",300);
        dbh.addReceiverEntry(2,9,"abc","6749087654","B+","300 ml",300);
        dbh.addReceiverEntry(3,9,"ac","6749087654","B+","300 ml",300);
        dbh.addReceiverEntry(4,9,"ab","6749987654","B+","300 ml",300);
        dbh.addReceiverEntry(5,9,"bbb","6749087654","B+","300 ml",300);
        dbh.addReceiverEntry(6,7,"abc","6749087654","B+","300 ml",300);
        dbh.addReceiverEntry(7,7,"abc","6749087654","B+","300 ml",300);
        dbh.addReceiverEntry(8,8,"abc","6749087654","B+","300 ml",300);
        dbh.addReceiverEntry(8,8,"abc","6749087654","B+","300 ml",300);





        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(),BloodReportDetails.class);
                intent.putExtra("Month",listview.getItemAtPosition(i).toString());
                startActivity(intent);
            }
        });

        int i=0;
        for(String trace:month){
            Report r=new Report(trace,down,bldqtyin[i],up,bldqtyout[i]);
            ra.add(r);
            i++;
        }

        listview.setAdapter(ra);


        // Inflate the layout for this fragment
        return view;
    }



}

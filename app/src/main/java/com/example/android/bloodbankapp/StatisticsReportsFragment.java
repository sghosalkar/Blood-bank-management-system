package com.example.android.bloodbankapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


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

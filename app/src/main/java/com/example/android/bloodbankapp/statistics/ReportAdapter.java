package com.example.android.bloodbankapp.statistics;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.bloodbankapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 19-Mar-17.
 */

public class ReportAdapter extends ArrayAdapter {
   private List list=new ArrayList();

    public ReportAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }


    public void add(Report object) {
     list.add(object);
        super.add(object);
    }

    static class ReportHolder{
        TextView month;
        ImageView up;
        TextView bldqtyin;
        ImageView down;
        TextView bldqtyout;
    }
    @Override
    public int getCount() {
        return this.list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row;
        row=convertView;
        ReportHolder holder;
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.row_layout, parent, false);
            holder = new ReportHolder();
            holder.month = (TextView) row.findViewById(R.id.tv_month);
            holder.up = (ImageView) row.findViewById(R.id.iv_bldout);
            holder.bldqtyin = (TextView) row.findViewById(R.id.tv_bldin);
            holder.down = (ImageView) row.findViewById(R.id.iv_bldin);
            holder.bldqtyout = (TextView) row.findViewById(R.id.tv_bldout);
            row.setTag(holder);
        }
        else{
            holder= (ReportHolder) row.getTag();


        }
        Report r= (Report) getItem(position);
        holder.month.setText(r.getMonth());
        holder.down.setImageResource(r.getBldin());
        holder.bldqtyin.setText(r.getBldqtyin());
        holder.up.setImageResource(r.getBldout());
        holder.bldqtyout.setText(r.getBldqtyout());

        return row;
    }
}

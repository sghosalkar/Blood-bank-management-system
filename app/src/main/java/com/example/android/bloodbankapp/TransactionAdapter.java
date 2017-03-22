package com.example.android.bloodbankapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by shubham on 22/3/17.
 */

public class TransactionAdapter extends CursorAdapter {

    public TransactionAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_transaction, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.nameView.setText("Mogambo");
        viewHolder.typeView.setText("Donor");
        viewHolder.bloodGroupView.setText("O+");
        viewHolder.quantityView.setText("300 mL");
    }

    public static class ViewHolder {
        public final TextView nameView;
        public final TextView typeView;
        public final TextView bloodGroupView;
        public final TextView quantityView;

        public ViewHolder(View view) {
            nameView = (TextView) view.findViewById(R.id.list_item_name_textview);
            typeView = (TextView) view.findViewById(R.id.list_item_type_textview);
            bloodGroupView = (TextView) view.findViewById(R.id.list_item_blood_group_textview);
            quantityView = (TextView) view.findViewById(R.id.list_item_quantity_textview);
        }
    }
}

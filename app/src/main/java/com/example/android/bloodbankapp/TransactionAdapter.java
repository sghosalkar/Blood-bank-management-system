package com.example.android.bloodbankapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by shubham on 22/3/17.
 */

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>{

    private static final String TAG = TransactionAdapter.class.getSimpleName();

    private int mNumberItems;

    final private ListItemClickListener mOnClickListener;

    public TransactionAdapter(int numberOfItems, ListItemClickListener listener) {
        mNumberItems = numberOfItems;
        mOnClickListener = listener;
    }

    @Override
    public TransactionAdapter.TransactionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item_transaction, parent, false);
        TransactionViewHolder viewHolder = new TransactionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TransactionAdapter.TransactionViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    class TransactionViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public final TextView nameView;
        public final TextView typeView;
        public final TextView bloodGroupView;
        public final TextView quantityView;

        public TransactionViewHolder(View view) {
            super(view);
            nameView = (TextView) view.findViewById(R.id.list_item_name_textview);
            typeView = (TextView) view.findViewById(R.id.list_item_type_textview);
            bloodGroupView = (TextView) view.findViewById(R.id.list_item_blood_group_textview);
            quantityView = (TextView) view.findViewById(R.id.list_item_quantity_textview);
            itemView.setOnClickListener(this);
        }

        void bind(int listIndex) {
            nameView.setText("Mogambo khush hua");
            typeView.setText("Donor");
            bloodGroupView.setText("O+");
            quantityView.setText("300 mL");
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }
}

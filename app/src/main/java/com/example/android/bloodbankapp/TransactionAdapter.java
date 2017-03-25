package com.example.android.bloodbankapp;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bloodbankapp.data.BloodBankContract;

/**
 * Created by shubham on 22/3/17.
 */

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>{

    private static final String TAG = TransactionAdapter.class.getSimpleName();

    private Cursor mCursor;

    final private ListItemClickListener mOnClickListener;

    public TransactionAdapter(Cursor cursor, ListItemClickListener listener) {
        this.mCursor = cursor;
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
        if (!mCursor.moveToPosition(position)) {
            holder.bind(position);
            return;
        }
        String donorKey = mCursor.getString(mCursor.getColumnIndex(BloodBankContract.DonorTransactionEntry.COLUMN_DONOR_KEY));
        String quantity = mCursor.getString(mCursor.getColumnIndex(BloodBankContract.DonorTransactionEntry.COLUMN_QUANTITY));
        String price = mCursor.getString(mCursor.getColumnIndex(BloodBankContract.DonorTransactionEntry.COLUMN_PRICE));
        String date = mCursor.getString(mCursor.getColumnIndex(BloodBankContract.DonorTransactionEntry.COLUMN_TRANSACTION_DATE));
    }

    @Override
    public int getItemCount() {
        if(mCursor.getCount() == 0)
            return 5;
        return mCursor.getCount();
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

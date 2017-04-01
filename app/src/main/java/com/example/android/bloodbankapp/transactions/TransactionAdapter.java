package com.example.android.bloodbankapp.transactions;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bloodbankapp.R;
import com.example.android.bloodbankapp.data.BloodBankContract;

/**
 * Created by shubham on 22/3/17.
 */

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>{

    private static final String LOG_TAG = TransactionAdapter.class.getSimpleName();

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
        //check if cursor is empty
        if (!mCursor.moveToPosition(position)) {
            return;
        }
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
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

        void bind(int position) {
            //Get data from cursor
            String name = mCursor.getString(mCursor.getColumnIndex(BloodBankContract.TransactionEntry.COLUMN_NAME));
            String contactNo = mCursor.getString(mCursor.getColumnIndex(BloodBankContract.TransactionEntry.COLUMN_CONTACT_NO));
            String type = mCursor.getString(mCursor.getColumnIndex(BloodBankContract.TransactionEntry.COLUMN_TYPE));
            String bloodGroup = mCursor.getString(mCursor.getColumnIndex(BloodBankContract.TransactionEntry.COLUMN_BLOOD_GROUP));
            String quantity = mCursor.getString(mCursor.getColumnIndex(BloodBankContract.TransactionEntry.COLUMN_QUANTITY));
            String price = mCursor.getString(mCursor.getColumnIndex(BloodBankContract.TransactionEntry.COLUMN_PRICE));
            String dateKey = mCursor.getString(mCursor.getColumnIndex(BloodBankContract.TransactionEntry.COLUMN_DATE_KEY));
//            String day = mCursor.getString(mCursor.getColumnIndex(BloodBankContract.DateEntry.COLUMN_DAY));
//            String month = mCursor.getString(mCursor.getColumnIndex(BloodBankContract.DateEntry.COLUMN_MONTH));
//            String year = mCursor.getString(mCursor.getColumnIndex(BloodBankContract.DateEntry.COLUMN_YEAR));
            nameView.setText(name);
            typeView.setText(type);
            bloodGroupView.setText(bloodGroup);
            quantityView.setText(quantity);
        }

        @Override
        public void onClick(View view) {
            //Get clicked position
            int clickedPosition = getAdapterPosition();
            //Set click listener on clicked position
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }
}

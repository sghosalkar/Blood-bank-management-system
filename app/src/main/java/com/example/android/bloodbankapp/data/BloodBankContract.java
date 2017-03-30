package com.example.android.bloodbankapp.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by shubham on 17/3/17.
 */

public class BloodBankContract {

    public static final String CONTENT_AUTHORITY = "com.example.android.bloodbankapp.app";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_TRANSACTION = "transactions";
    public static final String PATH_DATE = "date_table";

    public static final class TransactionEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_TRANSACTION).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TRANSACTION;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TRANSACTION;

        public static final String TABLE_NAME = "transactions";

        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_CONTACT_NO = "contact_no";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_BLOOD_GROUP = "blood_group";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_DATE_KEY = "date_id";
    }

    public static final class DateEntry implements BaseColumns{
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_DATE).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DATE;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DATE;
        public static final String TABLE_NAME="date_table";

        public static final String COLUMN_DAY = "day";
        public static final String COLUMN_MONTH = "month";
        public static final String COLUMN_YEAR = "year";

    }
}

package com.example.android.bloodbankapp.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by shubham on 17/3/17.
 */

public class BloodBankContract {

    public static final String CONTENT_AUTHORITY = "com.example.android.bloodbankapp.app";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_DONOR = "donor";
    public static final String PATH_RECEIVER = "receiver";
    public static final String PATH_DONOR_TRANSACTION = "donor_transaction";
    public static final String PATH_RECEIVER_TRANSACTION = "receiver_transaction";

    public static final class DonorEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_DONOR).build();
    }

    public static final class ReceiverEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_RECEIVER).build();
    }

    public static final class DonorTransactionEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_DONOR_TRANSACTION).build();
    }

    public static final class ReceiverTransactionEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_RECEIVER_TRANSACTION).build();
    }

}

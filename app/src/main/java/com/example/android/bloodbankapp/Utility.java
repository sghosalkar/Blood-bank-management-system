package com.example.android.bloodbankapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by shubham on 3/4/17.
 */

public class Utility {

    public static String[] getCurrentDate() {
        Calendar calender = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String currentDate = df.format(calender.getTime());
        return currentDate.split("-");
    }

}

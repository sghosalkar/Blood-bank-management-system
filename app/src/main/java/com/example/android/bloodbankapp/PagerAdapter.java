package com.example.android.bloodbankapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by shubham on 16/3/17.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    String[] tabTitles;

    public PagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        tabTitles = context.getResources().getStringArray(R.array.statistics_subtitle_array);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new StatisticsStatusFragment();
            case 1:
                return new StatisticsReportsFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}

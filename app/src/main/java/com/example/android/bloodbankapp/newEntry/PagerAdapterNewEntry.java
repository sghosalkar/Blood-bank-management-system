package com.example.android.bloodbankapp.newEntry;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.android.bloodbankapp.R;

/**
 * Created by jaihi on 3/27/2017.
 */

class PagerAdapterNewEntry  extends FragmentStatePagerAdapter {

    String[] tabTitles;

    public PagerAdapterNewEntry(FragmentManager fm, Context context) {
        super(fm);
        tabTitles = context.getResources().getStringArray(R.array.newentry_subtitle_array);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new NewEntryDonorFragment();
            case 1:
                return new NewEntryReceiverFragment();
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

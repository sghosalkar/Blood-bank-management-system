package com.example.android.bloodbankapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import layout.NewEntryFragment;
import layout.StatisticsFragment;
import layout.TransactionsFragment;

public class MainActivity extends AppCompatActivity implements NewEntryFragment.OnFragmentInteractionListener, TransactionsFragment.OnFragmentInteractionListener, StatisticsFragment.OnFragmentInteractionListener {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_new_entry:
                    NewEntryFragment newEntryFragment = new NewEntryFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, newEntryFragment).commit();
                    return true;
                case R.id.navigation_transactions:
                    TransactionsFragment transactionsFragment = new TransactionsFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, transactionsFragment).commit();
                    return true;
                case R.id.navigation_statistics:
                    StatisticsFragment statisticsFragment = new StatisticsFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, statisticsFragment).commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        View view = navigation.findViewById(R.id.navigation_new_entry);
        view.performClick();
    }

    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        if (id == R.id.sample_login) {
            startActivity(new Intent(this, LoginActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

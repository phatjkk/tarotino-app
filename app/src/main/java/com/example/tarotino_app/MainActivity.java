package com.example.tarotino_app;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.security.AccessControlContext;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    File myInternalFile;
    Tarot tarot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
//        Tarot tarot = new Tarot(contextWrapper);

        bottomNavigationView = findViewById(R.id.navigation_bar);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.item_1);
    }
    DailyFragment dailyFragment = new DailyFragment();
    AdvancedFragment advancedFragment = new AdvancedFragment();
    HistoryFragment historyFragment = new HistoryFragment();
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item_1:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, dailyFragment).commit();
                return true;

            case R.id.item_2:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, advancedFragment).commit();
                return true;

            case R.id.item_3:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, historyFragment).commit();
                return true;
        }
        return false;
    }
}

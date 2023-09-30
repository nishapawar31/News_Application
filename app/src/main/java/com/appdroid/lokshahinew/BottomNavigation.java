package com.appdroid.lokshahinew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.appdroid.lokshahinew.Activitys.ENewsPaper;
import com.appdroid.lokshahinew.Activitys.ShowingVillagesData;
import com.appdroid.lokshahinew.Fragments.CrimeFragment;
import com.appdroid.lokshahinew.Fragments.JalgaonCity;
import com.appdroid.lokshahinew.Fragments.JalgaonDistrict;
import com.appdroid.lokshahinew.Fragments.RajkranFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigation extends AppCompatActivity {

    RelativeLayout bottom_main;
    BottomNavigationView bottomNavigationView;
    Fragment firstfragment,secondfragment,thirdfragment,fourfragment;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        bottom_main = findViewById(R.id.bottom_main);
        firstfragment = new CrimeFragment();
        secondfragment = new RajkranFragment();
        thirdfragment = new JalgaonCity();
        fourfragment = new JalgaonDistrict();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               if(item.getItemId() == R.id.nav_one){
                   Fragment one = new CrimeFragment();
                   FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                   transaction.replace(R.id.bottom_main,firstfragment);
                   transaction.commit();
               }
                else if(item.getItemId() == R.id.nav_two){
                    Fragment two = new RajkranFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.bottom_main,secondfragment);
                    transaction.commit();
                }
               else if(item.getItemId() == R.id.nav_three){
                   Fragment three = new JalgaonCity();
                   FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                   transaction.replace(R.id.bottom_main,thirdfragment);
                   transaction.commit();
               }else if(item.getItemId() == R.id.nav_four){
                   Fragment four = new JalgaonDistrict();
                   FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                   transaction.replace(R.id.bottom_main,fourfragment);
                   transaction.commit();
               }

              return true;
            }
        });

    }
}
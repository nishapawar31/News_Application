 package com.appdroid.lokshahinew;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.appdroid.lokshahinew.Activitys.ENewsPaper;
import com.appdroid.lokshahinew.Activitys.ShowingVillagesData;
import com.appdroid.lokshahinew.Adepter.TabsAdepter;
import com.appdroid.lokshahinew.Fragments.CrimeFragment;
import com.appdroid.lokshahinew.Fragments.JalgaonCity;
import com.appdroid.lokshahinew.Fragments.JalgaonDistrict;
import com.appdroid.lokshahinew.Fragments.NewsFragment;
import com.appdroid.lokshahinew.Fragments.RajkranFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

 public class MainActivity extends AppCompatActivity {
     private static final String TAG = "HELLO";
     TabLayout tabLayout;
     public static ViewPager viewPager;
     TabsAdepter adepter;
     String action;
     ImageView button;
     Fragment crimeFragment,jalgaonDist,jalgaoncity,rajkaranfragment,newsfrag;
     private NavigationView navigationView;
     private ActionBarDrawerToggle toggle;
     private DrawerLayout drawerLayout;

    @SuppressLint("MissingInflatedId")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.pager);

        action =  getIntent().getAction();
        button = findViewById(R.id.button);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawer);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string. open,R.string.close);
        toggle.getDrawerArrowDrawable().setColor(getColor(R.color.black));
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        newsfrag= new NewsFragment();
        newsfrag.getRetainInstance();

        adepter = new TabsAdepter(getSupportFragmentManager());
        crimeFragment = new CrimeFragment();
        jalgaonDist = new JalgaonDistrict();
        jalgaoncity = new JalgaonCity();
        rajkaranfragment = new RajkranFragment();

        adepter.addFragment(newsfrag,"मुख्य पान");
        adepter.addFragment(jalgaonDist, "राष्ट्रीय");
        adepter.addFragment(jalgaoncity, "महाराष्ट्र");
        adepter.addFragment(rajkaranfragment,"जळगाव");
        adepter.addFragment(crimeFragment,"गुन्हे वार्ता");

        viewPager.setAdapter(adepter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(0);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                if (item.getItemId() == R.id.nav_one){
                    drawerLayout.closeDrawer(GravityCompat.START);
                    Intent i = new Intent(getApplicationContext(), ENewsPaper.class);
                    startActivity(i);
                }else if (item.getItemId() == R.id.nav_two){
                    drawerLayout.closeDrawer(GravityCompat.START);
                    intent =new Intent(MainActivity.this, ShowingVillagesData.class);
                    intent.putExtra("link",getString(R.string.brecking_cat));
                    startActivity(intent);

                }else if (item.getItemId() == R.id.nav_three){
                    drawerLayout.closeDrawer(GravityCompat.START);
                    intent =new Intent(MainActivity.this, ShowingVillagesData.class);
                    intent.putExtra("link",getString(R.string.vishesh));
                    startActivity(intent);

                }else if (item.getItemId() == R.id.nav_four){
                    drawerLayout.closeDrawer(GravityCompat.START);
                    intent =new Intent(MainActivity.this, ShowingVillagesData.class);
                    intent.putExtra("link",getString(R.string.jalgoanShahar));
                    startActivity(intent);

                }else if (item.getItemId() == R.id.nav_five){
                    drawerLayout.closeDrawer(GravityCompat.START);
                    intent =new Intent(MainActivity.this, ShowingVillagesData.class);
                    intent.putExtra("link",getString(R.string.nokri));
                    startActivity(intent);

                }else if (item.getItemId() == R.id.nav_six) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    intent = new Intent(MainActivity.this, ShowingVillagesData.class);
                    intent.putExtra("link", getString(R.string.havaman));
                    startActivity(intent);
                }

                else if(item.getItemId()==R.id.nav_jalgaon) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    intent = new Intent(MainActivity.this, ShowingVillagesData.class);
                    intent.putExtra("link", getString(R.string.sports));
                    startActivity(intent);
                }

                else if(item.getItemId()==R.id.nav_jalgaonJilha) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    intent = new Intent(MainActivity.this, ShowingVillagesData.class);
                    intent.putExtra("link", getString(R.string.lekh));
                    startActivity(intent);
                }
                else if(item.getItemId()== R.id.nav_bhusawal) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    intent = new Intent(MainActivity.this, ShowingVillagesData.class);
                    intent.putExtra("link", getString(R.string.bhusaval));
                    startActivity(intent);
                }

                else if(item.getItemId()== R.id.nav_chalisgaon) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    intent = new Intent(MainActivity.this, ShowingVillagesData.class);
                    intent.putExtra("link", getString(R.string.chalisgaon));
                    startActivity(intent);
                }
                else if(item.getItemId()== R.id.nav_raver) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    intent = new Intent(MainActivity.this, ShowingVillagesData.class);
                    intent.putExtra("link", getString(R.string.raver));
                    startActivity(intent);
                }
                return true;
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,BottomNavigation.class);
                startActivity(intent);
                finish();
            }
        });

    }
 }
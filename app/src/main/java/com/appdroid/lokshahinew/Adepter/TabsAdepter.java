package com.appdroid.lokshahinew.Adepter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabsAdepter extends FragmentPagerAdapter {
        int noOfTabs;
    List<Fragment> fragmentList = new ArrayList<>();
    List<String> titles = new ArrayList<>();

    public TabsAdepter(@NonNull FragmentManager fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = fragmentList.get(position);
        fragment.setRetainInstance(true);
        return fragment;
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    public  void addFragment(Fragment fragment, String s){
        fragmentList.add(fragment);
        titles.add(s);

    }
    public void removeTabPage(int position) {
        if (!fragmentList.isEmpty() && position<fragmentList.size()) {
            fragmentList.remove(position);
            notifyDataSetChanged();

        }
    }
}

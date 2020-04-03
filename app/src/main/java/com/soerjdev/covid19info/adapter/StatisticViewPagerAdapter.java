package com.soerjdev.covid19info.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class StatisticViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> listFragment = new ArrayList<>();
    private List<Integer> listTitleFragment = new ArrayList<>();
    private Context mContext;

    public StatisticViewPagerAdapter(@NonNull FragmentManager fm, int behavior, Context mContext) {
        super(fm, behavior);
        this.mContext = mContext;
    }

    public void addFragment(Fragment fragment, int title){
        listFragment.add(fragment);
        listTitleFragment.add(title);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(listTitleFragment.get(position));
    }
}

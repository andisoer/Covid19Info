package com.soerjdev.covid19info.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.soerjdev.covid19info.R;
import com.soerjdev.covid19info.adapter.StatisticViewPagerAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticFragment extends Fragment {

    private ViewPager viewPager;

    public StatisticFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_statistic, container, false);

        Toolbar toolbar = v.findViewById(R.id.tbStatistic);

        if (getActivity() != null){
            ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
            if (((AppCompatActivity)getActivity()).getSupportActionBar() != null){
                ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
            }
        }

        viewPager = v.findViewById(R.id.viewPagerStatistic);
        TabLayout tabLayoutStatistic = v.findViewById(R.id.tabLayoutStatistic);

        setUpViewPager();
        tabLayoutStatistic.setupWithViewPager(viewPager);

        return v;
    }

    private void setUpViewPager() {
        if (getActivity() != null){
            StatisticViewPagerAdapter viewPagerAdapter = new StatisticViewPagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, getActivity());
            viewPagerAdapter.addFragment(new IndonesiaStatisticFragment(), R.string.indonesia);
            viewPagerAdapter.addFragment(new WorldStatisticFragment(), R.string.world);
            viewPager.setAdapter(viewPagerAdapter);
            viewPager.setOffscreenPageLimit(viewPagerAdapter.getCount() - 1);
        }
    }
}

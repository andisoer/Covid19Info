package com.soerjdev.covid19info;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.soerjdev.covid19info.ui.MapFragment;
import com.soerjdev.covid19info.ui.ProvinceDataFragment;
import com.soerjdev.covid19info.ui.StatisticFragment;
import com.soerjdev.covid19info.viewmodel.IndonesiaStatisticViewModel;
import com.soerjdev.covid19info.viewmodel.MapFragmentViewModel;
import com.soerjdev.covid19info.viewmodel.ProvinceDataViewModel;
import com.soerjdev.covid19info.viewmodel.WorldStatiscticViewModel;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private WorldStatiscticViewModel worldStatiscticViewModel;
    private IndonesiaStatisticViewModel indonesiaStatisticViewModel;
    private MapFragmentViewModel mapFragmentViewModel;
    private ProvinceDataViewModel provinceDataViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {

        //instanisiasi bottom navigation
        bottomNavigationView = findViewById(R.id.bottomNavigationViewMain);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.menuStatistic);

        worldStatiscticViewModel = new ViewModelProvider(this).get(WorldStatiscticViewModel.class);
        indonesiaStatisticViewModel = new ViewModelProvider(this).get(IndonesiaStatisticViewModel.class);
        mapFragmentViewModel = new ViewModelProvider(this).get(MapFragmentViewModel.class);
        provinceDataViewModel = new ViewModelProvider(this).get(ProvinceDataViewModel.class);
    }

    //load fragment
    private boolean loadFragment(Fragment fragment) {
        if (fragment != null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayoutMain, fragment)
                    .commit();

            return true;
        }

        return false;
    }

    //pemilihan menu pada bottom navigation
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()){
            case R.id.menuStatistic:
                fragment = new StatisticFragment();
                break;
            case R.id.menuMaps:
                fragment = new MapFragment();
                break;
            case R.id.menuFactCheck:
                fragment = new ProvinceDataFragment();
                break;
        }

        return loadFragment(fragment);
    }
}

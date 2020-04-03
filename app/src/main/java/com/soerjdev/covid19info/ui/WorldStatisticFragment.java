package com.soerjdev.covid19info.ui;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.soerjdev.covid19info.R;
import com.soerjdev.covid19info.viewmodel.WorldStatiscticViewModel;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorldStatisticFragment extends Fragment {

    private PieChart pieChartWorld;
    private WorldStatiscticViewModel worldStatiscticViewModel;

    private int jumlahMeninggal;
    private int jumlahSembuh;
    private int jumlahPositif;

    private String TAG = WorldStatisticFragment.class.getSimpleName();

    public WorldStatisticFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_world_statistic, container, false);

        pieChartWorld = v.findViewById(R.id.pieChartWorldStatistic);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getActivity() != null){
            worldStatiscticViewModel = new ViewModelProvider(getActivity()).get(WorldStatiscticViewModel.class);
        }
        getWorldData();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        worldStatiscticViewModel.getDataMeninggal().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String dataMeninggal) {
                if(dataMeninggal != null){
                    try {
                        jumlahMeninggal = NumberFormat.getNumberInstance(Locale.getDefault()).parse(dataMeninggal).intValue();
                        setToPie();
                    } catch (ParseException e) {
                        e.printStackTrace();
                        Log.e(TAG+" onActivityCreated", "onChanged: "+e.getMessage());
                    }
                }
            }
        });

        worldStatiscticViewModel.getDataSembuh().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String dataSembuh) {
                if(dataSembuh != null){
                    try {
                        jumlahSembuh = NumberFormat.getNumberInstance(Locale.getDefault()).parse(dataSembuh).intValue();
                        setToPie();
                    } catch (ParseException e) {
                        e.printStackTrace();
                        Log.e(TAG+" onActivityCreated", "onChanged: "+e.getMessage());
                    }
                }
            }
        });

        worldStatiscticViewModel.getDataPositif().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String dataPositif) {
                if(dataPositif != null){
                    try {
                        jumlahPositif = NumberFormat.getNumberInstance(Locale.getDefault()).parse(dataPositif).intValue();
                        setToPie();
                    } catch (ParseException e) {
                        e.printStackTrace();
                        Log.e(TAG+" onActivityCreated", "onChanged: "+e.getMessage());
                    }
                }
            }
        });

    }

    //Set data ke pieChart
    private void setToPie() {

        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(jumlahSembuh,"Sembuh"));
        entries.add(new PieEntry(jumlahPositif, "Positif"));
        entries.add(new PieEntry(jumlahMeninggal, "Meninggal"));

        PieDataSet pieDataSet = new PieDataSet(entries, "Statistik Dunia");
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        pieDataSet.setValueTextSize(14);
        pieDataSet.setValueTextColor(Color.WHITE);

        PieData pieData = new PieData(pieDataSet);

        Legend legend = pieChartWorld.getLegend();
        legend.setTextSize(14);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);

        pieChartWorld.getDescription().setEnabled(false);
        pieChartWorld.setExtraTopOffset(-50);
        pieChartWorld.setData(pieData);
        pieChartWorld.invalidate();
    }

    private void getWorldData() {
        worldStatiscticViewModel.setDataMeninggal();
        worldStatiscticViewModel.setDataSembuh();
        worldStatiscticViewModel.setDataPositif();
    }
}

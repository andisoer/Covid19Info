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
import android.widget.ProgressBar;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.soerjdev.covid19info.R;
import com.soerjdev.covid19info.model.Indonesia;
import com.soerjdev.covid19info.viewmodel.IndonesiaStatisticViewModel;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class IndonesiaStatisticFragment extends Fragment {

    private PieChart pieChartIndonesia;
    private ProgressBar progressBar;
    private IndonesiaStatisticViewModel indonesiaStatisticViewModel;

    private int jumlahSembuh;
    private int jumlahPositif;
    private int jumlahMeninggal;

    private String TAG = IndonesiaStatisticFragment.class.getSimpleName();

    public IndonesiaStatisticFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_indonesia_statistic, container, false);

        pieChartIndonesia = v.findViewById(R.id.pieChartIndonesiaStatistic);
        progressBar = v.findViewById(R.id.progressBarIndonesiaStatistic);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getActivity() != null){
            indonesiaStatisticViewModel = new ViewModelProvider(getActivity()).get(IndonesiaStatisticViewModel.class);
        }
        getIndonesiaData();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        indonesiaStatisticViewModel.getDataIndonesia().observe(getViewLifecycleOwner(), new Observer<List<Indonesia>>() {
            @Override
            public void onChanged(List<Indonesia> indonesiaList) {
                Indonesia indonesia = indonesiaList.get(0);
                try {
                    jumlahMeninggal = NumberFormat.getNumberInstance(Locale.getDefault()).parse(indonesia.getMeninggal()).intValue();
                    jumlahSembuh = NumberFormat.getNumberInstance(Locale.getDefault()).parse(indonesia.getSembuh()).intValue();
                    jumlahPositif = NumberFormat.getNumberInstance(Locale.getDefault()).parse(indonesia.getPositif()).intValue();

                    setToPie();
                } catch (ParseException e) {
                    e.printStackTrace();
                    Log.e(TAG+" onActivityCreated", "onChanged: "+e.getMessage());
                }
            }
        });

        indonesiaStatisticViewModel.getIsLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if(isLoading != null){
                    if(!isLoading){
                        progressBar.setVisibility(View.GONE);
                        pieChartIndonesia.setVisibility(View.VISIBLE);
                        pieChartIndonesia.animateY(1500, Easing.EaseInOutSine);
                    }
                }
            }
        });

    }

    private void setToPie() {
        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(jumlahSembuh, "Sembuh"));
        entries.add(new PieEntry(jumlahPositif, "Positif"));
        entries.add(new PieEntry(jumlahMeninggal, "Meninggal"));

        PieDataSet pieDataSet = new PieDataSet(entries, "Statistik Indonesia");
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        pieDataSet.setValueTextSize(14);
        pieDataSet.setValueTextColor(Color.WHITE);

        PieData pieData = new PieData(pieDataSet);

        Legend legend = pieChartIndonesia.getLegend();
        legend.setTextSize(14);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);

        pieChartIndonesia.getDescription().setEnabled(false);
        pieChartIndonesia.setExtraTopOffset(-50);
        pieChartIndonesia.setData(pieData);
        pieChartIndonesia.invalidate();
    }

    private void getIndonesiaData() {
        indonesiaStatisticViewModel.setDataIndonesia();
    }
}

package com.soerjdev.covid19info.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.soerjdev.covid19info.R;
import com.soerjdev.covid19info.adapter.AdapterProvinceData;
import com.soerjdev.covid19info.model.provinsi.Provinsi;
import com.soerjdev.covid19info.viewmodel.ProvinceDataViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProvinceDataFragment extends Fragment {

    private RecyclerView rvProvinceData;
    private ProgressBar pbLoadProvinceData;

    private LinearLayoutManager linearLayoutManager;

    private List<Provinsi> listProvinsi;

    private AdapterProvinceData adapterProvinceData;

    private ProvinceDataViewModel provinceDataViewModel;

    private String TAG = ProvinceDataFragment.class.getSimpleName();

    public ProvinceDataFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listProvinsi = new ArrayList<>();

        if(getActivity() != null){
            provinceDataViewModel = new ViewModelProvider(getActivity()).get(ProvinceDataViewModel.class);
        }
        getProvinceData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_province_data, container, false);

        Toolbar toolbar = v.findViewById(R.id.tbProvinceData);

        if (getActivity() != null){
            ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
            if (((AppCompatActivity)getActivity()).getSupportActionBar() != null){
                ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
            }
        }

        rvProvinceData = v.findViewById(R.id.rvProvinceData);
        pbLoadProvinceData = v.findViewById(R.id.pbLoadProvinceData);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        adapterProvinceData = new AdapterProvinceData(listProvinsi, getActivity());

        rvProvinceData.setAdapter(adapterProvinceData);
        rvProvinceData.setLayoutManager(linearLayoutManager);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        provinceDataViewModel.getListProvinsiData().observe(getViewLifecycleOwner(), new Observer<List<Provinsi>>() {
            @Override
            public void onChanged(List<Provinsi> provinsis) {
                if(provinsis != null){
                    listProvinsi.clear();
                    listProvinsi.addAll(provinsis);
                    adapterProvinceData.notifyDataSetChanged();
                }

            }
        });

        provinceDataViewModel.getIsLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if(isLoading != null){
                    if(!isLoading){
                        pbLoadProvinceData.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    private void getProvinceData() {
        provinceDataViewModel.setListProvinsi();
    }
}

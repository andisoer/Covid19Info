package com.soerjdev.covid19info.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.soerjdev.covid19info.R;
import com.soerjdev.covid19info.model.worldata.WorldData;
import com.soerjdev.covid19info.viewmodel.MapFragmentViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mGoogleMap;
    private AlertDialog.Builder alertDialogBuilder;
    private LayoutInflater layoutInflater;
    private View dialogView;

    private MapFragmentViewModel mapFragmentViewModel;

    private List<WorldData> worldDataList = new ArrayList<>();

    private String TAG = MapFragment.class.getSimpleName();

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getActivity() != null){
            mapFragmentViewModel = new ViewModelProvider(getActivity()).get(MapFragmentViewModel.class);
        }
        getWorldData();
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_map, container, false);

        Toolbar toolbar = v.findViewById(R.id.tbWorldMap);

        if (getActivity() != null){
            ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
            if (((AppCompatActivity)getActivity()).getSupportActionBar() != null){
                ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
            }
        }

        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.mapFragment);
        if (supportMapFragment != null) {
            supportMapFragment.getMapAsync(this);
        }
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mapFragmentViewModel.getmListWorldData().observe(getViewLifecycleOwner(), new Observer<List<WorldData>>() {
            @Override
            public void onChanged(List<WorldData> worldData) {
                if(worldData != null){
                    worldDataList = worldData;
                    if(mGoogleMap != null){
                        setMarker();
                    }
                }
            }
        });
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mGoogleMap = googleMap;

        try {

            if(getContext() != null){
                boolean success = mGoogleMap.setMapStyle(MapStyleOptions.
                        loadRawResourceStyle(getContext(),
                        R.raw.style_json)
                );

                if(!success){
                    Log.e(TAG, "onMapReady: Style parsing failed");
                }
            }

        }catch (Resources.NotFoundException e){
            e.printStackTrace();
            Log.e(TAG, "onMapReady: "+e.getMessage());
        }

        mGoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                int position = (int) marker.getTag();
                final WorldData worldData = worldDataList.get(position);

                showDetailDialog(worldData);

                return false;

            }
        });
    }

    private void showDetailDialog(WorldData worldData) {
        alertDialogBuilder = new AlertDialog.Builder(getContext());
        layoutInflater = getLayoutInflater();
        dialogView = layoutInflater.inflate(R.layout.layout_dialog_maps, null);

        //Widget di dalam dialog;
        TextView tvNamaNegara = dialogView.findViewById(R.id.tvDialogNamaNegara);
        TextView tvJumlahPositif = dialogView.findViewById(R.id.tvDialogJumlahPositif);
        TextView tvJumlahSembuh = dialogView.findViewById(R.id.tvDialogJumlahSembuh);
        TextView tvJumlahMeninggal = dialogView.findViewById(R.id.tvDialogJumlahMeninggal);

        tvNamaNegara.setText(worldData.getAttributes().getCountry_Region());
        tvJumlahPositif.setText(String.valueOf(worldData.getAttributes().getConfirmed()));
        tvJumlahSembuh.setText(String.valueOf(worldData.getAttributes().getRecovered()));
        tvJumlahMeninggal.setText(String.valueOf(worldData.getAttributes().getDeaths()));

        alertDialogBuilder.setView(dialogView);
        alertDialogBuilder.setCancelable(true);

        alertDialogBuilder.setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        alertDialogBuilder.show();

    }

    private void getWorldData() {
        mapFragmentViewModel.setListWorldData();
    }

    private void setMarker() {
        for (int i = 0; i < worldDataList.size(); i++){

            WorldData worldData = worldDataList.get(i);
            String countryName = worldData.getAttributes().getCountry_Region();
            double latitude = worldData.getAttributes().getLat();
            double longitude = worldData.getAttributes().getLong_();

            Marker marker = mGoogleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(latitude, longitude))
                        .title(countryName)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
            );
            marker.setTag(i);
        }
    }
}

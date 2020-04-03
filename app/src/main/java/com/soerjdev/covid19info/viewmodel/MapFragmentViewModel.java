package com.soerjdev.covid19info.viewmodel;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.soerjdev.covid19info.api.ApiEndPoints;
import com.soerjdev.covid19info.api.ConfigApi;
import com.soerjdev.covid19info.model.worldata.WorldData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapFragmentViewModel extends ViewModel {

    private MutableLiveData<List<WorldData>> mListWorldData = new MutableLiveData<>();

    private String TAG = MapFragmentViewModel.class.getSimpleName();

    public void setListWorldData(){

        ApiEndPoints mApiEndPoints = ConfigApi.getApiService();
        Call<List<WorldData>> mCall = mApiEndPoints.getWorldData();
        mCall.enqueue(new Callback<List<WorldData>>() {
            @Override
            public void onResponse(@NotNull Call<List<WorldData>> call, @NotNull Response<List<WorldData>> response) {
                if(response.isSuccessful()){
                    List<WorldData> listWorldData = null;
                    if(response.body() != null){
                        listWorldData = response.body();
                    }
                    mListWorldData.postValue(listWorldData);
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<WorldData>> call, @NotNull Throwable t) {
                t.printStackTrace();
                Log.e(TAG, "onFailure: +setListWorldData"+t.getMessage());
            }
        });

    }

    public MutableLiveData<List<WorldData>> getmListWorldData() {
        return mListWorldData;
    }
}

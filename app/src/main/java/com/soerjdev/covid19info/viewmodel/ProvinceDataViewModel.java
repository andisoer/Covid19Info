package com.soerjdev.covid19info.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.soerjdev.covid19info.api.ApiEndPoints;
import com.soerjdev.covid19info.api.ConfigApi;
import com.soerjdev.covid19info.model.provinsi.Provinsi;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProvinceDataViewModel extends ViewModel {

    private MutableLiveData<List<Provinsi>> listProvinsiData = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    private String TAG = ProvinceDataViewModel.class.getSimpleName();

    public void setListProvinsi(){

        ApiEndPoints mApiEndPoints = ConfigApi.getApiService();
        Call<List<Provinsi>> mCall = mApiEndPoints.getProvinsiData();
        mCall.enqueue(new Callback<List<Provinsi>>() {
            @Override
            public void onResponse(@NotNull Call<List<Provinsi>> call, @NotNull Response<List<Provinsi>> response) {
                if(response.isSuccessful()){
                    List<Provinsi> listProvinsi = null;
                    if(response.body() != null){
                        listProvinsi = response.body();
                    }
                    listProvinsiData.postValue(listProvinsi);
                    setIsLoading();
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<Provinsi>> call, @NotNull Throwable t) {
                t.printStackTrace();
                Log.e(TAG, "onFailure: setListProvinisi "+t.getMessage());
            }
        });
    }

    public void setIsLoading() {
        isLoading.postValue(false);
    }

    public MutableLiveData<List<Provinsi>> getListProvinsiData() {
        return listProvinsiData;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }
}

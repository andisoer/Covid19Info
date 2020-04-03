package com.soerjdev.covid19info.viewmodel;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.soerjdev.covid19info.api.ApiEndPoints;
import com.soerjdev.covid19info.api.ConfigApi;
import com.soerjdev.covid19info.model.CovidMeninggal;
import com.soerjdev.covid19info.model.CovidPositif;
import com.soerjdev.covid19info.model.CovidSembuh;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorldStatiscticViewModel extends ViewModel {

    private MutableLiveData<String> dataMeninggal = new MutableLiveData<>();
    private MutableLiveData<String> dataSembuh = new MutableLiveData<>();
    private MutableLiveData<String> dataPositif = new MutableLiveData<>();

    private String TAG = WorldStatiscticViewModel.class.getSimpleName();


    public void setDataMeninggal(){

        ApiEndPoints mApiEndPoints = ConfigApi.getApiService();
        Call<CovidMeninggal> mCall = mApiEndPoints.getMeninggalData();
        mCall.enqueue(new Callback<CovidMeninggal>() {
            @Override
            public void onResponse(@NotNull Call<CovidMeninggal> call, @NotNull Response<CovidMeninggal> response) {
                if (response.isSuccessful()){

                    try {

                        String jumlahMeninggal = null;
                        if(response.body() != null){
                            jumlahMeninggal = response.body().getValue();
                        }
                        dataMeninggal.postValue(jumlahMeninggal);

                    }catch (Exception e){
                        e.printStackTrace();
                        Log.e(TAG+" setDataMeninggal", "onResponse: "+e.getMessage());
                    }

                }
            }

            @Override
            public void onFailure(@NotNull Call<CovidMeninggal> call, @NotNull Throwable t) {
                t.printStackTrace();
                Log.e(TAG+" setDataMeninggal", "onFailure: "+t.getMessage());
            }
        });
    }

    public void setDataSembuh(){
        ApiEndPoints mApiEndPoints = ConfigApi.getApiService();
        Call<CovidSembuh> mCall = mApiEndPoints.getSembuhData();
        mCall.enqueue(new Callback<CovidSembuh>() {
            @Override
            public void onResponse(@NotNull Call<CovidSembuh> call, @NotNull Response<CovidSembuh> response) {
                if (response.isSuccessful()){
                    if(response.body() != null){
                        dataSembuh.postValue(response.body().getValue());
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<CovidSembuh> call, @NotNull Throwable t) {
                t.printStackTrace();
                Log.e(TAG+" setDataSembuh", "onFailure: "+t.getMessage());
            }
        });
    }

    public void setDataPositif(){
        ApiEndPoints mApiEndPoints = ConfigApi.getApiService();
        Call<CovidPositif> mCall = mApiEndPoints.getPositifData();
        mCall.enqueue(new Callback<CovidPositif>() {
            @Override
            public void onResponse(@NotNull Call<CovidPositif> call, @NotNull Response<CovidPositif> response) {
                if (response.isSuccessful()){
                    if (response.body() != null)
                    dataPositif.postValue(response.body().getValue());
                }
            }

            @Override
            public void onFailure(@NotNull Call<CovidPositif> call, @NotNull Throwable t) {
                t.printStackTrace();
                Log.e(TAG+" setDataPositif", "onFailure: "+t.getMessage());
            }
        });
    }

    public MutableLiveData<String> getDataMeninggal() {
        return dataMeninggal;
    }

    public MutableLiveData<String> getDataSembuh() {
        return dataSembuh;
    }

    public MutableLiveData<String> getDataPositif() {
        return dataPositif;
    }
}

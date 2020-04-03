package com.soerjdev.covid19info.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.soerjdev.covid19info.api.ApiEndPoints;
import com.soerjdev.covid19info.api.ConfigApi;
import com.soerjdev.covid19info.model.Indonesia;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IndonesiaStatisticViewModel extends ViewModel {

    private MutableLiveData<List<Indonesia>> dataIndonesia = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    private String TAG = IndonesiaStatisticViewModel.class.getSimpleName();

    public void setDataIndonesia(){

        ApiEndPoints mApiEndPoints = ConfigApi.getApiService();
        Call<List<Indonesia>> mCall = mApiEndPoints.getIDNData();
        mCall.enqueue(new Callback<List<Indonesia>>() {
            @Override
            public void onResponse(@NotNull Call<List<Indonesia>> call, @NotNull Response<List<Indonesia>> response) {
                if (response.isSuccessful()){
                    List<Indonesia> indonesiaList = null;
                    if(response.body() != null){
                        indonesiaList = response.body();
                    }
                    dataIndonesia.postValue(indonesiaList);
                    setIsLoading();
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<Indonesia>> call, @NotNull Throwable t) {
                t.printStackTrace();
                Log.e(TAG, "onFailure: setDataIndonesia"+t.getMessage());
            }
        });

    }

    private void setIsLoading(){
        isLoading.postValue(false);
    }

    public MutableLiveData<List<Indonesia>> getDataIndonesia() {
        return dataIndonesia;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }
}

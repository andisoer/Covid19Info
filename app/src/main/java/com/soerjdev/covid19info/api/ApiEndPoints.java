package com.soerjdev.covid19info.api;

import com.soerjdev.covid19info.model.CovidMeninggal;
import com.soerjdev.covid19info.model.CovidPositif;
import com.soerjdev.covid19info.model.CovidSembuh;
import com.soerjdev.covid19info.model.Indonesia;
import com.soerjdev.covid19info.model.provinsi.Provinsi;
import com.soerjdev.covid19info.model.worldata.WorldData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndPoints {

    @GET(ConfigApi.END_POINT_WORLD)
    Call<List<WorldData>> getWorldData();

    @GET(ConfigApi.END_POINT_INDONESIA)
    Call<List<Indonesia>> getIDNData();

    @GET(ConfigApi.EMD_POINT_PROVINSI)
    Call<List<Provinsi>> getProvinsiData();

    @GET(ConfigApi.END_POINT_GLOBAL_MENINGGAL)
    Call<CovidMeninggal> getMeninggalData();

    @GET(ConfigApi.END_POINT_GLOBAL_POSITIF)
    Call<CovidPositif> getPositifData();

    @GET(ConfigApi.END_POINT_GLOBAL_SEMBUH)
    Call<CovidSembuh> getSembuhData();

}

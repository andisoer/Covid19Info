package com.soerjdev.covid19info.api;

public class ConfigApi {

    //URL API
    private static String BASE_URL_API = "https://api.kawalcorona.com/";

    //END POINT API
    public static final String END_POINT_WORLD = "api";
    public static final String END_POINT_INDONESIA = "indonesia";
    public static final String EMD_POINT_PROVINSI = "indonesia/provinsi";
    public static final String END_POINT_GLOBAL_POSITIF = "positif";
    public static final String END_POINT_GLOBAL_SEMBUH = "sembuh";
    public static final String END_POINT_GLOBAL_MENINGGAL = "meninggal";

    public static ApiEndPoints getApiService(){
        return RetrofitClient.getClient(BASE_URL_API).create(ApiEndPoints.class);
    }
}

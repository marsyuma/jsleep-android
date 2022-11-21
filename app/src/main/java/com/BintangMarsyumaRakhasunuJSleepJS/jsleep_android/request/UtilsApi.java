package com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.request;

public class UtilsApi {

    public static final String BASE_URL_API = "http://192.168.100.230:8080/";

    public static BaseApiService getApiService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }

}

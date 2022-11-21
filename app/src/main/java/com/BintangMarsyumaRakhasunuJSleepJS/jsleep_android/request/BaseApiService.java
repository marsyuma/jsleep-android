package com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.request;

import com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.model.Account;
import com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.model.Room;

import retrofit2.Call;
import retrofit2.http.*;

public interface BaseApiService {
    @GET("account/{id}")
    Call<Account> getAccount (@Path("id") int id);

    @POST("account/login")
    Call<Account> login (@Query("email") String email,@Query("password") String password);

    @GET("room/{id}")
    Call<Room> getRoom (@Path("id") int id);
}

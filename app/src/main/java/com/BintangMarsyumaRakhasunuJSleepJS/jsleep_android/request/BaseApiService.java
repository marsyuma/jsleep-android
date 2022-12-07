package com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.request;

import com.BintangMarsyumaRakhasunuJSleepJS.jsleep_android.model.*;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface BaseApiService {
    @GET("account/{id}")
    Call<Account> getAccount (@Path("id") int id);

    @POST("account/login")
    Call<Account> login (@Query("email") String email,@Query("password") String password);

    @POST("account/register")
    Call<Account> register (@Query("name") String name, @Query("email") String email,@Query("password") String password);

    @GET("room/{id}")
    Call<Room> getRoom (@Path("id") int id);

    @POST("account/{id}/registerRenter")
    Call<Renter> registerRenter(@Path("id") int id,
                                @Query("username") String username,
                                @Query("address") String address,
                                @Query("phoneNumber") String phoneNumber);

    @GET("room/getAllRoom")
    Call<List<Room>> getAllRoom(@Query("page") int page, @Query("pageSize") int pageSize);
    @POST("room/create")
    Call<Room> room(@Query("accountId") int accountId, @Query("name") String name, @Query("size") int size, @Query("price") int price, @Query("facility") ArrayList<Facility> facility, @Query("city") City city, @Query("address") String address, @Query("bedType") BedType bedType);

    @POST("account/{id}/topUp")
    Call<Boolean> topUp(@Path("id") int id,
                        @Query("balance") int balance);
}

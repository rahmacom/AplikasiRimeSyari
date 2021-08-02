package com.rahmacom.rimesyarifix.data.network.api;

import androidx.lifecycle.LiveData;

import com.rahmacom.rimesyarifix.data.network.ApiResponse;
import com.rahmacom.rimesyarifix.data.network.response.ResponseLogin;
import com.rahmacom.rimesyarifix.data.network.response.ResponseProduk;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RimeSyariAPI {

    @POST("login")
    @FormUrlEncoded
    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    Call<ResponseLogin> login(
            @Field("email") String email,
            @Field("password") String password);

    @POST("refresh")
    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    Call<ResponseLogin> refreshLogin(@Header("Authorization") String token);

    @POST("register")
    @FormUrlEncoded
    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    Call<ResponseLogin> register(
            @Field("name") String username,
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("products")
    @Headers({"Accept: application/json"})
    Call<List<ResponseProduk>> getAllProduk(@Header("Authorization") String token);
//    LiveData<ApiResponse<List<ResponseProduk>>> getProduk(@Header("Authorization") String token);
}

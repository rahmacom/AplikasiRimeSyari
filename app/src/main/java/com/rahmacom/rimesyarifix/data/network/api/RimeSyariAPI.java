package com.rahmacom.rimesyarifix.data.network.api;

import com.rahmacom.rimesyarifix.data.network.model.ResponseLogin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RimeSyariAPI {

    @POST("login")
    @FormUrlEncoded
    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    Call<ResponseLogin> setLogin(
            @Field("email") String email,
            @Field("password") String password);

    @POST("refresh")
    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    Call<ResponseLogin> refreshLogin();
}

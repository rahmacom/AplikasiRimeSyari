package com.rahmacom.rimesyarifix.data;

import androidx.lifecycle.LiveData;

import com.rahmacom.rimesyarifix.data.network.ApiResponse;
import com.rahmacom.rimesyarifix.data.network.model.ResponseLogin;
import com.rahmacom.rimesyarifix.data.network.model.ResponseProduk;

public interface MainDataSource {
    LiveData<ApiResponse<ResponseLogin>> setLogin(String username, String password);
    LiveData<ApiResponse<ResponseLogin>> refreshToken();
    LiveData<ApiResponse<ResponseProduk>> getProduks();
}

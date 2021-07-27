package com.rahmacom.rimesyarifix.ui.login;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rahmacom.rimesyarifix.data.network.api.RimeSyariAPI;
import com.rahmacom.rimesyarifix.data.network.model.ResponseLogin;
import com.rahmacom.rimesyarifix.di.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    public MutableLiveData<ResponseLogin> login = new MutableLiveData<>();

    public LiveData<ResponseLogin> setLogin(String email, String password) {
        RimeSyariAPI service = ApiClient.getApiClient().create(RimeSyariAPI.class);
        Call<ResponseLogin> api = service.setLogin(email, password);
        api.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                Log.d("login", String.valueOf(response.body()));
                if (response.isSuccessful()) {
                    login.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                login.postValue(null);
                Log.e("LoginViewModel", t.getMessage(), t);
            }
        });

        return login;
    }
}
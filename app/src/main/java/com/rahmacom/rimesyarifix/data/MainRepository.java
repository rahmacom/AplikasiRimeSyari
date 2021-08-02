package com.rahmacom.rimesyarifix.data;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rahmacom.rimesyarifix.data.network.api.RimeSyariAPI;
import com.rahmacom.rimesyarifix.data.network.response.ResponseLogin;
import com.rahmacom.rimesyarifix.data.network.response.ResponseProduk;
import com.rahmacom.rimesyarifix.data.vo.Resource;
import com.rahmacom.rimesyarifix.utils.AppExecutors;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class MainRepository {

    private final AppExecutors appExecutors;
    private final RimeSyariAPI rimeSyariAPI;

    @Inject
    public MainRepository(AppExecutors appExecutors, RimeSyariAPI rimeSyariAPI) {
        this.appExecutors = appExecutors;
        this.rimeSyariAPI = rimeSyariAPI;
    }


    public LiveData<Resource<ResponseLogin>> login(String name, String password) {
        MutableLiveData<Resource<ResponseLogin>> login = new MutableLiveData<>();
        login.setValue(Resource.loading(null));

        Call<ResponseLogin> api = rimeSyariAPI.login(name, password);
        api.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                Log.d("responseLogin", response.toString());
                switch (response.code()) {
                    case 200:
                    case 201:
                        login.postValue(Resource.success(response.body()));
                        break;

                    case 204:
                        login.postValue(Resource.empty(null));
                        break;

                    case 401:
                    case 403:
                        login.postValue(Resource.invalid(response.message()));
                        break;

                    case 404:
                        break;
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                login.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return login;
    }

    public LiveData<Resource<ResponseLogin>> register(String name, String email, String password) {
        MutableLiveData<Resource<ResponseLogin>> register = new MutableLiveData<>();
        register.setValue(Resource.loading(null));

        Call<ResponseLogin> api = rimeSyariAPI.register(name, email, password);
        api.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                switch (response.code()) {
                    case 200:
                    case 201:
                        register.postValue(Resource.success(response.body()));
                        break;

                    case 204:
                        register.postValue(Resource.empty(null));
                        break;

                    case 401:
                    case 403:
                        register.postValue(Resource.invalid(response.message()));
                        break;

                    case 404:
                        register.postValue(Resource.error(response.message(), response.body()));
                        break;
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                register.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return register;
    }

    public LiveData<Resource<List<ResponseProduk>>> getAllProducts(String token) {
        MutableLiveData<Resource<List<ResponseProduk>>> products = new MutableLiveData<>();
        products.setValue(Resource.loading(null));

        Call<List<ResponseProduk>> api = rimeSyariAPI.getAllProduk("Bearer " + token);
        api.enqueue(new Callback<List<ResponseProduk>>() {
            @Override
            public void onResponse(Call<List<ResponseProduk>> call, Response<List<ResponseProduk>> response) {
                Log.d("token", token);
                switch (response.code()) {
                    case 200:
                    case 201:
                        products.postValue(Resource.success(response.body()));
                        break;

                    case 204:
                        products.postValue(Resource.empty(null));
                        break;

                    case 401:
                    case 403:
                        Log.d("mainRepo", response.message());
                        products.postValue(Resource.invalid(response.message()));
                        break;

                    case 404:
                        products.postValue(Resource.error(response.message(), response.body()));
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<ResponseProduk>> call, Throwable t) {
                products.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return products;
    }
}

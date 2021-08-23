package com.rahmacom.rimesyarifix.data;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rahmacom.rimesyarifix.data.entity.User;
import com.rahmacom.rimesyarifix.data.network.api.RimeSyariAPI;
import com.rahmacom.rimesyarifix.data.network.response.LoginResponse;
import com.rahmacom.rimesyarifix.data.vo.Resource;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class AuthRepository {

    private final RimeSyariAPI rimeSyariAPI;

    @Inject
    public AuthRepository(RimeSyariAPI rimeSyariAPI) {
        this.rimeSyariAPI = rimeSyariAPI;
    }

    public LiveData<Resource<LoginResponse>> login(String name, String password) {
        MutableLiveData<Resource<LoginResponse>> data = new MutableLiveData<>();
        data.setValue(Resource.loading(null));

        Call<LoginResponse> api = rimeSyariAPI.login(name, password);
        api.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.d("responseLogin", response.message());
                switch (response.code()) {
                    case 200:
                    case 201:
                        data.postValue(Resource.success(response.body()));
                        break;

                    case 204:
                        data.postValue(Resource.empty(null));
                        break;

                    case 400:
                        data.postValue(Resource.invalid(response.message()));
                        break;

                    case 401:
                        data.postValue(Resource.unauthorized(response.message()));
                        break;

                    case 403:
                        data.postValue(Resource.forbidden(response.message()));
                        break;

                    case 404:
                    case 405:
                        data.postValue(Resource.error(response.message(), null));
                        break;

                    case 422:
                        data.postValue(Resource.unprocessableEntity(response.message(), null));
                        break;
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("errorLogin", t.getMessage());
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    public LiveData<Resource<LoginResponse>> updateLogin(String token,
                                                         String email,
                                                         String oldPassword,
                                                         String newPassword) {
        MutableLiveData<Resource<LoginResponse>> data = new MutableLiveData<>();
        data.setValue(Resource.loading(null));

        Call<LoginResponse> api = rimeSyariAPI.updateLogin(token, email, oldPassword, newPassword);
        api.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                switch (response.code()) {
                    case 200:
                    case 201:
                        data.postValue(Resource.success(response.body()));
                        break;

                    case 204:
                        data.postValue(Resource.empty(null));
                        break;

                    case 400:
                        data.postValue(Resource.invalid(response.message()));
                        break;

                    case 401:
                        data.postValue(Resource.unauthorized(response.message()));
                        break;

                    case 403:
                        data.postValue(Resource.forbidden(response.message()));
                        break;

                    case 404:
                    case 405:
                        data.postValue(Resource.error(response.message(), null));
                        break;

                    case 422:
                        data.postValue(Resource.unprocessableEntity(response.message(), null));
                        break;
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    public LiveData<Resource<LoginResponse>> logout(String token) {
        MutableLiveData<Resource<LoginResponse>> data = new MutableLiveData<>();
        data.setValue(Resource.loading(null));

        Call<LoginResponse> api = rimeSyariAPI.logout(token);
        api.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                switch (response.code()) {
                    case 200:
                    case 201:
                        data.postValue(Resource.success(response.body()));
                        break;

                    case 204:
                        data.postValue(Resource.empty(null));
                        break;

                    case 400:
                        data.postValue(Resource.invalid(response.message()));
                        break;

                    case 401:
                        data.postValue(Resource.unauthorized(response.message()));
                        break;

                    case 403:
                        data.postValue(Resource.forbidden(response.message()));
                        break;

                    case 404:
                    case 405:
                        data.postValue(Resource.error(response.message(), null));
                        break;

                    case 422:
                        data.postValue(Resource.unprocessableEntity(response.message(), null));
                        break;
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    public LiveData<Resource<LoginResponse>> refreshLogin(String token) {
        MutableLiveData<Resource<LoginResponse>> data = new MutableLiveData<>();
        data.setValue(Resource.loading(null));

        Call<LoginResponse> api = rimeSyariAPI.refresh(token);
        api.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                switch (response.code()) {
                    case 200:
                    case 201:
                        data.postValue(Resource.success(response.body()));
                        break;

                    case 204:
                        data.postValue(Resource.empty(null));
                        break;

                    case 400:
                        data.postValue(Resource.invalid(response.message()));
                        break;

                    case 401:
                        data.postValue(Resource.unauthorized(response.message()));
                        break;

                    case 403:
                        data.postValue(Resource.forbidden(response.message()));
                        break;

                    case 404:
                    case 405:
                        data.postValue(Resource.error(response.message(), null));
                        break;

                    case 422:
                        data.postValue(Resource.unprocessableEntity(response.message(), null));
                        break;
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    public LiveData<Resource<LoginResponse>> register(String name, String email, String password) {
        MutableLiveData<Resource<LoginResponse>> data = new MutableLiveData<>();
        data.setValue(Resource.loading(null));

        Call<LoginResponse> api = rimeSyariAPI.register(name, email, password);
        api.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                switch (response.code()) {
                    case 200:
                    case 201:
                        data.postValue(Resource.success(response.body()));
                        break;

                    case 204:
                        data.postValue(Resource.empty(null));
                        break;

                    case 400:
                        data.postValue(Resource.invalid(response.message()));
                        break;

                    case 401:
                        data.postValue(Resource.unauthorized(response.message()));
                        break;

                    case 403:
                        data.postValue(Resource.forbidden(response.message()));
                        break;

                    case 404:
                    case 405:
                        data.postValue(Resource.error(response.message(), null));
                        break;

                    case 422:
                        data.postValue(Resource.unprocessableEntity(response.message(), null));
                        break;
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    public LiveData<Resource<User>> profile(String token) {
        MutableLiveData<Resource<User>> data = new MutableLiveData<>();
        data.setValue(Resource.loading(null));

        Call<User> api = rimeSyariAPI.profile(token);
        api.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                switch (response.code()) {
                    case 200:
                    case 201:
                        data.postValue(Resource.success(response.body()));
                        break;

                    case 204:
                        data.postValue(Resource.empty(null));
                        break;

                    case 400:
                        data.postValue(Resource.invalid(response.message()));
                        break;

                    case 401:
                        data.postValue(Resource.unauthorized(response.message()));
                        break;

                    case 403:
                        data.postValue(Resource.forbidden(response.message()));
                        break;

                    case 404:
                    case 405:
                        data.postValue(Resource.error(response.message(), null));
                        break;

                    case 422:
                        data.postValue(Resource.unprocessableEntity(response.message(), null));
                        break;
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }
}

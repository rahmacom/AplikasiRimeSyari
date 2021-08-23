package com.rahmacom.rimesyarifix.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rahmacom.rimesyarifix.data.entity.Shipment;
import com.rahmacom.rimesyarifix.data.entity.User;
import com.rahmacom.rimesyarifix.data.network.api.RimeSyariAPI;
import com.rahmacom.rimesyarifix.data.vo.Resource;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class UserRepository {

    private final RimeSyariAPI rimeSyariAPI;

    @Inject
    public UserRepository(RimeSyariAPI rimeSyariAPI) {
        this.rimeSyariAPI = rimeSyariAPI;
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

    public LiveData<Resource<User>> updateProfile(String token, User user, Shipment shipment) {
        MutableLiveData<Resource<User>> data = new MutableLiveData<>();
        data.setValue(Resource.loading(null));

        Call<User> api = rimeSyariAPI.updateProfile(
                token,
                user.getName(),
                user.getJenisKelamin(),
                user.getTempatLahir(),
                user.getTglLahir(),
                shipment.getAlamat(),
                shipment.getId(),
                user.getNoHp(),
                user.getNoWa());

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

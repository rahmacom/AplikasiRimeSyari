package com.rahmacom.rimesyarifix.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rahmacom.rimesyarifix.data.entity.Order;
import com.rahmacom.rimesyarifix.data.entity.Shipment;
import com.rahmacom.rimesyarifix.data.network.api.RimeSyariAPI;
import com.rahmacom.rimesyarifix.data.vo.Resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class OrderRepository {

    private final RimeSyariAPI rimeSyariAPI;

    @Inject
    public OrderRepository(RimeSyariAPI rimeSyariAPI) {
        this.rimeSyariAPI = rimeSyariAPI;
    }

    public LiveData<Resource<List<Order>>> getAllOrders(String token, int statusId) {
        MutableLiveData<Resource<List<Order>>> data = new MutableLiveData<>();
        data.setValue(Resource.loading(null));

        Call<List<Order>> api = rimeSyariAPI.getAllOrders(token, statusId);
        api.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
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
            public void onFailure(Call<List<Order>> call, Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    public LiveData<Resource<Order>> viewOrder(String token, int orderId) {
        MutableLiveData<Resource<Order>> data = new MutableLiveData<>();
        data.setValue(Resource.loading(null));

        Call<Order> api = rimeSyariAPI.viewOrder(token, orderId);
        api.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(@NonNull Call<Order> call, @NonNull Response<Order> response) {
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
            public void onFailure(@NonNull Call<Order> call, @NonNull Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }
}

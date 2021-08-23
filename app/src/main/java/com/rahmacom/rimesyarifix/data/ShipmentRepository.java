package com.rahmacom.rimesyarifix.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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
public class ShipmentRepository {

    private final RimeSyariAPI rimeSyariAPI;

    @Inject
    public ShipmentRepository(RimeSyariAPI rimeSyariAPI) {
        this.rimeSyariAPI = rimeSyariAPI;
    }

    public LiveData<Resource<List<Shipment>>> getShipmentAddresses(String token) {
        MutableLiveData<Resource<List<Shipment>>> data = new MutableLiveData<>();
        data.setValue(Resource.loading(null));

        Call<List<Shipment>> api = rimeSyariAPI.getShipmentAddresses(token);
        api.enqueue(new Callback<List<Shipment>>() {
            @Override
            public void onResponse(Call<List<Shipment>> call, Response<List<Shipment>> response) {
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
            public void onFailure(Call<List<Shipment>> call, Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }
}

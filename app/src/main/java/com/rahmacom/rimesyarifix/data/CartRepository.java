package com.rahmacom.rimesyarifix.data;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rahmacom.rimesyarifix.data.entity.Cart;
import com.rahmacom.rimesyarifix.data.network.api.RimeSyariAPI;
import com.rahmacom.rimesyarifix.data.vo.Resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class CartRepository {

    private final RimeSyariAPI rimeSyariAPI;

    @Inject
    public CartRepository(RimeSyariAPI rimeSyariAPI) {
        this.rimeSyariAPI = rimeSyariAPI;
    }

    public LiveData<Resource<List<Cart>>> getAllCarts(String token) {
        MutableLiveData<Resource<List<Cart>>> data = new MutableLiveData<>();
        data.setValue(Resource.loading(null));

        Call<List<Cart>> api = rimeSyariAPI.getAllCarts(token);
        api.enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
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
            public void onFailure(Call<List<Cart>> call, Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    public LiveData<Resource<Cart>> viewCart(String token, int cartId) {
        MutableLiveData<Resource<Cart>> data = new MutableLiveData<>();
        data.setValue(Resource.loading(null));

        Call<Cart> api = rimeSyariAPI.viewCart(token, cartId);
        api.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                Log.d("responseCartDetail", response.code() + ": " + response.message());
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
            public void onFailure(Call<Cart> call, Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    public LiveData<Resource<Cart>> createNewCart(String token, Cart cart) {
        MutableLiveData<Resource<Cart>> data = new MutableLiveData<>();
        data.setValue(Resource.loading(null));

//        Call<Cart> api = rimeSyariAPI.createNewCart(token, cart.getJudul(), cart.getDeskripsi(),
//                cart.getPi);

        return data;
    }
}

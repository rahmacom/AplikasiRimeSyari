package com.rahmacom.rimesyarifix.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rahmacom.rimesyarifix.data.model.Cart;
import com.rahmacom.rimesyarifix.data.model.Color;
import com.rahmacom.rimesyarifix.data.model.District;
import com.rahmacom.rimesyarifix.data.model.Image;
import com.rahmacom.rimesyarifix.data.model.Order;
import com.rahmacom.rimesyarifix.data.model.PaymentMethod;
import com.rahmacom.rimesyarifix.data.model.Post;
import com.rahmacom.rimesyarifix.data.model.Product;
import com.rahmacom.rimesyarifix.data.model.Province;
import com.rahmacom.rimesyarifix.data.model.Regency;
import com.rahmacom.rimesyarifix.data.model.Size;
import com.rahmacom.rimesyarifix.data.model.Testimony;
import com.rahmacom.rimesyarifix.data.model.User;
import com.rahmacom.rimesyarifix.data.model.UserShipment;
import com.rahmacom.rimesyarifix.data.model.UserVerification;
import com.rahmacom.rimesyarifix.data.model.Village;
import com.rahmacom.rimesyarifix.data.network.api.RimeSyariAPI;
import com.rahmacom.rimesyarifix.data.network.response.ResponseLogin;
import com.rahmacom.rimesyarifix.data.vo.Resource;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

@Singleton
public class MainRepository {

    private final RimeSyariAPI rimeSyariAPI;

    @Inject
    public MainRepository(RimeSyariAPI rimeSyariAPI) {
        this.rimeSyariAPI = rimeSyariAPI;
    }

    /*
     * ---------------------------------------------------------------------------------------------
     *
     * AUTENTIKASI / PROFIL
     *
     * ---------------------------------------------------------------------------------------------
     */

    public LiveData<Resource<ResponseLogin>> login(String email, String password) {
        MutableLiveData<Resource<ResponseLogin>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<ResponseLogin> api = rimeSyariAPI.login(email, password);
        api.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(@NonNull Call<ResponseLogin> call, @NonNull Response<ResponseLogin> response) {
                Timber.d(response.message());
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
            public void onFailure(@NonNull Call<ResponseLogin> call, @NonNull Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    public LiveData<Resource<ResponseLogin>> updateLogin(String token, String email, String oldPassword, String newPassword) {
        MutableLiveData<Resource<ResponseLogin>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<ResponseLogin> api = rimeSyariAPI.updateLogin(token, email, oldPassword, newPassword);
        api.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(@NonNull Call<ResponseLogin> call, @NonNull Response<ResponseLogin> response) {
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
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    public LiveData<Resource<ResponseLogin>> refreshLogin(String token) {
        MutableLiveData<Resource<ResponseLogin>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<ResponseLogin> api = rimeSyariAPI.refresh(token);
        api.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
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
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    public LiveData<Resource<ResponseLogin>> register(String name, String email, String password) {
        MutableLiveData<Resource<ResponseLogin>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<ResponseLogin> api = rimeSyariAPI.register(name, email, password);
        api.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                Timber.d(response.message());
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
                        try {
                            Timber.d(response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    public LiveData<Resource<ResponseLogin>> logout(String token) {
        MutableLiveData<Resource<ResponseLogin>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<ResponseLogin> api = rimeSyariAPI.logout(token);
        api.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(@NonNull Call<ResponseLogin> call, @NonNull Response<ResponseLogin> response) {
                Timber.d(response.message());
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
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    public LiveData<Resource<User>> profile(String token) {
        MutableLiveData<Resource<User>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

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

    public LiveData<Resource<User>> updateProfile(String token, User user) {
        MutableLiveData<Resource<User>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<User> api = rimeSyariAPI.updateProfile(
                token,
                user.getNik(),
                user.getNamaLengkap(),
                user.getJk(),
                user.getTempatLahir(),
                user.getTglLahir(),
                user.getAlamat(),
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
                    case 500:
                        try {
                            Timber.e(response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
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

    public LiveData<Resource<Image>> uploadProfilePhoto(String token, File image) {
        MutableLiveData<Resource<Image>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        RequestBody requestImagePath = RequestBody.create(image, MediaType.parse("multipart/form-data"));
        MultipartBody.Part imagePart = MultipartBody.Part.createFormData("path", image.getName(), requestImagePath);

        Call<Image> api = rimeSyariAPI.uploadAvatar(token, imagePart);
        api.enqueue(new Callback<Image>() {
            @Override
            public void onResponse(@NonNull Call<Image> call, @NonNull Response<Image> response) {
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
                    case 500:
                        try {
                            Timber.e(response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        data.postValue(Resource.error(response.message(), null));
                        break;

                    case 422:
                        data.postValue(Resource.unprocessableEntity(response.message(), null));
                        break;
                }
            }

            @Override
            public void onFailure(@NonNull Call<Image> call, @NonNull Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    /*
     * ---------------------------------------------------------------------------------------------
     *
     * KERANJANG
     *
     * ---------------------------------------------------------------------------------------------
     */

    public LiveData<Resource<List<Cart>>> allCarts(String token) {
        MutableLiveData<Resource<List<Cart>>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<List<Cart>> api = rimeSyariAPI.allCarts(token);
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
        data.postValue(Resource.loading(null));

        Call<Cart> api = rimeSyariAPI.viewCart(token, cartId);
        api.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
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

    public LiveData<Resource<Product>> newCartViewProduct(String token, int id, int colorId, int sizeId) {
        MutableLiveData<Resource<Product>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<Product> api = rimeSyariAPI.newCartViewProduct(token, id, colorId, sizeId);
        api.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(@NonNull Call<Product> call, @NonNull Response<Product> response) {
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
            public void onFailure(@NonNull Call<Product> call, @NonNull Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    public LiveData<Resource<Cart>> newCart(String token, String judul, String deskripsi, int productId, int colorId, int sizeId, int jumlah) {
        MutableLiveData<Resource<Cart>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<Cart> api = rimeSyariAPI.newCart(token, judul, deskripsi, productId, colorId, sizeId, jumlah);

        api.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
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
                        try {
                            Timber.d(response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
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

    public LiveData<Resource<Cart>> updateCart(String token, int cartId, String judul, String deskripsi) {
        MutableLiveData<Resource<Cart>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<Cart> api = rimeSyariAPI.updateCart(token, cartId, judul, deskripsi);
        api.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
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

    public LiveData<Resource<Cart>> updateCartWithDetails(String token, int cartId, String judul, String deskripsi, List<Integer> productIds, List<Integer> colorIds, List<Integer> sizeIds, List<Integer> quantities) {
        MutableLiveData<Resource<Cart>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<Cart> api = rimeSyariAPI.updateCartWithDetails(token, cartId, judul, deskripsi, productIds, colorIds, sizeIds, quantities);
        api.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                Timber.d(response.message());
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

    public LiveData<Resource<Cart>> deleteCart(String token, int cartId) {
        MutableLiveData<Resource<Cart>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<Cart> api = rimeSyariAPI.deleteCart(token, cartId);
        api.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
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

    public LiveData<Resource<Cart>> addProductToCart(String token, int cartId, int productId, int colorId, int sizeId, int jumlah) {
        MutableLiveData<Resource<Cart>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<Cart> api = rimeSyariAPI.addProductToCart(token, cartId, productId, colorId, sizeId, jumlah);
        api.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
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

    public LiveData<Resource<Cart>> updateProductsInCart(String token, int cartId, List<Integer> productIds, List<Integer> colorIds, List<Integer> sizeIds, List<Integer> quantities) {
        MutableLiveData<Resource<Cart>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<Cart> api = rimeSyariAPI.updateProductsInCart(token, cartId, productIds, colorIds, sizeIds, quantities);
        api.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
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

    public LiveData<Resource<Cart>> removeProductFromCart(String token, int cartId, int productId) {
        MutableLiveData<Resource<Cart>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<Cart> api = rimeSyariAPI.removeProductsFromCart(token, cartId, productId);
        api.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
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
    /*
     * ---------------------------------------------------------------------------------------------
     *
     * ORDER
     *
     * ---------------------------------------------------------------------------------------------
     */

    public LiveData<Resource<List<Order>>> allOrders(String token, int statusId) {
        MutableLiveData<Resource<List<Order>>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<List<Order>> api = rimeSyariAPI.allOrders(token, statusId);
        api.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(@NonNull Call<List<Order>> call, @NonNull Response<List<Order>> response) {
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
            public void onFailure(@NonNull Call<List<Order>> call, @NonNull Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    public LiveData<Resource<Order>> viewOrder(String token, int orderId) {
        MutableLiveData<Resource<Order>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

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

    public LiveData<Resource<Order>> newOrder(String token, String pesan, String kodeDiskon, int userShipmentId, int paymentMethodId, List<Integer> productIds, List<Integer> colorIds, List<Integer> sizeIds, List<Integer> quantities) {
        MutableLiveData<Resource<Order>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<Order> api = rimeSyariAPI.newOrder(token, pesan, kodeDiskon, userShipmentId, paymentMethodId, productIds, colorIds, sizeIds, quantities);
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
                        Timber.e(response.message());
                        try {
                            Timber.e(response.errorBody().string());
                        } catch (IOException ignored) {

                        }
                        data.postValue(Resource.unprocessableEntity(response.message(), null));
                }
            }

            @Override
            public void onFailure(@NonNull Call<Order> call, @NonNull Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    public LiveData<Resource<List<PaymentMethod>>> availablePaymentMethods(String token) {
        MutableLiveData<Resource<List<PaymentMethod>>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<List<PaymentMethod>> api = rimeSyariAPI.availablePaymentMethods(token);
        api.enqueue(new Callback<List<PaymentMethod>>() {
            @Override
            public void onResponse(@NonNull Call<List<PaymentMethod>> call, @NonNull Response<List<PaymentMethod>> response) {
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
            public void onFailure(@NonNull Call<List<PaymentMethod>> call, @NonNull Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    public LiveData<Resource<Boolean>> payOrder(String token, int orderId, File image) {
        MutableLiveData<Resource<Boolean>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        RequestBody requestImagePath = RequestBody.create(image, MediaType.parse("multipart/form-data"));
        MultipartBody.Part imagePart = MultipartBody.Part.createFormData("path", image.getName(), requestImagePath);

        Call<Boolean> api = rimeSyariAPI.uploadPayment(token, orderId, imagePart);
        api.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
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
            public void onFailure(Call<Boolean> call, Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    /*
     * ---------------------------------------------------------------------------------------------
     *
     * PRODUK
     *
     * ---------------------------------------------------------------------------------------------
     */

    public LiveData<Resource<List<Product>>> allProducts(String token) {
        MutableLiveData<Resource<List<Product>>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<List<Product>> api = rimeSyariAPI.allProducts(token);
        api.enqueue(new Callback<List<Product>>() {

            @Override
            public void onResponse(@NonNull Call<List<Product>> call, @NonNull Response<List<Product>> response) {
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
            public void onFailure(@NonNull Call<List<Product>> call, @NonNull Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    public LiveData<Resource<Product>> viewProduct(String token, int id) {
        MutableLiveData<Resource<Product>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<Product> api = rimeSyariAPI.viewProduct(token, id);
        api.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(@NonNull Call<Product> call, @NonNull Response<Product> response) {
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
            public void onFailure(@NonNull Call<Product> call, @NonNull Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    public LiveData<Resource<List<Color>>> productColors(String token, int productId) {
        MutableLiveData<Resource<List<Color>>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<List<Color>> api = rimeSyariAPI.productColors(token, productId);
        api.enqueue(new Callback<List<Color>>() {
            @Override
            public void onResponse(@NonNull Call<List<Color>> call, @NonNull Response<List<Color>> response) {
                Timber.d(response.message());
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
            public void onFailure(@NonNull Call<List<Color>> call, @NonNull Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    public LiveData<Resource<List<Size>>> productSizes(String token, int productId, int colorId) {
        MutableLiveData<Resource<List<Size>>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<List<Size>> api = rimeSyariAPI.productSizes(token, productId, colorId);
        api.enqueue(new Callback<List<Size>>() {
            @Override
            public void onResponse(@NonNull Call<List<Size>> call, @NonNull Response<List<Size>> response) {
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
            public void onFailure(@NonNull Call<List<Size>> call, @NonNull Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    public LiveData<Resource<Integer>> likeProduct(String token, int productId) {
        MutableLiveData<Resource<Integer>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<Integer> api = rimeSyariAPI.likeProduct(token, productId);
        api.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(@NonNull Call<Integer> call, @NonNull Response<Integer> response) {
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
            public void onFailure(@NonNull Call<Integer> call, @NonNull Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    public LiveData<Resource<Integer>> dislikeProduct(String token, int productId) {
        MutableLiveData<Resource<Integer>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<Integer> api = rimeSyariAPI.dislikeProduct(token, productId);
        api.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(@NonNull Call<Integer> call, @NonNull Response<Integer> response) {
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
            public void onFailure(@NonNull Call<Integer> call, @NonNull Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    /*
     * ---------------------------------------------------------------------------------------------
     *
     * ALAMAT PENGIRIMAN
     *
     * ---------------------------------------------------------------------------------------------
     */

    public LiveData<Resource<List<UserShipment>>> shipmentAddresses(String token) {
        MutableLiveData<Resource<List<UserShipment>>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<List<UserShipment>> api = rimeSyariAPI.shipmentAddresses(token);
        api.enqueue(new Callback<List<UserShipment>>() {
            @Override
            public void onResponse(@NonNull Call<List<UserShipment>> call, @NonNull Response<List<UserShipment>> response) {
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
            public void onFailure(@NonNull Call<List<UserShipment>> call, @NonNull Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    public LiveData<Resource<UserShipment>> defaultShipmentAddress(String token) {
        MutableLiveData<Resource<UserShipment>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<UserShipment> api = rimeSyariAPI.defaultShipmentAddress(token);
        api.enqueue(new Callback<UserShipment>() {
            @Override
            public void onResponse(@NonNull Call<UserShipment> call, @NonNull Response<UserShipment> response) {
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
            public void onFailure(@NonNull Call<UserShipment> call, @NonNull Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    public LiveData<Resource<UserShipment>> setAsDefaultShipmentAddress(String token, int shipmentId) {
        MutableLiveData<Resource<UserShipment>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Timber.d(String.valueOf(shipmentId));

        Call<UserShipment> api = rimeSyariAPI.setAsDefaultShipmentAddress(token, shipmentId);
        api.enqueue(new Callback<UserShipment>() {
            @Override
            public void onResponse(@NonNull Call<UserShipment> call, @NonNull Response<UserShipment> response) {
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
                        try {
                            Timber.d(response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;

                    case 422:
                        data.postValue(Resource.unprocessableEntity(response.message(), null));
                        break;
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserShipment> call, @NonNull Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    public LiveData<Resource<UserShipment>> viewShipmentAddress(String token, int shipmentId) {
        MutableLiveData<Resource<UserShipment>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<UserShipment> api = rimeSyariAPI.viewShipmentAddress(token, shipmentId);
        api.enqueue(new Callback<UserShipment>() {
            @Override
            public void onResponse(@NonNull Call<UserShipment> call, @NonNull Response<UserShipment> response) {
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
            public void onFailure(@NonNull Call<UserShipment> call, @NonNull Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    public LiveData<Resource<UserShipment>> newShipmentAddress(String token, String alamat, String kodePos, String catatan, boolean isDefault, String village, String district, String regency, String province) {
        MutableLiveData<Resource<UserShipment>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<UserShipment> api = rimeSyariAPI.newShipmentAddress(token, alamat, kodePos, catatan, (isDefault) ? 1 : 0, village, district, regency, province);
        api.enqueue(new Callback<UserShipment>() {
            @Override
            public void onResponse(@NonNull Call<UserShipment> call, @NonNull Response<UserShipment> response) {
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
            public void onFailure(@NonNull Call<UserShipment> call, @NonNull Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    public LiveData<Resource<UserShipment>> updateShipmentAddress(String token, int shipmentId, String alamat, String kodePos, String catatan, boolean isDefault, String village, String district, String regency, String province) {
        MutableLiveData<Resource<UserShipment>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<UserShipment> api = rimeSyariAPI.updateShipmentAddress(token, shipmentId, alamat, kodePos, catatan, (isDefault) ? 1 : 0, village, district, regency, province);
        api.enqueue(new Callback<UserShipment>() {
            @Override
            public void onResponse(@NonNull Call<UserShipment> call, @NonNull Response<UserShipment> response) {
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
                        try {
                            Timber.d(response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;

                    case 422:
                        data.postValue(Resource.unprocessableEntity(response.message(), null));
                        try {
                            Timber.d(response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserShipment> call, @NonNull Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    public LiveData<Resource<UserShipment>> removeShipmentAddress(String token, int shipmentId) {
        MutableLiveData<Resource<UserShipment>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<UserShipment> api = rimeSyariAPI.removeShipmentAddress(token, shipmentId);
        api.enqueue(new Callback<UserShipment>() {
            @Override
            public void onResponse(Call<UserShipment> call, Response<UserShipment> response) {

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
                        try {
                            Timber.d(response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }

            @Override
            public void onFailure(Call<UserShipment> call, Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    public LiveData<Resource<List<Village>>> getVillages(String token, String district) {
        MutableLiveData<Resource<List<Village>>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<List<Village>> api = rimeSyariAPI.getVillages(token, district);
        api.enqueue(new Callback<List<Village>>() {
            @Override
            public void onResponse(@NonNull Call<List<Village>> call, @NonNull Response<List<Village>> response) {
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
            public void onFailure(@NonNull Call<List<Village>> call, @NonNull Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    public LiveData<Resource<List<District>>> getDistricts(String token, String regency) {
        MutableLiveData<Resource<List<District>>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<List<District>> api = rimeSyariAPI.getDistricts(token, regency);
        api.enqueue(new Callback<List<District>>() {
            @Override
            public void onResponse(@NonNull Call<List<District>> call, @NonNull Response<List<District>> response) {
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
            public void onFailure(@NonNull Call<List<District>> call, @NonNull Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    public LiveData<Resource<List<Regency>>> getRegencies(String token, String province) {
        MutableLiveData<Resource<List<Regency>>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<List<Regency>> api = rimeSyariAPI.getRegencies(token, province);
        api.enqueue(new Callback<List<Regency>>() {
            @Override
            public void onResponse(@NonNull Call<List<Regency>> call, @NonNull Response<List<Regency>> response) {
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
            public void onFailure(@NonNull Call<List<Regency>> call, @NonNull Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    public LiveData<Resource<List<Province>>> getProvinces(String token) {
        MutableLiveData<Resource<List<Province>>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<List<Province>> api = rimeSyariAPI.getProvinces(token);
        api.enqueue(new Callback<List<Province>>() {
            @Override
            public void onResponse(@NonNull Call<List<Province>> call, @NonNull Response<List<Province>> response) {
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
            public void onFailure(@NonNull Call<List<Province>> call, @NonNull Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    /*
     * ---------------------------------------------------------------------------------------------
     *
     * POSTINGAN
     *
     * ---------------------------------------------------------------------------------------------
     */

    public LiveData<Resource<List<Post>>> getLatestPosts(String token) {
        MutableLiveData<Resource<List<Post>>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<List<Post>> api = rimeSyariAPI.getLatestPosts(token);
        api.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(@NonNull Call<List<Post>> call, @NonNull Response<List<Post>> response) {
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
            public void onFailure(@NonNull Call<List<Post>> call, @NonNull Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    public LiveData<Resource<Post>> viewPost(String token, int postId) {
        MutableLiveData<Resource<Post>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<Post> api = rimeSyariAPI.viewPost(token, postId);
        api.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
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
            public void onFailure(Call<Post> call, Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    /*
     * ---------------------------------------------------------------------------------------------
     *
     * TESTIMONI
     *
     * ---------------------------------------------------------------------------------------------
     */

    public LiveData<Resource<List<Testimony>>> userTestimonies(String token) {
        MutableLiveData<Resource<List<Testimony>>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<List<Testimony>> api = rimeSyariAPI.userTestimonies(token);
        api.enqueue(new Callback<List<Testimony>>() {
            @Override
            public void onResponse(Call<List<Testimony>> call, Response<List<Testimony>> response) {
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
            public void onFailure(Call<List<Testimony>> call, Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    public LiveData<Resource<Testimony>> newTestimony(String token, String isi, int rating, int productId) {
        MutableLiveData<Resource<Testimony>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<Testimony> api = rimeSyariAPI.newTestimony(token, isi, rating, productId);
        api.enqueue(new Callback<Testimony>() {
            @Override
            public void onResponse(Call<Testimony> call, Response<Testimony> response) {
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
            public void onFailure(Call<Testimony> call, Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    /*
     * ---------------------------------------------------------------------------------------------
     *
     * VERIFIKASI KYC
     *
     * ---------------------------------------------------------------------------------------------
     */

    public LiveData<Resource<UserVerification>> newVerification(String token) {
        MutableLiveData<Resource<UserVerification>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<UserVerification> api = rimeSyariAPI.newVerification(token);
        api.enqueue(new Callback<UserVerification>() {
            @Override
            public void onResponse(Call<UserVerification> call, Response<UserVerification> response) {
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
            public void onFailure(@NonNull Call<UserVerification> call, @NonNull Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    public LiveData<Resource<Boolean>> checkIfUserIsElligible(String token) {
        MutableLiveData<Resource<Boolean>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<Boolean> api = rimeSyariAPI.checkIfUserIsElligible(token);
        api.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
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
            public void onFailure(Call<Boolean> call, Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    public LiveData<Resource<UserVerification>> uploadImage(String token, File image, int imageType) {
        MutableLiveData<Resource<UserVerification>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        RequestBody requestImagePath = RequestBody.create(image, MediaType.parse("multipart/form-data"));
        MultipartBody.Part imagePart = MultipartBody.Part.createFormData("path", image.getName(), requestImagePath);
        RequestBody requestImageType = RequestBody.create(String.valueOf(imageType), MediaType.parse("text/plain"));

        Timber.d(requestImagePath.toString());

        Call<UserVerification> api = rimeSyariAPI.uploadVerificationImage(token, imagePart, requestImageType);
        api.enqueue(new Callback<UserVerification>() {
            @Override
            public void onResponse(@NonNull Call<UserVerification> call, @NonNull Response<UserVerification> response) {
                Timber.d(response.code() + ": " + response.message());
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
            public void onFailure(Call<UserVerification> call, Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
                Timber.e(t);
            }
        });

        return data;
    }

    public LiveData<Resource<UserVerification>> verificationStatus(String token) {
        MutableLiveData<Resource<UserVerification>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<UserVerification> api = rimeSyariAPI.verificationStatus(token);
        api.enqueue(new Callback<UserVerification>() {
            @Override
            public void onResponse(@NonNull Call<UserVerification> call, @NonNull Response<UserVerification> response) {
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
            public void onFailure(@NonNull Call<UserVerification> call, @NonNull Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }

    public LiveData<Resource<UserVerification>> beginVerification(String token) {
        MutableLiveData<Resource<UserVerification>> data = new MutableLiveData<>();
        data.postValue(Resource.loading(null));

        Call<UserVerification> api = rimeSyariAPI.beginVerification(token);
        api.enqueue(new Callback<UserVerification>() {
            @Override
            public void onResponse(@NonNull Call<UserVerification> call, @NonNull Response<UserVerification> response) {
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
            public void onFailure(@NonNull Call<UserVerification> call, @NonNull Throwable t) {
                data.postValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;
    }
}

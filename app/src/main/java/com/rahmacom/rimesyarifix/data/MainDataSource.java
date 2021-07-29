package com.rahmacom.rimesyarifix.data;

import androidx.lifecycle.LiveData;

import com.rahmacom.rimesyarifix.data.network.ApiResponse;
import com.rahmacom.rimesyarifix.data.network.model.ResponseLogin;
import com.rahmacom.rimesyarifix.data.network.model.ResponseProduk;

public interface MainDataSource {

    // auth
    LiveData<ApiResponse<ResponseLogin>> login(String username, String password);
    LiveData<ApiResponse<ResponseLogin>> register(
            String namaLengkap,
            String handphone,
            String password,
            String passwordConfirm
    );
    LiveData<ApiResponse<ResponseLogin>> refreshToken();
    LiveData<ApiResponse<ResponseProduk>> logout(int cartId);

    // product
    LiveData<ApiResponse<ResponseProduk>> getAllProducts();
    LiveData<ApiResponse<ResponseProduk>> viewProduct(int productId);

    // testimonies / likes
    LiveData<ApiResponse<ResponseProduk>> viewProductTestimonies();
    LiveData<ApiResponse<ResponseProduk>> getLikedProducts();
    LiveData<ApiResponse<ResponseProduk>> getReviewedProducts();

    // profile
    LiveData<ApiResponse<ResponseProduk>> updateProfile();
    LiveData<ApiResponse<ResponseProduk>> updateProfileAddress(int cartId);

    // cart
    LiveData<ApiResponse<ResponseProduk>> addToCart(int productId);
    LiveData<ApiResponse<ResponseProduk>> removeFromCart(int productId);
    LiveData<ApiResponse<ResponseProduk>> viewCart(int cartId);
    LiveData<ApiResponse<ResponseProduk>> saveCart(int cartId);
    LiveData<ApiResponse<ResponseProduk>> removeCart(int cartId);

    // order
    LiveData<ApiResponse<ResponseProduk>> createOrder(int cartId);
    LiveData<ApiResponse<ResponseProduk>> cancelOrder(int orderId);
    LiveData<ApiResponse<ResponseProduk>> getAllOrders();

    // transaction

    // KYC
    LiveData<ApiResponse<ResponseProduk>> uploadImages();
    LiveData<ApiResponse<ResponseProduk>> getVerificationStatus();
    LiveData<ApiResponse<ResponseProduk>> submitVerification();
}

package com.rahmacom.rimesyarifix.data.network.api;

import com.rahmacom.rimesyarifix.data.entity.Cart;
import com.rahmacom.rimesyarifix.data.entity.District;
import com.rahmacom.rimesyarifix.data.entity.Order;
import com.rahmacom.rimesyarifix.data.entity.Product;
import com.rahmacom.rimesyarifix.data.entity.Province;
import com.rahmacom.rimesyarifix.data.entity.Regency;
import com.rahmacom.rimesyarifix.data.entity.Shipment;
import com.rahmacom.rimesyarifix.data.entity.Testimony;
import com.rahmacom.rimesyarifix.data.entity.User;
import com.rahmacom.rimesyarifix.data.entity.Village;
import com.rahmacom.rimesyarifix.data.network.response.LoginResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RimeSyariAPI {

    @POST("login")
    @FormUrlEncoded
    @Headers({"Content-Type: application/x-www-form-urlencoded", "Accept: application/json"})
    Call<LoginResponse> login(@Field("email") String email,
                              @Field("password") String password);

    @POST("register")
    @FormUrlEncoded
    @Headers({"Content-Type: application/x-www-form-urlencoded", "Accept: application/json"})
    Call<LoginResponse> register(@Field("name") String name,
                                 @Field("email") String email,
                                 @Field("password") String password);

    @POST("refresh")
    @FormUrlEncoded
    @Headers({"Content-Type: application/x-www-form-urlencoded", "Accept: application/json"})
    Call<LoginResponse> refresh(@Header("Authorization") String token);

    @POST("logout")
    @FormUrlEncoded
    @Headers({"Content-Type: application/x-www-form-urlencoded", "Accept: application/json"})
    Call<LoginResponse> logout(@Header("Authorization") String token);

    @PATCH("login/update")
    @FormUrlEncoded
    @Headers({"Content-Type: application/x-www-form-urlencoded", "Accept: application/json"})
    Call<LoginResponse> updateLogin(@Header("Authorization") String token,
                                    @Field("email") String email,
                                    @Field("old_password") String oldPassword,
                                    @Field("new_password") String newPassword);

    @GET("profile")
    @Headers({"Accept: application/json"})
    Call<User> profile(@Header("Authorization") String token);

    @PATCH("profile")
    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    Call<User> updateProfile(@Header("Authorization") String token,
                             @Field("name") String name,
                             @Field("jenis_kelamin") String jenisKelamin,
                             @Field("tempat_lahir") String tempatLahir,
                             @Field("tgl_lahir") String tglLahir,
                             @Field("alamat") String alamat,
                             @Field("shipment_id") int shipmentId,
                             @Field("no_hp") String noHP,
                             @Field("no_wa") String noWA);

    @GET("products")
    @Headers({"Accept: application/json"})
    Call<List<Product>> getAllProducts(@Header("Authorization") String token);

    @GET("products/{product}")
    @Headers({"Accept: application/json"})
    Call<Product> viewProduct(@Header("Authorization") String token,
                                    @Path("product") int productId);

    @GET("carts")
    @Headers({"Accept: application/json"})
    Call<List<Cart>> getAllCarts(@Header("Authorization") String token);

    @GET("carts/{cart}")
    @Headers({"Accept: application/json"})
    Call<Cart> viewCart(@Header("Authorization") String token,
                        @Path("cart") int cartId);

    @POST("carts")
    @FormUrlEncoded
    @Headers({"Content-Type: application/x-www-form-urlencoded", "Accept: application/json"})
    Call<Cart> newCart(@Header("Authorization") String token,
                       @Field("judul") String judul,
                       @Field("deskripsi") String deskripsi,
                       @Field("product_id[]") List<Integer> productIds,
                       @Field("color_id[]") List<Integer> colorIds,
                       @Field("size_id[]") List<Integer> sizeIds,
                       @Field("jumlah[]") List<Integer> quantities);

    @PUT("carts/{cart}")
    @FormUrlEncoded
    @Headers({"Content-Type: application/x-www-form-urlencoded", "Accept: application/json"})
    Call<Cart> updateCart(@Header("Authorization") String token,
                          @Path("cart") int cartId,
                          @Field("judul") String judul,
                          @Field("deskripsi") String deskripsi,
                          @Field("product_id[]") List<Integer> productIds,
                          @Field("color_id[]") List<Integer> colorIds,
                          @Field("size_id[]") List<Integer> sizeIds,
                          @Field("jumlah[]") List<Integer> quantities);

    @DELETE("carts/{cart}")
    @Headers({"Accept: application/json"})
    Call<Cart> deleteCart(@Header("Authorization") String token,
                          @Path("cart") int cartId);

    @GET("orders")
    @Headers({"Accept: application/json"})
    Call<List<Order>> getAllOrders(@Header("Authorization") String token,
                                   @Query("status_id") int statusId);

    @GET("orders/{order}")
    @Headers({"Accept: application/json"})
    Call<Order> viewOrder(@Header("Authorization") String token,
                          @Path("order") int orderId);

    @POST("orders")
    @FormUrlEncoded
    @Headers({"Content-Type: application/x-www-form-urlencoded", "Accept: application/json"})
    Call<Order> newOrder(@Header("Authorization") String token,
                         @Field("pesan") String pesan,
                         @Field("kode_diskon") String kodeDiskon,
                         @Field("product_id[]") List<Integer> productIds,
                         @Field("color_id[]") List<Integer> colorIds,
                         @Field("size_id[]") List<Integer> sizeIds,
                         @Field("jumlah[]") List<Integer> quantities);

    @PUT("orders/{order}")
    @FormUrlEncoded
    @Headers({"Content-Type: application/x-www-form-urlencoded", "Accept: application/json"})
    Call<Cart> updateOrder(@Header("Authorization") String token,
                           @Path("order") int orderId,
                           @Field("pesan") String pesan,
                           @Field("kode_diskon") String kodeDiskon,
                           @Field("product_id[]") List<Integer> productIds,
                           @Field("color_id[]") List<Integer> colorIds,
                           @Field("size_id[]") List<Integer> sizeIds,
                           @Field("jumlah[]") List<Integer> quantities);

    @GET("testimonies")
    @Headers({"Accept: application/json"})
    Call<List<Testimony>> getAllTestimonies(@Header("Authorization") String token,
                                            @Query("product_id") int productId);

    @POST("testimonies")
    @FormUrlEncoded
    @Headers({"Content-Type: application/x-www-form-urlencoded", "Accept: application/json"})
    Call<Testimony> newTestimony(@Header("Authorization") String token,
                                 @Field("judul") String judul,
                                 @Field("isi") String isi,
                                 @Field("review") int review,
                                 @Field("product_id") int productId);

    @GET("shipments")
    @Headers({"Accept: application/json"})
    Call<List<Shipment>> getShipmentAddresses(@Header("Authorization") String token);

    @GET("shipments/{shipment}")
    @Headers({"Accept: application/json"})
    Call<Shipment> viewShipmentAddress(@Header("Authorization") String token,
                                       @Path("shipment") int shipmentId);

    @POST("shipments")
    @FormUrlEncoded
    @Headers({"Content-Type: application/x-www-form-urlencoded", "Accept: application/json"})
    Call<Shipment> newShipmentAddress(@Header("Authorization") String token,
                                      @Field("alamat") String alamat,
                                      @Field("kode_pos") String kodePos,
                                      @Field("catatan") String catatan,
                                      @Field("village_id") int villageId);

    @PUT("shipments/{shipment}")
    @FormUrlEncoded
    @Headers({"Content-Type: application/x-www-form-urlencoded", "Accept: application/json"})
    Call<Shipment> updateShipmentAddress(@Header("Authorization") String token,
                                         @Path("shipment") int shipmentId,
                                         @Field("alamat") String alamat,
                                         @Field("kode_pos") String kodePos,
                                         @Field("catatan") String catatan,
                                         @Field("village_id") int villageId);

    @GET("shipments/provinces")
    @Headers({"Accept: application/json"})
    Call<List<Province>> getAllProvinces(@Header("Authorization") String token,
                                         @Query("id") int id,
                                         @Query("regency_id") int regencyId,
                                         @Query("district_id") int districtId,
                                         @Query("village_id") int villageId);

    @GET("shipments/regencies")
    @Headers({"Accept: application/json"})
    Call<List<Regency>> getAllRegencies(@Header("Authorization") String token,
                                        @Query("id") int id,
                                        @Query("province_id") int provinceId,
                                        @Query("district_id") int districtId,
                                        @Query("village_id") int villageId);

    @GET("shipments/districts")
    @Headers({"Accept: application/json"})
    Call<List<District>> getAllDistricts(@Header("Authorization") String token,
                                         @Query("id") int id,
                                         @Query("province_id") int provinceId,
                                         @Query("regency_id") int regencyId,
                                         @Query("village_id") int villageId);

    @GET("shipments/districts")
    @Headers({"Accept: application/json"})
    Call<List<Village>> getAllVillages(@Header("Authorization") String token,
                                       @Query("id") int id,
                                       @Query("province_id") int provinceId,
                                       @Query("regency_id") int regencyId,
                                       @Query("district_id") int districtId);
}

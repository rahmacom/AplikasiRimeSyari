package com.rahmacom.rimesyarifix.data.network.api;

import com.rahmacom.rimesyarifix.data.model.Cart;
import com.rahmacom.rimesyarifix.data.model.Color;
import com.rahmacom.rimesyarifix.data.model.District;
import com.rahmacom.rimesyarifix.data.model.Order;
import com.rahmacom.rimesyarifix.data.model.PaymentMethod;
import com.rahmacom.rimesyarifix.data.model.Product;
import com.rahmacom.rimesyarifix.data.model.Province;
import com.rahmacom.rimesyarifix.data.model.Regency;
import com.rahmacom.rimesyarifix.data.model.Size;
import com.rahmacom.rimesyarifix.data.model.Testimony;
import com.rahmacom.rimesyarifix.data.model.User;
import com.rahmacom.rimesyarifix.data.model.UserShipment;
import com.rahmacom.rimesyarifix.data.model.Village;
import com.rahmacom.rimesyarifix.data.network.response.ResponseLogin;

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
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RimeSyariAPI {

    @POST("login")
    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    Call<ResponseLogin> login(@Field("email") String email, @Field("password") String password);

    @POST("register")
    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    Call<ResponseLogin> register(@Field("nama_lengkap") String name, @Field("email") String email, @Field("password") String password);

    @POST("refresh")
    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    Call<ResponseLogin> refresh(@Header("Authorization") String token);

    @POST("logout")
    @Headers({"Accept: application/json"})
    Call<ResponseLogin> logout(@Header("Authorization") String token);

    @PATCH("login/update")
    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    Call<ResponseLogin> updateLogin(@Header("Authorization") String token, @Field("email") String email, @Field("old_password") String oldPassword, @Field("new_password") String newPassword);

    @GET("me")
    @Headers({"Accept: application/json"})
    Call<User> profile(@Header("Authorization") String token);

    @PATCH("profile/update")
    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    Call<User> updateProfile(@Header("Authorization") String token, @Field("nama_lengkap") String name, @Field("jenis_kelamin") String jenisKelamin, @Field("tempat_lahir") String tempatLahir, @Field("tgl_lahir") String tglLahir, @Field("alamat") String alamat, @Field("no_hp") String noHP, @Field("no_wa") String noWA);

    @GET("products")
    @Headers({"Accept: application/json"})
    Call<List<Product>> getAllProducts(@Header("Authorization") String token);

    @GET("products/{product}")
    @Headers({"Accept: application/json"})
    Call<Product> viewProduct(@Header("Authorization") String token, @Path("product") int productId);

    @GET("carts")
    @Headers({"Accept: application/json"})
    Call<List<Cart>> getAllCarts(@Header("Authorization") String token);

    @GET("carts/{cart}")
    @Headers({"Accept: application/json"})
    Call<Cart> viewCart(@Header("Authorization") String token, @Path("cart") int cartId);

    @POST("carts")
    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    Call<Cart> newCart(@Header("Authorization") String token,
                       @Field("judul") String judul,
                       @Field("deskripsi") String deskripsi,
                       @Field("product_id") int productId,
                       @Field("color_id") int colorId,
                       @Field("size_id") int sizeId,
                       @Field("jumlah") int jumlah);

    @PATCH("carts/{cart}")
    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    Call<Cart> updateCart(@Header("Authorization") String token,
                          @Path("cart") int cartId,
                          @Field("judul") String judul,
                          @Field("deskripsi") String deskripsi);

    @PATCH("carts/{cart}")
    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    Call<Cart> updateCartWithDetails(@Header("Authorization") String token,
                                     @Path("cart") int cartId,
                                     @Field("judul") String judul,
                                     @Field("deskripsi") String deskripsi,
                                     @Field("product_id[]") List<Integer> productIds,
                                     @Field("color_id[]") List<Integer> colorIds,
                                     @Field("size_id[]") List<Integer> sizeIds,
                                     @Field("jumlah[]") List<Integer> quantities);

    @POST("carts/{cart}/products")
    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    Call<Cart> addProductToCart(@Header("Authorization") String token,
                                @Path("cart") int cartId,
                                @Field("product_id") int productId,
                                @Field("color_id") int colorId,
                                @Field("size_id") int sizeId,
                                @Field("jumlah") int jumlah);

    @PATCH("carts/{cart}/products")
    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    Call<Cart> updateProductsInCart(@Header("Authorization") String token,
                                    @Path("cart") int cartId,
                                    @Field("product_id[]") List<Integer> productIds,
                                    @Field("color_id[]") List<Integer> colorIds,
                                    @Field("size_id[]") List<Integer> sizeIds,
                                    @Field("jumlah[]") List<Integer> quantities);

    @DELETE("carts/{cart}/products")
    @Headers({"Accept: application/json"})
    Call<Cart> removeProductsFromCart(@Header("Authorization") String token,
                                      @Path("cart") int cartId,
                                      @Field("product_id") int productId);

    @DELETE("carts/{cart}")
    @Headers({"Accept: application/json"})
    Call<Cart> deleteCart(@Header("Authorization") String token, @Path("cart") int cartId);

    @GET("orders")
    @Headers({"Accept: application/json"})
    Call<List<Order>> getAllOrders(@Header("Authorization") String token, @Query("status_id") int statusId);

    @GET("orders/{order}")
    @Headers({"Accept: application/json"})
    Call<Order> viewOrder(@Header("Authorization") String token, @Path("order") int orderId);

    @POST("orders")
    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    Call<Order> newOrder(@Header("Authorization") String token,
                         @Field("pesan") String pesan,
                         @Field("kode_diskon") String kodeDiskon,
                         @Field("user_shipment_id") int userShipmentId,
                         @Field("payment_method_id") int paymentMethodId,
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
    @Headers({"Accept: application/json"})
    Call<Testimony> newTestimony(@Header("Authorization") String token,
                                 @Field("judul") String judul,
                                 @Field("isi") String isi,
                                 @Field("review") int review,
                                 @Field("product_id") int productId);

    @GET("user_shipments")
    @Headers({"Accept: application/json"})
    Call<List<UserShipment>> getShipmentAddresses(@Header("Authorization") String token);

    @GET("user_shipments/{user_shipment}")
    @Headers({"Accept: application/json"})
    Call<UserShipment> viewShipmentAddress(@Header("Authorization") String token,
                                           @Path("user_shipment") int userShipmentId);

    @GET("user_shipments/default")
    @Headers({"Accept: application/json"})
    Call<UserShipment> getDefaultShipmentAddress(@Header("Authorization") String token);

    @POST("user_shipments")
    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    Call<UserShipment> newShipmentAddress(@Header("Authorization") String token,
                                          @Field("alamat") String alamat,
                                          @Field("kode_pos") String kodePos,
                                          @Field("catatan") String catatan,
                                          @Field("village_id") int villageId);

    @PATCH("user_shipments/{user_shipment}")
    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    Call<UserShipment> updateShipmentAddress(@Header("Authorization") String token,
                                             @Path("user_shipment") int shipmentId,
                                             @Field("alamat") String alamat,
                                             @Field("kode_pos") String kodePos,
                                             @Field("catatan") String catatan,
                                             @Field("is_default") boolean isDefault,
                                             @Field("village_id") int villageId);

    @GET("shipments/provinces")
    @Headers({"Accept: application/json"})
    Call<List<Province>> getAllProvinces(@Header("Authorization") String token, @Query("id") int id, @Query("regency_id") int regencyId, @Query("district_id") int districtId, @Query("village_id") int villageId);

    @GET("shipments/regencies")
    @Headers({"Accept: application/json"})
    Call<List<Regency>> getAllRegencies(@Header("Authorization") String token, @Query("id") int id, @Query("province_id") int provinceId, @Query("district_id") int districtId, @Query("village_id") int villageId);

    @GET("shipments/districts")
    @Headers({"Accept: application/json"})
    Call<List<District>> getAllDistricts(@Header("Authorization") String token, @Query("id") int id, @Query("province_id") int provinceId, @Query("regency_id") int regencyId, @Query("village_id") int villageId);

    @GET("shipments/districts")
    @Headers({"Accept: application/json"})
    Call<List<Village>> getAllVillages(@Header("Authorization") String token, @Query("id") int id, @Query("province_id") int provinceId, @Query("regency_id") int regencyId, @Query("district_id") int districtId);

    @GET("colors")
    @Headers({"Accept: application/json"})
    Call<List<Color>> getProductColors(@Header("Authorization") String token, @Query("product_id") int productId);

    @GET("sizes")
    @Headers({"Accept: application/json"})
    Call<List<Size>> getProductSizes(@Header("Authorization") String token, @Query("product_id") int productId, @Query("color_id") int colorId);

    @GET("payment_methods")
    @Headers({"Accept: application/json"})
    Call<List<PaymentMethod>> getAvailablePaymentMethods(@Header("Authorization") String token);
}

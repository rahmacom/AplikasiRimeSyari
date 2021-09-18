package com.rahmacom.rimesyarifix.data.network.api;

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
import com.rahmacom.rimesyarifix.data.network.response.ResponseLogin;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
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
//    Call<User> updateProfile(@Header("Authorization") String token, @Field("nama_lengkap") String name, @Field("jenis_kelamin") String jenisKelamin, @Field("tempat_lahir") String tempatLahir, @Field("tgl_lahir") String tglLahir, @Field("alamat") String alamat, @Field("no_hp") String noHP, @Field("no_wa") String noWA);
    Call<User> updateProfile(@Header("Authorization") String token, @Field("nama_lengkap") String name, @Field("jk") char jk, @Field("tempat_lahir") String tempatLahir, @Field("tgl_lahir") String tglLahir, @Field("alamat") String alamat, @Field("no_hp") String noHP, @Field("no_wa") String noWA);

    @GET("products")
    @Headers({"Accept: application/json"})
    Call<List<Product>> allProducts(@Header("Authorization") String token);

    @GET("products/{product}")
    @Headers({"Accept: application/json"})
    Call<Product> viewProduct(@Header("Authorization") String token, @Path("product") int productId);

    @POST("products/{product}/likes")
    @Headers({"Accept: application/json"})
    Call<Integer> likeProduct(@Header("Authorization") String token, @Path("product") int productId);

    @DELETE("products/{product}/likes")
    @Headers({"Accept: application/json"})
    Call<Integer> dislikeProduct(@Header("Authorization") String token, @Path("product") int productId);

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
    Call<List<Order>> allOrders(@Header("Authorization") String token, @Query("status_id") int statusId);

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
    Call<List<Testimony>> userTestimonies(@Header("Authorization") String token);

    @POST("testimonies")
    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    Call<Testimony> newTestimony(@Header("Authorization") String token,
                                 @Field("isi") String isi,
                                 @Field("rating") int rating,
                                 @Field("product_id") int productId);

    @GET("user_shipments")
    @Headers({"Accept: application/json"})
    Call<List<UserShipment>> shipmentAddresses(@Header("Authorization") String token);

    @GET("user_shipments/{user_shipment}")
    @Headers({"Accept: application/json"})
    Call<UserShipment> viewShipmentAddress(@Header("Authorization") String token,
                                           @Path("user_shipment") int userShipmentId);

    @GET("user_shipments/default")
    @Headers({"Accept: application/json"})
    Call<UserShipment> defaultShipmentAddress(@Header("Authorization") String token);

    @POST("user_shipments")
    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    Call<UserShipment> newShipmentAddress(@Header("Authorization") String token,
                                          @Field("alamat") String alamat,
                                          @Field("kode_pos") String kodePos,
                                          @Field("catatan") String catatan,
                                          @Field("is_default") int isDefault,
                                          @Field("village") String village,
                                          @Field("district") String district,
                                          @Field("regency") String regency,
                                          @Field("province") String province);

    @PATCH("user_shipments/{user_shipment}")
    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    Call<UserShipment> updateShipmentAddress(@Header("Authorization") String token,
                                             @Path("user_shipment") int shipmentId,
                                             @Field("alamat") String alamat,
                                             @Field("kode_pos") String kodePos,
                                             @Field("catatan") String catatan,
                                             @Field("is_default") int isDefault,
                                             @Field("village") String village,
                                             @Field("district") String district,
                                             @Field("regency") String regency,
                                             @Field("province") String province);

    @DELETE("user_shipments/{user_shipment}")
    @Headers({"Accept: application/json"})
    Call<UserShipment> removeShipmentAddress(@Header("Authorization") String token, @Path("user_shipment") int shipmentId);

    @PATCH("user_shipment/{user_shipment}")
    @Headers({"Accept: application/json"})
    Call<UserShipment> setAsDefaultShipmentAddress(@Header("Authorization") String token, @Path("user_shipment") int shipmentId);

    @GET("shipments/provinces")
    @Headers({"Accept: application/json"})
    Call<List<Province>> getProvinces(@Header("Authorization") String token);

    @GET("shipments/regencies")
    @Headers({"Accept: application/json"})
    Call<List<Regency>> getRegencies(@Header("Authorization") String token, @Query("province") String province);

    @GET("shipments/districts")
    @Headers({"Accept: application/json"})
    Call<List<District>> getDistricts(@Header("Authorization") String token, @Query("regency") String regency);

    @GET("shipments/villages")
    @Headers({"Accept: application/json"})
    Call<List<Village>> getVillages(@Header("Authorization") String token, @Query("district") String district);

    @GET("colors")
    @Headers({"Accept: application/json"})
    Call<List<Color>> productColors(@Header("Authorization") String token, @Query("product_id") int productId);

    @GET("sizes")
    @Headers({"Accept: application/json"})
    Call<List<Size>> productSizes(@Header("Authorization") String token, @Query("product_id") int productId, @Query("color_id") int colorId);

    @GET("payment_methods")
    @Headers({"Accept: application/json"})
    Call<List<PaymentMethod>> availablePaymentMethods(@Header("Authorization") String token);

    @GET("posts")
    @Headers({"Accept: application/json"})
    Call<List<Post>> getLatestPosts(@Header("Authorization") String token);

    @GET("posts/{post}")
    @Headers({"Accept: application/json"})
    Call<Post> viewPost(@Header("Authorization") String token, @Path("post") int postId);

    @POST("verifications/create")
    @Headers({"Accept: application/json"})
    Call<UserVerification> newVerification(@Header("Authorization") String token);

    @POST("verifications/verify")
    @Headers({"Accept: application/json"})
    Call<Boolean> checkIfUserIsElligible(@Header("Authorization") String token);

    @POST("verifications/upload")
    @Multipart
    @Headers({"Accept: application/json"})
    Call<UserVerification> uploadVerificationImage(@Header("Authorization") String token,
                                                   @Part MultipartBody.Part imagePart,
                                                   @Part("image_type") RequestBody imageType);

    @GET("verifications/status")
    @Headers({"Accept: application/json"})
    Call<UserVerification> verificationStatus(@Header("Authorization") String token);

    @POST("profile/upload")
    @Multipart
    @Headers({"Accept: application/json"})
    Call<Image> uploadAvatar(@Header("Authorization") String token,
                             @Part("path") MultipartBody.Part path);
}

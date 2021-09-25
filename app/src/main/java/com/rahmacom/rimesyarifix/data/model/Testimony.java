package com.rahmacom.rimesyarifix.data.model;

import com.google.gson.annotations.SerializedName;

public class Testimony {

    @SerializedName("id")
    private int id;

    @SerializedName("product_id")
    private int productId;

    @SerializedName("user_id")
    private int userId;

    @SerializedName("judul")
    private String judul;

    @SerializedName("isi")
    private String isi;

    @SerializedName("rating")
    private int rating;

    @SerializedName("user")
    private User user;

    @SerializedName("product")
    private Product product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public Product getProduct() {
        return product;
    }
}
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

    @SerializedName("review")
    private int review;

    @SerializedName("user")
    private User user;
}
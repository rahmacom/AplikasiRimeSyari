package com.rahmacom.rimesyarifix.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cart {

    @SerializedName("id")
    private int id;

    @SerializedName("judul")
    private String judul;

    @SerializedName("deskripsi")
    private String deskripsi;

    @SerializedName("jumlah")
    private int jumlah;

    @SerializedName("total")
    private int total;

    @SerializedName("products")
    private List<Product> products;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public List<Product> getProducts() {
        return products;
    }
}
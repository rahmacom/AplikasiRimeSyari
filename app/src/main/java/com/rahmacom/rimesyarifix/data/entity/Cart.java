package com.rahmacom.rimesyarifix.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "carts")
public class Cart {

    @PrimaryKey
    @SerializedName("id")
    private int id;

    @ColumnInfo(name = "judul")
    @SerializedName("judul")
    private String judul;

    @ColumnInfo(name = "deskripsi")
    @SerializedName("deskripsi")
    private String deskripsi;

    @ColumnInfo(name = "jumlah")
    @SerializedName("jumlah")
    private int jumlah;

    @ColumnInfo(name = "total")
    @SerializedName("total")
    private int total;

    @Ignore
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
package com.rahmacom.rimesyarifix.data.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseProduk {

    @SerializedName("harga_customer")
    private int hargaCustomer;

    @SerializedName("nama")
    private String nama;

    @SerializedName("id")
    private int id;

    @SerializedName("deskripsi")
    private String deskripsi;

    @SerializedName("harga_reseller")
    private int hargaReseller;

    @SerializedName("category")
    private ResponseKategori kategori;

    @SerializedName("reseller_minimum")
    private int resellerMinimum;

    @SerializedName("suka")
    private int suka;

    @SerializedName("total_stok")
    private int totalStok;

    @SerializedName("files")
    private List<ResponseFile> files;

    public int getTotalStok() {
        return totalStok;
    }

    public List<ResponseFile> getFiles() {
        return files;
    }

    public int getHargaCustomer() {
        return hargaCustomer;
    }

    public String getNama() {
        return nama;
    }

    public int getId() {
        return id;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public int getHargaReseller() {
        return hargaReseller;
    }

    public ResponseKategori getKategori() {
        return kategori;
    }

    public int getResellerMinimum() {
        return resellerMinimum;
    }

    public int getSuka() {
        return suka;
    }

    @Override
    public String toString() {
        return
                "ResponseProduk{" +
                        "harga_customer = '" + hargaCustomer + '\'' +
                        ",nama = '" + nama + '\'' +
                        ",id = '" + id + '\'' +
                        ",deskripsi = '" + deskripsi + '\'' +
                        ",harga_reseller = '" + hargaReseller + '\'' +
                        ",category = '" + kategori + '\'' +
                        ",reseller_minimum = '" + resellerMinimum + '\'' +
                        ",suka = '" + suka + '\'' +
                        "}";
    }
}
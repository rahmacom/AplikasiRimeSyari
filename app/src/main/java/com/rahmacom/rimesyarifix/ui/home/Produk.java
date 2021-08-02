package com.rahmacom.rimesyarifix.ui.home;

import com.google.gson.annotations.SerializedName;

public class Produk {
    private int id;
    private String nama;
    private String deskripsi;
    private String category;
    private int hargaCustomer;
    private int hargaReseller;
    private int totalStok;
    private int resellerMinimum;
    private int suka;

    public Produk(int id, String nama, String deskripsi, String category, int hargaCustomer, int hargaReseller, int totalStok, int resellerMinimum, int suka) {
        this.id = id;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.category = category;
        this.hargaCustomer = hargaCustomer;
        this.hargaReseller = hargaReseller;
        this.totalStok = totalStok;
        this.resellerMinimum = resellerMinimum;
        this.suka = suka;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getHargaCustomer() {
        return hargaCustomer;
    }

    public void setHargaCustomer(int hargaCustomer) {
        this.hargaCustomer = hargaCustomer;
    }

    public int getHargaReseller() {
        return hargaReseller;
    }

    public void setHargaReseller(int hargaReseller) {
        this.hargaReseller = hargaReseller;
    }

    public int getTotalStok() {
        return totalStok;
    }

    public void setTotalStok(int totalStok) {
        this.totalStok = totalStok;
    }

    public int getResellerMinimum() {
        return resellerMinimum;
    }

    public void setResellerMinimum(int resellerMinimum) {
        this.resellerMinimum = resellerMinimum;
    }

    public int getSuka() {
        return suka;
    }

    public void setSuka(int suka) {
        this.suka = suka;
    }
}

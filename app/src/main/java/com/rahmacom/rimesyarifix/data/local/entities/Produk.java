package com.rahmacom.rimesyarifix.data.local.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Produk {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "nama")
    private String nama;

    @ColumnInfo(name = "deskripsi")
    private String deskripsi;

    @ColumnInfo(name = "harga_customer")
    private int hargaCustomer;

    @ColumnInfo(name = "harga_reseller")
    private int hargaReseller;

    @ColumnInfo(name = "reseller_minimum")
    private int resellerMinimum;

    @ColumnInfo(name = "suka")
    private int suka;

    @ColumnInfo(name = "category_id")
    private int categoryId;

    @ColumnInfo(name = "total_stok")
    private int totalStok;

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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getTotalStok() {
        return totalStok;
    }

    public void setTotalStok(int totalStok) {
        this.totalStok = totalStok;
    }
}

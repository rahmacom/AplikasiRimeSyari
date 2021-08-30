package com.rahmacom.rimesyarifix.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "products")
public class Product {

    @PrimaryKey
    @SerializedName("id")
    private int id;

    @ColumnInfo(name = "nama")
    @SerializedName("nama")
    private String nama;

    @ColumnInfo(name = "deskripsi")
    @SerializedName("deskripsi")
    private String deskripsi;

    @ColumnInfo(name = "harga_customer")
    @SerializedName("harga_customer")
    private int hargaCustomer;

    @ColumnInfo(name = "harga_reseller")
    @SerializedName("harga_reseller")
    private int hargaReseller;

    @ColumnInfo(name = "reseller_minimum")
    @SerializedName("reseller_minimum")
    private int resellerMinimum;

    @ColumnInfo(name = "product_category_id")
    @SerializedName("product_category_id")
    private int productCategoryId;

    @ColumnInfo(name = "suka")
    @SerializedName("suka")
    private int suka;

    @ColumnInfo(name = "review_avg")
    @SerializedName("review_avg")
    private double reviewAvg;

    @ColumnInfo(name = "review_count")
    @SerializedName("review_count")
    private int reviewCount;

    @ColumnInfo(name = "total_stok")
    @SerializedName("total_stok")
    private int totalStok;

    @Ignore
    @SerializedName("product_category")
    private ProductCategory productCategory;

    @Ignore
    @SerializedName("product_stocks")
    private List<ProductStock> productStocks;

    @Ignore
    @SerializedName("image")
    private Image image;

    @Ignore
    @SerializedName("images")
    private List<Image> images;

    @Ignore
    @SerializedName("testimonies")
    private List<Testimony> testimonies;

    @Ignore
    @SerializedName("pivot")
    private Pivot pivot;

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

    public int getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(int productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public int getSuka() {
        return suka;
    }

    public void setSuka(int suka) {
        this.suka = suka;
    }

    public double getReviewAvg() {
        return reviewAvg;
    }

    public void setReviewAvg(double reviewAvg) {
        this.reviewAvg = reviewAvg;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public int getTotalStok() {
        return totalStok;
    }

    public void setTotalStok(int totalStok) {
        this.totalStok = totalStok;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public List<ProductStock> getProductStocks() {
        return productStocks;
    }

    public Image getImage() {
        return image;
    }

    public List<Image> getImages() {
        return images;
    }

    public List<Testimony> getTestimonies() {
        return testimonies;
    }

    public Pivot getPivot() {
        return pivot;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", nama='" + nama + '\'' + ", deskripsi='" + deskripsi + '\'' + ", hargaCustomer=" + hargaCustomer + ", hargaReseller=" + hargaReseller + ", resellerMinimum=" + resellerMinimum + ", productCategoryId=" + productCategoryId + ", suka=" + suka + ", reviewAvg=" + reviewAvg + ", reviewCount=" + reviewCount + ", totalStok=" + totalStok + ", productCategory=" + productCategory + ", productStocks=" + productStocks + ", image=" + image + ", images=" + images + ", testimonies=" + testimonies + ", pivot=" + pivot + '}';
    }
}

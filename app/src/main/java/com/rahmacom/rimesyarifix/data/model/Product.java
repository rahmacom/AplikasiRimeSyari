package com.rahmacom.rimesyarifix.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Product {

    @SerializedName("id")
    private int id;

    @SerializedName("nama")
    private String nama;

    @SerializedName("deskripsi")
    private String deskripsi;

    @SerializedName("harga")
    private int harga;

    @SerializedName("reseller_minimum")
    private int resellerMinimum;

    @SerializedName("product_category_id")
    private int productCategoryId;

    @SerializedName("suka")
    private int suka;

    @SerializedName("liked")
    private boolean liked;

    @SerializedName("review_avg")
    private double reviewAvg;

    @SerializedName("review_count")
    private int reviewCount;

    @SerializedName("total_stok")
    private int totalStok;

    @SerializedName("product_category")
    private ProductCategory productCategory;

    @SerializedName("product_stocks")
    private List<ProductStock> productStocks;

    @SerializedName("image")
    private Image image;

    @SerializedName("images")
    private List<Image> images;

    @SerializedName("testimonies")
    private List<Testimony> testimonies;

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

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
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

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
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

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public List<ProductStock> getProductStocks() {
        return productStocks;
    }

    public void setProductStocks(List<ProductStock> productStocks) {
        this.productStocks = productStocks;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Testimony> getTestimonies() {
        return testimonies;
    }

    public void setTestimonies(List<Testimony> testimonies) {
        this.testimonies = testimonies;
    }

    public Pivot getPivot() {
        return pivot;
    }

    public void setPivot(Pivot pivot) {
        this.pivot = pivot;
    }
}

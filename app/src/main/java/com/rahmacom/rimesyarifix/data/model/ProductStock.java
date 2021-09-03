package com.rahmacom.rimesyarifix.data.model;

import com.google.gson.annotations.SerializedName;

public class ProductStock {

    @SerializedName("id")
    private int id;

    @SerializedName("product_id")
    private int productId;

    @SerializedName("color_id")
    private int colorId;

    @SerializedName("size_id")
    private int sizeId;

    @SerializedName("stok_ready")
    private int stokReady;

    @SerializedName("color")
    private Color color;

    @SerializedName("size")
    private Size size;

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

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public int getStokReady() {
        return stokReady;
    }

    public void setStokReady(int stokReady) {
        this.stokReady = stokReady;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
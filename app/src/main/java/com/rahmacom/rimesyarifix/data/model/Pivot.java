package com.rahmacom.rimesyarifix.data.model;

import com.google.gson.annotations.SerializedName;

public class Pivot {

    @SerializedName("color_id")
    private int colorId;

    @SerializedName("size_id")
    private int sizeId;

    @SerializedName("jumlah")
    private int jumlah;

    @SerializedName("sub_total")
    private int subTotal;

    @SerializedName("diskon")
    private int diskon;

    @SerializedName("color")
    private Color color;

    @SerializedName("size")
    private Size size;

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

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(int subTotal) {
        this.subTotal = subTotal;
    }

    public int getDiskon() {
        return diskon;
    }

    public void setDiskon(int diskon) {
        this.diskon = diskon;
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
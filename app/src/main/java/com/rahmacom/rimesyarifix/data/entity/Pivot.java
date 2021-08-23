package com.rahmacom.rimesyarifix.data.entity;

import androidx.room.Entity;

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

    public int getSizeId() {
        return sizeId;
    }

    public int getJumlah() {
        return jumlah;
    }

    public int getSubTotal() {
        return subTotal;
    }

    public int getDiskon() {
        return diskon;
    }

    public Color getColor() {
        return color;
    }

    public Size getSize() {
        return size;
    }
}
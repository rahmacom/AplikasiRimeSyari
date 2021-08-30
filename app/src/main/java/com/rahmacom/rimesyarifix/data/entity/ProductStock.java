package com.rahmacom.rimesyarifix.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "product_stocks")
public class ProductStock {

    @PrimaryKey
    @SerializedName("id")
    private int id;

    @ColumnInfo(name = "product_id")
    @SerializedName("product_id")
    private int productId;

    @ColumnInfo(name = "color_id")
    @SerializedName("color_id")
    private int colorId;

    @ColumnInfo(name = "size_id")
    @SerializedName("size_id")
    private int sizeId;

    @ColumnInfo(name = "stok_ready")
    @SerializedName("stok_ready")
    private int stokReady;

    @Ignore
    @SerializedName("color")
    private Color color;

    @Ignore
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

    public Size getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "ProductStock{" + "id=" + id + ", productId=" + productId + ", colorId=" + colorId + ", sizeId=" + sizeId + ", stokReady=" + stokReady + ", color=" + color + ", size=" + size + '}';
    }
}
package com.rahmacom.rimesyarifix.data.model;

import com.google.gson.annotations.SerializedName;

public class PaymentMethod {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("ket")
    private String ket;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKet() {
        return ket;
    }

    public void setKet(String ket) {
        this.ket = ket;
    }
}
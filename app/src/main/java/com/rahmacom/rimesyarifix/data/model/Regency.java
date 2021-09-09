package com.rahmacom.rimesyarifix.data.model;

import com.google.gson.annotations.SerializedName;

public class Regency {

    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;

    @SerializedName("province")
    private Province province;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Province getProvince() {
        return province;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
}
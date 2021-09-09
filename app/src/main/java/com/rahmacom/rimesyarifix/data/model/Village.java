package com.rahmacom.rimesyarifix.data.model;

import com.google.gson.annotations.SerializedName;

public class Village {

    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;

    @SerializedName("district")
    private District district;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public District getDistrict() {
        return district;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDistrict(District district) {
        this.district = district;
    }
}
package com.rahmacom.rimesyarifix.data.model;

import com.google.gson.annotations.SerializedName;

public class District {

    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;

    @SerializedName("regency")
    private Regency regency;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Regency getRegency() {
        return regency;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRegency(Regency regency) {
        this.regency = regency;
    }

    @Override
    public String toString() {
        return name;
    }
}
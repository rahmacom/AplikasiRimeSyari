package com.rahmacom.rimesyarifix.data.entity;

import com.google.gson.annotations.SerializedName;

public class District {

    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
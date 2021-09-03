package com.rahmacom.rimesyarifix.data.model;

import com.google.gson.annotations.SerializedName;

public class Village {

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
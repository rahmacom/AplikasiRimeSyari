package com.rahmacom.rimesyarifix.data.entity;

import com.google.gson.annotations.SerializedName;

public class Status {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("desc")
    private Object desc;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Object getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "Status{" + "id=" + id + ", name='" + name + '\'' + ", desc=" + desc + '}';
    }
}
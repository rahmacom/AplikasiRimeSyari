package com.rahmacom.rimesyarifix.data.model;

import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("mime_type")
    private String mimeType;

    @SerializedName("format")
    private String format;

    @SerializedName("size")
    private String size;

    @SerializedName("path")
    private String path;

    @SerializedName("url")
    private String url;

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

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
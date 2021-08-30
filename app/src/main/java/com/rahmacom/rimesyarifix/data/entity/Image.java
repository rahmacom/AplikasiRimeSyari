package com.rahmacom.rimesyarifix.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "images")
public class Image {

    @PrimaryKey
    @SerializedName("id")
    private int id;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    private String name;

    @ColumnInfo(name = "mime_type")
    @SerializedName("mime_type")
    private String mimeType;

    @ColumnInfo(name = "format")
    @SerializedName("format")
    private String format;

    @ColumnInfo(name = "size")
    @SerializedName("size")
    private String size;

    @ColumnInfo(name = "path")
    @SerializedName("path")
    private String path;

    @ColumnInfo(name = "url")
    @SerializedName("url")
    private String url;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMimeType() {
        return mimeType;
    }

    public String getFormat() {
        return format;
    }

    public String getSize() {
        return size;
    }

    public String getPath() {
        return path;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Image{" + "id=" + id + ", name='" + name + '\'' + ", mimeType='" + mimeType + '\'' + ", format='" + format + '\'' + ", size='" + size + '\'' + ", path='" + path + '\'' + ", url='" + url + '\'' + '}';
    }
}
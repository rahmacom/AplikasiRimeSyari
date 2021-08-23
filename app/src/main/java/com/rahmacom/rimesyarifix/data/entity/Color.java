package com.rahmacom.rimesyarifix.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "colors")
public class Color {

    @PrimaryKey
    @SerializedName("id")
    private int id;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    private String name;

    @ColumnInfo(name = "rgba_color")
    @SerializedName("rgba_color")
    private String rgbaColor;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRgbaColor() {
        return rgbaColor;
    }

	@Override
	public String toString() {
		return "Color{" +
				"id=" + id +
				", name='" + name + '\'' +
				", rgbaColor='" + rgbaColor + '\'' +
				'}';
	}
}
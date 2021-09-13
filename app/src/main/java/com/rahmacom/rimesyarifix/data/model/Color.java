package com.rahmacom.rimesyarifix.data.model;

import com.google.gson.annotations.SerializedName;

public class Color {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("rgba_color")
    private String rgbaColor;

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

	public String getRgbaColor() {
		return rgbaColor;
	}

	public void setRgbaColor(String rgbaColor) {
		this.rgbaColor = rgbaColor;
	}
}
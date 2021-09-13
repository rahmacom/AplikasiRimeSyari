package com.rahmacom.rimesyarifix.data.model;

import com.google.gson.annotations.SerializedName;

public class PostCategory {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

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
}
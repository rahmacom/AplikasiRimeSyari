package com.rahmacom.rimesyarifix.data.network.response;

import com.google.gson.annotations.SerializedName;

public class ResponseKategori {

	@SerializedName("nama")
	private String nama;

	@SerializedName("id")
	private int id;

	public String getNama(){
		return nama;
	}

	public int getId(){
		return id;
	}

	@Override
	public String toString(){
		return
				"Category{" +
						"nama = '" + nama + '\'' +
						",id = '" + id + '\'' +
						"}";
	}
}

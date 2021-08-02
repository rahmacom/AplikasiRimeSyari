package com.rahmacom.rimesyarifix.data.network.response;

import com.google.gson.annotations.SerializedName;

public class ResponseKeranjang{

	@SerializedName("total")
	private int total;

	@SerializedName("id")
	private int id;

	@SerializedName("deskripsi")
	private String deskripsi;

	@SerializedName("judul")
	private String judul;

	@SerializedName("nomor")
	private String nomor;

	public int getTotal(){
		return total;
	}

	public int getId(){
		return id;
	}

	public String getDeskripsi(){
		return deskripsi;
	}

	public String getJudul(){
		return judul;
	}

	public String getNomor(){
		return nomor;
	}

	@Override
 	public String toString(){
		return 
			"ResponseKeranjang{" + 
			"total = '" + total + '\'' + 
			",id = '" + id + '\'' + 
			",deskripsi = '" + deskripsi + '\'' + 
			",judul = '" + judul + '\'' + 
			",nomor = '" + nomor + '\'' + 
			"}";
		}
}
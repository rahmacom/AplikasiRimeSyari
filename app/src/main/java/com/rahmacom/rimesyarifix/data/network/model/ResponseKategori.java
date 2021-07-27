package com.rahmacom.rimesyarifix.data.network.model;

public class ResponseKategori{
	private String nama;
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
			"ResponseKategori{" + 
			"nama = '" + nama + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}

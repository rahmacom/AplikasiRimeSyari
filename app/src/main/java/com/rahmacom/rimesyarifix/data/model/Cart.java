package com.rahmacom.rimesyarifix.data.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Cart {
	@SerializedName("id")
	private int id;

	@SerializedName("judul")
	private String judul;

	@SerializedName("deskripsi")
	private String deskripsi;

	@SerializedName("jumlah")
	private int jumlah;

	@SerializedName("total")
	private int total;

	@SerializedName("last_updated")
	private String lastUpdated;

	@SerializedName("products")
	private List<Product> products;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJudul() {
		return judul;
	}

	public void setJudul(String judul) {
		this.judul = judul;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}

	public int getJumlah() {
		return jumlah;
	}

	public void setJumlah(int jumlah) {
		this.jumlah = jumlah;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
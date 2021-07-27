package com.rahmacom.rimesyarifix.data.network.model;

import com.google.gson.annotations.SerializedName;

public class ResponseProduk{

	@SerializedName("harga_customer")
	private int hargaCustomer;

	@SerializedName("nama")
	private String nama;

	@SerializedName("category_id")
	private int categoryId;

	@SerializedName("id")
	private int id;

	@SerializedName("deskripsi")
	private String deskripsi;

	@SerializedName("harga_reseller")
	private int hargaReseller;

	@SerializedName("total_stok")
	private String totalStok;

	@SerializedName("reseller_minimum")
	private int resellerMinimum;

	public void setHargaCustomer(int hargaCustomer){
		this.hargaCustomer = hargaCustomer;
	}

	public int getHargaCustomer(){
		return hargaCustomer;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setCategoryId(int categoryId){
		this.categoryId = categoryId;
	}

	public int getCategoryId(){
		return categoryId;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setDeskripsi(String deskripsi){
		this.deskripsi = deskripsi;
	}

	public String getDeskripsi(){
		return deskripsi;
	}

	public void setHargaReseller(int hargaReseller){
		this.hargaReseller = hargaReseller;
	}

	public int getHargaReseller(){
		return hargaReseller;
	}

	public void setTotalStok(String totalStok){
		this.totalStok = totalStok;
	}

	public String getTotalStok(){
		return totalStok;
	}

	public void setResellerMinimum(int resellerMinimum){
		this.resellerMinimum = resellerMinimum;
	}

	public int getResellerMinimum(){
		return resellerMinimum;
	}

	@Override
 	public String toString(){
		return 
			"ResponseProduk{" + 
			"harga_customer = '" + hargaCustomer + '\'' + 
			",nama = '" + nama + '\'' + 
			",category_id = '" + categoryId + '\'' + 
			",id = '" + id + '\'' + 
			",deskripsi = '" + deskripsi + '\'' + 
			",harga_reseller = '" + hargaReseller + '\'' + 
			",total_stok = '" + totalStok + '\'' + 
			",reseller_minimum = '" + resellerMinimum + '\'' + 
			"}";
		}
}
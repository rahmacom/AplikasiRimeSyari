package com.rahmacom.rimesyarifix.data.network.response;

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

	@SerializedName("suka")
	private int suka;

	public int getHargaCustomer(){
		return hargaCustomer;
	}

	public String getNama(){
		return nama;
	}

	public int getCategoryId(){
		return categoryId;
	}

	public int getId(){
		return id;
	}

	public String getDeskripsi(){
		return deskripsi;
	}

	public int getHargaReseller(){
		return hargaReseller;
	}

	public String getTotalStok(){
		return totalStok;
	}

	public int getResellerMinimum(){
		return resellerMinimum;
	}

	public int getSuka(){
		return suka;
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
			",suka = '" + suka + '\'' + 
			"}";
		}
}
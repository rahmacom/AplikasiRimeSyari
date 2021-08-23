package com.rahmacom.rimesyarifix.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "testimonies")
public class Testimony {

	@PrimaryKey
    @SerializedName("id")
    private int id;

	@ColumnInfo(name = "product_id")
    @SerializedName("product_id")
    private int productId;

	@ColumnInfo(name = "user_id")
    @SerializedName("user_id")
    private int userId;

	@ColumnInfo(name = "judul")
    @SerializedName("judul")
    private String judul;

	@ColumnInfo(name = "isi")
    @SerializedName("isi")
    private String isi;

	@ColumnInfo(name = "review")
    @SerializedName("review")
    private int review;

	@Ignore
    @SerializedName("user")
    private User user;

	public int getId() {
		return id;
	}

	public int getProductId() {
		return productId;
	}

	public int getUserId() {
		return userId;
	}

	public String getJudul() {
		return judul;
	}

	public String getIsi() {
		return isi;
	}

	public int getReview() {
		return review;
	}

	public User getUser() {
		return user;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setJudul(String judul) {
		this.judul = judul;
	}

	public void setIsi(String isi) {
		this.isi = isi;
	}

	public void setReview(int review) {
		this.review = review;
	}

	@Override
	public String toString() {
		return "Testimony{" +
				"id=" + id +
				", productId=" + productId +
				", userId=" + userId +
				", judul='" + judul + '\'' +
				", isi='" + isi + '\'' +
				", review=" + review +
				", user=" + user +
				'}';
	}
}
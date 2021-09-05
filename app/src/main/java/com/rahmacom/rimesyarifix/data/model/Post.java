package com.rahmacom.rimesyarifix.data.model;

import com.google.gson.annotations.SerializedName;

public class Post {
    @SerializedName("id")
    private int id;

    @SerializedName("judul")
    private String judul;

    @SerializedName("konten")
    private String konten;

    @SerializedName("post_category_id")
    private int postCategoryId;

    @SerializedName("last_updated")
    private String lastUpdated;

    @SerializedName("slug")
    private String slug;

    @SerializedName("post_category")
    private PostCategory postCategory;

    @SerializedName("image")
    private Image image;

	public int getId() {
		return id;
	}

	public String getJudul() {
		return judul;
	}

	public String getKonten() {
		return konten;
	}

	public int getPostCategoryId() {
		return postCategoryId;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public String getSlug() {
		return slug;
	}

	public PostCategory getPostCategory() {
		return postCategory;
	}

	public Image getImage() {
		return image;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setJudul(String judul) {
		this.judul = judul;
	}

	public void setKonten(String konten) {
		this.konten = konten;
	}

	public void setPostCategoryId(int postCategoryId) {
		this.postCategoryId = postCategoryId;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public void setPostCategory(PostCategory postCategory) {
		this.postCategory = postCategory;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
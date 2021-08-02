package com.rahmacom.rimesyarifix.data.network.response;

import com.google.gson.annotations.SerializedName;

public class ResponseFile{

	@SerializedName("fileable_type")
	private String fileableType;

	@SerializedName("path")
	private String path;

	@SerializedName("size")
	private String size;

	@SerializedName("mime_type")
	private String mimeType;

	@SerializedName("name")
	private String name;

	@SerializedName("fileable_id")
	private int fileableId;

	@SerializedName("format")
	private String format;

	@SerializedName("id")
	private int id;

	@SerializedName("url")
	private String url;

	public String getFileableType(){
		return fileableType;
	}

	public String getPath(){
		return path;
	}

	public String getSize(){
		return size;
	}

	public String getMimeType(){
		return mimeType;
	}

	public String getName(){
		return name;
	}

	public int getFileableId(){
		return fileableId;
	}

	public String getFormat(){
		return format;
	}

	public int getId(){
		return id;
	}

	public String getUrl(){
		return url;
	}
}
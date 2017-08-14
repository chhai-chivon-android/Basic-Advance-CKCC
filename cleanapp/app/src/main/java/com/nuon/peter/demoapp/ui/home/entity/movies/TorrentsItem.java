package com.nuon.peter.demoapp.ui.home.entity.movies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TorrentsItem{

	@SerializedName("size_bytes")
	@Expose
	private String sizeBytes;

	@SerializedName("size")
	@Expose
	private String size;

	@SerializedName("seeds")
	@Expose
	private int seeds;

	@SerializedName("date_uploaded")
	@Expose
	private String dateUploaded;

	@SerializedName("peers")
	@Expose
	private int peers;

	@SerializedName("date_uploaded_unix")
	@Expose
	private String dateUploadedUnix;

	@SerializedName("url")
	@Expose
	private String url;

	@SerializedName("hash")
	@Expose
	private String hash;

	@SerializedName("quality")
	@Expose
	private String quality;

	public void setSizeBytes(String sizeBytes){
		this.sizeBytes = sizeBytes;
	}

	public String getSizeBytes(){
		return sizeBytes;
	}

	public void setSize(String size){
		this.size = size;
	}

	public String getSize(){
		return size;
	}

	public void setSeeds(int seeds){
		this.seeds = seeds;
	}

	public int getSeeds(){
		return seeds;
	}

	public void setDateUploaded(String dateUploaded){
		this.dateUploaded = dateUploaded;
	}

	public String getDateUploaded(){
		return dateUploaded;
	}

	public void setPeers(int peers){
		this.peers = peers;
	}

	public int getPeers(){
		return peers;
	}

	public void setDateUploadedUnix(String dateUploadedUnix){
		this.dateUploadedUnix = dateUploadedUnix;
	}

	public String getDateUploadedUnix(){
		return dateUploadedUnix;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setHash(String hash){
		this.hash = hash;
	}

	public String getHash(){
		return hash;
	}

	public void setQuality(String quality){
		this.quality = quality;
	}

	public String getQuality(){
		return quality;
	}
}
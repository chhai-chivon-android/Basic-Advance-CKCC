package com.nuon.peter.demoapp.model.detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TorrentItem{

	@SerializedName("size_bytes")
	@Expose
	private String sizeBytes;

	@SerializedName("size")
	@Expose
	private String size;

	@SerializedName("framerate")
	@Expose
	private String framerate;

	@SerializedName("seeds")
	@Expose
	private String seeds;

	@SerializedName("date_uploaded")
	@Expose
	private String dateUploaded;

	@SerializedName("peers")
	@Expose
	private String peers;

	@SerializedName("date_uploaded_unix")
	@Expose
	private String dateUploadedUnix;

	@SerializedName("resolution")
	@Expose
	private String resolution;

	@SerializedName("url")
	@Expose
	private String url;

	@SerializedName("hash")
	@Expose
	private String hash;

	@SerializedName("quality")
	@Expose
	private String quality;

	@SerializedName("download_count")
	@Expose
	private String downloadCount;

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

	public void setFramerate(String framerate){
		this.framerate = framerate;
	}

	public String getFramerate(){
		return framerate;
	}

	public void setSeeds(String seeds){
		this.seeds = seeds;
	}

	public String getSeeds(){
		return seeds;
	}

	public void setDateUploaded(String dateUploaded){
		this.dateUploaded = dateUploaded;
	}

	public String getDateUploaded(){
		return dateUploaded;
	}

	public void setPeers(String peers){
		this.peers = peers;
	}

	public String getPeers(){
		return peers;
	}

	public void setDateUploadedUnix(String dateUploadedUnix){
		this.dateUploadedUnix = dateUploadedUnix;
	}

	public String getDateUploadedUnix(){
		return dateUploadedUnix;
	}

	public void setResolution(String resolution){
		this.resolution = resolution;
	}

	public String getResolution(){
		return resolution;
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

	public void setDownloadCount(String downloadCount){
		this.downloadCount = downloadCount;
	}

	public String getDownloadCount(){
		return downloadCount;
	}
}
package com.nuon.peter.demoapp.model.detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseMovieDetail{

	@SerializedName("status_message")
	@Expose
	private String statusMessage;

	@SerializedName("data")
	@Expose
	private Data data;

	@SerializedName("meta")
	@Expose
	private Meta meta;

	@SerializedName("status")
	@Expose
	private String status;

	public void setStatusMessage(String statusMessage){
		this.statusMessage = statusMessage;
	}

	public String getStatusMessage(){
		return statusMessage;
	}

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	public void setMeta(Meta meta){
		this.meta = meta;
	}

	public Meta getMeta(){
		return meta;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}
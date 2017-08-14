package com.nuon.peter.demoapp.ui.home.entity.movies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Meta{

	@SerializedName("server_time")
	@Expose
	private int serverTime;

	@SerializedName("server_timezone")
	@Expose
	private String serverTimezone;

	@SerializedName("api_version")
	@Expose
	private int apiVersion;

	@SerializedName("execution_time")
	@Expose
	private String executionTime;

	public void setServerTime(int serverTime){
		this.serverTime = serverTime;
	}

	public int getServerTime(){
		return serverTime;
	}

	public void setServerTimezone(String serverTimezone){
		this.serverTimezone = serverTimezone;
	}

	public String getServerTimezone(){
		return serverTimezone;
	}

	public void setApiVersion(int apiVersion){
		this.apiVersion = apiVersion;
	}

	public int getApiVersion(){
		return apiVersion;
	}

	public void setExecutionTime(String executionTime){
		this.executionTime = executionTime;
	}

	public String getExecutionTime(){
		return executionTime;
	}
}
package com.nuon.peter.demoapp.model.detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Meta{

	@SerializedName("server_time")
	@Expose
	private String serverTime;

	@SerializedName("server_timezone")
	@Expose
	private String serverTimezone;

	@SerializedName("api_version")
	@Expose
	private String apiVersion;

	@SerializedName("execution_time")
	@Expose
	private String executionTime;

	public void setServerTime(String serverTime){
		this.serverTime = serverTime;
	}

	public String getServerTime(){
		return serverTime;
	}

	public void setServerTimezone(String serverTimezone){
		this.serverTimezone = serverTimezone;
	}

	public String getServerTimezone(){
		return serverTimezone;
	}

	public void setApiVersion(String apiVersion){
		this.apiVersion = apiVersion;
	}

	public String getApiVersion(){
		return apiVersion;
	}

	public void setExecutionTime(String executionTime){
		this.executionTime = executionTime;
	}

	public String getExecutionTime(){
		return executionTime;
	}
}
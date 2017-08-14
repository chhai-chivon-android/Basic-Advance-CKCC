package com.nuon.peter.demoapp.rest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductionCountriesItem{

	@SerializedName("iso_3166_1")
	@Expose
	private String iso31661;

	@SerializedName("name")
	@Expose
	private String name;

	public void setIso31661(String iso31661){
		this.iso31661 = iso31661;
	}

	public String getIso31661(){
		return iso31661;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}
}
package com.nuon.peter.demoapp.ui.moviedetail.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductionCompaniesItem{

	@SerializedName("name")
	@Expose
	private String name;

	@SerializedName("id")
	@Expose
	private int id;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}
}
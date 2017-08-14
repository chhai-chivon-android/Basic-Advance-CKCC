package com.nuon.peter.demoapp.ui.moviedetail.entity.cast;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseActors{

	@SerializedName("cast")
	@Expose
	private List<CastItem> cast;

	@SerializedName("id")
	@Expose
	private int id;

	@SerializedName("crew")
	@Expose
	private List<CrewItem> crew;

	public void setCast(List<CastItem> cast){
		this.cast = cast;
	}

	public List<CastItem> getCast(){
		return cast;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setCrew(List<CrewItem> crew){
		this.crew = crew;
	}

	public List<CrewItem> getCrew(){
		return crew;
	}
}
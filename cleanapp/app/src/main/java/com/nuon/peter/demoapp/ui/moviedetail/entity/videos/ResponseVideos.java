package com.nuon.peter.demoapp.ui.moviedetail.entity.videos;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseVideos{

	@SerializedName("id")
	@Expose
	private int id;

	@SerializedName("results")
	@Expose
	private List<ResultsItem> results;

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setResults(List<ResultsItem> results){
		this.results = results;
	}

	public List<ResultsItem> getResults(){
		return results;
	}
}
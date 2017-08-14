package com.nuon.peter.demoapp.ui.genres.entity.movie;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseMovies{

	@SerializedName("id")
	@Expose
	private int id;

	@SerializedName("page")
	@Expose
	private int page;

	@SerializedName("total_pages")
	@Expose
	private int totalPages;

	@SerializedName("results")
	@Expose
	private List<ResultsItem> results;

	@SerializedName("total_results")
	@Expose
	private int totalResults;

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setPage(int page){
		this.page = page;
	}

	public int getPage(){
		return page;
	}

	public void setTotalPages(int totalPages){
		this.totalPages = totalPages;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public void setResults(List<ResultsItem> results){
		this.results = results;
	}

	public List<ResultsItem> getResults(){
		return results;
	}

	public void setTotalResults(int totalResults){
		this.totalResults = totalResults;
	}

	public int getTotalResults(){
		return totalResults;
	}
}
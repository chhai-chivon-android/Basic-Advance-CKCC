package com.nuon.peter.demoapp.ui.home.entity.movies;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("movies")
	@Expose
	private List<MoviesItem> movies;

	@SerializedName("page_number")
	@Expose
	private int pageNumber;

	@SerializedName("movie_count")
	@Expose
	private int movieCount;

	@SerializedName("limit")
	@Expose
	private int limit;

	public void setMovies(List<MoviesItem> movies){
		this.movies = movies;
	}

	public List<MoviesItem> getMovies(){
		return movies;
	}

	public void setPageNumber(int pageNumber){
		this.pageNumber = pageNumber;
	}

	public int getPageNumber(){
		return pageNumber;
	}

	public void setMovieCount(int movieCount){
		this.movieCount = movieCount;
	}

	public int getMovieCount(){
		return movieCount;
	}

	public void setLimit(int limit){
		this.limit = limit;
	}

	public int getLimit(){
		return limit;
	}

	@Override public String toString() {
		return "Data{" +
				"movies=" + movies +
				", pageNumber=" + pageNumber +
				", movieCount=" + movieCount +
				", limit=" + limit +
				'}';
	}
}
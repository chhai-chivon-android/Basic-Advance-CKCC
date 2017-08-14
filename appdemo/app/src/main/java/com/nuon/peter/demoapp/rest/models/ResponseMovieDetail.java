package com.nuon.peter.demoapp.rest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseMovieDetail{

	@SerializedName("original_language")
	@Expose
	private String originalLanguage;

	@SerializedName("imdb_id")
	@Expose
	private String imdbId;

	@SerializedName("video")
	@Expose
	private boolean video;

	@SerializedName("title")
	@Expose
	private String title;

	@SerializedName("backdrop_path")
	@Expose
	private String backdropPath;

	@SerializedName("revenue")
	@Expose
	private int revenue;

	@SerializedName("genres")
	@Expose
	private List<GenresItem> genres;

	@SerializedName("popularity")
	@Expose
	private double popularity;

	@SerializedName("production_countries")
	@Expose
	private List<ProductionCountriesItem> productionCountries;

	@SerializedName("id")
	@Expose
	private int id;

	@SerializedName("vote_count")
	@Expose
	private int voteCount;

	@SerializedName("budget")
	@Expose
	private int budget;

	@SerializedName("overview")
	@Expose
	private String overview;

	@SerializedName("original_title")
	@Expose
	private String originalTitle;

	@SerializedName("runtime")
	@Expose
	private int runtime;

	@SerializedName("poster_path")
	@Expose
	private String posterPath;

	@SerializedName("spoken_languages")
	@Expose
	private List<SpokenLanguagesItem> spokenLanguages;

	@SerializedName("production_companies")
	@Expose
	private List<ProductionCompaniesItem> productionCompanies;

	@SerializedName("release_date")
	@Expose
	private String releaseDate;

	@SerializedName("vote_average")
	@Expose
	private double voteAverage;

	@SerializedName("belongs_to_collection")
	@Expose
	private Object belongsToCollection;

	@SerializedName("tagline")
	@Expose
	private String tagline;

	@SerializedName("adult")
	@Expose
	private boolean adult;

	@SerializedName("homepage")
	@Expose
	private String homepage;

	@SerializedName("status")
	@Expose
	private String status;

	public void setOriginalLanguage(String originalLanguage){
		this.originalLanguage = originalLanguage;
	}

	public String getOriginalLanguage(){
		return originalLanguage;
	}

	public void setImdbId(String imdbId){
		this.imdbId = imdbId;
	}

	public String getImdbId(){
		return imdbId;
	}

	public void setVideo(boolean video){
		this.video = video;
	}

	public boolean isVideo(){
		return video;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setBackdropPath(String backdropPath){
		this.backdropPath = backdropPath;
	}

	public String getBackdropPath(){
		return backdropPath;
	}

	public void setRevenue(int revenue){
		this.revenue = revenue;
	}

	public int getRevenue(){
		return revenue;
	}

	public void setGenres(List<GenresItem> genres){
		this.genres = genres;
	}

	public List<GenresItem> getGenres(){
		return genres;
	}

	public void setPopularity(double popularity){
		this.popularity = popularity;
	}

	public double getPopularity(){
		return popularity;
	}

	public void setProductionCountries(List<ProductionCountriesItem> productionCountries){
		this.productionCountries = productionCountries;
	}

	public List<ProductionCountriesItem> getProductionCountries(){
		return productionCountries;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setVoteCount(int voteCount){
		this.voteCount = voteCount;
	}

	public int getVoteCount(){
		return voteCount;
	}

	public void setBudget(int budget){
		this.budget = budget;
	}

	public int getBudget(){
		return budget;
	}

	public void setOverview(String overview){
		this.overview = overview;
	}

	public String getOverview(){
		return overview;
	}

	public void setOriginalTitle(String originalTitle){
		this.originalTitle = originalTitle;
	}

	public String getOriginalTitle(){
		return originalTitle;
	}

	public void setRuntime(int runtime){
		this.runtime = runtime;
	}

	public int getRuntime(){
		return runtime;
	}

	public void setPosterPath(String posterPath){
		this.posterPath = posterPath;
	}

	public String getPosterPath(){
		return posterPath;
	}

	public void setSpokenLanguages(List<SpokenLanguagesItem> spokenLanguages){
		this.spokenLanguages = spokenLanguages;
	}

	public List<SpokenLanguagesItem> getSpokenLanguages(){
		return spokenLanguages;
	}

	public void setProductionCompanies(List<ProductionCompaniesItem> productionCompanies){
		this.productionCompanies = productionCompanies;
	}

	public List<ProductionCompaniesItem> getProductionCompanies(){
		return productionCompanies;
	}

	public void setReleaseDate(String releaseDate){
		this.releaseDate = releaseDate;
	}

	public String getReleaseDate(){
		return releaseDate;
	}

	public void setVoteAverage(double voteAverage){
		this.voteAverage = voteAverage;
	}

	public double getVoteAverage(){
		return voteAverage;
	}

	public void setBelongsToCollection(Object belongsToCollection){
		this.belongsToCollection = belongsToCollection;
	}

	public Object getBelongsToCollection(){
		return belongsToCollection;
	}

	public void setTagline(String tagline){
		this.tagline = tagline;
	}

	public String getTagline(){
		return tagline;
	}

	public void setAdult(boolean adult){
		this.adult = adult;
	}

	public boolean isAdult(){
		return adult;
	}

	public void setHomepage(String homepage){
		this.homepage = homepage;
	}

	public String getHomepage(){
		return homepage;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}
package com.nuon.peter.demoapp.model.detail;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("rt_audience_rating")
	@Expose
	private String rtAudienceRating;

	@SerializedName("year")
	@Expose
	private String year;

	@SerializedName("rt_audience_score")
	@Expose
	private String rtAudienceScore;

	@SerializedName("description_full")
	@Expose
	private String descriptionFull;

	@SerializedName("rating")
	@Expose
	private String rating;

	@SerializedName("title_long")
	@Expose
	private String titleLong;

	@SerializedName("language")
	@Expose
	private String language;

	@SerializedName("yt_trailer_code")
	@Expose
	private String ytTrailerCode;

	@SerializedName("title")
	@Expose
	private String title;

	@SerializedName("mpa_rating")
	@Expose
	private String mpaRating;

	@SerializedName("genres")
	@Expose
	private List<String> genres;

	@SerializedName("id")
	@Expose
	private String id;

	@SerializedName("state")
	@Expose
	private String state;

	@SerializedName("slug")
	@Expose
	private String slug;

	@SerializedName("like_count")
	@Expose
	private String likeCount;

	@SerializedName("date_uploaded")
	@Expose
	private List<String> dateUploaded;

	@SerializedName("description_intro")
	@Expose
	private String descriptionIntro;

	@SerializedName("runtime")
	@Expose
	private String runtime;

	@SerializedName("rt_critics_score")
	@Expose
	private String rtCriticsScore;

	@SerializedName("url")
	@Expose
	private String url;

	@SerializedName("imdb_code")
	@Expose
	private String imdbCode;

	@SerializedName("download_count")
	@Expose
	private String downloadCount;

	@SerializedName("rt_critics_rating")
	@Expose
	private String rtCriticsRating;

	@SerializedName("torrents")
	@Expose
	private Torrents torrents;

	@SerializedName("date_uploaded_unix")
	@Expose
	private List<String> dateUploadedUnix;

	@SerializedName("medium_cover_image")
	@Expose
	private String mediumCoverImage;

	public void setRtAudienceRating(String rtAudienceRating){
		this.rtAudienceRating = rtAudienceRating;
	}

	public String getRtAudienceRating(){
		return rtAudienceRating;
	}

	public void setYear(String year){
		this.year = year;
	}

	public String getYear(){
		return year;
	}

	public void setRtAudienceScore(String rtAudienceScore){
		this.rtAudienceScore = rtAudienceScore;
	}

	public String getRtAudienceScore(){
		return rtAudienceScore;
	}

	public void setDescriptionFull(String descriptionFull){
		this.descriptionFull = descriptionFull;
	}

	public String getDescriptionFull(){
		return descriptionFull;
	}

	public void setRating(String rating){
		this.rating = rating;
	}

	public String getRating(){
		return rating;
	}

	public void setTitleLong(String titleLong){
		this.titleLong = titleLong;
	}

	public String getTitleLong(){
		return titleLong;
	}

	public void setLanguage(String language){
		this.language = language;
	}

	public String getLanguage(){
		return language;
	}

	public void setYtTrailerCode(String ytTrailerCode){
		this.ytTrailerCode = ytTrailerCode;
	}

	public String getYtTrailerCode(){
		return ytTrailerCode;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setMpaRating(String mpaRating){
		this.mpaRating = mpaRating;
	}

	public String getMpaRating(){
		return mpaRating;
	}

	public void setGenres(List<String> genres){
		this.genres = genres;
	}

	public List<String> getGenres(){
		return genres;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setState(String state){
		this.state = state;
	}

	public String getState(){
		return state;
	}

	public void setSlug(String slug){
		this.slug = slug;
	}

	public String getSlug(){
		return slug;
	}

	public void setLikeCount(String likeCount){
		this.likeCount = likeCount;
	}

	public String getLikeCount(){
		return likeCount;
	}

	public void setDateUploaded(List<String> dateUploaded){
		this.dateUploaded = dateUploaded;
	}

	public List<String> getDateUploaded(){
		return dateUploaded;
	}

	public void setDescriptionIntro(String descriptionIntro){
		this.descriptionIntro = descriptionIntro;
	}

	public String getDescriptionIntro(){
		return descriptionIntro;
	}

	public void setRuntime(String runtime){
		this.runtime = runtime;
	}

	public String getRuntime(){
		return runtime;
	}

	public void setRtCriticsScore(String rtCriticsScore){
		this.rtCriticsScore = rtCriticsScore;
	}

	public String getRtCriticsScore(){
		return rtCriticsScore;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setImdbCode(String imdbCode){
		this.imdbCode = imdbCode;
	}

	public String getImdbCode(){
		return imdbCode;
	}

	public void setDownloadCount(String downloadCount){
		this.downloadCount = downloadCount;
	}

	public String getDownloadCount(){
		return downloadCount;
	}

	public void setRtCriticsRating(String rtCriticsRating){
		this.rtCriticsRating = rtCriticsRating;
	}

	public String getRtCriticsRating(){
		return rtCriticsRating;
	}

	public void setTorrents(Torrents torrents){
		this.torrents = torrents;
	}

	public Torrents getTorrents(){
		return torrents;
	}

	public void setDateUploadedUnix(List<String> dateUploadedUnix){
		this.dateUploadedUnix = dateUploadedUnix;
	}

	public List<String> getDateUploadedUnix(){
		return dateUploadedUnix;
	}

	public void setMediumCoverImage(String mediumCoverImage){
		this.mediumCoverImage = mediumCoverImage;
	}

	public String getMediumCoverImage(){
		return mediumCoverImage;
	}
}
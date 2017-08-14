package com.nuon.peter.demoapp.ui.home.entity.movies;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoviesItem{

	@SerializedName("small_cover_image")
	@Expose
	private String smallCoverImage;

	@SerializedName("year")
	@Expose
	private int year;

	@SerializedName("directors")
	@Expose
	private List<String> directors;

	@SerializedName("date_uploaded")
	@Expose
	private String dateUploaded;

	@SerializedName("rating")
	@Expose
	private double rating;

	@SerializedName("runtime")
	@Expose
	private String runtime;

	@SerializedName("title_long")
	@Expose
	private String titleLong;

	@SerializedName("language")
	@Expose
	private String language;

	@SerializedName("synopsis")
	@Expose
	private String synopsis;

	@SerializedName("yt_trailer_code")
	@Expose
	private String ytTrailerCode;

	@SerializedName("title")
	@Expose
	private String title;

	@SerializedName("url")
	@Expose
	private String url;

	@SerializedName("imdb_code")
	@Expose
	private String imdbCode;

	@SerializedName("background_image")
	@Expose
	private String backgroundImage;

	@SerializedName("cast")
	@Expose
	private List<String> cast;

	@SerializedName("google_video")
	@Expose
	private String googleVideo;

	@SerializedName("mpa_rating")
	@Expose
	private String mpaRating;

	@SerializedName("genres")
	@Expose
	private List<String> genres;

	@SerializedName("torrents")
	@Expose
	private List<TorrentsItem> torrents;

	@SerializedName("id")
	@Expose
	private String id;

	@SerializedName("state")
	@Expose
	private String state;

	@SerializedName("date_uploaded_unix")
	@Expose
	private int dateUploadedUnix;

	@SerializedName("slug")
	@Expose
	private String slug;

	@SerializedName("medium_cover_image")
	@Expose
	private String mediumCoverImage;

	public void setSmallCoverImage(String smallCoverImage){
		this.smallCoverImage = smallCoverImage;
	}

	public String getSmallCoverImage(){
		return smallCoverImage;
	}

	public void setYear(int year){
		this.year = year;
	}

	public int getYear(){
		return year;
	}

	public void setDirectors(List<String> directors){
		this.directors = directors;
	}

	public List<String> getDirectors(){
		return directors;
	}

	public void setDateUploaded(String dateUploaded){
		this.dateUploaded = dateUploaded;
	}

	public String getDateUploaded(){
		return dateUploaded;
	}

	public void setRating(double rating){
		this.rating = rating;
	}

	public double getRating(){
		return rating;
	}

	public void setRuntime(String runtime){
		this.runtime = runtime;
	}

	public String getRuntime(){
		return runtime;
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

	public void setSynopsis(String synopsis){
		this.synopsis = synopsis;
	}

	public String getSynopsis(){
		return synopsis;
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

	public void setBackgroundImage(String backgroundImage){
		this.backgroundImage = backgroundImage;
	}

	public String getBackgroundImage(){
		return backgroundImage;
	}

	public void setCast(List<String> cast){
		this.cast = cast;
	}

	public List<String> getCast(){
		return cast;
	}

	public void setGoogleVideo(String googleVideo){
		this.googleVideo = googleVideo;
	}

	public String getGoogleVideo(){
		return googleVideo;
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

	public void setTorrents(List<TorrentsItem> torrents){
		this.torrents = torrents;
	}

	public List<TorrentsItem> getTorrents(){
		return torrents;
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

	public void setDateUploadedUnix(int dateUploadedUnix){
		this.dateUploadedUnix = dateUploadedUnix;
	}

	public int getDateUploadedUnix(){
		return dateUploadedUnix;
	}

	public void setSlug(String slug){
		this.slug = slug;
	}

	public String getSlug(){
		return slug;
	}

	public void setMediumCoverImage(String mediumCoverImage){
		this.mediumCoverImage = mediumCoverImage;
	}

	public String getMediumCoverImage(){
		return mediumCoverImage;
	}

	@Override public String toString() {
		return "MoviesItem{" +
				"smallCoverImage='" + smallCoverImage + '\'' +
				", year=" + year +
				", directors=" + directors +
				", dateUploaded='" + dateUploaded + '\'' +
				", rating=" + rating +
				", runtime='" + runtime + '\'' +
				", titleLong='" + titleLong + '\'' +
				", language='" + language + '\'' +
				", synopsis='" + synopsis + '\'' +
				", ytTrailerCode='" + ytTrailerCode + '\'' +
				", title='" + title + '\'' +
				", url='" + url + '\'' +
				", imdbCode='" + imdbCode + '\'' +
				", backgroundImage='" + backgroundImage + '\'' +
				", cast=" + cast +
				", googleVideo='" + googleVideo + '\'' +
				", mpaRating='" + mpaRating + '\'' +
				", genres=" + genres +
				", torrents=" + torrents +
				", id='" + id + '\'' +
				", state='" + state + '\'' +
				", dateUploadedUnix=" + dateUploadedUnix +
				", slug='" + slug + '\'' +
				", mediumCoverImage='" + mediumCoverImage + '\'' +
				'}';
	}
}
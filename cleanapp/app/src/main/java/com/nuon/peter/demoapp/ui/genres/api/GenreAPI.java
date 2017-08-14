package com.nuon.peter.demoapp.ui.genres.api;

import com.nuon.peter.demoapp.base.RetrofitManager;
import com.nuon.peter.demoapp.ui.genres.entity.genre.ResponseGenres;
import com.nuon.peter.demoapp.ui.genres.entity.movie.ResponseMovies;
import com.nuon.peter.demoapp.utils.Common;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by manithnuon on 2/11/17.
 */

public class GenreAPI {
  private static final String MOVIEAPI = "https://api.themoviedb.org/3/";

  public interface GenreManager{
    //https://api.themoviedb.org/3/genre/movie/list?api_key=0e0698e20510523e4714467048549e01
    @GET("genre/movie/list?api_key="+ Common.APIKEY) Call<ResponseGenres> getMovieListGenre();
    //https://api.themoviedb.org/3/genre/tv/list?api_key=0e0698e20510523e4714467048549e01
    @GET("genre/movie/list?api_key="+ Common.APIKEY) Call<ResponseGenres> getTVListGenre();
    //https://api.themoviedb.org/3/genre/28/movies?api_key=0e0698e20510523e4714467048549e01
    @GET("genre/{genre}/movies?api_key="+ Common.APIKEY) Call<ResponseMovies> getGenreMovies(@Path("genre") int id);
    //https://api.themoviedb.org/3/genre/28/movies?api_key=0e0698e20510523e4714467048549e01&page=1
    @GET("genre/{genre}/movies?api_key="+ Common.APIKEY) Call<ResponseMovies> getGenreMoviesPage(@Path("genre") int id,@Query("page") int page);
    //https://api.themoviedb.org/3/genre/28/movies?api_key=0e0698e20510523e4714467048549e01&include_adult=true
    @GET("genre/{genre}/movies?api_key="+ Common.APIKEY) Call<ResponseMovies> getGenreMoviesWithAdult(@Path("genre") int id, @Query("include_adult") boolean isAdult);
    //https://api.themoviedb.org/3/genre/28/movies?api_key=0e0698e20510523e4714467048549e01&include_adult=true&sort_by=created_at.asc
    @GET("genre/{genre}/movies?api_key="+ Common.APIKEY) Call<ResponseMovies> getGenreMoviesWithAdultAndSort(@Path("genre") int id, @Query("include_adult") boolean isAdult,@Query("sort_by") String sort);
    //https://api.themoviedb.org/3/genre/28/movies?api_key=0e0698e20510523e4714467048549e01&include_adult=true&sort_by=created_at.asc&page=1
    @GET("genre/{genre}/movies?api_key="+ Common.APIKEY) Call<ResponseMovies> getGenreMoviesWithAdultAndSortPage(@Path("genre") int id, @Query("include_adult") boolean isAdult,@Query("sort_by") String sort,@Query("page") int page);
  }
  public static GenreManager getGenreManager(){
    return RetrofitManager.getAPIManager(MOVIEAPI).create(GenreManager.class);
  }

  public static String getImageURL(String size){
    return Common.IMGURL + size ;
  }
}

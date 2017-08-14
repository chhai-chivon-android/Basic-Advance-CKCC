package com.nuon.peter.demoapp.ui.movielist.api;

import com.nuon.peter.demoapp.base.RetrofitManager;
import com.nuon.peter.demoapp.ui.movielist.entity.ResponseMovies;
import com.nuon.peter.demoapp.utils.Common;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by beniten on 2/2/17.
 */

public class MovieListAPI {

  private static final String MOVIEAPI = "https://api.themoviedb.org/3/";

  public interface MovieListManager{

    //https://api.themoviedb.org/3/movie/now_playing?api_key=0e0698e20510523e4714467048549e01&page=1
    @GET("movie/now_playing?api_key="+ Common.APIKEY)
    Call<ResponseMovies> getNowPlaying(@Query("page") int page);

    //https://api.themoviedb.org/3/movie/popular?api_key=0e0698e20510523e4714467048549e01&page=1
    @GET("movie/popular?api_key="+Common.APIKEY)
    Call<ResponseMovies> getPopularity(@Query("page") int page);

    //https://api.themoviedb.org/3/movie/top_rated?api_key=0e0698e20510523e4714467048549e01&page=1
    @GET("movie/top_rated?api_key="+Common.APIKEY)
    Call<ResponseMovies> getTopRate(@Query("page") int page);

    //https://api.themoviedb.org/3/movie/upcoming?api_key=0e0698e20510523e4714467048549e01&page=1
    @GET("movie/upcoming?api_key="+Common.APIKEY)
    Call<ResponseMovies> getUpComing(@Query("page") int page);

  }
  public static MovieListManager getMovieListManager(){
    return RetrofitManager.getAPIManager(MOVIEAPI).create(MovieListManager.class);
  }

  public static String getImageURL(String size){
    return Common.IMGURL + size ;
  }

}

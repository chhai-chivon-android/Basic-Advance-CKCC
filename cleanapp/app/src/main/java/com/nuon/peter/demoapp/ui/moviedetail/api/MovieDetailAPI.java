package com.nuon.peter.demoapp.ui.moviedetail.api;

import com.nuon.peter.demoapp.base.RetrofitManager;
import com.nuon.peter.demoapp.ui.home.entity.movies.ResponseMovies;
import com.nuon.peter.demoapp.ui.home.entity.movies.deserialize.ResponseData;
import com.nuon.peter.demoapp.ui.home.entity.movies.deserialize.ResponseMoviesList;
import com.nuon.peter.demoapp.ui.moviedetail.entity.ResponseMovieDetail;
import com.nuon.peter.demoapp.ui.moviedetail.entity.cast.ResponseActors;
import com.nuon.peter.demoapp.ui.moviedetail.entity.similar.ResponseSimilar;
import com.nuon.peter.demoapp.ui.moviedetail.entity.videos.ResponseVideos;
import com.nuon.peter.demoapp.ui.movielist.api.MovieListAPI;
import com.nuon.peter.demoapp.utils.Common;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by beniten on 2/2/17.
 */

public class MovieDetailAPI {

  private static final String MOVIEAPI = "https://api.themoviedb.org/3/";

  public interface MovieDetailManager{

    //http://food2fork.com/api/search?key=c73fdab22f35cda927f91ffdbd4b7db4&q=Fish
    @GET("search?key")
    Call<ResponseActors> getFood(@Query("q") String search);


    //https://api.themoviedb.org/3/movie/297761?api_key=0e0698e20510523e4714467048549e01
    @GET("movie/{movie_id}?api_key="+ Common.APIKEY)
    Call<ResponseMovieDetail> getMovieDetail(@Path("movie_id") int id);

    //https://api.themoviedb.org/3/movie/{movie_id}/similar?api_key
    @GET("movie/{movie_id}/similar?api_key="+ Common.APIKEY)
    Call<ResponseSimilar> getSimilarMovies(@Path("movie_id") int id);

      //https://api.themoviedb.org/3/movie/135397/credits?api_key=0e0698e20510523e4714467048549e01
    @GET("movie/{movie_id}/credits?api_key="+ Common.APIKEY)
    Call<ResponseActors> getCreditsOfMovie(@Path("movie_id") int id);

    //https://api.themoviedb.org/3/movie/{movie_id}/videos?api_key=
    @GET("movie/{movie_id}/videos?api_key="+ Common.APIKEY)
    Call<ResponseVideos> getVideosOfMovie(@Path("movie_id") int id);

  }
  public static MovieDetailManager getMovieListManager(){
    return RetrofitManager.getAPIManager(MOVIEAPI).create(MovieDetailManager.class);
  }

  public static String getImageURL(String size){
    return Common.IMGURL + size ;
  }


}
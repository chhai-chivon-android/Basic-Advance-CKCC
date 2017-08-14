package com.nuon.peter.demoapp.ui.home.api;

import com.nuon.peter.demoapp.base.RetrofitManager;
import com.nuon.peter.demoapp.utils.Common;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import com.nuon.peter.demoapp.ui.movielist.entity.ResponseMovies;

/**
 * Created by beniten on 2/2/17.
 */

public class MovieAPI {

  /*private static final String MOVIEAPI = "http://yify.is/api/v2/";
  private static final String LISTMOVIE = "list_movies.json";*/
  private static final String MOVIEAPI = "https://api.themoviedb.org/3/";

  public interface MovieManager{

    /*@GET(LISTMOVIE)
    Call<ResponseMovies> getMovies();

    @GET(LISTMOVIE)
    Call<ResponseMovies> getMovieByGenre(@Query("genre") String type);

    @GET(LISTMOVIE)
    Call<ResponseMovies> getMovieSortByAndLimit(@Query("sort_by") String by, @Query("limit") int limit);

    @GET(LISTMOVIE)
    Call<ResponseBody> getMovieSortByAndLimitRaw(@Query("sort_by") String by, @Query("limit") int limit);

    @GET(LISTMOVIE)
    Call<ResponseMovies> getMovieByName(@Query("query_term") String type);

    @GET(LISTMOVIE)
    Call<ResponseMovies> getMovieDetailWithImageAndCast(@Query("movie_id") int id, @Query("with_images") boolean withImage,@Query("with_cast") boolean withCast);

    @GET(LISTMOVIE)
    Call<ResponseData> getMoviesDataMovieSortByAndLimit(@Query("sort_by") String by, @Query("limit") int limit);

    @GET(LISTMOVIE)
    Call<ResponseMoviesList> getMoviesListMovieSortByAndLimit(@Query("sort_by") String by, @Query("limit") int limit);*/

    @GET("movie/popular?api_key="+ Common.APIKEY)
    Call<ResponseMovies> getPopularity(@Query("page") int page);

  }
  public static MovieManager getMovieManager(){
    return RetrofitManager.getAPIManager(MOVIEAPI).create(MovieManager.class);
  }

}

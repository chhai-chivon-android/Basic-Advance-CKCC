package com.nuon.peter.demoapp;

import com.nuon.peter.demoapp.model.movies.Data;
import com.nuon.peter.demoapp.model.movies.MoviesItem;
import com.nuon.peter.demoapp.model.movies.ResponseMovies;
import com.nuon.peter.demoapp.model.detail.ResponseMovieDetail;
import com.nuon.peter.demoapp.model.movies.deserialize.ResponseData;
import com.nuon.peter.demoapp.model.movies.deserialize.ResponseMoviesList;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by beniten on 2/2/17.
 */

public class MovieAPI {

  private static final String MOVIEAPI = "http://yify.is/api/v2/";
  private static final String LISTMOVIE = "list_movies.json";
  private static final String MOVIEDETAIL = "movie_details.json";

  public interface MovieManager{
    @GET(LISTMOVIE)
    Call<ResponseMovies> getMovies();
    @GET(LISTMOVIE)
    Call<ResponseMovies> getMovieByGenre(@Query("genre") String type);
    @GET(LISTMOVIE)
    Call<ResponseMovies> getMovieSortByAndLimit(@Query("sort_by") String by, @Query("limit") int limit);
    @GET(LISTMOVIE)
    Call<ResponseMovies> getMovieByName(@Query("query_term") String type);
    @GET(MOVIEDETAIL)
    Call<ResponseMovieDetail> getMovieDetailById(@Query("movie_id") int id);
    @GET(LISTMOVIE)
    Call<ResponseMovies> getMovieDetailWithImageAndCast(@Query("movie_id") int id, @Query("with_images") boolean withImage,@Query("with_cast") boolean withCast);

    @GET(LISTMOVIE)
    Call<ResponseData> getMoviesDataMovieSortByAndLimit(@Query("sort_by") String by, @Query("limit") int limit);
    @GET(LISTMOVIE)
    Call<ResponseMoviesList> getMoviesListMovieSortByAndLimit(@Query("sort_by") String by, @Query("limit") int limit);
  }
  public static MovieManager getMovieManager(){
    return RetrofitManager.getAPIManager(MOVIEAPI).create(MovieManager.class);
  }

}

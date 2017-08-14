package com.nuon.peter.demoapp.rest;

import com.nuon.peter.demoapp.model.ResponseMovieData;
import com.nuon.peter.demoapp.rest.models.ResponseMovieDetail;
import com.nuon.peter.demoapp.rest.models.ResponseMovies;
import com.nuon.peter.demoapp.rest.models.cast.ResponseActors;
import com.nuon.peter.demoapp.rest.models.similar.ResponseSimilar;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by manithnuon on 6/2/17.
 */

public interface MovieService {

    /*//https://api.themoviedb.org/3/movie/now_playing?api_key=0e0698e20510523e4714467048549e01&page=1
    @GET("movie/now_playing?api_key="+ Common.API_KEY)
    Call<ResponseMovies> getNowPlaying(@Query("page") int page);

    //https://api.themoviedb.org/3/movie/popular?api_key=0e0698e20510523e4714467048549e01&page=1
    @GET("movie/popular?api_key="+Common.API_KEY)
    Call<ResponseMovies> getPopularity(@Query("page") int page);

    //https://api.themoviedb.org/3/movie/top_rated?api_key=0e0698e20510523e4714467048549e01&page=1
    @GET("movie/top_rated?api_key="+Common.API_KEY)
    Call<ResponseMovies> getTopRate(@Query("page") int page);

    //https://api.themoviedb.org/3/movie/upcoming?api_key=0e0698e20510523e4714467048549e01&page=1
    @GET("movie/upcoming?api_key="+Common.API_KEY)
    Call<ResponseMovies> getUpComing(@Query("page") int page);*/



    /**
     * http://139.59.125.251:3030/api /nowplay/1
     *  Get now play list
     * @param page
     * @return
     */
    @GET("nowplay/{page}")
    Call<ResponseMovies> getNowPlayingMovie(@Path("page") int page);

    /**
     * http://139.59.125.251:3030/api/popular/1
     *  Get popular list
     * @param page
     * @return
     */
    @GET("popular/{page}")
    Call<ResponseMovies> getPopularityMovie(@Path("page") int page);

    /**
     * http://139.59.125.251:3030/api/top/1
     *  Get top rate list
     * @param page
     * @return
     */
    @GET("top/{page}")
    Call<ResponseMovies> getTopRateMovie(@Path("page") int page);

    /**
     * http://139.59.125.251:3030/api/upcoming/1
     *  Get upcoming list
     * @param page
     * @return
     */
    @GET("upcoming/{page}")
    Call<ResponseMovies> getUpComingMovie(@Path("page") int page);

    /**
     * http://139.59.125.251:3030/api/genre
     *  Get genres list
     *
     */


    /**
     * http://139.59.125.251:3030/api/genre/28/1
     *  Get genres movie list
     * @param id
     * @param page
     * @return
     */
    @GET("genre/{id}/{page}")
    Call<ResponseMovies> getMovieByGenres(@Path("id") int id, @Path("page") int page);


    /**
     * http://139.59.125.251:3030/api/movie/297762
     * Get movie detail
     * @param id
     * @return
     */
    @GET("movie/{movie_id}")
    Call<ResponseMovieDetail> getMovieDetail(@Path("movie_id") int id);

    /**
     * http://139.59.125.251:3030/api/similar/297762/1
     * Get similar movie list
     * @param id
     * @param page
     * @return
     */
    @GET("similar/{movie_id}/{page}")
    Call<ResponseSimilar> getSimilarMovies(@Path("movie_id") int id, @Path("page") int page);

    /**
     * http://139.59.125.251:3030/api/actor/297762
     * Get actor
     * @param id
     * @return
     */

    @GET("actor/{movie_id}")
    Call<ResponseActors> getCreditsOfMovie(@Path("movie_id") int id);

}

package com.nuon.peter.demoapp;

import com.nuon.peter.demoapp.model.github.ResponseGithub;
import com.nuon.peter.demoapp.model.movies.ResponseMovies;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by beniten on 2/2/17.
 */

public class GithubAPI {

  private static final String BASEAPI = "https://api.github.com/";



  public interface GithubManager{

    @GET("users/{user}/followers")
    Call<List<ResponseGithub>> getFollowersList(@Path("user") String user);

  }

  public static GithubManager getGithubManager(){
    return RetrofitManager.getAPIManager(BASEAPI).create(GithubManager.class);
  }

}

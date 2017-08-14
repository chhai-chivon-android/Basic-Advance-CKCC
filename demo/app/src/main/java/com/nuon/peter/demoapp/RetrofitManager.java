package com.nuon.peter.demoapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nuon.peter.demoapp.model.movies.ResponseMovies;
import com.nuon.peter.demoapp.model.movies.deserialize.ResponseData;
import com.nuon.peter.demoapp.model.movies.deserialize.ResponseMovieDeserialize;
import com.nuon.peter.demoapp.model.movies.deserialize.ResponseMoviesList;
import com.nuon.peter.demoapp.model.movies.deserialize.ResponseMoviesListDeserialize;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
  private static Retrofit.Builder sInstance;
  public static Retrofit.Builder getInstance() {
    if (sInstance == null) {
      synchronized (RetrofitManager.class) {
        if (sInstance == null) {
          sInstance = new Retrofit.Builder();
        }
      }
    }
    return sInstance;
  }
  private static Retrofit getRetrofit(String url) {
    return RetrofitManager.getInstance()
        .client(OKHttpManager.getInstance())
        //.addConverterFactory(refactoryGson())
        //.addConverterFactory(refactoryListGson())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(url)
        .build();
  }

  /*private static GsonConverterFactory refactoryGson() {
    GsonBuilder builder = new GsonBuilder();
    builder.registerTypeAdapter(ResponseData.class, new ResponseMovieDeserialize());
    Gson myGson = builder.create();
    return GsonConverterFactory.create(myGson);
  }

  private static GsonConverterFactory refactoryListGson() {
    GsonBuilder builder = new GsonBuilder();
    builder.registerTypeAdapter(ResponseMoviesList.class, new ResponseMoviesListDeserialize());
    Gson myGson = builder.create();
    return GsonConverterFactory.create(myGson);
  }*/

  public static Retrofit getAPIManager(String api) {
    return RetrofitManager.getRetrofit(api);
  }

}

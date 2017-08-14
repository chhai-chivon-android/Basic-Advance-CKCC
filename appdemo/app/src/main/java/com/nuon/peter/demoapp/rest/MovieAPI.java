package com.nuon.peter.demoapp.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by manithnuon on 6/2/17.
 */

public class MovieAPI {

    private static Retrofit.Builder sInstance;
    public static Retrofit.Builder getInstance() {
        if (sInstance == null) {
            synchronized (MovieAPI.class) {
                if (sInstance == null) {
                    sInstance = new Retrofit.Builder();
                }
            }
        }
        return sInstance;
    }
    private static Retrofit getRetrofit(String url) {
        return MovieAPI.getInstance()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build();
    }

    public static Retrofit getAPIManager(String url){
        return MovieAPI.getRetrofit(url);
    }
}

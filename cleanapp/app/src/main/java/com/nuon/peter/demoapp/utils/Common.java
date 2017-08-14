package com.nuon.peter.demoapp.utils;

import com.nuon.peter.demoapp.BuildConfig;

/**
 * Created by beniten on 2/10/17.
 */

public class Common {
  public static final int EVENT_BEGIN = 0X100;
  public static final int STATE_NOW_PLAY = EVENT_BEGIN + 10;
  public static final int STATE_TOP_RATE = EVENT_BEGIN + 20;
  public static final int STATE_POPULARITY = EVENT_BEGIN + 30;
  public static final int STATE_UPCOMING = EVENT_BEGIN + 40;
  public static final int STATE_GENRES = EVENT_BEGIN + 50;
  public static final int STATE_GENRES_MOVIES = EVENT_BEGIN + 60;
  public static final int STATE_GENRES_MOVIES_MORE = EVENT_BEGIN + 40;
  public static final String APIKEY = "0e0698e20510523e4714467048549e01";
  public static final String IMGURL = "http://image.tmdb.org/t/p/";
  public static final String PREFS_FILE = "prefs";
  public static Boolean DEBUG_ENABLED = BuildConfig.DEBUG; // will be set to true for debug builds and false for release builds
  public static final String[] MOVIE_URLS = {"http://api-fetch.website/movies/api/v2/"};
  public static final Integer SERVER_PORT = 55723;
}

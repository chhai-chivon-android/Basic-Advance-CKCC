package com.nuon.peter.demoapp;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by beniten on 2/2/17.
 */

public class OKHttpManager {

  private static OkHttpClient sInstance;

  public static OkHttpClient getInstance() {
    if (sInstance == null) {
      synchronized (OKHttpManager.class) {
        if (sInstance == null) {
          HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
          logging.setLevel(HttpLoggingInterceptor.Level.BODY);

          sInstance = new OkHttpClient().newBuilder().connectTimeout(10, TimeUnit.SECONDS)
              .writeTimeout(10,TimeUnit.SECONDS)
              .addInterceptor(logging)
              .readTimeout(10,TimeUnit.SECONDS)
              .build();
        }
      }
    }
    return sInstance;
  }

}

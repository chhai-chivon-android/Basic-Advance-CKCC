package com.nuon.peter.demoapp.base.network;

import com.nuon.peter.demoapp.app.App;
import java.io.IOException;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
          logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
          sInstance = new OkHttpClient.Builder()
              .cache(new Cache(App.getInstance().getBaseContext().getCacheDir(), 10 * 1024 * 1024)) // 10 MB
              .addInterceptor(logging)
              .addInterceptor(new RewriteRequestInterceptor())
              .addNetworkInterceptor(new RewriteResponseCacheControlInterceptor())
              .build();
        }
      }
    }
    return sInstance;
  }

  public static class RewriteRequestInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
      int maxStale = 60 * 60 * 24 * 5;
      Request request;
      if (App.isNetworkAvailable(App.getInstance().getBaseContext())) {
        request = chain.request();
      } else {
        request = chain.request().newBuilder().header("Cache-Control", "max-stale=" + maxStale).build();
      }
      return chain.proceed(request);
    }
  }

  public static class RewriteResponseCacheControlInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
      int maxStale = 60 * 60 * 24 * 5;
      Response originalResponse = chain.proceed(chain.request());
      return originalResponse.newBuilder().header("Cache-Control", "public, max-age=120, max-stale=" + maxStale).build();
    }
  }



}

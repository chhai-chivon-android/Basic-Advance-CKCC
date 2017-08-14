package com.nuon.peter.demoapp.app;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.nuon.peter.demoapp.base.component.content.preferences.Prefs;
import com.nuon.peter.demoapp.base.network.OKHttpManager;
import com.nuon.peter.demoapp.utils.PrefUtils;
import com.nuon.peter.demoapp.utils.StorageUtils;
import java.io.File;

/**
 * Created by beniten on 2/6/17.
 */

public class App extends Application {

  private static Application application;
  @Override public void onCreate() {
    super.onCreate();
    application = this;

  }

  public static Application getInstance(){
    if (application == null) {
      synchronized (App.class) {
        if (application == null) {
          application = new Application();
        }
      }
    }
    return application;
  }
  public static  boolean isNetworkAvailable(Context context) {
    if(context == null) { return false; }
    ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    try {
      NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
      if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
        return true;
      }
    } catch (Exception e) {
    }
    return false;
  }

  public static String getStreamDir() {
    File path = new File(PrefUtils.get(getInstance().getBaseContext(), Prefs.STORAGE_LOCATION, StorageUtils.getIdealCacheDirectory(getInstance().getBaseContext()).toString()));
    File directory = new File(path, "/torrents/");
    return directory.toString();
  }


}

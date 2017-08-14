package com.nuon.peter.demoapp.app;

import android.app.Application;
import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.facebook.FacebookSdk;
import com.google.firebase.FirebaseApp;
import com.nuon.peter.demoapp.R;


/**
 * Created by manithnuon on 6/19/17.
 */

public class App extends Application {

    private static Context ApplicationContext;
    @Override
    public void onCreate() {

        super.onCreate();
        ApplicationContext = getApplicationContext();
        FacebookSdk.setApplicationId(getString(R.string.facebook_app_id));

    }

    public static Context getContext() {
        return ApplicationContext;
    }
}

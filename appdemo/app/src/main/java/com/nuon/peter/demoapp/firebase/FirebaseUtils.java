package com.nuon.peter.demoapp.firebase;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.nuon.peter.demoapp.app.App;

/**
 * Created by manithnuon on 6/19/17.
 */

abstract public class FirebaseUtils {

    private final static String TAG = FirebaseUtils.class.getSimpleName();


    public static void SignInWithEmail(String email, String password) {

        Toast.makeText(App.getContext(), password,
                Toast.LENGTH_SHORT).show();
    }

    public static boolean isValidEmail(CharSequence email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    public static boolean isValidPassword(CharSequence password) {

        if (TextUtils.isEmpty(password) || password.length()< 6) {
            Log.d(TAG, "password length= "+ password.length());
            return false;
        } else {
            return true;
        }
    }

    public static boolean isValidName(CharSequence name) {

        if (TextUtils.isEmpty(name)){
            return false;
        } else {
            return true;
        }
    }

}

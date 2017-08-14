package com.nuon.peter.demoapp.utils;

import android.app.UiModeManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import com.nuon.peter.demoapp.app.App;

public class VersionUtils {

    public static boolean isMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    public static boolean isLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static boolean isJellyBean() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    public static boolean isAndroidTV() {
        UiModeManager uiModeManager = (UiModeManager) App.getInstance().getSystemService(
            Context.UI_MODE_SERVICE);
        return uiModeManager.getCurrentModeType() == Configuration.UI_MODE_TYPE_TELEVISION;
    }

    public static boolean isUsingCorrectBuild() {
        String buildAbi = getBuildAbi();
        if(buildAbi.equalsIgnoreCase("local"))
            return true;

        String deviceAbi;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            deviceAbi = Build.CPU_ABI;
        } else {
            deviceAbi = Build.SUPPORTED_ABIS[0];
        }

        return deviceAbi.equalsIgnoreCase(buildAbi);
    }

    private static String getBuildAbi() {
        PackageManager manager = App.getInstance().getPackageManager();
        try {
            PackageInfo
                info = manager.getPackageInfo(App.getInstance().getPackageName(), 0);
            Integer versionCode = info.versionCode;

            if(info.versionName.contains("local"))
                return "local";

            if(versionCode > 40000000) {
                return "x86";
            } else if(versionCode > 30000000) {
                return "arm64-v8a";
            } else if(versionCode > 20000000) {
                return "armeabi-v7a";
            } else if(versionCode > 10000000) {
                return "armeabi";
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return "unsupported";
    }

}

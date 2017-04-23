package com.ys.zy.catchoice;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Ys on 17/3/14.
 * GG Application
 */

public class GGApp extends Application {

    protected static final String META_DB_NAME = "db_name";
    protected static final String META_DB_VERSION = "db_version";

    private Bundle mMetaData;

    private static GGApp instance;

    public static GGApp getApp() {
        return instance;
    }

    @Override
    public void onCreate() {
        Log.d("gg_app", "onCreate~");
        super.onCreate();
        if (instance == null) {
            instance = this;
        }

        try {
            ApplicationInfo appInfo = getPackageManager()
                    .getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            if (appInfo != null) {
                mMetaData = appInfo.metaData;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getAppVersionName() {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = getPackageManager();
            PackageInfo pi = pm.getPackageInfo(getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }

    public int getAppVersionCode() {
        int versionName = 0;
        try {
            // ---get the package info---
            PackageManager pm = getPackageManager();
            PackageInfo pi = pm.getPackageInfo(getPackageName(), 0);
            versionName = pi.versionCode;
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }

    public Object getMetaValue(String metaKey) {
        return mMetaData == null ? "" : mMetaData.get(metaKey);
    }
}

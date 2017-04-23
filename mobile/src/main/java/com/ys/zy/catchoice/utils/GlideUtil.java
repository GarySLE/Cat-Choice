package com.ys.zy.catchoice.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import com.ys.zy.catchoice.GGApp;

/**
 * Created by Ys on 17/3/13.
 * 封装Glide
 */
public class GlideUtil {

    public static RequestManager with(Context context) {
        if (context instanceof Activity) {
            return with((Activity) context);
        }
        return context != null ? Glide.with(context) : Glide.with(GGApp.getApp());
    }

    public static RequestManager with(Activity activity) {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1
                && activity.isDestroyed() ? Glide.with(GGApp.getApp()) : Glide.with(activity);
    }

    public static RequestManager with(FragmentActivity activity) {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1
                && activity.isDestroyed() ? Glide.with(GGApp.getApp()) : Glide.with(activity);
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static RequestManager with(android.app.Fragment fragment) {
        return fragment.getActivity() == null ? Glide.with(GGApp.getApp()) : Glide.with(fragment);
    }

    public static RequestManager with(Fragment fragment) {
        return fragment.getActivity() == null ? Glide.with(GGApp.getApp()) : Glide.with(fragment);
    }
}

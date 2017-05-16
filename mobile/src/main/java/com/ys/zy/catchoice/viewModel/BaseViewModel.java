package com.ys.zy.catchoice.viewModel;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Created by Ys on 17/3/9.
 * Base ViewModel
 */

public class BaseViewModel implements IViewModel {

    @NonNull
    protected Activity mActivity;

    public BaseViewModel(@NonNull Activity activity) {
        this.mActivity = activity;
    }

    public void startActivity(@NonNull Intent intent, Bundle extras) {
        mActivity.startActivity(intent, extras);
    }

    public void startActivity(@NonNull Intent intent) {
        mActivity.startActivity(intent);
    }

    public void startActivityForResult(@NonNull Intent intent, int requestCode) {
        mActivity.startActivityForResult(intent, requestCode);
    }

    public Resources getResources() {
        return mActivity.getResources();
    }

}

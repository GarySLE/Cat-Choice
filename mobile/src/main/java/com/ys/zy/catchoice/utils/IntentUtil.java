package com.ys.zy.catchoice.utils;

import android.content.Intent;

import com.ys.zy.catchoice.viewModel.BaseViewModel;

/**
 * Created by Ys on 17/4/28.
 * 处理Intent跳转
 */

public class IntentUtil {

    public static final int NOT_FOR_RESULT = 0;

    // 相册
    public static final int REQUEST_ALBUM = 0x10;

    // 相机
    public static final int REQUEST_CAMERA = 0x11;

    public static void goToImageContent(BaseViewModel vm) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("uri/*");
        goToActivityForResult(vm, intent, REQUEST_ALBUM);
    }

    public static void goToPhotoCapture(BaseViewModel vm) {
        // TODO: 17/4/28 调用相机
    }

    public static void goToActivityForResult(BaseViewModel vm, Intent intent, int code) {
        vm.startActivityForResult(intent, code);
    }
}

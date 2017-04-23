package com.ys.zy.catchoice.utils;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;

/**
 * Created by Ys on 17/3/15.
 * 相册和相机图片处理
 */

public class PhotoUtil {

    public static String disposeImage(Context ctx, Intent data) {
        if (SystemUtil.isKitKat()) {
            return disposeImageExceedKitKat(ctx, data);
        } else {
            return disposeImageUnderKitKat(ctx, data);
        }
    }

    private static String disposeImageUnderKitKat(Context ctx, Intent data) {
        return analysisImagePath(ctx, data.getData(), null);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private static String disposeImageExceedKitKat(Context ctx, Intent data) {
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(ctx, uri)) {
            // 通过id解析Document类型Uri
            String docId = DocumentsContract.getDocumentId(uri);
            if (TextUtils.equals("com.android.providers.media.documents", uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = String.format("%s=%s", MediaStore.Images.Media._ID, id);
                return analysisImagePath(ctx, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if (TextUtils.equals("com.android.providers.downloads.documents", uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                return analysisImagePath(ctx, contentUri, null);
            }
        } else if (TextUtils.equals("content", uri.getScheme())) {
            return analysisImagePath(ctx, uri, null);
        }
        return null;
    }

    private static String analysisImagePath(Context ctx, Uri uri, String selection) {
        String path = null;
        Cursor cursor = ctx.getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
}

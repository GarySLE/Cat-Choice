package com.ys.zy.catchoice.content;

import android.support.annotation.NonNull;

import com.ys.zy.catchoice.constant.DataFlags;
import com.ys.zy.catchoice.model.ImageOption;

/**
 * Created by Ys on 17/3/13.
 * ImageCellContent
 */

public class ImageCellContent implements ICellContent {

    @NonNull private ImageOption mOption;

    public ImageCellContent(@NonNull String imageUri) {
        this.mOption = new ImageOption(imageUri);
    }

    public String getImage() {
        return this.mOption.uri;
    }

    @Override
    public String getStringData(int flag) {
        if (DataFlags.FLAG_IMAGE == flag) {
            return getImage();
        }
        return "";
    }

    @Override
    public int getIntData(int flag) {
        if (DataFlags.FLAG_STAR == flag) {
            return mOption.star;
        }
        return 0;
    }
}

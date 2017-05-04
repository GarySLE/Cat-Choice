package com.ys.zy.catchoice.content;

import android.support.annotation.NonNull;

import com.ys.zy.catchoice.constant.DataFlags;
import com.ys.zy.catchoice.model.TextAndImageOption;

/**
 * Created by Ys on 17/3/13.
 * TextAndImageContent
 */

public class TextAndImageContent implements ICellContent {

    @NonNull
    private TextAndImageOption mTextAndImageOption;

    public TextAndImageContent(@NonNull String text, @NonNull String image) {
        this.mTextAndImageOption = new TextAndImageOption(text, image);
    }

    public String getImage() {
        return this.mTextAndImageOption.getImage();
    }

    public String getTxt() {
        return this.mTextAndImageOption.getTxt();
    }

    @Override
    public String getStringData(int flag) {
        switch (flag) {
            case DataFlags.FLAG_TITLE:
                return getTxt();
            case DataFlags.FLAG_IMAGE:
                return getImage();
            default:
                return "";
        }
    }
}

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
    private TextAndImageOption mOption;

    public TextAndImageContent(@NonNull String text, @NonNull String image) {
        this.mOption = new TextAndImageOption(text, image);
    }

    public TextAndImageContent(@NonNull TextAndImageOption option) {
        this.mOption = option;
    }

    public String getImage() {
        return this.mOption.getImage();
    }

    public String getTxt() {
        return this.mOption.getTxt();
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

    @Override
    public int getIntData(int flag) {
        if (DataFlags.FLAG_STAR == flag) {
            return mOption.star.get();
        }
        return 0;
    }
}

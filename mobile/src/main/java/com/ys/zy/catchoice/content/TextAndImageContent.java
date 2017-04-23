package com.ys.zy.catchoice.content;

import android.support.annotation.NonNull;

import com.ys.zy.catchoice.constant.DataFlag;

/**
 * Created by Ys on 17/3/13.
 * TextAndImageContent
 */

public class TextAndImageContent implements ICellContent {

    @NonNull private ImageCellContent mImage;
    @NonNull private TextCellContent mText;

    public TextAndImageContent(@NonNull String text, @NonNull String image) {
        this.mText = new TextCellContent(text);
        this.mImage = new ImageCellContent(image);
    }

    public String getImage() {
        return this.mImage.getImage();
    }

    public String getText() {
        return this.mText.getText();
    }

    @Override
    public String getStringData(int flag) {
        switch (flag) {
            case DataFlag.FLAG_TITLE:
                return getText();
            case DataFlag.FLAG_IMAGE:
                return getImage();
            default:
                return "";
        }
    }
}

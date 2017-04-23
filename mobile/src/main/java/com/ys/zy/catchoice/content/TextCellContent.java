package com.ys.zy.catchoice.content;

import android.support.annotation.NonNull;

import com.ys.zy.catchoice.constant.DataFlag;
import com.ys.zy.catchoice.model.TextOption;

/**
 * Created by Ys on 17/1/15.
 * TextCellContent
 */

public class TextCellContent implements ICellContent {

    @NonNull private TextOption mOption;

    public TextCellContent(@NonNull String txt) {
        this.mOption = new TextOption(txt);
    }

    public String getText() {
        return this.mOption.mTxt;
    }

    @Override
    public String getStringData(int flag) {
        if (DataFlag.FLAG_TITLE == flag) {
            return getText();
        }
        return "";
    }
}

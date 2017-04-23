package com.ys.zy.catchoice.multiple;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.Serializable;

import com.ys.zy.catchoice.content.ICellContent;

/**
 * Created by Ys on 17/1/2.
 * MultiCell
 */

public class MultiCell implements Serializable {

    @NonNull public ICellContent mContent;
    @Nullable public String mAdditions;

    public MultiCell(@NonNull ICellContent content, @Nullable String additions) {
        this.mContent = content;
        this.mAdditions = additions;
    }

    @Override
    public String toString() {
        return "MultiCell {mContent = " + mContent.toString()
                + ", addtitions = " + mAdditions
                + "}";
    }
}

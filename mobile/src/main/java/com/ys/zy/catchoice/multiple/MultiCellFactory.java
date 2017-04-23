package com.ys.zy.catchoice.multiple;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.ys.zy.catchoice.content.ICellContent;

/**
 * Created by Ys on 17/1/4.
 * MultiCellFactory
 */

public class MultiCellFactory {

    @Nullable public final String mExtra;

    private MultiCellFactory(@Nullable String extra) {
        this.mExtra = extra;
    }

    public MultiCell newCell(@NonNull ICellContent content) {
        return newCell(content, null);
    }

    public MultiCell newCell(@NonNull ICellContent content, @Nullable String additions) {
        return new MultiCell(content, TextUtils.isEmpty(additions) ? mExtra : additions);
    }

    public static class Builder {

        @Nullable private String mExtra;

        public Builder setExtra(@Nullable String extra) {
            if (extra != null)
                this.mExtra = extra;
            return this;
        }

        public MultiCellFactory build() {
            return new MultiCellFactory(mExtra);
        }
    }
}

package com.ys.zy.catchoice.model;

import android.databinding.ObservableField;

/**
 * Created by Ys on 17/3/13.
 * 图形选项
 */

public class ImageOption extends BaseOption {

    public ObservableField<String> uri;

    public ImageOption(String imageUri) {
        super();
        this.uri = new ObservableField<>(imageUri);
    }

    public ImageOption(String imageUri, int star) {
        super(star);
        this.uri = new ObservableField<>(imageUri);
    }

}

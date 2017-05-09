package com.ys.zy.catchoice.model;

/**
 * Created by Ys on 17/3/13.
 * 图形选项
 */

public class ImageOption extends BaseOption {

    public String uri;

    public ImageOption(String imageUri) {
        super();
        this.uri = imageUri;
    }

    public ImageOption(String imageUri, int star) {
        super(star);
        this.uri = imageUri;
    }

}

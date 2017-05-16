package com.ys.zy.catchoice.model;

import android.databinding.ObservableField;

/**
 * Created by Ys on 17/5/1.
 * 图文选项
 */

public class TextAndImageOption extends BaseOption {

    private ObservableField<String> txt;

    private ObservableField<String> image;

    public TextAndImageOption(String txt, String imageUri, int star) {
        super(star);
        this.txt = new ObservableField<>(txt);
        this.image = new ObservableField<>(imageUri);
    }

    public TextAndImageOption(String txt, String imageUri) {
        super();
        this.txt = new ObservableField<>(txt);
        this.image = new ObservableField<>(imageUri);
    }

    public void setTxt(String txt) {
        this.txt.set(txt);
    }

    public void setImage(String image) {
        this.image.set(image);
    }

    public String getTxt() {
        return this.txt.get();
    }

    public String getImage() {
        return this.image.get();
    }
}

package com.ys.zy.catchoice.model;

/**
 * Created by Ys on 17/5/1.
 * 图文选项
 */

public class TextAndImageOption extends BaseOption {

    private String txt;

    private String image;

    public TextAndImageOption(String txt, String imageUri, int star) {
        super(star);
        this.txt = txt;
        this.image = imageUri;
    }

    public TextAndImageOption(String txt, String imageUri) {
        super();
        this.txt = txt;
        this.image = imageUri;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTxt() {
        return this.txt;
    }

    public String getImage() {
        return this.image;
    }
}

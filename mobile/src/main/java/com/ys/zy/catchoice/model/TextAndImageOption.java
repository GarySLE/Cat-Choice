package com.ys.zy.catchoice.model;

/**
 * Created by Ys on 17/5/1.
 * 图文选项
 */

public class TextAndImageOption extends BaseOption {

    private TextOption txt;

    private ImageOption image;

    public TextAndImageOption(String txt, String imageUri) {
        super();
        this.txt = new TextOption(txt);
        this.image = new ImageOption(imageUri);
    }

    public void setTxt(String txt) {
        this.txt.txt = txt;
    }

    public void setImage(String image) {
        this.image.uri = image;
    }

    public String getTxt() {
        return this.txt.txt;
    }

    public String getImage() {
        return this.image.uri;
    }
}

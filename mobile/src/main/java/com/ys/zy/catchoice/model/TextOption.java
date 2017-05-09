package com.ys.zy.catchoice.model;

/**
 * Created by Ys on 17/1/16.
 * 文字选项
 */

public class TextOption extends BaseOption {

    public String txt;

    public TextOption(String txt) {
        super();
        this.txt = txt;
    }

    public TextOption(String txt, int star) {
        super(star);
        this.txt = txt;
    }

}

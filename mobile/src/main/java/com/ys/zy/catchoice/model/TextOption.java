package com.ys.zy.catchoice.model;

import android.databinding.ObservableField;

/**
 * Created by Ys on 17/1/16.
 * 文字选项
 */

public class TextOption extends BaseOption {

    public ObservableField<String> txt;

    public TextOption(String txt) {
        super();
        this.txt = new ObservableField<>(txt);
    }

    public TextOption(String txt, int star) {
        super(star);
        this.txt = new ObservableField<>(txt);
    }

}

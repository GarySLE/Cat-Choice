package com.ys.zy.catchoice.model;

import android.databinding.ObservableField;

import java.io.Serializable;

/**
 * Created by Ys on 17/1/18.
 * BaseOption
 */

public class BaseOption implements Serializable {

    public ObservableField<Integer> star;

    public BaseOption() {
        this.star = new ObservableField<>(0);
    }

    public BaseOption(int star) {
        this.star = new ObservableField<>(star);
    }
}

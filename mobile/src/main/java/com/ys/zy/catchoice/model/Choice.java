package com.ys.zy.catchoice.model;

import android.databinding.ObservableField;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/**
 * Created by Ys on 17/5/12.
 * Choice
 */

public class Choice implements Parcelable {

    private ObservableField<String> title;
    private ObservableField<String> uri;

    public Choice(String title, String uri) {
        this.title = new ObservableField<>(title);
        this.uri = new ObservableField<>(uri);
    }

    protected Choice(Parcel in) {
        title = new ObservableField<>(in.readString());
        uri = new ObservableField<>(in.readString());
    }

    public static final Creator<Choice> CREATOR = new Creator<Choice>() {
        @Override
        public Choice createFromParcel(Parcel in) {
            return new Choice(in);
        }

        @Override
        public Choice[] newArray(int size) {
            return new Choice[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title.get());
        dest.writeString(uri.get());
    }

    public boolean isNoImage() {
        return TextUtils.isEmpty(uri.get());
    }

    public boolean isNoTitle() {
        return TextUtils.isEmpty(title.get());
    }

    public String getTitle() {
        return title.get();
    }

    public String getImage() {
        return uri.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public void setImage(String uri) {
        this.uri.set(uri);
    }
}

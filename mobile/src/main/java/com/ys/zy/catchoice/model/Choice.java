package com.ys.zy.catchoice.model;

import android.databinding.ObservableField;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ys on 17/5/12.
 * Choice
 */

public class Choice implements Parcelable {

    public ObservableField<String> title;
    public ObservableField<String> uri;

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
}

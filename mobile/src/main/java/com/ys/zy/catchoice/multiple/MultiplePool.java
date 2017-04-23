package com.ys.zy.catchoice.multiple;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import com.ys.zy.catchoice.content.ICellContent;
import com.ys.zy.catchoice.provider.MultiCellProvider;

/**
 * Created by Ys on 17/1/2.
 * MultiplePool
 */

public class MultiplePool {

    private static final String TAG = "MultiplePool";

    private List<Class<? extends ICellContent>> mContents = new ArrayList<>();
    private List<MultiCellProvider> mProviders = new ArrayList<>();

    private static MultiplePool sInstance = new MultiplePool();

    private MultiplePool() {
        this.mContents = new ArrayList<>();
        this.mProviders = new ArrayList<>();
    }

    public static MultiplePool getInstance() {
        return sInstance;
    }

    public synchronized void register(@NonNull Class<? extends ICellContent> content,
                                      @NonNull MultiCellProvider provider) {
        if (!mContents.contains(content)) {
            mContents.add(content);
            mProviders.add(provider);
        } else {
            Log.e(TAG, "this type has registered!");
        }
    }

    @NonNull
    public List<Class<? extends ICellContent>> getContents() {
        return mContents;
    }

    public int indexOfContents(Class<? extends ICellContent> c) {
        return mContents.indexOf(c);
    }

    @NonNull
    public List<MultiCellProvider> getProviders() {
        return mProviders;
    }

    public MultiCellProvider getProvider(int index) {
        if (index >= 0 && index < mProviders.size())
            return mProviders.get(index);
        else
            return null;
    }
}

package com.ys.zy.catchoice.ui.widget;

import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Ys on 17/1/18.
 * 空白间距
 */

public class BlankItemDecoration extends RecyclerView.ItemDecoration {

    public static final int HORIZONTAL = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL = LinearLayoutManager.VERTICAL;

    private int mDistance;
    private int mOrientation;

    public BlankItemDecoration(int distance, int orientation) {
        this.mDistance = distance;
        this.mOrientation = orientation;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (VERTICAL == mOrientation)
            outRect.set(0, mDistance, 0, 0);
        else if (HORIZONTAL == mOrientation)
            outRect.set(mDistance, 0, 0, 0);
    }
}

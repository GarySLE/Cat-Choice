package com.ys.zy.catchoice.listener;

import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by Ys on 17/3/9.
 * MultiCell Click Listener in Adapter
 */

public class CellClickListener implements View.OnClickListener {

    @NonNull
    private View view;
    private int position;
    private OnCellClickListener listener;

    public CellClickListener(@NonNull View view, int position, @NonNull OnCellClickListener listener) {
        this.view = view;
        this.position = position;
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        listener.onCellClick(this.view, position);
    }

}

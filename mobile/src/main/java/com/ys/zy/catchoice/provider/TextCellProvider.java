package com.ys.zy.catchoice.provider;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ys.zy.catchoice.R;
import com.ys.zy.catchoice.content.TextCellContent;
import com.ys.zy.catchoice.multiple.MultiCellAdapter;

/**
 * Created by Ys on 17/1/15.
 * TextCellProvider
 */

public class TextCellProvider
        extends MultiCellProvider<TextCellContent, MultiCellAdapter.MultiCellViewHolder> {

    @NonNull
    @Override
    public MultiCellAdapter.MultiCellViewHolder onViewHolderCreate(@NonNull LayoutInflater inflater,
                                                                   @NonNull ViewGroup parent,
                                                                   int type) {
        View view = inflater.inflate(R.layout.cell_text, parent, false);
        return new MultiCellAdapter.MultiCellViewHolder(view, type);
    }
}

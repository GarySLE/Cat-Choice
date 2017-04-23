package com.ys.zy.catchoice.provider;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ys.zy.catchoice.BR;
import com.ys.zy.catchoice.content.ICellContent;
import com.ys.zy.catchoice.multiple.MultiCell;
import com.ys.zy.catchoice.multiple.MultiCellAdapter;

/**
 * Created by Ys on 17/1/2.
 * MultiCellProvider
 */

public abstract class MultiCellProvider<C extends ICellContent, VH extends MultiCellAdapter.MultiCellViewHolder> {

    @NonNull
    public abstract VH onViewHolderCreate(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent,
                                          int type);

    protected void onViewHolderBind(@NonNull VH holder, @NonNull C content, @NonNull MultiCell multiCell) {
        holder.mBinding.setVariable(BR.content, content);
        holder.mBinding.executePendingBindings();
    }

    @SuppressWarnings("unchecked")
    public final void onViewHolderBind(@NonNull VH holder, @NonNull MultiCell multiCell) {
        this.onViewHolderBind(holder, (C) multiCell.mContent, multiCell);
    }
}

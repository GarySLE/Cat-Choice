package com.ys.zy.catchoice.databinding;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.Collection;

/**
 * Created by Ys on 17/1/13.
 * DataBinding Adapter
 */

public abstract class DataBindingAdapter<T, VH extends DataBindingAdapter.ObservableViewHolder>
        extends RecyclerView.Adapter<VH> {

    @NonNull protected Context mCtx;
    @NonNull protected ObservableArrayList<T> mCells;
    protected LayoutInflater mInflater;

    public DataBindingAdapter(@NonNull Context ctx) {
        this.mCtx = ctx;
        this.mCells = new ObservableArrayList<>();
    }

    public DataBindingAdapter(@NonNull Context ctx, @NonNull ObservableArrayList<T> cells) {
        this.mCtx = ctx;
        this.mCells = cells;
    }

    @Override
    public int getItemCount() {
        return mCells.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Nullable
    public T getCell(int index) {
        if (index >= 0 && index < mCells.size()) {
            return mCells.get(index);
        }
        return null;
    }

    @NonNull
    public ObservableArrayList<T> getCells() {
        return mCells;
    }

    public void addCell(@NonNull T cell) {
        this.mCells.add(cell);
    }

    public void removeCell(int index) {
        if (index < 0 || index >= mCells.size()) return;
        this.mCells.remove(index);
    }

    public void clearCells() {
        this.mCells.clear();
    }

    public void addAllCells(@NonNull Collection<T> cells) {
        this.mCells.addAll(cells);
    }

    public boolean isEmpty() {
        return this.mCells.isEmpty();
    }

    public static class ObservableViewHolder<DB extends ViewDataBinding> extends RecyclerView.ViewHolder {

        public DB mBinding;

        public ObservableViewHolder(@NonNull View view) {
            super(view);
            this.mBinding = DataBindingUtil.bind(view);
        }
    }
}

package com.ys.zy.catchoice.multiple;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.support.annotation.NonNull;
import android.support.v4.util.ArraySet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ys.zy.catchoice.R;
import com.ys.zy.catchoice.content.ICellContent;
import com.ys.zy.catchoice.databinding.DataBindingAdapter;
import com.ys.zy.catchoice.listener.CellClickListener;
import com.ys.zy.catchoice.listener.OnCellClickListener;
import com.ys.zy.catchoice.utils.DensityUtil;

/**
 * Created by Ys on 17/1/2.
 * MultiCellAdapter
 */

public class MultiCellAdapter extends DataBindingAdapter<MultiCell, MultiCellAdapter.MultiCellViewHolder>
        implements OnCellClickListener {

    interface OnViewHolderBindingListener {
        void onHolderBinding(ObservableViewHolder holder, int position);
    }

    private boolean isEditable;
    private ArraySet<Integer> mCheckedEditableCells;

    private OnCellClickListener mEditableCellClickListener = new OnCellClickListener() {
        @Override
        public void onCellClick(View view, int position) {
            boolean isExist = mCheckedEditableCells.contains(position);
            if (isExist) {
                mCheckedEditableCells.remove(position);
                uncheckCell(view);
            } else {
                mCheckedEditableCells.add(position);
                checkCell(view);
            }
        }
    };

    private OnCellClickListener mOnCellClickListener;
    private OnViewHolderBindingListener mOnViewHolderBindingListener;

    public MultiCellAdapter(@NonNull Context ctx, @NonNull ObservableArrayList<MultiCell> cells) {
        super(ctx, cells);
        this.mCheckedEditableCells = new ArraySet<>();
    }

    @Override
    public int getItemViewType(int position) {
        ICellContent content = mCells.get(position).mContent;
        return MultiplePool.getInstance().indexOfContents(content.getClass());
    }

    @Override
    public MultiCellViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(parent.getContext());
        }

        return MultiplePool.getInstance().getProvider(viewType)
                .onViewHolderCreate(mInflater, parent, viewType);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(MultiCellViewHolder holder, int position) {
        MultiCell cell = mCells.get(position);

        if (isEditable) {
            if (!mCheckedEditableCells.isEmpty() && mCheckedEditableCells.contains(position)) {
                checkCell(holder.itemView);
            } else {
                uncheckCell(holder.itemView);
            }
        } else {
            uncheckCell(holder.itemView);
        }

        holder.itemView.setOnClickListener(
                new CellClickListener(holder.itemView, position, this));

        if (mOnViewHolderBindingListener != null) {
            mOnViewHolderBindingListener.onHolderBinding(holder, position);
        }

        MultiplePool.getInstance().getProvider(getItemViewType(position))
                .onViewHolderBind(holder, cell);
    }

    public boolean isEditable() {
        return isEditable;
    }

    public void setEditable(boolean editable) {
        this.isEditable = editable;
        if (!isEditable) {
            clearCheckedEditableCells();
        }
        this.notifyDataSetChanged();
    }

    public ArraySet<Integer> getCheckedEditableCells() {
        return mCheckedEditableCells;
    }

    public void clearCheckedEditableCells() {
        this.mCheckedEditableCells.clear();
    }

    public void setOnCellClickListener(OnCellClickListener onCellClickListener) {
        this.mOnCellClickListener = onCellClickListener;
    }

    public void setOnViewHolderBindingListener(OnViewHolderBindingListener onViewHolderBindingListener) {
        this.mOnViewHolderBindingListener = onViewHolderBindingListener;
    }

    private void uncheckCell(@NonNull View v) {
        setCellElevation(v, 0);
        setCheckedViewVisibility(v, View.GONE);
    }

    private void checkCell(@NonNull View v) {
        setCellElevation(v, 8);
        setCheckedViewVisibility(v, View.VISIBLE);
    }

    private void setCheckedViewVisibility(@NonNull View v, int visibility) {
        View checked = v.findViewById(R.id.iv_checked);
        if (checked != null) {
            checked.setVisibility(visibility);
        }
    }

    private void setCellElevation(@NonNull View v, int offsetDip) {
        float defElevation = mCtx.getResources().getDimension(R.dimen.cardview_default_elevation);
        int offsetPx = DensityUtil.dip2px(mCtx, offsetDip);
        v.setElevation(defElevation + offsetPx);
    }

    @Override
    public void onCellClick(View view, int position) {
        if (isEditable) {
            mEditableCellClickListener.onCellClick(view, position);
        } else if (mOnCellClickListener != null) {
            mOnCellClickListener.onCellClick(view, position);
        }
    }

    public static class MultiCellViewHolder extends DataBindingAdapter.ObservableViewHolder {

        public int type;

        public MultiCellViewHolder(@NonNull View view, int type) {
            super(view);
            this.type = type;
        }
    }

}

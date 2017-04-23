package com.ys.zy.catchoice.provider;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.ys.zy.catchoice.GGApp;
import com.ys.zy.catchoice.R;
import com.ys.zy.catchoice.content.ImageCellContent;
import com.ys.zy.catchoice.databinding.CellImageBinding;
import com.ys.zy.catchoice.multiple.MultiCell;
import com.ys.zy.catchoice.multiple.MultiCellAdapter;
import com.ys.zy.catchoice.utils.GlideUtil;

/**
 * Created by Ys on 17/3/13.
 * ImageCellProvider
 */

public class ImageCellProvider
        extends MultiCellProvider<ImageCellContent, MultiCellAdapter.MultiCellViewHolder> {

    @NonNull
    @Override
    public MultiCellAdapter.MultiCellViewHolder onViewHolderCreate(@NonNull LayoutInflater inflater,
                                                                   @NonNull ViewGroup parent,
                                                                   int type) {
        View view = inflater.inflate(R.layout.cell_image, parent, false);
        return new MultiCellAdapter.MultiCellViewHolder(view, type);
    }

    @Override
    protected void onViewHolderBind(@NonNull MultiCellAdapter.MultiCellViewHolder holder,
                                    @NonNull ImageCellContent content, @NonNull MultiCell multiCell) {
        super.onViewHolderBind(holder, content, multiCell);
        if (holder.mBinding instanceof CellImageBinding) {
            GlideUtil.with(GGApp.getApp())
                    .load(content.getImage())
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .centerCrop()
                    .thumbnail(0.1f)
                    .placeholder(R.color.whiteFlower)
                    .into(((CellImageBinding) holder.mBinding).image);
        }
    }
}

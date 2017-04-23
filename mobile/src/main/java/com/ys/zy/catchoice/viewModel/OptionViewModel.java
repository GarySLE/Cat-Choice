package com.ys.zy.catchoice.viewModel;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.ys.zy.catchoice.R;
import com.ys.zy.catchoice.constant.DataFlag;
import com.ys.zy.catchoice.content.ICellContent;
import com.ys.zy.catchoice.databinding.ActivityOptionBinding;
import com.ys.zy.catchoice.multiple.MultiCell;
import com.ys.zy.catchoice.utils.GlideUtil;

/**
 * Created by Ys on 17/4/18.
 * OptionViewModel
 */

public class OptionViewModel extends BaseViewModel {

    public static final String KEY_CELL_CONTENT = "cell_content";

    @NonNull
    private ActivityOptionBinding mBinding;
    private ICellContent mContent;

    public OptionViewModel(@NonNull Activity activity, @NonNull ActivityOptionBinding binding) {
        super(activity);
        this.mBinding = binding;
    }

    public void initContent(Intent intent) {
        if (intent == null) return;
        MultiCell mCell = (MultiCell) intent.getSerializableExtra(KEY_CELL_CONTENT);
        if (mCell != null) {
            this.mContent = mCell.mContent;
        }
        GlideUtil.with(mActivity)
                .load(getStringContent(DataFlag.FLAG_IMAGE))
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .centerCrop()
                .thumbnail(0.1f)
                .placeholder(R.color.whiteFlower)
                .into(mBinding.appBarImage);
    }

    public String getStringContent(int flag, String defaultString) {
        return this.mContent == null ? defaultString : this.mContent.getStringData(flag);
    }

    public String getStringContent(int flag) {
        return getStringContent(flag, "");
    }

    public void setScrollRootTransitionGroup() {
        if (!mBinding.scrollRoot.isTransitionGroup()) {
            mBinding.scrollRoot.setTransitionGroup(true);
        }
    }
}

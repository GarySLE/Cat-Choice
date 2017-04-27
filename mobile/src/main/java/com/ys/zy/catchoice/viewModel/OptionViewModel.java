package com.ys.zy.catchoice.viewModel;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionSet;
import android.view.Gravity;

import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.ys.zy.catchoice.R;
import com.ys.zy.catchoice.constant.DataFlags;
import com.ys.zy.catchoice.constant.DataKeys;
import com.ys.zy.catchoice.content.ICellContent;
import com.ys.zy.catchoice.databinding.ActivityOptionBinding;
import com.ys.zy.catchoice.multiple.MultiCell;
import com.ys.zy.catchoice.utils.GlideUtil;
import com.ys.zy.catchoice.utils.IntentUtil;

/**
 * Created by Ys on 17/4/18.
 * OptionViewModel
 */

public class OptionViewModel extends BaseViewModel {

    @NonNull
    private ActivityOptionBinding mBinding;
    private ICellContent mContent;
    private int mShareElements;

    public OptionViewModel(@NonNull Activity activity, @NonNull ActivityOptionBinding binding) {
        super(activity);
        this.mBinding = binding;
    }

    public void analysisIntent(Intent intent) {
        if (intent == null) return;
        MultiCell mCell = (MultiCell) intent.getSerializableExtra(DataKeys.KEY_CELL_CONTENT);
        if (mCell != null) {
            this.mContent = mCell.mContent;
        }
        this.mShareElements = intent.getIntExtra(DataKeys.KEY_SHARED_ELEMENTS_SETUP, 0);
        GlideUtil.with(mActivity)
                .load(getStringContent(DataFlags.FLAG_IMAGE))
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .fitCenter()
                .thumbnail(0.1f)
                .placeholder(R.mipmap.ic_pets_white_36dp)
                .into(mBinding.appBarImage);
    }

    public void resolveSharedElements(@NonNull TransitionSet set) {
        switch (mShareElements) {
            case DataKeys.SHARED_ELEMENT_TEXT:
                mBinding.titleCard.setTransitionName(
                        getResources().getString(R.string.name_shared_element_text));
                set.addTransition(new Fade()
                        .setDuration(500)
                        .addTarget(mBinding.appBarImage));
                break;
            case DataKeys.SHARED_ELEMENT_IMAGE:
                mBinding.appBarImage.setTransitionName(
                        getResources().getString(R.string.name_shared_element_image));
                set.addTransition(new Slide(Gravity.TOP)
                        .setDuration(500)
                        .addTarget(mBinding.titleCard));
                break;
            case DataKeys.SHARED_ELEMENT_TEXT_AND_IMAGE:
                mBinding.titleCard.setTransitionName(
                        getResources().getString(R.string.name_shared_element_text));
                mBinding.appBarImage.setTransitionName(
                        getResources().getString(R.string.name_shared_element_image));
                break;
        }
    }

    public void onPhotoFabClick() {
        IntentUtil.goToImageContent(this);
    }

    public String getStringContent(int flag, String defaultString) {
        return this.mContent == null ? defaultString : this.mContent.getStringData(flag);
    }

    public String getStringContent(int flag) {
        return getStringContent(flag, "");
    }

    public int getShareElements() {
        return mShareElements;
    }

    public void setShareElements(int mShareElements) {
        this.mShareElements = mShareElements;
    }
}

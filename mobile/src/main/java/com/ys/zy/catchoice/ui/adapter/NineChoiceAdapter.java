package com.ys.zy.catchoice.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ys.zy.catchoice.R;
import com.ys.zy.catchoice.databinding.DataBindingAdapter;
import com.ys.zy.catchoice.databinding.NineChoiceBinding;
import com.ys.zy.catchoice.model.Choice;
import com.ys.zy.catchoice.utils.GlideUtil;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Ys on 17/5/12.
 * Nine Choice Adapter
 */

public class NineChoiceAdapter extends RecyclerView.Adapter<NineChoiceAdapter.NineChoiceViewHolder> {

    @NonNull
    private Context mCtx;
    private LayoutInflater mInflater;
    @NonNull
    private ArrayList<Choice> mList;

    public NineChoiceAdapter(@NonNull Context mCtx) {
        this.mCtx = mCtx;
        this.mList = new ArrayList<>();
    }

    public NineChoiceAdapter(@NonNull Context mCtx, @NonNull ArrayList<Choice> mList) {
        this.mCtx = mCtx;
        this.mList = mList;
    }

    @Override
    public NineChoiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(mCtx);
        }
        View view = mInflater.inflate(R.layout.nine_choice, parent, false);
        return new NineChoiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NineChoiceViewHolder holder, int position) {
        Choice choice = mList.get(position);
        holder.mBinding.setContent(choice);
        if (!choice.isNoImage()) {
            holder.mBinding.title.setTextColor(mCtx.getResources().getColor(R.color.blackTxtPrimary));
            GlideUtil.with(mCtx)
                    .load(choice)
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .centerCrop()
                    .thumbnail(0.1f)
                    .placeholder(R.color.whiteFlower)
                    .into(holder.mBinding.image);
        } else {
            holder.mBinding.title.setTextColor(mCtx.getResources().getColor(R.color.white));
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Nullable
    public Choice getChoice(int position) {
        if (position < 0 && position >= mList.size()) {
            return null;
        } else {
            return mList.get(position);
        }
    }

    public void clear() {
        mList.clear();
    }

    public void add(Choice choice) {
        mList.add(choice);
    }

    public void addAll(Collection<? extends Choice> choices) {
        mList.addAll(choices);
    }

    public void remove(int index) {
        mList.remove(index);
    }

    public void remove(Choice choice) {
        mList.remove(choice);
    }

    class NineChoiceViewHolder extends DataBindingAdapter.ObservableViewHolder<NineChoiceBinding> {

        public NineChoiceViewHolder(View itemView) {
            super(itemView);
        }
    }
}

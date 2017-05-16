package com.ys.zy.catchoice.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ys.zy.catchoice.R;
import com.ys.zy.catchoice.model.Choice;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Ys on 17/5/12.
 * Nine Choice Adapter
 */

public class NineChoiceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @NonNull
    private Context mCtx;
    private LayoutInflater mInflater;
    @NonNull
    private ArrayList<Choice> mList;

    public NineChoiceAdapter(@NonNull Context mCtx, @NonNull ArrayList<Choice> mList) {
        this.mCtx = mCtx;
        this.mList = mList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(parent.getContext());
        }
        View view = mInflater.inflate(R.layout.nine_choice, parent, false);
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
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
}

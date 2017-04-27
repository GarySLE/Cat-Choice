package com.ys.zy.catchoice.multiple;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.databinding.ObservableArrayList;
import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.ViewOutlineProvider;
import android.view.animation.AnimationUtils;

import java.util.Collections;

import com.ys.zy.catchoice.R;
import com.ys.zy.catchoice.utils.DensityUtil;

/**
 * Created by Ys on 17/2/9.
 * cell touch
 */

public class CellTouchCallback extends ItemTouchHelper.Callback {

    private Context mCtx;
    private MultiCellAdapter mAdapter;

    public CellTouchCallback(Context ctx, MultiCellAdapter adapter) {
        this.mCtx = ctx;
        this.mAdapter = adapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlag, swipeFlag;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            dragFlag = ItemTouchHelper.LEFT | ItemTouchHelper.UP | ItemTouchHelper.RIGHT
                    | ItemTouchHelper.DOWN;
            swipeFlag = 0;
        } else {
            dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            swipeFlag = ItemTouchHelper.END;
        }
        return makeMovementFlags(dragFlag, swipeFlag);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int fromP = viewHolder.getAdapterPosition();
        int toP = target.getAdapterPosition();
        ObservableArrayList<MultiCell> cellList = mAdapter.getCells();
        if (fromP < toP) {
            for (int i = fromP; i < toP; i++) {
                Collections.swap(cellList, i, i + 1);
            }
        } else {
            for (int i = fromP; i > toP; i--) {
                Collections.swap(cellList, i, i - 1);
            }
        }
        mAdapter.notifyItemMoved(fromP, toP);
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int p = viewHolder.getAdapterPosition();
        if (ItemTouchHelper.END == direction) {
            mAdapter.removeCell(p);
            mAdapter.notifyItemRemoved(p);
        }
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        if (ItemTouchHelper.ACTION_STATE_DRAG == actionState) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Animator animator = AnimatorInflater.loadAnimator(mCtx, R.animator.touch_up);
                animator.setTarget(viewHolder.itemView);
                animator.start();
            } else {
                viewHolder.itemView.setAlpha(0.5f);
            }
        }
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Animator animator = AnimatorInflater.loadAnimator(mCtx, R.animator.touch_down);
            animator.setTarget(viewHolder.itemView);
            animator.start();
        } else {
            viewHolder.itemView.setAlpha(1.0f);
        }
    }
}

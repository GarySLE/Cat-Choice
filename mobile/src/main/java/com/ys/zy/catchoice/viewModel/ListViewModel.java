package com.ys.zy.catchoice.viewModel;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ys.zy.catchoice.multiple.MultiCellAdapter;

/**
 * Created by Ys on 16/12/30.
 * ListViewModel
 */

public class ListViewModel extends BaseViewModel {

    private RecyclerView.LayoutManager mManager;
    protected MultiCellAdapter mAdapter;

    public ListViewModel(Activity activity) {
        super(activity);
        this.mManager = new LinearLayoutManager(activity);
    }

    public ListViewModel(Activity activity, RecyclerView.LayoutManager manager) {
        super(activity);
        this.mManager = manager;
    }

    public ListViewModel(Activity activity, RecyclerView.LayoutManager manager, MultiCellAdapter adapter) {
        super(activity);
        this.mManager = manager;
        this.mAdapter = adapter;
    }

    public RecyclerView.LayoutManager getManager() {
        return mManager;
    }

    public MultiCellAdapter getAdapter() {
        return mAdapter;
    }
}

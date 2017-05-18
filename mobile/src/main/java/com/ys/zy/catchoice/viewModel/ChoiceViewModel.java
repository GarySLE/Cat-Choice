package com.ys.zy.catchoice.viewModel;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import com.ys.zy.catchoice.databinding.ActivityChoiceBinding;
import com.ys.zy.catchoice.model.Choice;
import com.ys.zy.catchoice.ui.adapter.NineChoiceAdapter;

import java.util.ArrayList;

/**
 * Created by Ys on 17/5/9.
 * Choice ViewModel
 */

public class ChoiceViewModel extends ListViewModel {

    public static final String KEY_CHOICE_LIST = "choice_list";

    private ActivityChoiceBinding mBinding;
    private NineChoiceAdapter mAdapter;

    public ChoiceViewModel(Activity activity, RecyclerView.LayoutManager manager,
                           ActivityChoiceBinding binding) {
        super(activity, manager);
        this.mBinding = binding;
    }

    public void handleIntent(Intent intent) {
        if (intent == null) return;
        // TODO: 17/5/9 handle data from intent
        ArrayList<Choice> choices = intent.getParcelableArrayListExtra(KEY_CHOICE_LIST);
        mAdapter = new NineChoiceAdapter(mActivity, choices);
        mBinding.rvList.setAdapter(mAdapter);
    }
}

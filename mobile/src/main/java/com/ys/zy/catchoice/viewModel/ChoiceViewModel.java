package com.ys.zy.catchoice.viewModel;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import com.ys.zy.catchoice.databinding.ActivityChoiceBinding;

/**
 * Created by Ys on 17/5/9.
 * Choice ViewModel
 */

public class ChoiceViewModel extends ListViewModel {

    private ActivityChoiceBinding mBinding;

    public ChoiceViewModel(Activity activity, RecyclerView.LayoutManager manager,
                           ActivityChoiceBinding binding) {
        super(activity, manager);
        this.mBinding = binding;
    }

    public void handleIntent(Intent intent) {
        if (intent == null) return;
        // TODO: 17/5/9 handle data from intent
    }
}

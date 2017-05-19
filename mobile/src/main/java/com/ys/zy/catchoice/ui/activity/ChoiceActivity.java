package com.ys.zy.catchoice.ui.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import com.ys.zy.catchoice.R;
import com.ys.zy.catchoice.databinding.ActivityChoiceBinding;
import com.ys.zy.catchoice.viewModel.ChoiceViewModel;

/**
 * Created by Ys on 17/05/09.
 * Choice View
 */

public class ChoiceActivity extends AppCompatActivity {

    private ChoiceViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityChoiceBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_choice);
        mViewModel = new ChoiceViewModel(this, new GridLayoutManager(this, 3), binding);
        mViewModel.handleIntent(getIntent());
        binding.setViewModel(mViewModel);
    }
}

package com.ys.zy.catchoice.ui.activity;

import android.animation.Animator;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;

import com.ys.zy.catchoice.R;
import com.ys.zy.catchoice.databinding.ActivityOptionBinding;
import com.ys.zy.catchoice.viewModel.OptionViewModel;

/**
 * Created by Ys on 17/04/16.
 * Single Option View
 */

public class OptionActivity extends BaseActivity {

    private OptionViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityOptionBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_option);
        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mViewModel = new OptionViewModel(this, binding);
        mViewModel.analysisIntent(getIntent());
        binding.setViewModel(mViewModel);

        Window window = getWindow();
        window.setEnterTransition(initEnterTransition());
        window.setSharedElementEnterTransition(initSharedElementEnterTransition(binding.appBarImage));
        window.setReturnTransition(initReturnTransition());
    }

    private Transition initEnterTransition() {
        TransitionSet set = new TransitionSet();
        Transition fadeEnter = TransitionInflater.from(this).inflateTransition(R.transition.enter_fade);
        set.addTransition(fadeEnter);
        // add transition when no uri view shared element or no text view shared element.
        mViewModel.resolveSharedElements(set);
        return set;
    }

    private Transition initReturnTransition() {
        return TransitionInflater.from(this).inflateTransition(R.transition.return_slide);
    }

    private Transition initSharedElementEnterTransition(final View view) {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.share_bounds_arc);
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                Animator animator = ViewAnimationUtils.createCircularReveal(view,
                        view.getWidth() >> 1, view.getHeight() >> 1,
                        view.getWidth() >> 1, Math.max(view.getWidth(), view.getHeight()));
                animator.setDuration(500);
                animator.start();
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
        return transition;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

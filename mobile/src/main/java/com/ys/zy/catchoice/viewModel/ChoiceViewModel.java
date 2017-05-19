package com.ys.zy.catchoice.viewModel;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;

import com.ys.zy.catchoice.R;
import com.ys.zy.catchoice.databinding.ActivityChoiceBinding;
import com.ys.zy.catchoice.listener.OnCellClickListener;
import com.ys.zy.catchoice.model.Choice;
import com.ys.zy.catchoice.ui.adapter.NineChoiceAdapter;
import com.ys.zy.catchoice.ui.widget.SwitchCardView;

import java.util.ArrayList;

/**
 * Created by Ys on 17/5/9.
 * Choice ViewModel
 */

public class ChoiceViewModel extends ListViewModel implements OnCellClickListener {

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
        mAdapter.setOnCellClickListener(this);
        mBinding.rvList.setAdapter(mAdapter);
    }

    @Override
    public void onCellClick(View view, int position) {
//        if (view instanceof SwitchCardView) {
//            ((SwitchCardView) view).switchCard();
//        }

        animFlipCard(view);
    }

    private void animFlipCard(final View view) {
        Transition transition = TransitionInflater.from(mActivity)
                .inflateTransition(R.transition.explode_slide_change);
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                if (view instanceof SwitchCardView) {
                    final SwitchCardView switchCard = (SwitchCardView) view;
                    AnimatorSet out = (AnimatorSet) AnimatorInflater
                            .loadAnimator(mActivity, R.animator.card_rotation_out);
                    AnimatorSet in = (AnimatorSet) AnimatorInflater
                            .loadAnimator(mActivity, R.animator.card_rotation_in);
                    out.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            switchCard.setClickable(false);
                        }
                    });
                    in.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            switchCard.switchCard();
                        }
                    });
                    switchCard.setSwitchAnimator(out, in);
                    switchCard.setRotationY(true);
                    switchCard.startAnimSwitch();
                }
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
        TransitionManager.beginDelayedTransition(mBinding.root, transition);

        changeSize(view);
        changeLayout(view);
        changeVisibility(mBinding.rvList);
        view.setVisibility(View.VISIBLE);
    }

    private void changeVisibility(RecyclerView list) {
        for (int i = 0, c = list.getChildCount(); i < c; i++) {
            list.getChildAt(i).setVisibility(View.GONE);
        }
    }

    private void changeSize(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup.LayoutParams lp = view.getLayoutParams();
            lp.width *= 1.5;
            lp.height *= 1.5;
            view.setLayoutParams(lp);
            view.requestLayout();
            ViewGroup root = (ViewGroup) view;
            for (int i = 0, c = root.getChildCount(); i < c; i++) {
                View v = root.getChildAt(i);
                changeSize(v);
            }
        }
    }

    private void changeLayout(View view) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        int listHalfWidth = mBinding.rvList.getWidth() >> 1;
        float x = listHalfWidth - (lp.width >> 1);
        float y = listHalfWidth - (lp.height >> 1);
        view.setX(x);
        view.setY(y);
    }
}

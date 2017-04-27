package com.ys.zy.catchoice.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.ys.zy.catchoice.R;

/**
 * Created by Ys on 17/4/26.
 * 触摸事件切换视图的CardView
 */

public class SwitchCardView extends CardView {

    public static final int EVENT_TYPE_CLICK = 0;
    public static final int EVENT_TYPE_TOUCH = 1;

    public interface OnViewSwitchListener {

        void onViewSwitch(boolean isCover, View coverView, View realView);
    }

    public interface OnSwitchTouchListener {

        void onSwitchTouch(View coverView, View realView, MotionEvent event);
    }

    private View mCoverView;
    private View mRealView;
    private int mSwitchEventType;
    private int mEventClickViewId;
    private int mEventBackViewId;

    private OnViewSwitchListener mViewSwitchListener;
    private OnSwitchTouchListener mSwitchTouchListener;

    public SwitchCardView(@NonNull Context context) {
        super(context);
    }

    public SwitchCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwitchCardView(@NonNull Context context, @Nullable AttributeSet attrs,
                          @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initSwitchLayout(context, attrs, defStyleAttr);
    }

    private void initSwitchLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SwitchCardView,
                defStyleAttr, R.style.Card);

        mSwitchEventType = a.getInt(R.styleable.SwitchCardView_eventType, EVENT_TYPE_CLICK);
        mEventClickViewId = a.getResourceId(R.styleable.SwitchCardView_eventClickViewId, 0);
        mEventBackViewId = a.getResourceId(R.styleable.SwitchCardView_eventBackViewId, 0);

        a.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int count = getChildCount();
        if (count != 2) {
            throw new IllegalArgumentException("Must be only two childs in SwitchCardView!");
        }
        mRealView = getChildAt(0);
        mCoverView = getChildAt(1);

        switch (mSwitchEventType) {
            case EVENT_TYPE_CLICK:
                initCoverClick();
                initRealBackClick();
                break;
            case EVENT_TYPE_TOUCH:
                // TODO: 17/4/26 more preset gesture
                break;
        }
        mRealView.setVisibility(GONE);
        mCoverView.setVisibility(VISIBLE);
        mCoverView.bringToFront();
    }

    private void initRealBackClick() {
        View backClickView = null;
        EventBackClickListener listener = new EventBackClickListener();
        if (mEventBackViewId > 0) {
            backClickView = mRealView.findViewById(mEventBackViewId);
        }
        if (backClickView != null) {
            backClickView.setOnClickListener(listener);
        } else {
            mRealView.setOnClickListener(listener);
        }
    }

    private void initCoverClick() {
        View clickView = null;
        EventClickListener listener = new EventClickListener();
        if (mEventClickViewId > 0) {
            clickView = mCoverView.findViewById(mEventClickViewId);
        }
        if (clickView != null) {
            clickView.setOnClickListener(listener);
        } else {
            mCoverView.setOnClickListener(listener);
        }
    }

    public void setViewSwitchListener(OnViewSwitchListener viewSwitchListener) {
        this.mViewSwitchListener = viewSwitchListener;
    }

    public void setSwitchTouchListener(OnSwitchTouchListener switchTouchListener) {
        this.mSwitchTouchListener = switchTouchListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean flag = super.onTouchEvent(event);
        if (mSwitchTouchListener != null) {
            mSwitchTouchListener.onSwitchTouch(mCoverView, mRealView, event);
        }
        return flag;
    }

    private class EventClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            mCoverView.setVisibility(GONE);
            mRealView.setVisibility(VISIBLE);
            mRealView.bringToFront();

            if (mViewSwitchListener != null) {
                mViewSwitchListener.onViewSwitch(false, mCoverView, mRealView);
            }
        }
    }

    private class EventBackClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            mRealView.setVisibility(GONE);
            mCoverView.setVisibility(VISIBLE);
            mCoverView.bringToFront();

            if (mViewSwitchListener != null) {
                mViewSwitchListener.onViewSwitch(true, mCoverView, mRealView);
            }
        }
    }
}

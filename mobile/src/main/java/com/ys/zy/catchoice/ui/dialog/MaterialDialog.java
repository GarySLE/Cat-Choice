package com.ys.zy.catchoice.ui.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ys.zy.catchoice.R;
import com.ys.zy.catchoice.utils.SystemUtil;
import com.ys.zy.catchoice.utils.ViewUtil;

/**
 * Created by Ys on 17/1/25.
 * MaterialDialog
 */

public class MaterialDialog {

    @NonNull private Context mCtx;
    private AlertDialog mDialog;

    private TextView mTitleView;
    private TextView mMessageView;
    private ViewGroup mMessageContentRoot;
    private LinearLayout mButtonRoot;
    private Button mPositiveButton;
    private Button mNegativeButton;

    public MaterialDialog(@NonNull Context ctx) {
        this.mCtx = ctx;
    }

    public static class Builder {

        @NonNull Context mCtx;
        Window mDialogWindow;
        View mView;
        View mContentView;

        int mTitleResId;
        int mMessageResId;
        int mBackgroundResId;
        int mContentViewResId;
        int mPositiveTxtId;
        int mNegativeTxtId;
        CharSequence mPositiveTxt, mNegativeTxt;
        boolean isCanceledOnTouchOutside;

        CharSequence mTitle;
        CharSequence mMessage;
        Drawable mBackgroundDrawable;

        DialogInterface.OnDismissListener mOnDismissListener;
        View.OnClickListener mPositiveButtonListener, mNegativeButtonListener;

        public Builder(@NonNull Context ctx) {
            this.mCtx = ctx;
        }

        public MaterialDialog create() {
            MaterialDialog materialDialog = new MaterialDialog(mCtx);
            materialDialog.mDialog = new AlertDialog.Builder(mCtx).create();
            materialDialog.mDialog.show();

            mDialogWindow = materialDialog.mDialog.getWindow();
            mDialogWindow.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                    | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
            mDialogWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MASK_STATE);
            mDialogWindow.setBackgroundDrawableResource(R.drawable.bg_dialog_window);

            View contentView = LayoutInflater.from(mCtx).inflate(R.layout.dialog_material, null);
            contentView.setFocusable(true);
            contentView.setFocusableInTouchMode(true);
            mDialogWindow.setContentView(contentView);

            materialDialog.mMessageContentRoot = (ViewGroup) mDialogWindow.findViewById(
                    R.id.root_message_content);
            materialDialog.mButtonRoot = (LinearLayout) mDialogWindow.findViewById(
                    R.id.root_button);
            materialDialog.mTitleView = (TextView) mDialogWindow.findViewById(R.id.title);
            materialDialog.mMessageView = (TextView) mDialogWindow.findViewById(R.id.message);
            materialDialog.mPositiveButton = (Button) materialDialog.mButtonRoot.findViewById(R.id.position_button);
            materialDialog.mNegativeButton = (Button) materialDialog.mButtonRoot.findViewById(R.id.negative_button);

            if (mView != null) {
                LinearLayout contentRoot = (LinearLayout) mDialogWindow.findViewById(R.id.contentView);
                contentRoot.removeAllViews();
                contentRoot.addView(mView);
            }

            if (TextUtils.isEmpty(mTitle) && mTitleResId == 0) {
                materialDialog.mTitleView.setVisibility(View.GONE);
            } else {
                if (mTitleResId != 0)
                    materialDialog.setTitle(mTitleResId);
                else if (!TextUtils.isEmpty(mTitle))
                    materialDialog.setTitle(mTitle);
            }

            if (mMessageResId != 0)
                materialDialog.setMessage(mMessageResId);
            else if (!TextUtils.isEmpty(mMessage))
                materialDialog.setMessage(mMessage);

            if (TextUtils.isEmpty(mPositiveTxt) && mPositiveTxtId == 0) {
                materialDialog.mPositiveButton.setVisibility(View.GONE);
            } else {
                if (mPositiveTxtId != 0)
                    materialDialog.setPositiveButton(mPositiveTxtId, mPositiveButtonListener);
                else if (!TextUtils.isEmpty(mPositiveTxt))
                    materialDialog.setPositiveButton(mPositiveTxt, mPositiveButtonListener);
            }

            if (TextUtils.isEmpty(mNegativeTxt) && mNegativeTxtId == 0) {
                materialDialog.mNegativeButton.setVisibility(View.GONE);
            } else {
                if (mNegativeTxtId != 0)
                    materialDialog.setNegativeButton(mNegativeTxtId, mNegativeButtonListener);
                else if (!TextUtils.isEmpty(mNegativeTxt))
                    materialDialog.setNegativeButton(mNegativeTxt, mNegativeButtonListener);
            }

            if (mBackgroundResId != 0) {
                LinearLayout container = (LinearLayout) mDialogWindow.findViewById(R.id.container);
                container.setBackgroundResource(mBackgroundResId);
            } else if (mBackgroundDrawable != null) {
                LinearLayout container = (LinearLayout) mDialogWindow.findViewById(R.id.container);
                if (SystemUtil.isJellyBean())
                    container.setBackground(mBackgroundDrawable);
                else
                    container.setBackgroundDrawable(mBackgroundDrawable);
            }

            if (mContentView != null)
                materialDialog.setMessageContentView(mContentView);
            else if (mContentViewResId != 0)
                materialDialog.setMessageContentView(mContentViewResId);

            materialDialog.mDialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside);
            materialDialog.mDialog.setCancelable(isCanceledOnTouchOutside);
            if (mOnDismissListener != null)
                materialDialog.mDialog.setOnDismissListener(mOnDismissListener);

            return materialDialog;
        }

        public Builder setView(View view) {
            this.mView = view;
            return this;
        }

        public Builder setContentView(View contentView) {
            this.mContentView = contentView;
            return this;
        }

        public Builder setPositiveButton(int resId, View.OnClickListener listener) {
            this.mPositiveTxtId = resId;
            this.mPositiveButtonListener = listener;
            return this;
        }

        public Builder setNegativeButton(int resId, View.OnClickListener listener) {
            this.mNegativeTxtId = resId;
            this.mNegativeButtonListener = listener;
            return this;
        }

        public Builder setTitle(int resId) {
            this.mTitleResId = resId;
            return this;
        }

        public Builder setMessage(int resId) {
            this.mMessageResId = resId;
            return this;
        }

        public Builder setBackgroundResId(int resId) {
            this.mBackgroundResId = resId;
            return this;
        }

        public Builder setContentView(int resId) {
            this.mContentViewResId = resId;
            return this;
        }

        public Builder setPositiveButton(CharSequence pTxt, View.OnClickListener listener) {
            this.mPositiveTxt = pTxt;
            this.mPositiveButtonListener = listener;
            return this;
        }

        public Builder setNegativeButton(CharSequence nTxt, View.OnClickListener listener) {
            this.mNegativeTxt = nTxt;
            this.mNegativeButtonListener = listener;
            return this;
        }

        public Builder setTitle(CharSequence title) {
            this.mTitle = title;
            return this;
        }

        public Builder setMessage(CharSequence message) {
            this.mMessage = message;
            return this;
        }

        public Builder setBackgroundDrawable(Drawable backgroundDrawable) {
            this.mBackgroundDrawable = backgroundDrawable;
            return this;
        }

        public Builder setOnDismissListener(DialogInterface.OnDismissListener mOnDismissListener) {
            this.mOnDismissListener = mOnDismissListener;
            return this;
        }

        public Builder setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
            this.isCanceledOnTouchOutside = canceledOnTouchOutside;
            return this;
        }
    }

    public void show() {
        mDialog.show();
    }

    public void dismiss() {
        mDialog.dismiss();
    }

    public void setMessageContentView(int layoutId) {
        mMessageContentRoot.removeAllViews();
        LayoutInflater.from(mCtx).inflate(layoutId, mMessageContentRoot);
    }

    public void setMessageContentView(View contentView) {
        if (mDialog == null || mDialog.getWindow() == null) return;
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        contentView.setLayoutParams(lp);
        if (contentView instanceof ListView)
            ViewUtil.setListViewHeightByChildren((ListView) contentView);

        Window dialogWindow = mDialog.getWindow();
        LinearLayout messageContent = (LinearLayout) dialogWindow.findViewById(R.id.message_content);
        if (messageContent != null) {
            messageContent.removeAllViews();
            messageContent.addView(contentView);
            for (int i = 0, c = messageContent.getChildCount(); i < c; i++) {
                if (messageContent.getChildAt(i) instanceof AutoCompleteTextView) {
                    AutoCompleteTextView autoText = (AutoCompleteTextView) messageContent.getChildAt(i);
                    autoText.setFocusable(true);
                    autoText.setFocusableInTouchMode(true);
                    autoText.requestFocus();
                }
            }
        }
    }

    public View getMessageContentById(int resId) {
        return mMessageContentRoot.findViewById(resId);
    }

    public void setPositiveButton(int resId, View.OnClickListener listener) {
        setPositiveButton(mCtx.getResources().getString(resId), listener);
    }

    public void setPositiveButton(CharSequence positiveTxt, View.OnClickListener listener) {
        if (mPositiveButton == null) return;
        mPositiveButton.setText(positiveTxt);
        mPositiveButton.setOnClickListener(listener);
        if (SystemUtil.isLollipop())
            mPositiveButton.setElevation(0);
        mPositiveButton.setVisibility(View.VISIBLE);
    }

    public void setNegativeButton(int resId, View.OnClickListener listener) {
        setNegativeButton(mCtx.getResources().getString(resId), listener);
    }

    public void setNegativeButton(CharSequence negativeTxt, View.OnClickListener listener) {
        if (mNegativeButton == null) return;
        mNegativeButton.setText(negativeTxt);
        mNegativeButton.setOnClickListener(listener);
        if (SystemUtil.isLollipop())
            mNegativeButton.setElevation(0);
        mNegativeButton.setVisibility(View.VISIBLE);
    }

    public void setMessage(CharSequence message) {
        if (mMessageView == null) return;
        mMessageView.setText(message);
    }

    public void setMessage(int resId) {
        if (mMessageView == null) return;
        mMessageView.setText(resId);
    }

    public void setTitle(CharSequence title) {
        if (mTitleView == null) return;
        mTitleView.setText(title);
    }

    public void setTitle(int resId) {
        if (mTitleView == null) return;
        mTitleView.setText(resId);
    }

}

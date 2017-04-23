package com.ys.zy.catchoice.viewModel;

import android.app.Activity;
import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.ArraySet;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ys.zy.catchoice.R;
import com.ys.zy.catchoice.content.ICellContent;
import com.ys.zy.catchoice.content.ImageCellContent;
import com.ys.zy.catchoice.content.TextAndImageContent;
import com.ys.zy.catchoice.content.TextCellContent;
import com.ys.zy.catchoice.databinding.ActivityMainBinding;
import com.ys.zy.catchoice.listener.OnCellClickListener;
import com.ys.zy.catchoice.multiple.CellTouchCallback;
import com.ys.zy.catchoice.multiple.MultiCell;
import com.ys.zy.catchoice.multiple.MultiCellAdapter;
import com.ys.zy.catchoice.multiple.MultiCellFactory;
import com.ys.zy.catchoice.multiple.MultiplePool;
import com.ys.zy.catchoice.provider.ImageCellProvider;
import com.ys.zy.catchoice.provider.TextAndImageCellProvider;
import com.ys.zy.catchoice.provider.TextCellProvider;
import com.ys.zy.catchoice.ui.activity.MainActivity;
import com.ys.zy.catchoice.ui.activity.OptionActivity;
import com.ys.zy.catchoice.ui.dialog.MaterialDialog;
import com.ys.zy.catchoice.ui.widget.BlankItemDecoration;
import com.ys.zy.catchoice.utils.DensityUtil;
import com.ys.zy.catchoice.utils.GlideUtil;
import com.ys.zy.catchoice.utils.NumberUtil;
import com.ys.zy.catchoice.utils.PhotoUtil;

import java.util.List;

/**
 * Created by Ys on 16/12/30.
 * MainViewModel
 */

public class MainViewModel extends ListViewModel implements OnCellClickListener {

    private ActivityMainBinding mBinding;
    private Handler mHandler;
    private MaterialDialog mDialog;

    public MainViewModel(Activity activity, ActivityMainBinding binding) {
        super(activity);
        this.mBinding = binding;
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    public void initList() {
        // 吃货测试列表
        MultiCellFactory factory = new MultiCellFactory.Builder().build();
        ObservableArrayList<MultiCell> contents = new ObservableArrayList<>();

        String[] foods = mActivity.getResources().getStringArray(R.array.list_eat);
        for (String food : foods)
            contents.add(factory.newCell(new TextCellContent(food), null));

        MultiplePool pool = MultiplePool.getInstance();
        pool.register(TextCellContent.class, new TextCellProvider());
        pool.register(ImageCellContent.class, new ImageCellProvider());
        pool.register(TextAndImageContent.class, new TextAndImageCellProvider());

        mAdapter = new MultiCellAdapter(mActivity, contents);
        mAdapter.setOnCellClickListener(this);

        RecyclerView rvList = mBinding.content.rvList;
        rvList.setAdapter(mAdapter);
        rvList.addItemDecoration(new BlankItemDecoration(DensityUtil.dip2px(mActivity, 10),
                BlankItemDecoration.VERTICAL));
        ItemTouchHelper touchHelper = new ItemTouchHelper(new CellTouchCallback(mActivity, mAdapter));
        touchHelper.attachToRecyclerView(rvList);
        // TODO: 17/2/9 Item Animator 
//        rvList.setItemAnimator();

    }

    public void onRandomFabClick() {
        closeEditable();

        List<MultiCell> cells = mAdapter.getCells();
        if (cells.isEmpty()) return;
        int index = NumberUtil.randomInt(0, cells.size());
        ICellContent content = cells.get(index).mContent;
        if (content instanceof TextCellContent) {
            mDialog = new MaterialDialog.Builder(mActivity)
                    .setMessage(((TextCellContent) content).getText())
                    .setCanceledOnTouchOutside(true)
                    .setPositiveButton(R.string.ok, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mDialog.dismiss();
                        }
                    })
                    .create();
        }
    }

    public void onAddFabClick() {
        closeEditable();

        View contentView = LayoutInflater.from(mActivity).inflate(R.layout.content_simple_edit, null);
        final ImageView imageView = (ImageView) contentView.findViewById(R.id.edit_image);
        final EditText editText = (EditText) contentView.findViewById(R.id.edit_text);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, MainActivity.REQUEST_ALBUM);
            }
        });
        mDialog = new MaterialDialog.Builder(mActivity)
                .setTitle(R.string.add_new_option_text)
                .setContentView(contentView)
                .setCanceledOnTouchOutside(true)
                .setPositiveButton(R.string.add_button, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String txt = editText.getText().toString().trim();
                        String image = imageView.getTag() == null ? "" : imageView.getTag().toString();
                        boolean hasTxt = !TextUtils.isEmpty(txt);
                        boolean hasImage = !TextUtils.isEmpty(image);
                        if (!hasTxt && !hasImage) return;

                        MultiCell cell;
                        MultiCellFactory factory = new MultiCellFactory.Builder().build();
                        if (hasTxt && hasImage) {
                            cell = factory.newCell(new TextAndImageContent(txt, image));
                        } else if (hasImage) {
                            cell = factory.newCell(new ImageCellContent(image));
                        } else {
                            cell = factory.newCell(new TextCellContent(txt));
                        }
                        mAdapter.addCell(cell);
                        mDialog.dismiss();
                    }
                })
                .create();
    }

    private void closeEditable() {
        if (mAdapter.isEditable()) {
            mAdapter.setEditable(false);
        }
    }

    /**
     * 已选图片的数据处理
     *
     * @param data Extras
     */
    public void onImageSelected(Intent data) {
        String path = PhotoUtil.disposeImage(mActivity, data);
        if (!TextUtils.isEmpty(path)) {
            ImageView imageView = (ImageView) mDialog.getMessageContentById(R.id.edit_image);
            GlideUtil.with(mActivity)
                    .load(path)
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .centerCrop()
                    .thumbnail(0.1f)
                    .placeholder(R.color.whiteFlower)
                    .into(imageView);
            imageView.setTag(path);
        }
    }

    public void onRemoveFabClick(@NonNull View v) {
        if (mAdapter.isEmpty()) return;

        if (!mAdapter.isEditable()) {
            mAdapter.setEditable(true);
            setDeleteFabState(v, R.mipmap.ic_delete_forever_white_18dp, DensityUtil.dip2px(mActivity, 10f));
        } else {
            ArraySet<Integer> array = mAdapter.getCheckedEditableCells();
            for (int p : array) {
                mAdapter.removeCell(p);
            }
            mAdapter.clearCheckedEditableCells();
            mAdapter.setEditable(false);
            setDeleteFabState(v, R.mipmap.ic_delete_white_18dp, -DensityUtil.dip2px(mActivity, 10f));
        }
    }

    private void setDeleteFabState(@NonNull View v, int resId, float elevation) {
        FloatingActionButton fab = (FloatingActionButton) v;
        fab.setImageResource(resId);
        fab.setCompatElevation(fab.getCompatElevation() + elevation);
    }

    public void saveDate() {
    }

    @Override
    public void onCellClick(View view, int position) {
        MultiCell cell = mAdapter.getCell(position);
        if (cell == null) return;

        View text = view.findViewById(R.id.text);
        View image = view.findViewById(R.id.image);
        ActivityOptionsCompat activityOptionsCompat = null;
        if (text != null && image != null) {
            activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity,
                    new Pair<>(text, getResources().getString(R.string.name_share_element_text)),
                    new Pair<>(image, getResources().getString(R.string.name_share_element_image)));
        } else if (text != null) {
            activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    mActivity, text, getResources().getString(R.string.name_share_element_text));
        } else if (image != null) {
            activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    mActivity, image, getResources().getString(R.string.name_share_element_image));
        }

        Bundle extras = activityOptionsCompat != null ? activityOptionsCompat.toBundle() : new Bundle();
        startActivity(new Intent(mActivity, OptionActivity.class)
                .putExtra(OptionViewModel.KEY_CELL_CONTENT, cell), extras);
    }
}

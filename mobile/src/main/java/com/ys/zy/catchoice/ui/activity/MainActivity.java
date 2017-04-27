package com.ys.zy.catchoice.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.util.Log;

import com.ys.zy.catchoice.R;
import com.ys.zy.catchoice.databinding.ActivityMainBinding;
import com.ys.zy.catchoice.multiple.MultiCell;
import com.ys.zy.catchoice.viewModel.MainViewModel;

import static com.ys.zy.catchoice.utils.IntentUtil.REQUEST_ALBUM;

/**
 * Created by Ys on 16/12/28.
 * MainView
 */

public class MainActivity extends BaseActivity {

    MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mViewModel = new MainViewModel(this, binding);
        binding.setViewModel(mViewModel);

        setSupportActionBar(binding.toolbar);

        mViewModel.initList();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("request_code = ", String.valueOf(requestCode));
        if (resultCode != RESULT_OK) return;

        switch (requestCode) {
            case REQUEST_ALBUM:
                mViewModel.onImageSelected(data);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("data", mViewModel.getAdapter().getCells());
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            ObservableArrayList<MultiCell> data = (ObservableArrayList<MultiCell>) savedInstanceState
                    .getSerializable("data");
            if (data != null) {
                mViewModel.getAdapter().addAllCells(data);
            }
        }
    }
}

package com.github.easyview.demo.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModel;
import androidx.viewbinding.ViewBinding;

/**
 * 封装 DialogFragment 的基类
 */
public abstract class BaseFragmentDialog<VB extends ViewBinding,VM extends ViewModel> extends DialogFragment {
    protected VB viewBinding;
    protected VM viewModel;
    private float widthDensity = 1.0f;
    private float heightDensity = 1.0f;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = getViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 去掉 dialog 标题栏
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        viewBinding = getViewBinding(inflater,container);
        return viewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView();
        bindObserver();
        initData();
    }

    @Override
    public void onStart() {
        super.onStart();

        // 设置对话框大小
        Dialog dialog = getDialog();
        dialog.setCancelable(false);

        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();

        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);

        lp.width = (int) (point.x*widthDensity);
        lp.height = (int) (point.y*heightDensity);
        dialog.getWindow().setAttributes(lp);
    }

    /**
     * 设置对话框的宽度与屏幕宽度的百分比
     * @param widthDensity
     */
    protected void setWidthDensity(float widthDensity) {
        this.widthDensity = widthDensity;
    }

    /**
     * 设置对话框的高度与屏幕高度的百分比
     * @param heightDensity
     */
    protected void setHeightDensity(float heightDensity) {
        this.heightDensity = heightDensity;
    }

    protected abstract VM getViewModel();

    protected abstract VB getViewBinding(LayoutInflater inflater, ViewGroup container);

    protected abstract void initView();

    protected abstract void bindObserver();

    protected abstract void initData();

}

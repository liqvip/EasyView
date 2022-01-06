package com.github.easyview.demo.base;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.viewbinding.ViewBinding;

/**
 * 封装 Activity 的基类
 */
public abstract class BaseActivity<VB extends ViewBinding,VM extends ViewModel> extends AppCompatActivity {
    protected VB viewBinding;
    protected VM viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = getViewModel();
        viewBinding = getViewBinding(getLayoutInflater());
        setContentView(viewBinding.getRoot());

        initView();
        bindObserver();
        initData();
    }

    protected abstract VM getViewModel();

    protected abstract VB getViewBinding(LayoutInflater inflater);

    protected abstract void initView();

    protected abstract void bindObserver();

    protected abstract void initData();

}

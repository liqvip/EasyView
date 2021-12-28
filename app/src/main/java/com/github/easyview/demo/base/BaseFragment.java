package com.github.easyview.demo.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.viewbinding.ViewBinding;

/**
 * 封装 Fragment 的基类
 */
public abstract class BaseFragment<VB extends ViewBinding,VM extends ViewModel> extends Fragment {
    protected VB viewBinding;
    protected VM viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = getViewModel();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewBinding = getViewBinding(inflater,container);
        return viewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView();
        bindObserver();
        initData();
    }

    protected abstract VM getViewModel();

    protected abstract VB getViewBinding(LayoutInflater inflater, ViewGroup container);

    protected abstract void initView();

    protected abstract void bindObserver();

    protected abstract void initData();

}

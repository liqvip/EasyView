package com.github.easyview.demo

import android.view.LayoutInflater
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModel
import com.github.easyview.demo.base.BaseActivity
import com.github.easyview.demo.databinding.ActivityDemoBinding

/**
 * @author: Little Bei
 * @Date: 2022/1/6
 */
class DemoActivity: BaseActivity<ActivityDemoBinding, ViewModel>() {
    override fun getViewModel(): ViewModel? {
        return null
    }

    override fun getViewBinding(inflater: LayoutInflater): ActivityDemoBinding {
        return ActivityDemoBinding.inflate(inflater)
    }

    override fun initView() {
    }

    override fun bindObserver() {
    }

    override fun initData() {

    }
}
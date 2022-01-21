package com.github.easyview.demo

import android.view.LayoutInflater
import androidx.lifecycle.ViewModel
import com.github.easyview.demo.base.BaseActivity
import com.github.easyview.demo.databinding.ActivityEasyIndicatorProgressBarBinding
import com.github.easyview.demo.databinding.ActivityEasyTickMarkProgressBarBinding

/**
 * @author: Little Bei
 * @Date: 2022/1/21
 */

class EasyIndicatorProgressBarActivity: BaseActivity<ActivityEasyIndicatorProgressBarBinding, ViewModel>() {
    override fun getViewModel(): ViewModel? {
        return null
    }

    override fun getViewBinding(inflater: LayoutInflater): ActivityEasyIndicatorProgressBarBinding {
        return ActivityEasyIndicatorProgressBarBinding.inflate(inflater)
    }

    override fun initView() {

    }

    override fun bindObserver() {
    }

    override fun initData() {
    }
}
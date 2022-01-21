package com.github.easyview.demo

import android.view.LayoutInflater
import androidx.lifecycle.ViewModel
import com.github.easyview.demo.base.BaseActivity
import com.github.easyview.demo.databinding.ActivityEasyTickMarkProgressBarBinding

/**
 * @author: Little Bei
 * @Date: 2021/12/31
 */
class EasyTickMarkProgressBarActivity: BaseActivity<ActivityEasyTickMarkProgressBarBinding, ViewModel>() {
    override fun getViewModel(): ViewModel? {
        return null
    }

    override fun getViewBinding(inflater: LayoutInflater): ActivityEasyTickMarkProgressBarBinding {
        return ActivityEasyTickMarkProgressBarBinding.inflate(inflater)
    }

    override fun initView() {

    }

    override fun bindObserver() {
    }

    override fun initData() {
    }
}
package com.github.easyview.demo

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModel
import com.github.easyview.demo.base.BaseActivity
import com.github.easyview.demo.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, ViewModel>(), View.OnClickListener {

    override fun getViewModel(): ViewModel? {
        return null
    }

    override fun getViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }

    override fun initView() {
        viewBinding.btDemo.setOnClickListener(this)
        viewBinding.btRoundCorner.setOnClickListener(this)
        viewBinding.btTextViewGroup.setOnClickListener(this)
        viewBinding.btEasyTickMarkProgressBar.setOnClickListener(this)
        viewBinding.btEasyIndicatorProgressBar.setOnClickListener(this)
    }

    override fun bindObserver() {
    }

    override fun initData() {
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.bt_demo -> {
                startActivity(Intent(this, DemoActivity::class.java))
            }
            R.id.bt_round_corner -> {
                startActivity(Intent(this, RoundCornerActivity::class.java))
            }
            R.id.bt_text_view_group -> {
                startActivity(Intent(this, EasyTextViewGroupActivity::class.java))
            }
            R.id.bt_easyTickMarkProgressBar -> {
                startActivity(Intent(this, EasyTickMarkProgressBarActivity::class.java))
            }
            R.id.bt_easyIndicatorProgressBar -> {
                startActivity(Intent(this, EasyIndicatorProgressBarActivity::class.java))
            }
        }
    }
}
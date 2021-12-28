package com.github.easyview.demo

import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModel
import com.github.easyview.demo.base.BaseActivity
import com.github.easyview.demo.databinding.ActivityEasyTextViewGroupBinding


/**
 * UI for for the EasyTextViewGroup.
 * @author: Little Bei
 * @Date: 2021/12/27
 */
class EasyTextViewGroupActivity: BaseActivity<ActivityEasyTextViewGroupBinding, ViewModel>(), View.OnClickListener {
    override fun getViewModel(): ViewModel? {
        return null
    }

    override fun getViewBinding(inflater: LayoutInflater): ActivityEasyTextViewGroupBinding {
        return ActivityEasyTextViewGroupBinding.inflate(inflater)
    }

    override fun initView() {
        viewBinding.tvGroup.setChildrenText(arrayOf("托儿索", "杰斯", "鳄鱼"))
        viewBinding.tvGroup.setChildrenBgColor(arrayOf("#4C6D99", "#39A977", "#FF9134"))
        viewBinding.tvGroup.setChildrenTextSize(30)

        viewBinding.btHide1.setOnClickListener(this)
        viewBinding.btHide2.setOnClickListener(this)
        viewBinding.btHide3.setOnClickListener(this)

        viewBinding.btShow1.setOnClickListener(this)
        viewBinding.btShow2.setOnClickListener(this)
        viewBinding.btShow3.setOnClickListener(this)
    }

    override fun bindObserver() {
    }

    override fun initData() {

    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.bt_hide1 -> {
                viewBinding.tvGroup.hide(0)
            }
            R.id.bt_hide2 -> {
                viewBinding.tvGroup.hide(1)
            }
            R.id.bt_hide3 -> {
                viewBinding.tvGroup.hide(2)
            }
            R.id.bt_show1 -> {
                viewBinding.tvGroup.show(0)
            }
            R.id.bt_show2 -> {
                viewBinding.tvGroup.show(1)
            }
            R.id.bt_show3 -> {
                viewBinding.tvGroup.show(2)
            }
        }
    }
}
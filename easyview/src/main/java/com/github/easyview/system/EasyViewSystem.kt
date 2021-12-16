package com.github.easyview.system

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet

/**
 * @author: Little Bei
 * @Date: 2021/12/13
 */
interface EasyViewSystem {
    fun init(context: Context, attributeSet: AttributeSet?)

    fun onSizeChanged(width: Int, height: Int)

    fun draw(canvas: Canvas)
}
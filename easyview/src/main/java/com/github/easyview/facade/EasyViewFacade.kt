package com.github.easyview.facade

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

/**
 * @author: Little Bei
 * @Date: 2021/12/13
 */

interface EasyViewFacade {
    fun init(context: Context, view: View, attrSet: AttributeSet?)

    fun onSizeChanged(width: Int, height: Int)

    fun preDraw(canvas: Canvas)

    fun onDraw(canvas: Canvas)

    fun afterDraw(canvas: Canvas)

    fun setRadius(dp: Float)

    fun setRadius(topLeftDp: Float, topRightDp: Float, bottomRightDp: Float, bottomLeftDp: Float)

    fun setLeftRadius(dp: Float)

    fun setTopRadius(dp: Float)

    fun setRightRadius(dp: Float)

    fun setBottomRadius(dp: Float)

    fun setTopLeftRadius(dp: Float)

    fun setTopRightRadius(dp: Float)

    fun setBottomRightRadius(dp: Float)

    fun setBottomLeftRadius(dp: Float)

    fun setStrokeWidth(dp: Float)

    fun setStrokeColor(color: Int)

    fun setStrokeWidthColor(widthDp: Float, color: Int)
}
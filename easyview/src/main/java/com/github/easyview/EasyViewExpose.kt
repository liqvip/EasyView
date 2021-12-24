package com.github.easyview

/**
 * @author: Little Bei
 * @Date: 2021/12/13
 */
interface EasyViewExpose {

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

    fun setStrokeColor(argb: String)

    fun setStrokeWidthColor(widthDp: Float, color: Int)

}
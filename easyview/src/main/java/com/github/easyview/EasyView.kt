package com.github.easyview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import com.github.easyview.facade.EasyViewFacadeImpl

/**
 * @author: Little Bei
 * @Date: 2021/12/13
 */
class EasyView(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) :
        View(context, attrs, defStyleAttr, defStyleRes), EasyViewExpose {

    private val easyViewFacadeImpl = EasyViewFacadeImpl()

    constructor(context: Context): this(context, null)
    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): this(context, attrs, defStyleAttr, 0){
        easyViewFacadeImpl.init(context, this, attrs)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        easyViewFacadeImpl.onSizeChanged(w, h)
    }

    override fun draw(canvas: Canvas) {
        easyViewFacadeImpl.preDraw(canvas)
        super.draw(canvas)
        easyViewFacadeImpl.onDraw(canvas)
    }

    override fun setRadius(dp: Float) {
        easyViewFacadeImpl.setRadius(dp)
    }

    override fun setRadius(topLeftDp: Float, topRightDp: Float, bottomRightDp: Float, bottomLeftDp: Float) {
        easyViewFacadeImpl.setRadius(topLeftDp, topRightDp, bottomRightDp, bottomLeftDp)
    }

    override fun setLeftRadius(dp: Float) {
        easyViewFacadeImpl.setLeftRadius(dp)
    }

    override fun setTopRadius(dp: Float) {
        easyViewFacadeImpl.setTopRadius(dp)
    }

    override fun setRightRadius(dp: Float) {
        easyViewFacadeImpl.setRightRadius(dp)
    }

    override fun setBottomRadius(dp: Float) {
        easyViewFacadeImpl.setBottomRadius(dp)
    }

    override fun setTopLeftRadius(dp: Float) {
        easyViewFacadeImpl.setTopLeftRadius(dp)
    }

    override fun setTopRightRadius(dp: Float) {
        easyViewFacadeImpl.setTopRightRadius(dp)
    }

    override fun setBottomRightRadius(dp: Float) {
        easyViewFacadeImpl.setBottomRightRadius(dp)
    }

    override fun setBottomLeftRadius(dp: Float) {
        easyViewFacadeImpl.setBottomLeftRadius(dp)
    }

    override fun setStrokeWidth(dp: Float) {
        easyViewFacadeImpl.setStrokeWidth(dp)
    }

    override fun setStrokeColor(color: Int) {
        easyViewFacadeImpl.setStrokeColor(color)
    }

    override fun setStrokeColor(argb: String) {
        easyViewFacadeImpl.setStrokeColor(Color.parseColor(argb))
    }

    override fun setStrokeWidthColor(widthDp: Float, color: Int) {
        easyViewFacadeImpl.setStrokeWidthColor(widthDp, color)
    }


}
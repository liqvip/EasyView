package com.github.easyview.facade

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import com.github.easyview.system.RoundSystem
import com.github.easyview.system.StrokeSystem
import com.github.easyview.util.dip2px

/**
 * @author: Little Bei
 * @Date: 2021/12/13
 */

class EasyViewFacadeImpl: BaseEasyViewFacade() {
    private lateinit var context: Context
    private lateinit var view: View

    private var width = 0
    private var height = 0

    // System
    private val roundSystem = RoundSystem()
    private val strokeSystem = StrokeSystem()

    override fun init(context: Context, view: View, attrSet: AttributeSet?) {
        /**
         * Note: ViewGroup is special, setWillNotDraw(false) so that ViewGroup can call #draw(Canvas canvas) method.
         */
        if (view is ViewGroup && view.background == null){
            view.setWillNotDraw(false)
        }

        this.context = context
        this.view = view

        roundSystem.init(context, attrSet)
        strokeSystem.init(context, attrSet)
    }

    override fun preDraw(canvas: Canvas) {
        canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), null)
    }

    override fun onDraw(canvas: Canvas) {
        roundSystem.draw(canvas)
        strokeSystem.draw(canvas)
    }

    override fun onSizeChanged(width: Int, height: Int) {
        this.width  = width
        this.height = height

        roundSystem.onSizeChanged(width, height)
        strokeSystem.onSizeChanged(width, height)
    }


    override fun setRadius(dp: Float) {
        roundSystem.apply {
            val px = dip2px(context,dp)
            topLeftRadius = px
            topRightRadius = px
            bottomRightRadius = px
            bottomLeftRadius = px
        }
        roundSystem.onRadiusChanged()
        strokeSystem.apply {
            val px = dip2px(context,dp)
            topLeftRadius = px
            topRightRadius = px
            bottomRightRadius = px
            bottomLeftRadius = px
        }
        strokeSystem.onRadiusChanged()
        view.invalidate()
    }

    override fun setRadius(topLeftDp: Float, topRightDp: Float, bottomRightDp: Float, bottomLeftDp: Float) {
        roundSystem.apply {
            topLeftRadius = dip2px(context, topLeftDp)
            topRightRadius = dip2px(context, topRightDp)
            bottomRightRadius = dip2px(context, bottomRightDp)
            bottomLeftRadius = dip2px(context, bottomLeftDp)
        }
        roundSystem.onRadiusChanged()
        strokeSystem.apply {
            topLeftRadius = dip2px(context, topLeftDp)
            topRightRadius = dip2px(context, topRightDp)
            bottomRightRadius = dip2px(context, bottomRightDp)
            bottomLeftRadius = dip2px(context, bottomLeftDp)
        }
        strokeSystem.onRadiusChanged()
        view.invalidate()
    }

    override fun setLeftRadius(dp: Float) {
        roundSystem.apply {
            topLeftRadius = dip2px(context, dp)
            bottomLeftRadius = dip2px(context, dp)
        }
        roundSystem.onRadiusChanged()
        strokeSystem.apply {
            topLeftRadius = dip2px(context, dp)
            bottomLeftRadius = dip2px(context, dp)
        }
        strokeSystem.onRadiusChanged()
        view.invalidate()
    }

    override fun setTopRadius(dp: Float) {
        roundSystem.apply {
            topLeftRadius = dip2px(context, dp)
            topRightRadius = dip2px(context, dp)
        }
        roundSystem.onRadiusChanged()
        strokeSystem.apply {
            topLeftRadius = dip2px(context, dp)
            topRightRadius = dip2px(context, dp)
        }
        strokeSystem.onRadiusChanged()
        view.invalidate()
    }

    override fun setRightRadius(dp: Float) {
        roundSystem.apply {
            topRightRadius = dip2px(context, dp)
            bottomRightRadius = dip2px(context, dp)
        }
        roundSystem.onRadiusChanged()
        strokeSystem.apply {
            topRightRadius = dip2px(context, dp)
            bottomRightRadius = dip2px(context, dp)
        }
        strokeSystem.onRadiusChanged()
        view.invalidate()
    }

    override fun setBottomRadius(dp: Float) {
        roundSystem.apply {
            bottomRightRadius = dip2px(context, dp)
            bottomLeftRadius = dip2px(context, dp)
        }
        roundSystem.onRadiusChanged()
        strokeSystem.apply {
            bottomRightRadius = dip2px(context, dp)
            bottomLeftRadius = dip2px(context, dp)
        }
        strokeSystem.onRadiusChanged()
        view.invalidate()
    }

    override fun setTopLeftRadius(dp: Float) {
        roundSystem.apply {
            topLeftRadius = dip2px(context, dp)
        }
        roundSystem.onRadiusChanged()
        strokeSystem.apply {
            topLeftRadius = dip2px(context, dp)
        }
        strokeSystem.onRadiusChanged()
        view.invalidate()
    }

    override fun setTopRightRadius(dp: Float) {
        roundSystem.apply {
            topRightRadius = dip2px(context, dp)
        }
        roundSystem.onRadiusChanged()
        strokeSystem.apply {
            topRightRadius = dip2px(context, dp)
        }
        strokeSystem.onRadiusChanged()
        view.invalidate()
    }

    override fun setBottomRightRadius(dp: Float) {
        roundSystem.apply {
            bottomRightRadius = dip2px(context, dp)
        }
        roundSystem.onRadiusChanged()
        strokeSystem.apply {
            bottomRightRadius = dip2px(context, dp)
        }
        strokeSystem.onRadiusChanged()
        view.invalidate()
    }

    override fun setBottomLeftRadius(dp: Float) {
        roundSystem.apply {
            bottomLeftRadius = dip2px(context, dp)
        }
        roundSystem.onRadiusChanged()
        strokeSystem.apply {
            bottomLeftRadius = dip2px(context, dp)
        }
        strokeSystem.onRadiusChanged()
        view.invalidate()
    }

    override fun setStrokeWidth(dp: Float) {
        strokeSystem.apply {
            strokeWidth = dip2px(context, dp)
        }
        view.invalidate()
    }

    override fun setStrokeColor(color: Int) {
        strokeSystem.apply {
            strokeColor = color
        }
        view.invalidate()
    }

    override fun setStrokeWidthColor(widthDp: Float, color: Int) {
        strokeSystem.apply {
            strokeWidth = dip2px(context, widthDp)
            strokeColor = color
        }
        view.invalidate()
    }
}
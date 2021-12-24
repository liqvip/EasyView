package com.github.easyview.system

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import com.github.easyview.R

/**
 * @author: Little Bei
 * @Date: 2021/12/13
 */
class RoundSystem: EasyViewSystem {
    var radius = 0f
    var topLeftRadius = 0f
    var topRightRadius = 0f
    var bottomRightRadius = 0f
    var bottomLeftRadius = 0f
    var strokeWidth = 0f

    private val outerRectF = RectF()
    private val innerRectF = RectF()
    private val roundRadii = floatArrayOf(0f,0f,0f,0f,0f,0f,0f,0f)

    private val outerPath = Path()
    private val innerPath = Path()

    private val paint = Paint()
    private val xFerMode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)


    override fun init(context: Context, attributeSet: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.EasyView)

        radius = typedArray.getDimension(R.styleable.EasyView_ev_radius, radius)
        val leftRadius = typedArray.getDimension(R.styleable.EasyView_ev_left_radius, radius)
        val topRadius = typedArray.getDimension(R.styleable.EasyView_ev_top_radius, radius)
        val rightRadius = typedArray.getDimension(R.styleable.EasyView_ev_right_radius, radius)
        val bottomRadius = typedArray.getDimension(R.styleable.EasyView_ev_bottom_radius, radius)

        topLeftRadius = typedArray.getDimension(R.styleable.EasyView_ev_topLeft_radius, if (topRadius > 0) topRadius else leftRadius)
        topRightRadius = typedArray.getDimension(R.styleable.EasyView_ev_topRight_radius, if (topRadius > 0) topRadius else rightRadius)
        bottomRightRadius = typedArray.getDimension(R.styleable.EasyView_ev_bottomRight_radius, if (bottomRadius > 0) bottomRadius else rightRadius)
        bottomLeftRadius = typedArray.getDimension(R.styleable.EasyView_ev_bottomLeft_radius, if (bottomRadius > 0) bottomRadius else leftRadius)

        strokeWidth = typedArray.getDimension(R.styleable.EasyView_ev_stroke_width, strokeWidth)

        onRadiusChanged()

        typedArray.recycle()
    }

    fun onRadiusChanged() {
        roundRadii[0] = topLeftRadius - strokeWidth
        roundRadii[1] = topLeftRadius - strokeWidth
        roundRadii[2] = topRightRadius - strokeWidth
        roundRadii[3] = topRightRadius - strokeWidth
        roundRadii[4] = bottomRightRadius - strokeWidth
        roundRadii[5] = bottomRightRadius - strokeWidth
        roundRadii[6] = bottomLeftRadius - strokeWidth
        roundRadii[7] = bottomLeftRadius - strokeWidth
    }

    override fun onSizeChanged(width: Int, height: Int) {
        outerRectF.set(0f, 0f, width.toFloat(), height.toFloat())
        innerRectF.set(strokeWidth, strokeWidth, width.toFloat()-strokeWidth, height.toFloat()-strokeWidth)
    }

    override fun draw(canvas: Canvas) {
        if (topLeftRadius == 0f && topRightRadius == 0f && bottomRightRadius == 0f && bottomLeftRadius == 0f)
            return

        paint.reset()
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL
        paint.xfermode = xFerMode

        outerPath.reset()
        innerPath.reset()
        outerPath.addRect(outerRectF, Path.Direction.CCW)
        innerPath.addRoundRect(innerRectF, roundRadii, Path.Direction.CCW)
        outerPath.op(innerPath, Path.Op.DIFFERENCE)
        canvas.drawPath(outerPath,paint)
        paint.xfermode = null
        canvas.restore()
    }

}
package com.github.easyview.system

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import com.github.easyview.R

/**
 * @author: Little Bei
 * @Date: 2021/12/14
 */
class StrokeSystem: EasyViewSystem {
    var radius = 0f
    var topLeftRadius = 0f
    var topRightRadius = 0f
    var bottomRightRadius = 0f
    var bottomLeftRadius = 0f
    var strokeWidth = 0f
    var strokeColor = Color.WHITE

    private val rectF = RectF()
    private val strokeRadii = floatArrayOf(0f,0f,0f,0f,0f,0f,0f,0f)
    private val path = Path()

    private val paint = Paint()

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
        strokeColor = typedArray.getColor(R.styleable.EasyView_ev_stroke_color, strokeColor)

        onRadiusChanged()

        typedArray.recycle()
    }

    override fun onSizeChanged(width: Int, height: Int) {
        rectF.set(strokeWidth/2, strokeWidth/2, width.toFloat()-strokeWidth/2, height.toFloat()-strokeWidth/2)
    }

    fun onRadiusChanged() {
        strokeRadii[0] = topLeftRadius - strokeWidth / 2
        strokeRadii[1] = topLeftRadius - strokeWidth / 2
        strokeRadii[2] = topRightRadius - strokeWidth / 2
        strokeRadii[3] = topRightRadius - strokeWidth / 2
        strokeRadii[4] = bottomRightRadius - strokeWidth / 2
        strokeRadii[5] = bottomRightRadius - strokeWidth / 2
        strokeRadii[6] = bottomLeftRadius - strokeWidth / 2
        strokeRadii[7] = bottomLeftRadius - strokeWidth / 2
    }

    override fun draw(canvas: Canvas) {
        if(strokeWidth > 0){
            paint.reset()
            paint.isAntiAlias = true
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = strokeWidth
            paint.color = strokeColor

            path.reset()
            path.addRoundRect(rectF, strokeRadii, Path.Direction.CCW)
            canvas.drawPath(path, paint)
        }
    }

}
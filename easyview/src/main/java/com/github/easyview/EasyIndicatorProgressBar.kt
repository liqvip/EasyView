package com.github.easyview

/**
 * @author: Little Bei
 * @Date: 2022/1/21
 */

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.widget.ProgressBar
import kotlin.math.pow
import kotlin.math.sqrt

class EasyIndicatorProgressBar(context: Context, attrs: AttributeSet?): ProgressBar(context, attrs) {
    private var spanWidth = 0.0f
    private var spanHeight = 0.0f
    private var circleRadius = 0.0f

    // TickMark
    private var tickMarkRadius = 10.0f
    private var tickMarkColor = Color.BLACK
    private var tickMarkActiveColor = Color.BLUE
    private var tickMarkOverlayRadius = 0.0f
    private var tickMarkOverlayColor = Color.WHITE
    private val tickMarkPaint: Paint = Paint()
    private val tickMarkActivePaint: Paint = Paint()
    private val tickMarkOverlayPaint: Paint = Paint()

    // Indicator
    var indicatorText = "0/0"
    private var indicatorIntervalBetweenTickMark = 4.0f
    private var indicatorRectWidth = 80.0f
    private var indicatorRectHeight = 40.0f
    private var indicatorRectRadius = 40.0f
    private var indicatorTriangleSideLength = 16.0f
    private var indicatorTextColor = Color.WHITE
    private var indicatorTextSize = 24.0f
    private var indicatorBackgroundColor = Color.BLACK
    private val indicatorPaint: Paint = Paint()
    private val indicatorTextPaint: Paint = Paint()
    private val indicatorTrianglePath = Path()
    private val indicatorRectPath = Path()

    companion object {
        private var TAG = EasyIndicatorProgressBar::class.java.simpleName
    }

    constructor(context: Context): this(context, null)

    // Init custom attrs.
    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.EasyIndicatorProgressBar)
        try {
            tickMarkRadius = typedArray.getDimension(R.styleable.EasyIndicatorProgressBar_tickMark_radius, tickMarkRadius)
            tickMarkColor = typedArray.getColor(R.styleable.EasyIndicatorProgressBar_tickMark_color, tickMarkColor)
            tickMarkActiveColor = typedArray.getColor(R.styleable.EasyIndicatorProgressBar_tickMark_activeColor, tickMarkActiveColor)
            tickMarkOverlayRadius = typedArray.getDimension(R.styleable.EasyIndicatorProgressBar_tickMark_overlayRadius, tickMarkOverlayRadius)
            tickMarkOverlayColor = typedArray.getColor(R.styleable.EasyIndicatorProgressBar_tickMark_overlayColor, tickMarkOverlayColor)

            indicatorIntervalBetweenTickMark = typedArray.getDimension(R.styleable.EasyIndicatorProgressBar_indicator_interval_betweenTickMark, indicatorIntervalBetweenTickMark)
            indicatorRectWidth = typedArray.getDimension(R.styleable.EasyIndicatorProgressBar_indicator_rectWidth, indicatorRectWidth)
            indicatorRectHeight = typedArray.getDimension(R.styleable.EasyIndicatorProgressBar_indicator_rectHeight, indicatorRectHeight)
            indicatorRectRadius  = typedArray.getDimension(R.styleable.EasyIndicatorProgressBar_indicator_rectRadius, indicatorRectRadius)
            indicatorTriangleSideLength = typedArray.getDimension(R.styleable.EasyIndicatorProgressBar_indicator_triangle_sideLength, indicatorTriangleSideLength)
            indicatorTextColor = typedArray.getColor(R.styleable.EasyIndicatorProgressBar_indicator_textColor, indicatorTextColor)
            indicatorTextSize = typedArray.getDimension(R.styleable.EasyIndicatorProgressBar_indicator_textSize, indicatorTextSize)
            indicatorBackgroundColor = typedArray.getColor(R.styleable.EasyIndicatorProgressBar_indicator_backgroundColor, indicatorBackgroundColor)
        } catch (e: Exception) {
            throw Exception(e)
        } finally {
            typedArray.recycle()
        }

        indicatorText = "$progress/$max"
        checkSpace()
    }

    /**
     * Ensure the most left and most right tickMark or indicator have enough space to draw.
     */
    private fun checkSpace() {
        circleRadius = tickMarkRadius.coerceAtLeast(tickMarkOverlayRadius)
        val rectHalfWidth = indicatorRectWidth / 2 + 1
        val needPadding = circleRadius.coerceAtLeast(rectHalfWidth)
        if (paddingLeft < needPadding) {
            setPadding(needPadding.toInt(), paddingTop, paddingRight, paddingBottom)
        }
        if (paddingRight < needPadding) {
            setPadding(paddingLeft, paddingTop, needPadding.toInt(), paddingBottom)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        spanWidth = if(max <= 1){
            (w - paddingLeft - paddingRight).toFloat()
        }else{
            (w - paddingLeft - paddingRight).toFloat() / max
        }

        spanHeight = (h - paddingTop - paddingBottom).toFloat()

        Log.i(TAG, "onSizeChanged: spanWidth = $spanWidth, spanHeight = $spanHeight, paddingLeft = $paddingLeft, paddingRight = $paddingRight")

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        tickMarkPaint.reset()
        tickMarkPaint.flags = Paint.ANTI_ALIAS_FLAG
        tickMarkPaint.color = tickMarkColor

        tickMarkActivePaint.reset()
        tickMarkActivePaint.flags = Paint.ANTI_ALIAS_FLAG
        tickMarkActivePaint.color = tickMarkActiveColor

        tickMarkOverlayPaint.reset()
        tickMarkOverlayPaint.flags = Paint.ANTI_ALIAS_FLAG
        tickMarkOverlayPaint.color = tickMarkOverlayColor

        indicatorPaint.reset()
        indicatorPaint.flags = Paint.ANTI_ALIAS_FLAG
        indicatorPaint.color = indicatorBackgroundColor

        indicatorTextPaint.reset()
        indicatorTextPaint.flags = Paint.ANTI_ALIAS_FLAG
        indicatorTextPaint.color = indicatorTextColor
        indicatorTextPaint.textSize = indicatorTextSize
        indicatorTextPaint.textAlign = Paint.Align.CENTER

        // Step 1
        drawTickMark(canvas)
    }


    /**
     * Draw tickMark, default shape is circle.
     */
    private fun drawTickMark(canvas: Canvas) {
        if(tickMarkRadius > 0){
            var cx = paddingLeft.toFloat()
            val cy = spanHeight / 2 + paddingTop
            for (i in 0..max){
                canvas.drawCircle(cx, cy, tickMarkRadius, tickMarkPaint)
                cx += spanWidth
            }
            // Step 2
            drawTickMarkOverlay(canvas)
        }else{
            Log.e(TAG, "tickMarkRadius can't less than 0: $tickMarkRadius")
        }
    }

    /**
     * Draw tickMarkOverlay.
     */
    private fun drawTickMarkOverlay(canvas: Canvas) {
        if(progress >= 0){
            val cx = paddingLeft + progress*spanWidth
            val cy = spanHeight / 2 + paddingTop
            if(tickMarkOverlayRadius > tickMarkRadius){
                canvas.drawCircle(cx, cy, tickMarkOverlayRadius, tickMarkOverlayPaint)
            }
            // Step 3
            drawTickMarkActive(canvas)
            // Step 4
            drawIndicator(canvas)
        }
    }

    /**
     *  Draw active tickMark.
     */
    private fun drawTickMarkActive(canvas: Canvas) {
        var cx = paddingLeft.toFloat()
        val cy = spanHeight / 2 + paddingTop
        for (i in 0..progress){
            canvas.drawCircle(cx, cy, tickMarkRadius, tickMarkActivePaint)
            cx += spanWidth
        }
    }

    /**
     * Draw indicator.
     */
    private fun drawIndicator(canvas: Canvas) {
        // 1. Draw triangle.
        indicatorTrianglePath.reset()
        val startX = paddingStart + spanWidth*progress
        val startY = paddingTop + spanHeight/2 - circleRadius - indicatorIntervalBetweenTickMark
        indicatorTrianglePath.moveTo(startX, startY)
        indicatorTrianglePath.lineTo(startX + indicatorTriangleSideLength / 2, startY - getIndicatorTriangleHeight().toFloat())
        indicatorTrianglePath.rLineTo(-indicatorTriangleSideLength, 0.0f)
        indicatorTrianglePath.close()
        canvas.drawPath(indicatorTrianglePath, indicatorPaint)

        // 2. Draw rect.
        indicatorRectPath.reset()
        val left = paddingStart + spanWidth*progress - indicatorRectWidth / 2
        val top = paddingTop + spanHeight/2 - circleRadius - indicatorIntervalBetweenTickMark -
                getIndicatorTriangleHeight().toFloat() - indicatorRectHeight
        val right = paddingStart + spanWidth*progress + indicatorRectWidth / 2
        val bottom = paddingTop + spanHeight/2 - circleRadius - indicatorIntervalBetweenTickMark - getIndicatorTriangleHeight().toFloat() + 1

        val rectF = RectF(left, top, right, bottom)
        indicatorRectPath.addRoundRect(rectF, indicatorRectRadius, indicatorRectRadius, Path.Direction.CCW)
        canvas.drawPath(indicatorRectPath, indicatorPaint)

        // 3. Draw indicator text.
        val textBounds = Rect()
        indicatorTextPaint.getTextBounds(indicatorText, 0, indicatorText.length, textBounds)
        val x = paddingStart + spanWidth*progress
        val y = paddingTop + spanHeight/2 - circleRadius - indicatorIntervalBetweenTickMark -
                getIndicatorTriangleHeight().toFloat() - indicatorRectHeight /2 + textBounds.height()/2
        canvas.drawText(indicatorText, x, y, indicatorTextPaint)
    }

    private fun getIndicatorTriangleHeight(): Double {
        return sqrt(indicatorTriangleSideLength.toDouble().pow(2.0) - (indicatorTriangleSideLength / 2.toDouble()).pow(2.0))
    }
}
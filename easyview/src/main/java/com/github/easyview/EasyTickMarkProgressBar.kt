package com.github.easyview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.widget.ProgressBar

/**
 * @author: Little Bei
 * @Date: 2022/1/21
 */

class EasyTickMarkProgressBar(context: Context, attrs: AttributeSet?) : ProgressBar(context, attrs) {
    private var tickMarkCount = 0
    private var tickMarkWidth = 1.0f
    private var tickMarkColor = Color.BLACK
    private var tickMarkText: Array<CharSequence>? = null
    private var tickMarkTextSize = 24.0f
    private var tickMarkTextColor = Color.WHITE
    private var tickMarkNextText: Array<CharSequence>? = null
    private var tickMarkNextTextSize = 20.0f
    private var tickMarkNextTextColor = Color.WHITE
    private var spanWidth = 0.0f
    private var spanHeight = 0.0f
    private var tickMarkPaint = Paint()
    private var tickMarkTextPaint = Paint()
    private var tickMarkNextTextPaint = Paint()

    companion object {
        private val TAG = EasyTickMarkProgressBar::class.java.simpleName
    }

    constructor(context: Context) : this(context, null)

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.EasyTickMarkProgressBar)
        try {
            tickMarkColor = typedArray.getColor(R.styleable.EasyTickMarkProgressBar_tickMark_color, tickMarkColor)
            tickMarkWidth = typedArray.getDimension(R.styleable.EasyTickMarkProgressBar_tickMark_width, tickMarkWidth)
            tickMarkText = typedArray.getTextArray(R.styleable.EasyTickMarkProgressBar_tickMark_text)
            tickMarkTextSize = typedArray.getDimension(R.styleable.EasyTickMarkProgressBar_tickMark_textSize, tickMarkTextSize)
            tickMarkTextColor = typedArray.getColor(R.styleable.EasyTickMarkProgressBar_tickMark_textColor, tickMarkTextColor)
            tickMarkNextText = typedArray.getTextArray(R.styleable.EasyTickMarkProgressBar_tickMark_nextText)
            tickMarkNextTextSize = typedArray.getDimension(R.styleable.EasyTickMarkProgressBar_tickMark_nextTextSize, tickMarkNextTextSize)
            tickMarkNextTextColor = typedArray.getColor(R.styleable.EasyTickMarkProgressBar_tickMark_nextTextColor, tickMarkNextTextColor)
        } finally {
            typedArray.recycle()
        }

        if (tickMarkText != null && tickMarkText!!.isNotEmpty()) {
            max = tickMarkText!!.size
            tickMarkCount = tickMarkText!!.size - 1
            Log.i(TAG, "init: tickMarkCount = $tickMarkCount")
        }

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        tickMarkPaint.reset()
        tickMarkPaint.flags = Paint.ANTI_ALIAS_FLAG
        tickMarkPaint.style = Paint.Style.STROKE
        tickMarkPaint.strokeWidth = tickMarkWidth
        tickMarkPaint.color = tickMarkColor

        tickMarkTextPaint.reset()
        tickMarkTextPaint.flags = Paint.ANTI_ALIAS_FLAG
        tickMarkTextPaint.strokeWidth = 1.0f
        tickMarkTextPaint.color = tickMarkTextColor
        tickMarkTextPaint.textSize = tickMarkTextSize
        tickMarkTextPaint.textAlign = Paint.Align.CENTER

        tickMarkNextTextPaint.reset()
        tickMarkNextTextPaint.flags = Paint.ANTI_ALIAS_FLAG
        tickMarkNextTextPaint.strokeWidth = 1.0f
        tickMarkNextTextPaint.color = tickMarkNextTextColor
        tickMarkNextTextPaint.textSize = tickMarkNextTextSize
        tickMarkNextTextPaint.textAlign = Paint.Align.CENTER

        if (tickMarkCount != 0) {
            drawTickMark(canvas)
        }
        if (tickMarkText != null && tickMarkText!!.isNotEmpty()) {
            drawTickMarkText(canvas)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        Log.i(TAG, "onSizeChanged: w = $w, h = $h")
        spanWidth = (w - paddingStart - paddingEnd) * 1.0f / (tickMarkCount + 1)
        spanHeight = h.toFloat()
        Log.i(TAG, "onSizeChanged: spanHeight = $spanHeight")
    }

    /**
     * Draw tickMark, from left to right, from top to bottom.
     */
    private fun drawTickMark(canvas: Canvas) {
        Log.i(TAG, "drawTickMark: getPaddingStart() = $paddingStart, getPaddingTop() = $paddingTop, " +
                "getPaddingBottom() = $paddingBottom, getPaddingEnd() = $paddingEnd")
        var startX = spanWidth + paddingStart
        val startY = 0f
        var stopX = startX
        val stopY = spanHeight
        for (i in 1..tickMarkCount) {
            canvas.drawLine(startX, startY, stopX, stopY, tickMarkPaint)
            startX += spanWidth
            stopX = startX
            Log.i(TAG, "drawTickMark: startX = $startX")
        }
    }

    /**
     * Draw text in each span, from left to right
     */
    private fun drawTickMarkText(canvas: Canvas) {
        if (tickMarkNextText != null && tickMarkNextText!!.isNotEmpty()) {
            drawTwoLineText(canvas)
        } else {
            drawSingleLineText(canvas)
        }
    }

    private fun drawTwoLineText(canvas: Canvas) {
        val horizontalCenter = spanHeight / 2
        var x1 = spanWidth / 2 + paddingStart

        // Draw first line.
        for (charSequence in tickMarkText!!) {
            canvas.drawText(charSequence.toString(), x1, horizontalCenter, tickMarkTextPaint)
            x1 += spanWidth
        }
        var x2 = spanWidth / 2 + paddingStart
        val y2 = horizontalCenter + tickMarkTextPaint.fontSpacing

        // Draw second line.
        for (charSequence in tickMarkNextText!!) {
            canvas.drawText(charSequence.toString(), x2, y2, tickMarkNextTextPaint)
            x2 += spanWidth
        }
    }

    private fun drawSingleLineText(canvas: Canvas) {
        val textBounds = Rect()
        tickMarkTextPaint.getTextBounds(tickMarkText!![0].toString(), 0, tickMarkText!![0].length, textBounds)
        var x = spanWidth / 2 + paddingStart
        val y = spanHeight / 2 + textBounds.height() * 1.0f / 2
        for (charSequence in tickMarkText!!) {
            canvas.drawText(charSequence.toString(), x, y, tickMarkTextPaint)
            x += spanWidth
        }
    }

    /**
     * Set each span's text
     * @param tickMarkText，text array in each tickMark span
     */
    fun setTickMarkText(tickMarkText: Array<CharSequence>) {
        if (tickMarkText.isNotEmpty()) {
            this.tickMarkText = tickMarkText
            tickMarkCount = this.tickMarkText!!.size - 1
            max = this.tickMarkText!!.size
        }
    }

    fun setTickMarkNextText(tickMarkNextText: Array<CharSequence>) {
        if (tickMarkNextText.isNotEmpty()) {
            this.tickMarkNextText = tickMarkNextText
        }
    }
}
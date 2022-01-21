package com.github.easyview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * A ViewGroup which composed by multiple TextView. It also supports round corners.
 * @author: Little Bei
 * @Date: 2021/12/27
 */

class EasyTextViewGroup(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    ViewGroup(context, attrs, defStyleAttr) {
    private var textViewCount = 0
    var radius = 0f
    var topLeftRadius = 0f
    var topRightRadius = 0f
    var bottomRightRadius = 0f
    var bottomLeftRadius = 0f

    private val outerRectF = RectF()
    private val innerRectF = RectF()
    private val roundRadii = floatArrayOf(0f,0f,0f,0f,0f,0f,0f,0f)

    private val outerPath = Path()
    private val innerPath = Path()

    private val paint = Paint()
    private val xFerMode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)

    private var childWidth = 0

    constructor(context: Context): this(context, null)
    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0)

    init {
        setWillNotDraw(false)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.EasyView)

        textViewCount = typedArray.getInt(R.styleable.EasyView_ev_textView_count, textViewCount)
        radius = typedArray.getDimension(R.styleable.EasyView_ev_radius, radius)
        val leftRadius = typedArray.getDimension(R.styleable.EasyView_ev_left_radius, radius)
        val topRadius = typedArray.getDimension(R.styleable.EasyView_ev_top_radius, radius)
        val rightRadius = typedArray.getDimension(R.styleable.EasyView_ev_right_radius, radius)
        val bottomRadius = typedArray.getDimension(R.styleable.EasyView_ev_bottom_radius, radius)

        topLeftRadius = typedArray.getDimension(R.styleable.EasyView_ev_topLeft_radius, if (topRadius > 0) topRadius else leftRadius)
        topRightRadius = typedArray.getDimension(R.styleable.EasyView_ev_topRight_radius, if (topRadius > 0) topRadius else rightRadius)
        bottomRightRadius = typedArray.getDimension(R.styleable.EasyView_ev_bottomRight_radius, if (bottomRadius > 0) bottomRadius else rightRadius)
        bottomLeftRadius = typedArray.getDimension(R.styleable.EasyView_ev_bottomLeft_radius, if (bottomRadius > 0) bottomRadius else leftRadius)

        for (i in 0 until textViewCount) {
            val view = TextView(context)
            view.setTextColor(Color.parseColor("#FFFFFF"))
            addView(view)
        }

        onRadiusChanged()
        typedArray.recycle()
    }

    private fun onRadiusChanged() {
        roundRadii[0] = topLeftRadius
        roundRadii[1] = topLeftRadius
        roundRadii[2] = topRightRadius
        roundRadii[3] = topRightRadius
        roundRadii[4] = bottomRightRadius
        roundRadii[5] = bottomRightRadius
        roundRadii[6] = bottomLeftRadius
        roundRadii[7] = bottomLeftRadius
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val measureWidth = MeasureSpec.getSize(widthMeasureSpec)
        val measureHeight = MeasureSpec.getSize(heightMeasureSpec)
        if (textViewCount == 0) {
            return
        }
        childWidth = measureWidth / textViewCount
        for (i in 0 until textViewCount) {
            getChildAt(i).layoutParams.width = childWidth
            getChildAt(i).layoutParams.height = measureHeight
        }
        measureChildren(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var childLeft = 0
        for (i in 0 until textViewCount) {
            val childView: View = getChildAt(i)
            if (childView.visibility == View.VISIBLE) {
                childView.layout(childLeft, 0, childLeft + childWidth, measuredHeight)
                childLeft += childWidth
            }
        }
    }


    override fun draw(canvas: Canvas) {
        val saved = canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), null,)
        super.draw(canvas)
        drawCorner(canvas)
        canvas.restoreToCount(saved)
    }


    private fun drawCorner(canvas: Canvas) {
        var visibleCount = 0
        for (i in 0 until childCount) {
            if (getChildAt(i).visibility == View.VISIBLE) {
                visibleCount++
            }
        }
        if (visibleCount == 0) {
            return
        }

        paint.apply {
            reset()
            style = Paint.Style.FILL
            flags = Paint.ANTI_ALIAS_FLAG
            xfermode = xFerMode
        }

        outerRectF[0f, 0f, width.toFloat()] = height.toFloat()
        innerRectF[0f, 0f, width * 1.0f / childCount * visibleCount] = height.toFloat()
        outerPath.reset()
        innerPath.reset()
        outerPath.addRect(outerRectF, Path.Direction.CCW)
        innerPath.addRoundRect(innerRectF, roundRadii, Path.Direction.CCW)
        outerPath.op(innerPath, Path.Op.DIFFERENCE)
        canvas.drawPath(outerPath, paint)
        paint.xfermode = null
    }


    /**
     * Set children view's background color.
     * @param resId Color resource ID array, such as R.color.xxx.
     */
    fun setChildrenBgColor(resId: IntArray) {
        require(resId.size <= textViewCount) { "Max textViewColor.length is " + textViewCount + ", now is " + resId.size }
        for (i in resId.indices) {
            val view = getChildAt(i) as TextView
            view.setBackgroundColor(resId[i])
        }
    }

    /**
     * Set children view's background color.
     * @param argb Hex color，such as "#FF0000".
     */
    fun setChildrenBgColor(argb: Array<String?>) {
        require(argb.size <= textViewCount) { "Max textViewColor.length is " + textViewCount + ", now is " + argb.size }
        for (i in argb.indices) {
            val view = getChildAt(i) as TextView
            view.setBackgroundColor(Color.parseColor(argb[i]))
        }
    }

    /**
     * Set children view's text.
     * @param resId Children text resId array.
     */
    fun setChildrenText(resId: IntArray) {
        require(resId.size <= textViewCount) { "Max childrenText.length is " + textViewCount + ", now is " + resId.size }
        for (i in resId.indices) {
            val view = getChildAt(i) as TextView
            view.textAlignment = View.TEXT_ALIGNMENT_CENTER
            view.gravity = Gravity.CENTER_HORIZONTAL or Gravity.CENTER_VERTICAL
            view.setText(resId[i])
        }
    }

    /**
     * Set children view's text.
     * @param text Children text string array.
     */
    fun setChildrenText(text: Array<String?>) {
        require(text.size <= textViewCount) { "Max childrenText.length is " + textViewCount + ", now is " + text.size }
        for (i in text.indices) {
            val view = getChildAt(i) as TextView
            view.textAlignment = View.TEXT_ALIGNMENT_CENTER
            view.gravity = Gravity.CENTER_HORIZONTAL or Gravity.CENTER_VERTICAL
            view.text = text[i]
        }
    }

    /**
     * Set children view's font color.
     * @param resId Color resId array.
     */
    fun setChildrenTextColor(resId: IntArray) {
        require(resId.size <= textViewCount) { "TextViewCount is less than resId.length!" }
        for (i in resId.indices) {
            val view = getChildAt(i) as TextView
            view.setTextColor(context.resources.getColor(resId[i]))
        }
    }

    /**
     * Set children view's font color.
     * @param argb Hex color，such as "#FF0000".
     */
    fun setChildrenTextColor(argb: Array<String?>) {
        require(argb.size <= textViewCount) { "TextViewCount is less than argb.length!" }
        for (i in argb.indices) {
            val view = getChildAt(i) as TextView
            view.setTextColor(Color.parseColor(argb[i]))
        }
    }

    /**
     * Set children view's font size.
     * @param sp size unit
     */
    fun setChildrenTextSize(sp: Int) {
        for (i in 0 until textViewCount) {
            val view = getChildAt(i) as TextView
            view.textSize = sp.toFloat()
        }
    }


    /**
     * show one of the TextView.
     * @param position, TextView's index, begin with 0.
     */
    fun show(position: Int) {
        require(position < textViewCount) { "Max index is " + (textViewCount - 1) + ", now is " + position }
        val view: View = getChildAt(position)
        if (view.visibility == View.GONE) {
            getChildAt(position).visibility = View.VISIBLE
            requestLayout()
        }
    }

    /**
     * hide one of the TextView.
     * @param position, TextView's index, begin with 0.
     */
    fun hide(position: Int) {
        require(position < textViewCount) { "Max index is " + (textViewCount - 1) + ", now is " + position }
        val view: View = getChildAt(position)
        if (view.visibility == View.VISIBLE) {
            getChildAt(position).visibility = View.GONE
            requestLayout()
        }
    }
}
package com.github.easyview.util

import android.content.Context

/**
 * @author: Little Bei
 * @Date: 2021/12/14
 */

/**
 * convert dp to px
 */
fun dip2px(context: Context, dpValue: Float): Float {
    val scale = context.resources.displayMetrics.density
    return dpValue * scale
}

/**
 * convert px to dp
 */
fun px2dip(context: Context, pxValue: Float): Float {
    val scale = context.resources.displayMetrics.density
    return pxValue / scale
}



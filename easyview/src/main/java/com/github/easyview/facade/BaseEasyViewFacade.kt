package com.github.easyview.facade

import android.graphics.Canvas

/**
 * @author: Little Bei
 * @Date: 2021/12/13
 */

abstract class BaseEasyViewFacade: EasyViewFacade {
    override fun preDraw(canvas: Canvas) {
    }

    override fun onDraw(canvas: Canvas) {
    }

    override fun afterDraw(canvas: Canvas) {
    }
}
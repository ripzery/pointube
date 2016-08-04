package com.socket9.pointube.customviews

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

/**
 * Created by Euro (ripzery@gmail.com) on 7/12/2016 AD.
 */
class OneOneHeightImageView : ImageView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(heightMeasureSpec, heightMeasureSpec)
    }
}
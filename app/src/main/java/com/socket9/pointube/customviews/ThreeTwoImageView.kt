package com.socket9.pointube.customviews

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

/**
 * Created by Euro (ripzery@gmail.com) on 7/12/2016 AD.
 */
class ThreeTwoImageView : ImageView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = width / 3 * 2

        val measuredHeightSpec = MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.EXACTLY)

        super.onMeasure(widthMeasureSpec, measuredHeightSpec)

        setMeasuredDimension(widthSize, heightSize)
    }
}
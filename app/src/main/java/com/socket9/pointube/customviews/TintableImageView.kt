package com.socket9.pointube.customviews

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.widget.ImageView
import com.socket9.pointube.R

class TintableImageView : ImageView {

    private var tint: ColorStateList? = null

    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(context, attrs, defStyle)
    }

    private fun init(context: Context, attrs: AttributeSet, defStyle: Int) {
        val a = context.obtainStyledAttributes(
                attrs, R.styleable.TintableImageView, defStyle, 0)
        tint = a.getColorStateList(
                R.styleable.TintableImageView_tint)
        a.recycle()
    }

    override fun drawableStateChanged() {
        super.drawableStateChanged()
        if (tint != null && tint!!.isStateful()) {
            updateTintColor()
        }
    }

    fun setColorFilter(tint: ColorStateList) {
        this.tint = tint
        super.setColorFilter(tint.getColorForState(getDrawableState(), 0))
    }

    private fun updateTintColor() {
        val color = tint!!.getColorForState(getDrawableState(), 0)
        setColorFilter(color)
    }

}
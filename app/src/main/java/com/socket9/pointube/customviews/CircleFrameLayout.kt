package com.socket9.pointube.screens.home

import android.annotation.TargetApi
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.socket9.pointube.R
import org.jetbrains.anko.AnkoLogger

class CircleFrameLayout : FrameLayout, AnkoLogger {

    /** Variable zone **/
    private var isGrey: Boolean = false

    /** Override method zone **/
    constructor(context: Context) : super(context) {
        initInflate()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initInflate()
        initAttrs(attrs)
        initInstance()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initInflate()
        initAttrs(attrs)
        initInstance()
    }

    @TargetApi(21)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        initInflate()
        initAttrs(attrs)
        initInstance()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        if (isInEditMode) {
            super.onMeasure(MeasureSpec.makeMeasureSpec(210, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(210, MeasureSpec.EXACTLY))
//            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            return
        }

        val child: View? = getChildAt(0)

        /* For debugging */
//        loggingOnMeasured(child, heightMeasureSpec, widthMeasureSpec)

        if (child != null) {
            /* set radius by greater value */
            val radius = if (child.width > child.height) child.width else child.height

            /* build measure spec class */
            val radiusSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.EXACTLY, radius)

            super.onMeasure(radiusSpec, radiusSpec)

            setMeasuredDimension(radius, radius)
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }

//    private fun loggingOnMeasured(child: View, heightMeasureSpec: Int, widthMeasureSpec: Int) {
//        info { "child_height : ${child.height}" }
//        info { "child_width : ${child.width}" }
//        info { "parent_width : ${MeasureSpec.getSize(widthMeasureSpec)}" }
//        info { "parent_height : ${MeasureSpec.getSize(heightMeasureSpec)}" }
//    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    private fun initInflate() {
//        setBackgroundResource(R.drawable.shape_circle_red)
    }

    private fun initAttrs(attrs: AttributeSet) {
        val typedArray: TypedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.CircleFrameLayout, 0, 0)

        try {

            isGrey = typedArray.getBoolean(R.styleable.CircleFrameLayout_isGrey, false)

        } finally {
            typedArray.recycle()
        }
    }

    private fun initInstance() {
        setBackgroundResource(if (isGrey) R.drawable.shape_circle_grey else R.drawable.shape_circle_red)
    }

    fun isGrey(): Boolean {
        return isGrey
    }

    fun setIsGrey(isGrey: Boolean){
        this.isGrey = isGrey
        setBackgroundResource(if (isGrey) R.drawable.shape_circle_grey else R.drawable.shape_circle_red)
        invalidate()
        requestLayout()
    }
}

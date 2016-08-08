package com.socket9.pointube.screens.about

import android.annotation.TargetApi
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.socket9.pointube.R
import com.socket9.pointube.screens.about.AboutItem
import kotlinx.android.synthetic.main.viewgroup_about_pointube.view.*

/**
 * Created by ripzery on 7/21/16.
 */
class AboutViewGroup : FrameLayout {

    /** Variable zone **/
    lateinit private var viewContainer: View

    /** Override method zone **/
    constructor(context: Context) : super(context) {
        initInflate()
        initInstances()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initInflate()
        initInstances()
        initWithAttrs(attrs, 0, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initInflate()
        initInstances()
        initWithAttrs(attrs, defStyleAttr, 0)
    }

    @TargetApi(21)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        initInflate()
        initInstances()
        initWithAttrs(attrs, defStyleAttr, defStyleRes)
    }


    private fun initInflate() {
        viewContainer = inflate(context, R.layout.viewgroup_about_pointube, this)
    }

    private fun initInstances() {
        // findViewById here
    }

    private fun initWithAttrs(attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) {
        /*
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.StyleableName,
                defStyleAttr, defStyleRes);

        try {

        } finally {
            a.recycle();
        }
        */
    }

    /** Method zone **/
    fun setModel(model: AboutItem) {
        with(model) {
            viewContainer.tvAbout.text = title
            Glide.with(context).load(icon).into(viewContainer.ivIconAbout)
        }
    }
}
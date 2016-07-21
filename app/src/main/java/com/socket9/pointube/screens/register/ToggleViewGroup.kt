package com.socket9.pointube.screens.register

import android.annotation.TargetApi
import android.content.Context
import android.content.res.TypedArray
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.socket9.pointube.R
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.subjects.PublishSubject

/**
 * Created by ripzery on 7/21/16.
 */
class ToggleViewGroup : FrameLayout {

    /** Variable zone **/
    lateinit private var viewContainer: View
    private var listener: OnToggleListener? = null
    private var currentEnable: Int = 1
    private val tvLeft: TextView by lazy { viewContainer.findViewById(R.id.tvLeft) as TextView }
    private val tvRight: TextView by lazy { viewContainer.findViewById(R.id.tvRight) as TextView }
    private val layoutLeft: FrameLayout by lazy { viewContainer.findViewById(R.id.layoutLeft) as FrameLayout }
    private val layoutRight: FrameLayout by lazy { viewContainer.findViewById(R.id.layoutRight) as FrameLayout }
    private val toggleObservable: PublishSubject<CharSequence> = PublishSubject.create()

    companion object {
        val LEFT = 1
        val RIGHT = 2
    }

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
        viewContainer = inflate(context, R.layout.viewgroup_toggle, this)
    }

    private fun initInstances() {

        layoutLeft.setOnClickListener {
            enableLeft()
            listener?.onToggleLeft(true)
            toggleObservable.onNext("$LEFT")
        }

        layoutRight.setOnClickListener {
            enableRight()
            listener?.onToggleLeft(false)
            toggleObservable.onNext("$RIGHT")
        }

        enableLeft()
        toggleObservable.onNext("$LEFT")
    }

    private fun enableRight() {
        currentEnable = 2
        layoutLeft.isSelected = false
        layoutRight.isSelected = true
        tvLeft.setTextColor(tvRight.currentTextColor)
        tvRight.setTextColor(ContextCompat.getColor(context, android.R.color.white))
        layoutLeft.isEnabled = true
        layoutRight.isEnabled = false
    }

    private fun enableLeft() {
        currentEnable = 1
        layoutLeft.isSelected = true
        layoutRight.isSelected = false
        tvRight.setTextColor(tvLeft.currentTextColor)
        tvLeft.setTextColor(ContextCompat.getColor(context, android.R.color.white))
        layoutLeft.isEnabled = false
        layoutRight.isEnabled = true
    }

    private fun initWithAttrs(attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) {

        val a: TypedArray = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.ToggleViewGroup,
                defStyleAttr, defStyleRes)

        try {

            val textLeft = a.getString(R.styleable.ToggleViewGroup_leftText)
            val textRight = a.getString(R.styleable.ToggleViewGroup_rightText)

            tvLeft.text = textLeft
            tvRight.text = textRight

        } finally {
            a.recycle()
        }

    }

    /** Method zone **/
    fun getToggleObservable(): Observable<CharSequence> {
        return toggleObservable.subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(AndroidSchedulers.mainThread())
    }

    fun setEnable(side: Int) {
        if (LEFT == side) enableLeft() else enableRight()
    }

    fun getEnable(): Int {
        return currentEnable
    }

    fun setOnToggleListener(listener: OnToggleListener) {
        this.listener = listener
    }

    interface OnToggleListener {
        fun onToggleLeft(isLeft: Boolean)
    }
}
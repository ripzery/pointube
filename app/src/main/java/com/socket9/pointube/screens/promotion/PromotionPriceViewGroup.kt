package com.socket9.pointube.screens.promotion

import android.annotation.TargetApi
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.socket9.pointube.R
import org.jetbrains.anko.find

class PromotionPriceViewGroup : FrameLayout {

    /** Variable zone **/
    lateinit private var viewContainer: View
    lateinit private var tvPrice: TextView
    lateinit private var tvUnit: TextView
    private var showCurrency: Boolean = true
    private var currency: String = ""
    private var price: String = ""

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
        viewContainer = inflate(context, R.layout.viewgroup_promotion_price, this)
    }

    private fun initInstances() {
        // findViewById here
        tvPrice = find(R.id.tvPrice)
        tvUnit = find(R.id.tvUnit)
    }

    private fun initWithAttrs(attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) {

        val a: TypedArray = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.PromotionPriceViewGroup,
                defStyleAttr, defStyleRes)

        showCurrency = a.getBoolean(R.styleable.PromotionPriceViewGroup_showCurrency, true)
        price = a.getString(R.styleable.PromotionPriceViewGroup_price)
        currency = a.getString(R.styleable.PromotionPriceViewGroup_currency)

        try {

            tvUnit.text = currency
            tvPrice.text = price

            if (!showCurrency) tvUnit.visibility = View.GONE

            invalidate()
            requestLayout()

        } finally {
            a.recycle()
        }

    }

    /** Method zone **/

    fun setModel(price: String, currency: String) {
        tvUnit.text = currency
        tvPrice.text = price
    }
}

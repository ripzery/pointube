package com.socket9.pointube.screens.promotion.viewgroups

import android.annotation.TargetApi
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.socket9.pointube.R
import com.socket9.pointube.extensions.toPx
import com.socket9.pointube.screens.home.CircleFrameLayout
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find
import org.jetbrains.anko.info

class PromotionPriceViewGroup : FrameLayout, AnkoLogger {

    /** Variable zone **/
    lateinit private var viewContainer: View
    lateinit private var circleLayout: CircleFrameLayout
    lateinit private var tvPrice: TextView
    lateinit private var tvUnit: TextView
    private var showCurrency: Boolean = true
    private var currency: String = ""
    private var price: String = ""
    private var isGrey: Boolean = false
    private var maxLines: Int = 1
    private var mValueTextSize: Int = 0
    private var mUnitTextSize: Int = 0

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
        circleLayout = find(R.id.circleLayout)
    }

    private fun initWithAttrs(attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) {

        val a: TypedArray = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.PromotionPriceViewGroup,
                defStyleAttr, defStyleRes)

        try {

            showCurrency = a.getBoolean(R.styleable.PromotionPriceViewGroup_showCurrency, true)
            isGrey = a.getBoolean(R.styleable.PromotionPriceViewGroup_isCircleGrey, false)
            price = a.getString(R.styleable.PromotionPriceViewGroup_price) ?: ""
            maxLines = a.getInt(R.styleable.PromotionPriceViewGroup_maxLines, 1)
            currency = a.getString(R.styleable.PromotionPriceViewGroup_currency) ?: ""
            mUnitTextSize = a.getDimensionPixelSize(R.styleable.PromotionPriceViewGroup_unitTextSize, 14.toPx().toInt()).toInt()
            mValueTextSize = a.getDimensionPixelSize(R.styleable.PromotionPriceViewGroup_valueTextSize, 14.toPx().toInt()).toInt()

            setCurrency(currency)
            setPrice(price)
            setIsGrey(isGrey)
            tvPrice.maxLines = maxLines
            tvUnit.maxLines = maxLines

            info { mValueTextSize }

            if (mValueTextSize > 0 && mUnitTextSize > 0) {
                tvPrice.setTextSize(TypedValue.COMPLEX_UNIT_PX, mValueTextSize.toFloat())
                tvUnit.setTextSize(TypedValue.COMPLEX_UNIT_PX, mUnitTextSize.toFloat())
            }
            setShowCurrency(showCurrency)

        } catch(e: IllegalStateException) {

            e.printStackTrace()

        } finally {
            a.recycle()

        }

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    /** Method zone **/

    fun setModel(price: String, currency: String) {
        tvUnit.text = currency
        tvPrice.text = price
    }

    fun setPrice(price: String) {
        tvPrice.text = price
    }

    fun setCurrency(unit: String) {
        tvUnit.text = unit
    }

    fun setShowCurrency(isShow: Boolean) {
        if (!showCurrency) tvUnit.visibility = View.GONE
    }

    fun setIsGrey(isGrey: Boolean) {
        circleLayout.setIsGrey(isGrey)
    }
}

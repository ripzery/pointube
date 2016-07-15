package com.socket9.pointube.screens.promotion.viewgroups

import android.annotation.TargetApi
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.socket9.pointube.R
import com.socket9.pointube.extensions.toPx
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find

class PromotionPriceSaleViewGroup : FrameLayout, AnkoLogger {

    /** Variable zone **/
    lateinit private var viewContainer: View
    lateinit private var promotionOriginal: PromotionPriceViewGroup
    lateinit private var tvExtraPrice: TextView
    lateinit private var promotionSale: PromotionPriceViewGroup
    private var isSale: Boolean = false
    private var isShowExtraPrice: Boolean = false

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
        viewContainer = inflate(context, R.layout.viewgroup_promotion_price_sale, this)
    }

    private fun initInstances() {
        // findViewById here
        promotionOriginal = viewContainer.findViewById(R.id.promotionPriceOriginal) as PromotionPriceViewGroup
        promotionSale = viewContainer.findViewById(R.id.promotionPriceSale) as PromotionPriceViewGroup
        tvExtraPrice = viewContainer.findViewById(R.id.tvExtraPrice) as TextView
    }

    private fun initWithAttrs(attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) {

        val a: TypedArray = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.PromotionPriceSaleViewGroup,
                defStyleAttr, defStyleRes)

        try {
            isSale = a.getBoolean(R.styleable.PromotionPriceSaleViewGroup_isSale, false)
            isShowExtraPrice = a.getBoolean(R.styleable.PromotionPriceSaleViewGroup_isShowExtraPrice, false)

            setIsSale(isSale)
            showExtraPrice(isShowExtraPrice)
            if (isInEditMode) setTitleTextSize(60.0f)

        } catch(e: IllegalStateException) {

            e.printStackTrace()

        } finally {
            a.recycle()
        }

    }

    /** Method zone **/

    private fun setMargins(view: View, left: Int, top: Int, right: Int, bottom: Int) {
        val layoutParams: LayoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(left.toPx().toInt(), top.toPx().toInt(), right.toPx().toInt(), bottom.toPx().toInt())
        view.layoutParams = layoutParams
    }

    fun showExtraPrice(isShowExtraPrice: Boolean) {
        this.isShowExtraPrice = isShowExtraPrice
        tvExtraPrice.visibility = if (isShowExtraPrice) View.VISIBLE else View.GONE
    }

    fun setIsSale(isSale: Boolean) {
        this.isSale = isSale
        promotionOriginal.visibility = if (isSale) View.VISIBLE else View.GONE
        setMargins(promotionSale, if (isSale) 32 else 0, 0, 0, 0)
    }

    fun setOriginalPrice(price: String) {
        promotionOriginal.setPrice(price)
    }

    fun setSalePrice(price: String) {
        promotionSale.setPrice(price)
    }

    fun setTitleTextSize(size: Float) {
        promotionOriginal.setPriceTextSize(size)
        promotionSale.setPriceTextSize(size)
    }

    fun setDescTextSize(size: Float) {
        promotionOriginal.setCurrencyTextSize(size)
        promotionSale.setCurrencyTextSize(size)
    }

    fun setCurrency(currency: String) {
        promotionOriginal.setCurrency(currency)
        promotionSale.setCurrency(currency)
    }

}

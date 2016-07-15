package com.socket9.pointube.screens.promotion

import android.annotation.TargetApi
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.socket9.pointube.R
import com.socket9.pointube.extensions.toPx
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find

class PromotionPriceSaleViewGroup : FrameLayout, AnkoLogger {

    /** Variable zone **/
    lateinit private var viewContainer: View
    lateinit private var promotionOriginal: PromotionPriceViewGroup
    lateinit private var promotionSale: PromotionPriceViewGroup
    private var isSale: Boolean = false

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
        promotionOriginal = find(R.id.promotionPriceOriginal)
        promotionSale = find(R.id.promotionPriceSale)
    }

    private fun initWithAttrs(attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) {

        val a: TypedArray = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.PromotionPriceSaleViewGroup,
                defStyleAttr, defStyleRes)

        try {

            isSale = a.getBoolean(R.styleable.PromotionPriceSaleViewGroup_isSale, false)

            promotionOriginal.visibility = if (isSale) View.VISIBLE else View.GONE

            setMargins(promotionSale, if (isSale) 28 else 0, 0, 0, 0)

        } catch(e: IllegalStateException) {

            e.printStackTrace()

        } finally {
            invalidate()
            requestLayout()
            a.recycle()
        }

    }

    /** Method zone **/

    private fun setMargins(view: View, left: Int, top: Int, right: Int, bottom: Int) {
        val layoutParams: FrameLayout.LayoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(left.toPx().toInt(), top.toPx().toInt(), right.toPx().toInt(), bottom.toPx().toInt())
        view.layoutParams = layoutParams
    }

}

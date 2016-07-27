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
import kotlinx.android.synthetic.main.viewgroup_promotion_price_sale.view.*
import org.jetbrains.anko.AnkoLogger

class PromotionPriceSaleViewGroup : FrameLayout, AnkoLogger {

    /** Variable zone **/
    lateinit private var viewContainer: View
    lateinit private var mPromotionOriginal: PromotionPriceViewGroup
    lateinit private var mTvExtraPrice: TextView
    lateinit private var mPromotionSale: PromotionPriceViewGroup
    private var mIsSale: Boolean = false
    private var mIsShowExtraPrice: Boolean = false
    private var mSaleCurrency: String = "baht"

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
        mPromotionOriginal = viewContainer.findViewById(R.id.promotionPriceOriginal) as PromotionPriceViewGroup
        mPromotionSale = viewContainer.findViewById(R.id.promotionPriceSale) as PromotionPriceViewGroup
        mTvExtraPrice = viewContainer.findViewById(R.id.tvExtraPrice) as TextView
    }

    private fun initWithAttrs(attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) {

        val a: TypedArray = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.PromotionPriceSaleViewGroup,
                defStyleAttr, defStyleRes)

        try {
            mIsSale = a.getBoolean(R.styleable.PromotionPriceSaleViewGroup_isSale, false)
            mIsShowExtraPrice = a.getBoolean(R.styleable.PromotionPriceSaleViewGroup_isShowExtraPrice, false)
            mSaleCurrency = a.getString(R.styleable.PromotionPriceSaleViewGroup_saleCurrencyPromotion)

            setIsSale(mIsSale)
            showExtraPrice(mIsShowExtraPrice)

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
        this.mIsShowExtraPrice = isShowExtraPrice
        mTvExtraPrice.visibility = if (isShowExtraPrice) View.VISIBLE else View.GONE
    }

    fun setIsSale(isSale: Boolean) {
        this.mIsSale = isSale
        mPromotionOriginal.visibility = if (isSale) View.VISIBLE else View.GONE
        setMargins(mPromotionSale, if (isSale) 40 else 0, 0, 0, 0)
    }

    fun setOriginalPrice(price: String) {
        mPromotionOriginal.setPrice(price)
    }

    fun setSalePrice(price: String) {
        mPromotionSale.setPrice(price)
    }

    fun setCurrency(currency: String) {
        mSaleCurrency = currency
        mPromotionOriginal.setCurrency(mSaleCurrency)
        mPromotionSale.setCurrency(mSaleCurrency)
        mTvExtraPrice.text = tvExtraPrice.text.toString().replace("baht", mSaleCurrency)
    }

}

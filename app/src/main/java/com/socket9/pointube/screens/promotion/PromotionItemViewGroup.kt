package com.socket9.pointube.screens.promotion

import android.annotation.TargetApi
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.socket9.pointube.R
import org.jetbrains.anko.find

class PromotionItemViewGroup : FrameLayout {

    /** Variable zone **/
    lateinit private var viewContainer: View
    lateinit private var mPriceViewGroup: PromotionPriceViewGroup
    private var currency: String = ""
    private var price: String = ""
    private var isGrey: Boolean = false
    private var maxLines: Int = 1
    private var titleTextSize: Float = 14.0f
    private var descTextSize: Float = 14.0f

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
        viewContainer = inflate(context, R.layout.viewgroup_promotion_item, this)
    }

    private fun initInstances() {
        // findViewById here
        mPriceViewGroup = viewContainer.find(R.id.promotionPrice)
    }

    private fun initWithAttrs(attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) {

        val a: TypedArray = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.PromotionItemViewGroup,
                defStyleAttr, defStyleRes);

        try {

            currency = a.getString(R.styleable.PromotionItemViewGroup_currencyPromotion)
            price = a.getString(R.styleable.PromotionItemViewGroup_pricePromotion)
            titleTextSize = a.getDimension(R.styleable.PromotionItemViewGroup_titleTextSizePromotion, 14.0f)
            descTextSize = a.getDimension(R.styleable.PromotionItemViewGroup_descTextSizePromotion, 14.0f)

            mPriceViewGroup.setPrice(price)
            mPriceViewGroup.setUnit(currency)
            mPriceViewGroup.setPriceTextSize(titleTextSize)
            mPriceViewGroup.setUnitTextSize(descTextSize)

        } finally {
            a.recycle();
        }

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
    /** Method zone **/

    fun setModel() {

    }
}

package com.socket9.pointube.screens.promotion.viewgroups

import android.annotation.TargetApi
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.socket9.pointube.R
import com.socket9.pointube.extensions.farFrom
import com.socket9.pointube.extensions.plainText
import com.socket9.pointube.repository.programs.PublishedProgramItemRepo
import kotlinx.android.synthetic.main.viewgroup_promotion_item.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find
import java.util.*

class PromotionItemViewGroup : FrameLayout, AnkoLogger {

    /** Variable zone **/
    companion object {
        val SHOW_DAY_LEFT_THRESHOLD = 10
    }

    lateinit private var viewContainer: View
    lateinit private var mPriceSaleViewGroup: PromotionPriceSaleViewGroup
    lateinit private var mTvDayLeft: TextView
    lateinit private var mTvTitle: TextView
    lateinit private var mTvContent: TextView
    private var mCurrency: String = ""
    private var mSalePrice: String = ""
    private var mOriginalPrice: String = ""
    private var mIsGrey: Boolean = false
    private var mIsShowDayLeft: Boolean = false
    private var mIsExtraPriceSale: Boolean = false
    private var mMaxLines: Int = 1

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
        mPriceSaleViewGroup = viewContainer.find(R.id.promotionPrice)
        mTvTitle = viewContainer.find(R.id.tvTitle)
        mTvDayLeft = viewContainer.find(R.id.tvDayLeft)
        mTvContent = viewContainer.find(R.id.tvContent)
    }

    private fun initWithAttrs(attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) {

        val a: TypedArray = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.PromotionItemViewGroup,
                defStyleAttr, defStyleRes)

        try {

            mCurrency = a.getString(R.styleable.PromotionItemViewGroup_currencyPromotion)
            mSalePrice = a.getString(R.styleable.PromotionItemViewGroup_salePricePromotion)
            mOriginalPrice = a.getString(R.styleable.PromotionItemViewGroup_originalPricePromotion)
            mIsShowDayLeft = a.getBoolean(R.styleable.PromotionItemViewGroup_isShowDayLeft, false)
            mIsExtraPriceSale = a.getBoolean(R.styleable.PromotionItemViewGroup_isShowExtraPriceSale, false)

            isShowDayLeft(mIsShowDayLeft)

        } finally {
            a.recycle()
        }
    }

    /** Method zone **/

    fun setModel(model: PublishedProgramItemRepo) {
        /* pass model to price sale view group */
        mPriceSaleViewGroup.setModel(model)

        with(model) {
            mTvTitle.text = Title
            mTvContent.text = Description.plainText()
            Glide.with(context).load(MasterPath).into(ivCover)

            if (PublishPeriod != null) {
                val dayBetween = Date().farFrom(PublishPeriod!!.EndDate)
                val isShow = dayBetween < SHOW_DAY_LEFT_THRESHOLD
                isShowDayLeft(isShow)
                setDayLeft(dayBetween)
            }
        }

        /* Add logic to toggle showing day left */
    }

    fun setLogo(logo: String) {
        Glide.with(context).load(logo).into(civLogo)
    }

    fun isShowDayLeft(isShowDayLeft: Boolean) {
        mTvDayLeft.visibility = if (isShowDayLeft) View.VISIBLE else View.GONE
    }

    fun setDayLeft(dayLeft: Long) {
        when {
            dayLeft == 1L -> mTvDayLeft.text = "1 day left"
            dayLeft > 1L -> mTvDayLeft.text = "$dayLeft days left"
            dayLeft == 0L -> mTvDayLeft.text = "In 24 hours"
        }
    }
}

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
import org.jetbrains.anko.info
import java.util.*

class PromotionItemViewGroup : FrameLayout, AnkoLogger {

    /** Variable zone **/
    private val SHOW_DAY_LEFT_THRESHOLD = 10

    lateinit private var viewContainer: View
    lateinit private var mPriceSaleViewGroup: PromotionPriceSaleViewGroup
    lateinit private var tvDayLeft: TextView
    lateinit private var mTvTitle: TextView
    private var currency: String = ""
    private var salePrice: String = ""
    private var originalPrice: String = ""
    private var isGrey: Boolean = false
    private var isShowDayLeft: Boolean = false
    private var isExtraPriceSale: Boolean = false
    private var maxLines: Int = 1

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
        tvDayLeft = viewContainer.find(R.id.tvDayLeft)
    }

    private fun initWithAttrs(attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) {

        val a: TypedArray = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.PromotionItemViewGroup,
                defStyleAttr, defStyleRes)

        try {

            currency = a.getString(R.styleable.PromotionItemViewGroup_currencyPromotion)
            salePrice = a.getString(R.styleable.PromotionItemViewGroup_salePricePromotion)
            originalPrice = a.getString(R.styleable.PromotionItemViewGroup_originalPricePromotion)
            isShowDayLeft = a.getBoolean(R.styleable.PromotionItemViewGroup_isShowDayLeft, false)
            isExtraPriceSale = a.getBoolean(R.styleable.PromotionItemViewGroup_isShowExtraPriceSale, false)

            isShowDayLeft(isShowDayLeft)

        } finally {
            a.recycle()
        }
    }

    /** Method zone **/

    fun setModel(model: PublishedProgramItemRepo) {
        /* pass model to price sale view group */
        mPriceSaleViewGroup.setModel(model)

        info { model.PublishPeriod!!.EndDate.toString() }

        with(model) {
            mTvTitle.text = Title
            tvContent.text = Description.plainText()
            info { Description.plainText() }
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

    fun isShowDayLeft(isShowDayLeft: Boolean) {
        tvDayLeft.visibility = if (isShowDayLeft) View.VISIBLE else View.GONE
    }

    fun setDayLeft(dayLeft: Long){
        when{
            dayLeft == 1L -> tvDayLeft.text = "1 day left"
            dayLeft > 1L ->  tvDayLeft.text = "$dayLeft days left"
            dayLeft == 0L ->  tvDayLeft.text = "In 24 hours"
        }
    }
}

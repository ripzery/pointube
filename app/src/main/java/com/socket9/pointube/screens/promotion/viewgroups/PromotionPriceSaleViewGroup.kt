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
import com.socket9.pointube.repository.programs.PublishedProgramItemRepo
import org.jetbrains.anko.AnkoLogger

class PromotionPriceSaleViewGroup : FrameLayout, AnkoLogger {

    companion object {
        val STATE_SHOW_RED = 1
        val STATE_SHOW_GREY_RED = 2
        val STATE_SHOW_BAHT = 3
        val STATE_SHOW_RED_WITH_BAHT = 4
        val STATE_SHOW_GREY_RED_WITH_BAHT = 5
        val STATE_SHOW_NOTHING = 6
    }

    /** Variable zone **/
    lateinit private var viewContainer: View
    lateinit private var mPromotionGrey: PromotionPriceViewGroup
    lateinit private var mTvExtraPrice: TextView
    lateinit private var mPromotionRed: PromotionPriceViewGroup
    private var mModel: PublishedProgramItemRepo? = null
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
        mPromotionGrey = viewContainer.findViewById(R.id.promotionPriceOriginal) as PromotionPriceViewGroup
        mPromotionRed = viewContainer.findViewById(R.id.promotionPriceSale) as PromotionPriceViewGroup
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

            setShowGrey(mIsSale)
            showExtraPrice(mIsShowExtraPrice)

        } catch(e: IllegalStateException) {

            e.printStackTrace()

        } finally {
            a.recycle()
        }

    }

    /** Method zone **/

    fun setModel(model: PublishedProgramItemRepo) {
        mModel = model
        setCurrency(mModel!!.UnitOfPoint)

        with(model){
            when{
                Point > 0 && SpecialPoint == 0 && BahtValue == 0 -> setState(STATE_SHOW_RED)
                Point > 0 && SpecialPoint > 0 && BahtValue == 0 -> setState(STATE_SHOW_GREY_RED)
                Point > 0 && SpecialPoint > 0 && BahtValue > 0 -> setState(STATE_SHOW_GREY_RED_WITH_BAHT)
                Point > 0 && SpecialPoint == 0 && BahtValue > 0 -> setState(STATE_SHOW_RED_WITH_BAHT)
                Point == 0 && SpecialPoint == 0 && BahtValue > 0 -> setState(STATE_SHOW_BAHT)
                else -> setState(STATE_SHOW_NOTHING)
            }
        }
    }

    private fun setState(state: Int) {
        when (state) {
            STATE_SHOW_RED -> {
                /* Toggle visibility */
                setShowRed(true)
                setShowGrey(false)
                showExtraPrice(false)

                /* Set Value */
                with(mModel!!) {
                    setRedPrice(Point.toString())
                }
            }
            STATE_SHOW_GREY_RED -> {
                /* Toggle visibility */
                setShowGrey(true)
                setShowRed(true)
                showExtraPrice(false)

                /* Set Value */
                with(mModel!!) {
                    setGreyPrice(Point.toString())
                    setRedPrice(SpecialPoint.toString())
                }
            }
            STATE_SHOW_BAHT -> {
                /* Toggle visibility */
                setShowGrey(false)
                setShowRed(false)

                /* Set Value */
                with(mModel!!) {
                    setExtraPrice(BahtValue.toString())
                }
            }
            STATE_SHOW_RED_WITH_BAHT -> {
                /* Toggle visibility */
                setShowGrey(false)
                setShowRed(true)
                showExtraPrice(true)

                /* Set Value */
                with(mModel!!) {
                    setExtraPrice(" + ${BahtValue.toString()}")
                    setRedPrice(Point.toString())
                }
            }
            STATE_SHOW_GREY_RED_WITH_BAHT -> {
                /* Toggle visibility */
                setShowGrey(true)
                setShowRed(true)
                showExtraPrice(true)

                /* Set Value */
                with(mModel!!) {
                    setExtraPrice(" + ${BahtValue.toString()}")
                    setRedPrice(SpecialPoint.toString())
                    setGreyPrice(Point.toString())
                }
            }
            STATE_SHOW_NOTHING -> {
                /* Toggle visibility */
                setShowGrey(false)
                setShowRed(false)
                showExtraPrice(false)
            }
        }
    }

    private fun setMargins(view: View, left: Int, top: Int, right: Int, bottom: Int) {
        val layoutParams: LayoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(left.toPx().toInt(), top.toPx().toInt(), right.toPx().toInt(), bottom.toPx().toInt())
        view.layoutParams = layoutParams
    }

    private fun showExtraPrice(isShowExtraPrice: Boolean) {
        this.mIsShowExtraPrice = isShowExtraPrice
        mTvExtraPrice.visibility = if (isShowExtraPrice) View.VISIBLE else View.GONE
    }

    private fun setExtraPrice(price: String) {
        mTvExtraPrice.text = "$price baht"
    }

    private fun setShowGrey(isSale: Boolean) {
        this.mIsSale = isSale
        mPromotionGrey.visibility = if (isSale) View.VISIBLE else View.GONE
        setMargins(mPromotionRed, if (isSale) 40 else 0, 0, 0, 0)
    }

    private fun setGreyPrice(price: String) {
        mPromotionGrey.setPrice(price)
    }

    private fun setRedPrice(price: String) {
        mPromotionRed.setPrice(price)
    }

    private fun setShowRed(show: Boolean) {
        mPromotionRed.visibility == if (show) View.VISIBLE else View.GONE
    }

    private fun setCurrency(currency: String) {
        mSaleCurrency = currency
        mPromotionGrey.setCurrency(mSaleCurrency)
        mPromotionRed.setCurrency(mSaleCurrency)
//        mTvExtraPrice.text = tvExtraPrice.text.toString().replace("baht", mSaleCurrency)
    }

}

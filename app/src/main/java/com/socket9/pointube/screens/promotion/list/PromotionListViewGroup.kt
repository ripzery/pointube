package com.socket9.pointube.screens.promotion.list

import android.annotation.TargetApi
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.socket9.pointube.R
import com.socket9.pointube.customviews.OneOneHeightImageView
import com.socket9.pointube.extensions.plainText
import com.socket9.pointube.repository.programs.PublishedProgramItemRepo
import com.socket9.pointube.screens.promotion.viewgroups.PromotionPriceViewGroup
import org.jetbrains.anko.find

/**
 * Created by ripzery on 7/21/16.
 */
class PromotionListViewGroup : FrameLayout {

    /** Variable zone **/
    lateinit private var viewContainer: View
    lateinit private var mTvBrandTitle: TextView
    lateinit private var mTvBrandDetail: TextView
    lateinit private var mIvLogo: OneOneHeightImageView
    lateinit private var mPromotionPriceViewGroup: PromotionPriceViewGroup
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
        viewContainer = inflate(context, R.layout.viewgroup_promotion_list, this)
    }

    private fun initInstances() {
        // findViewById here
        mTvBrandTitle = viewContainer.find(R.id.tvBrandName)
        mTvBrandDetail = viewContainer.find(R.id.tvPromotionDetail)
        mIvLogo = viewContainer.find(R.id.ivLogo)
        mPromotionPriceViewGroup = viewContainer.find(R.id.promotionPrice)
    }

    private fun initWithAttrs(attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) {
        /*
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.StyleableName,
                defStyleAttr, defStyleRes);

        try {

        } finally {
            a.recycle();
        }
        */
    }

    /** Method zone **/
    fun setModel(model : PublishedProgramItemRepo){
        with(model){
            mTvBrandTitle.text = Title
            mTvBrandDetail.text = Description.plainText()
            Glide.with(context).load(this.MasterPath).into(mIvLogo)
            mPromotionPriceViewGroup.setPrice(Point.toString())
            mPromotionPriceViewGroup.setCurrency(UnitOfPoint)
        }
    }
}
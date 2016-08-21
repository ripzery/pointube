package com.socket9.pointube.screens.promotion.main

import PromotionProgramTypeFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import com.socket9.pointube.R
import com.socket9.pointube.utils.ContextUtil
import org.jetbrains.anko.find

/**
 * Created by ripzery on 7/26/16.
 */
class PromotionPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    companion object {
        /* index in promotion */
        val INDEX_HOTDEAL = 0
        val INDEX_DINING = 1
        val INDEX_SHOPPING = 2
        val INDEX_TRAVEL = 3
        val INDEX_ENTERTAINMENT = 4
        val INDEX_HEALTH = 5
        val INDEX_BEAUTY = 6
        val INDEX_EDUCATION = 7
        val INDEX_READING = 8
        val INDEX_HOME_LIVING = 9
        val INDEX_IT_GADGET = 10
        val INDEX_OTHER = 11

        /* type id */
        val TYPE_HOTDEAL = 11
        val TYPE_DINING = 0
        val TYPE_SHOPPING = 1
        val TYPE_TRAVEL = 2
        val TYPE_ENTERTAINMENT = 3
        val TYPE_HEALTH = 4
        val TYPE_EDUCATION = 5
        val TYPE_READING = 6
        val TYPE_HOME_LIVING = 7
        val TYPE_IT_GADGET = 8
        val TYPE_OTHER = 9
        val TYPE_BEAUTY = 10

        /* map position to provider type */
        val PAIR_POSITION_TO_TYPE = listOf(
                INDEX_HOTDEAL to TYPE_HOTDEAL,
                INDEX_DINING to TYPE_DINING,
                INDEX_SHOPPING to TYPE_SHOPPING,
                INDEX_TRAVEL to TYPE_TRAVEL,
                INDEX_ENTERTAINMENT to TYPE_ENTERTAINMENT,
                INDEX_HEALTH to TYPE_HEALTH,
                INDEX_BEAUTY to TYPE_BEAUTY,
                INDEX_EDUCATION to TYPE_EDUCATION,
                INDEX_READING to TYPE_READING,
                INDEX_HOME_LIVING to TYPE_HOME_LIVING,
                INDEX_IT_GADGET to TYPE_IT_GADGET,
                INDEX_OTHER to TYPE_OTHER
        )

        private val TOTAL_PROMOTION = 12

        val TAB_ITEM_TITLES = listOf(ContextUtil.context!!.getString(R.string.promotion_list_text_hot_deal),
                ContextUtil.context!!.getString(R.string.promotion_list_text_dining),
                ContextUtil.context!!.getString(R.string.promotion_list_text_shopping),
                ContextUtil.context!!.getString(R.string.promotion_list_text_travel),
                ContextUtil.context!!.getString(R.string.promotion_list_text_entertainment),
                ContextUtil.context!!.getString(R.string.promotion_list_text_health),
                ContextUtil.context!!.getString(R.string.promotion_list_text_beauty),
                ContextUtil.context!!.getString(R.string.promotion_list_text_education),
                ContextUtil.context!!.getString(R.string.promotion_list_text_reading),
                ContextUtil.context!!.getString(R.string.promotion_list_text_home_and_living),
                ContextUtil.context!!.getString(R.string.promotion_list_text_it_gadget),
                ContextUtil.context!!.getString(R.string.promotion_list_text_other))

        val TAB_ITEM_COLORS = mutableListOf(
                R.color.colorHotDeal,
                R.color.colorDining,
                R.color.colorShopping,
                R.color.colorTravel,
                R.color.colorEntertain,
                R.color.colorHealth,
                R.color.colorBeauty,
                R.color.colorEducation,
                R.color.colorReading,
                R.color.colorHomeLiving,
                R.color.colorItGadget,
                R.color.colorOther)

        val TAB_ITEM_DRAWABLES = mutableListOf(
                R.drawable.world_ball,
                R.drawable.world_ball,
                R.drawable.world_ball,
                R.drawable.world_ball,
                R.drawable.world_ball,
                R.drawable.world_ball,
                R.drawable.world_ball,
                R.drawable.world_ball,
                R.drawable.world_ball,
                R.drawable.world_ball,
                R.drawable.world_ball,
                R.drawable.world_ball
        )

    }

    override fun getItem(position: Int): Fragment {
        return PromotionProgramTypeFragment.newInstance(PAIR_POSITION_TO_TYPE.find { it.first == position }!!.second, TAB_ITEM_TITLES[position])
    }

    override fun getCount(): Int {
        return TOTAL_PROMOTION
    }

    fun getTabView(parent: ViewGroup, position: Int): View {
        val context = ContextUtil.context
        val view: View = LayoutInflater.from(context).inflate(R.layout.layout_custom_tab_layout, parent, false)
        val ivIcon = view.find<ImageView>(R.id.ivIcon)
        val layoutBackground = view.find<FrameLayout>(R.id.layoutBackground)

        ivIcon.setImageDrawable(ContextCompat.getDrawable(context, TAB_ITEM_DRAWABLES[position]))
        layoutBackground.background = ContextCompat.getDrawable(context, TAB_ITEM_COLORS[position])

        return view
    }

}
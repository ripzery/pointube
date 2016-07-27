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
        private val TOTAL_PROMOTION = 12

        val TAB_ITEM_TITLES = listOf<String>("Hot deal", "Dining", "Shopping", "Travel", "Entertainment",
                "Health", "Beauty", "Education", "Reading", "Home and Living", "IT Gadget", "Other")

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
        return PromotionProgramTypeFragment.newInstance(position, TAB_ITEM_TITLES[position])
    }

    override fun getCount(): Int {
        return TOTAL_PROMOTION
    }

    fun getTabView(parent:ViewGroup, position: Int): View {
        val context = ContextUtil.context
        val view: View = LayoutInflater.from(context).inflate(R.layout.layout_custom_tab_layout, parent, false)
        val ivIcon = view.find<ImageView>(R.id.ivIcon)
        val layoutBackground = view.find<FrameLayout>(R.id.layoutBackground)

        ivIcon.setImageDrawable(ContextCompat.getDrawable(context, TAB_ITEM_DRAWABLES[position]))
        layoutBackground.background = ContextCompat.getDrawable(context, TAB_ITEM_COLORS[position])

        return view
    }

}
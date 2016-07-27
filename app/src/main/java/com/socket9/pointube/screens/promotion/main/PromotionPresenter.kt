package com.socket9.pointube.screens.promotion.main

import android.content.Context
import android.support.v4.content.ContextCompat
import com.gigamole.navigationtabbar.ntb.NavigationTabBar
import com.socket9.pointube.R
import com.socket9.pointube.utils.ContextUtil
import org.jetbrains.anko.AnkoLogger

/**
 * Created by ripzery on 7/26/16.
 */
class PromotionPresenter(var view: PromotionContract.View?) : PromotionContract.Presenter, AnkoLogger {
    private val INDEX_HOTDEAL = 0
    private val INDEX_DINING = 1
    private val INDEX_SHOPPING = 2
    private val INDEX_TRAVEL = 3
    private val INDEX_ENTERTAINMENT = 4
    private val INDEX_HEALTH = 5
    private val INDEX_BEAUTY = 6
    private val INDEX_EDUCATION = 7
    private val INDEX_READING = 8
    private val INDEX_HOME_LIVING = 9
    private val INDEX_IT_GADGET = 10
    private val INDEX_OTHER = 11
    lateinit private var context: Context

    override fun prepareViewPager() {
        view?.setupViewPager()
    }

    override fun prepareTabBar() {
        val tabModels: MutableList<NavigationTabBar.Model> = mutableListOf()
        var idDrawable: Int = 0
        var idColor: Int = 0

        for (i in INDEX_HOTDEAL..INDEX_OTHER) {
            when(i){
                INDEX_HOTDEAL -> { idColor = R.color.colorHotDeal; idDrawable = R.drawable.ic_person_black_24dp }
                INDEX_DINING -> { idColor = R.color.colorDining; idDrawable = R.drawable.ic_shopping_basket_black_24dp  }
                INDEX_SHOPPING -> { idColor = R.color.colorShopping; idDrawable = R.drawable.world_ball }
                INDEX_TRAVEL -> { idColor = R.color.colorTravel; idDrawable = R.drawable.world_ball }
                INDEX_ENTERTAINMENT -> {idColor = R.color.colorEntertain; idDrawable = R.drawable.world_ball }
                INDEX_HEALTH -> { idColor = R.color.colorHealth; idDrawable = R.drawable.world_ball }
                INDEX_BEAUTY -> { idColor = R.color.colorBeauty; idDrawable = R.drawable.world_ball }
                INDEX_EDUCATION -> { idColor = R.color.colorEducation; idDrawable = R.drawable.world_ball }
                INDEX_READING -> { idColor = R.color.colorReading; idDrawable = R.drawable.world_ball }
                INDEX_HOME_LIVING -> { idColor = R.color.colorHomeLiving; idDrawable = R.drawable.world_ball }
                INDEX_IT_GADGET -> { idColor = R.color.colorItGadget; idDrawable = R.drawable.world_ball }
                INDEX_OTHER -> { idColor = R.color.colorOther; idDrawable = R.drawable.world_ball }
            }

            val model = NavigationTabBar.Model.Builder(
                    ContextCompat.getDrawable(context, idDrawable),
                    ContextCompat.getColor(context, idColor)
            ).build()

            tabModels.add(model)
        }

        view?.showNavigationTabBar(tabModels)
    }

    override fun onCreate() {
        context = ContextUtil.context!!
    }

    override fun onDestroy() {
        view = null
    }

}
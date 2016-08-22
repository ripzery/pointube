package com.socket9.pointube.screens.promotion.main

import com.gigamole.navigationtabbar.ntb.NavigationTabBar
import com.socket9.pointube.base.BasePresenter
import com.socket9.pointube.repository.programs.PublishedProgramItemRepo

/**
 * Created by ripzery on 7/26/16.
 */
interface PromotionContract {
    interface View {
        fun showNavigationTabBar(iconList: MutableList<Int>)

        fun showLoading()

        fun hideLoading()

        fun setupViewPager()

        fun showRecommendMe()

        fun showErrorMsgRecommendMe(msg: String)
    }

    interface Presenter : BasePresenter {
        fun prepareViewPager()

        fun prepareTabBar()

        fun clickRecommendMe()
    }
}
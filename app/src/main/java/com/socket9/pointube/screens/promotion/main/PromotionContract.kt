package com.socket9.pointube.screens.promotion.main

import com.gigamole.navigationtabbar.ntb.NavigationTabBar
import com.socket9.pointube.base.BasePresenter
import com.socket9.pointube.repository.programs.PublishedProgramItemRepo

/**
 * Created by ripzery on 7/26/16.
 */
interface PromotionContract {
    interface View {
        fun showNavigationTabBar(tabModels: MutableList<NavigationTabBar.Model>)

        fun showLoading()

        fun hideLoading()

        fun setupViewPager()
    }

    interface Presenter : BasePresenter {
        fun prepareViewPager()

        fun prepareTabBar()
    }
}
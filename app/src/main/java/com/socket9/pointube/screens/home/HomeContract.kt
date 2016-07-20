package com.socket9.pointube.screens.home

import com.socket9.pointube.base.BasePresenter

/**
 * Created by Euro (ripzery@gmail.com) on 7/16/2016 AD.
 */
interface HomeContract {
    interface View {
        fun showProviderList(allBrands: HomeModel.AllBrands)

        fun showPublishedProgramList(allPublishedProgram: HomeModel.PublishedProgramListRepo)

        fun showEmptyProviderList()

        fun showEmptyPublishedProgramList()

        fun showProgressBar()

        fun goLogin()

        fun showUnLoggedInState()

        fun showLoggedInState()

        fun updatePromotionCount(newList : HomeModel.AllBrands)
    }

    interface Presenter : BasePresenter {
        fun loadProviderList()

        fun loadPublishedProgramList()

        fun doLogin()

        fun countProviderProgram()
    }
}
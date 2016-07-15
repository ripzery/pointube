package com.socket9.pointube.screens.home

/**
 * Created by Euro (ripzery@gmail.com) on 7/16/2016 AD.
 */
interface HomeContract {
    interface View {
        fun showProviderList(allBrands: HomeModel.AllBrands)

        fun showEmptyProviderList()

        fun showProgressBar()

        fun showLogin()
    }

    interface Presenter {
        fun loadProviderList()

        fun doLogin()
    }
}
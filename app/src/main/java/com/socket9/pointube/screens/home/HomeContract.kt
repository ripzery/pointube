package com.socket9.pointube.screens.home

import com.socket9.pointube.base.BasePresenter
import com.socket9.pointube.repository.brands.BrandRepo
import com.socket9.pointube.repository.programs.PublishedProgramItemRepo

/**
 * Created by Euro (ripzery@gmail.com) on 7/16/2016 AD.
 */
interface HomeContract {
    interface View {
        fun showProviderList(allBrands: MutableList<BrandRepo>)

        fun showPublishedProgramList(allPublishedProgram: MutableList<PublishedProgramItemRepo>)

        fun showEmptyProviderList()

        fun showEmptyPublishedProgramList()

        fun showProgressBar()

        fun goLogin()

        fun goSignUp()

        fun showProgramListByBrand(brandId: Int, brandTitle: String)

        fun showUnLoggedInState()

        fun showLoggedInState()

        fun updatePromotionCount(newList : MutableList<BrandRepo>)
    }

    interface Presenter : BasePresenter {
        fun loadProviderList()

        fun loadPublishedProgramList()

        fun clickBrand(brand: BrandRepo)

        fun doLogin()

        fun doSignUp()
    }
}
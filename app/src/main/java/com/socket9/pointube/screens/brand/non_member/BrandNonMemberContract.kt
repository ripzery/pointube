package com.socket9.pointube.screens.brand.non_member

import com.socket9.pointube.base.BasePresenter
import com.socket9.pointube.repository.brands.BrandRepo
import com.socket9.pointube.repository.programs.PublishedProgramItemRepo
import com.socket9.pointube.screens.brand.BrandModel
import com.socket9.pointube.screens.home.HomeModel

/**
 * Created by ripzery on 8/1/16.
 */
interface BrandNonMemberContract {
    interface View {
        fun highlightAllBrands(allBrands: MutableList<BrandRepo>)

        fun unHighlightAllBrands(allBrands: MutableList<BrandRepo>)

        fun showQualifiedBrand(allBrands: MutableList<BrandRepo>)

        fun showLoading()

        fun hideLoading()

        fun showSaveSuccess()

        fun showSaveFailed()

        fun showEmptyBrands()

        fun showLoadingError()

        fun goNext()
    }

    interface Presenter : BasePresenter {
        fun loadAllBrands(exceptProvider: MutableList<Int>)

        fun selectAllBrand(isSelect: Boolean)

        fun save(memberId: String, token: String, brandRepoList: MutableList<BrandRepo>)
    }
}
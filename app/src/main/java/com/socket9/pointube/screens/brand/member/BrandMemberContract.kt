package com.socket9.pointube.screens.brand.member

import com.socket9.pointube.base.BasePresenter
import com.socket9.pointube.screens.brand.BrandModel

/**
 * Created by ripzery on 7/25/16.
 */
interface BrandMemberContract {
    interface View {
        fun highlightAllBrands(qualifiedList: MutableList<BrandModel.Response.GetMemberBrandResult>)

        fun unHighlightAllBrands(qualifiedList: MutableList<BrandModel.Response.GetMemberBrandResult>)

        fun showQualifiedBrand(qualifiedList: MutableList<BrandModel.Response.GetMemberBrandResult>)

        fun showLoading()

        fun hideLoading()

        fun showEmptyQualifiedBrand()

        fun showLoadingError()

        fun goNext()
    }

    interface Presenter : BasePresenter {
        fun loadQualifiedBrand(memberId: String, token: String)

        fun selectAllBrand(isSelect: Boolean)

        fun next()
    }
}
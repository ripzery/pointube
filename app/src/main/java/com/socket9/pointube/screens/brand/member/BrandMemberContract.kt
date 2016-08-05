package com.socket9.pointube.screens.brand.member

import com.socket9.pointube.base.BasePresenter
import com.socket9.pointube.repository.brands.GetMemberBrandResult
import com.socket9.pointube.screens.brand.BrandModel

/**
 * Created by ripzery on 7/25/16.
 */
interface BrandMemberContract {
    interface View {
        fun highlightAllBrands(qualifiedList: MutableList<GetMemberBrandResult>)

        fun unHighlightAllBrands(qualifiedList: MutableList<GetMemberBrandResult>)

        fun showQualifiedBrand(qualifiedList: MutableList<GetMemberBrandResult>)

        fun showLoading()

        fun hideLoading()

        fun showEmptyBrands()

        fun showLoadingError()

        fun showSaveSuccess()

        fun showSaveFailed(msg: String)

        fun goNext(selectedBrand: MutableList<Int>, qualifiedBrandIdList: MutableList<Int>)
    }

    interface Presenter : BasePresenter {
        fun loadAllBrands(memberId: String, token: String, isEdit: Boolean = false)

        fun selectAllBrand(isSelect: Boolean)

        fun saveBrand()

        fun next()
    }
}
package com.socket9.pointube.screens.setting.mybrand

import com.socket9.pointube.base.BasePresenter
import com.socket9.pointube.screens.brand.BrandModel

/**
 * Created by ripzery on 7/26/16.
 */
interface MyBrandContract {
    interface View {

        fun showLoading()

        fun hideLoading()

        fun showMyBrands(model : MutableList<BrandModel.Response.GetMemberBrandResult>)

        fun showEmptyBrand()

        fun showLoadingError()
    }

    interface Presenter : BasePresenter {
        fun loadMyBrands()

        fun selectAll()

        fun unselectAll()
    }
}

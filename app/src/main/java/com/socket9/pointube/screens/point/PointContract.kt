package com.socket9.pointube.screens.point

import com.socket9.pointube.base.BasePresenter
import com.socket9.pointube.screens.brand.BrandModel

/**
 * Created by ripzery on 8/3/16.
 */
interface PointContract {
    interface View {
        fun showBrands(allBrands: MutableList<BrandModel.Response.GetMemberBrandResult>)

        fun showLoading()

        fun hideLoading()
    }

    interface Presenter: BasePresenter {
        fun loadBrands()
    }
}
package com.socket9.pointube.screens.point

import com.socket9.pointube.base.BasePresenter
import com.socket9.pointube.screens.brand.BrandModel
import com.socket9.pointube.screens.home.LoginModel

/**
 * Created by ripzery on 8/3/16.
 */
interface PointContract {
    interface View {
        fun showBrands(allBrands: MutableList<BrandModel.Response.GetMemberBrandResult>)

        fun showLoading()

        fun hideLoading()

        fun initUser(model: LoginModel.Response.LoginResult)
    }

    interface Presenter: BasePresenter {
        fun loadBrands()

        fun loadUser()
    }
}
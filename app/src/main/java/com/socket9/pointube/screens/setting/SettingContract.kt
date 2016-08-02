package com.socket9.pointube.screens.setting

import com.socket9.pointube.base.BasePresenter

/**
 * Created by ripzery on 7/26/16.
 */
interface SettingContract {
    interface View {
        fun showMyBrand()

        fun showMyProfile()

        fun showLoading()

        fun hideLoading()

        fun showLogout()
    }

    interface Presenter : BasePresenter {
        fun clickMyBrand()

        fun clickMyProfile()

        fun clickLogout()

    }
}
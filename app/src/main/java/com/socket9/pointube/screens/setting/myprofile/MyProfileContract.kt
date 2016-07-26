package com.socket9.pointube.screens.setting.myprofile

import com.socket9.pointube.base.BasePresenter

/**
 * Created by ripzery on 7/26/16.
 */
interface MyProfileContract {
    interface View {
        fun showMyProfile()

        fun showSaveComplete()

        fun showSaveFailed()

        fun showLoading()

        fun hideLoading()

        fun goBack()
    }

    interface Presenter : BasePresenter{

        fun loadMyProfile()

        fun saveMyProfile()

        fun back()

    }
}
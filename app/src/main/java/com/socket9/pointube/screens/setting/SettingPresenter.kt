package com.socket9.pointube.screens.setting

import org.jetbrains.anko.AnkoLogger

/**
 * Created by ripzery on 7/26/16.
 */
class SettingPresenter(var view: SettingContract.View?) : AnkoLogger, SettingContract.Presenter {
    override fun clickMyBrand() {
        view?.showMyBrand()
    }

    override fun clickMyProfile() {
        view?.showMyProfile()
    }

    override fun onCreate() {

    }

    override fun onDestroy() {
        view = null
    }

    override fun verifyLogin() {

    }
}
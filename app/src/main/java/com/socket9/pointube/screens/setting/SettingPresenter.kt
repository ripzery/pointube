package com.socket9.pointube.screens.setting

import com.socket9.pointube.manager.DataManager
import com.socket9.pointube.utils.SharedPrefUtil
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

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

    override fun clickLogout() {
        view?.showLoading()
        DataManager.logout(SharedPrefUtil.loadLoginResult()!!.id)
                .subscribe({
                    view?.hideLoading()
                    if (it.result.isSuccess) {
                        view?.showLogout()
                    } else {
                        /* Member not found. Should not be possible */
                        info { it }
                    }
                }, {
                    view?.hideLoading()
                    SharedPrefUtil.clearLogin()
                    view?.showLogout()
                    info { it }
                })
    }

    override fun onCreate() {

    }

    override fun onDestroy() {
        view = null
    }
}
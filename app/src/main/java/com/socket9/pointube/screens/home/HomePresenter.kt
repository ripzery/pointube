package com.socket9.pointube.screens.home

import com.socket9.pointube.manager.DataManager
import com.socket9.pointube.repository.brands.BrandRepo
import com.socket9.pointube.utils.LoginStateUtil
import com.socket9.pointube.utils.RealmUtil
import com.socket9.pointube.utils.SharedPrefUtil
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

/**
 * Created by Euro (ripzery@gmail.com) on 7/16/2016 AD.
 */
class HomePresenter(var view: HomeContract.View?) : HomeContract.Presenter, AnkoLogger {
    /* Variable zone */

    /**  Override UserActionsListener Interface zone **/
    override fun loadProviderList() {
        DataManager.getAllProvider()
                .subscribe({
                    view?.showProviderList(it.Results)
                }, {
                    it.printStackTrace()
                    view?.showEmptyProviderList()
                })
    }

    override fun loadPublishedProgramList() {
        DataManager.getAllPublishedProgramList()
                .map { it.copy(true, null, it.Results.filter { it.IsHot }.toMutableList(), true) }
                .subscribe({
                    view?.showPublishedProgramList(it.Results)
                }, {
                    it.printStackTrace()
                    view?.showEmptyPublishedProgramList()
                })
    }

    override fun doLogin() {
        view?.goLogin()
    }

    override fun doSignUp() {
        view?.goSignUp()
    }

    override fun onCreate() {
        loadProviderList()
        loadPublishedProgramList()

        if (LoginStateUtil.isLogin()) {
            view?.showLoggedInState()
            info { SharedPrefUtil.loadLoginResult().toString() }
        } else {
            view?.showUnLoggedInState()
        }
    }

    override fun onDestroy() {
        view = null
    }
}
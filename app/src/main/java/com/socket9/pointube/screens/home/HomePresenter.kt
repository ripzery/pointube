package com.socket9.pointube.screens.home

import com.socket9.pointube.manager.DataManager
import com.socket9.pointube.utils.LoginState
import com.socket9.pointube.utils.SharedPref
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
                    view?.showProviderList(it)
                }, {
                    info { it }
                    view?.showEmptyProviderList()
                })
    }

    override fun loadPublishedProgramList() {
        DataManager.getAllPublishedProgramList()
                .map { it.copy(true, null, it.Results.filter { it.IsHot }.toMutableList(), true) }
                .subscribe({
                    view?.showPublishedProgramList(it)
                }, {
                    info { it }
                    view?.showEmptyPublishedProgramList()
                })
    }

    override fun doLogin() {
        view?.goLogin()
    }

    override fun onCreate() {
        info { "Home : OnCreate" }
        loadProviderList()
        loadPublishedProgramList()
        if (LoginState.isLogin()) {
            view?.showLoggedInState()
            info { SharedPref.loadLoginResult().toString() }
        } else {
            view?.showUnLoggedInState()
        }
    }

    override fun onDestroy() {
        view = null
    }
}
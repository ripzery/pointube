package com.socket9.pointube.screens.home

import com.socket9.pointube.manager.DataManager
import com.socket9.pointube.utils.LoginState
import com.socket9.pointube.utils.RealmUtil
import com.socket9.pointube.utils.SharedPref
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

/**
 * Created by Euro (ripzery@gmail.com) on 7/16/2016 AD.
 */
class HomePresenter(var view: HomeContract.View?) : HomeContract.Presenter, AnkoLogger {
    /* Variable zone */
    private var allProviders: HomeModel.AllBrands? = null
    private var allPrograms: HomeModel.PublishedProgramListRepo? = null
    private var isCountProviderList: Boolean = false

    /**  Override UserActionsListener Interface zone **/
    override fun loadProviderList() {
        DataManager.getAllProvider()
                .subscribe({
                    allProviders = it
                    view?.showProviderList(it)
                    countProviderProgram()
                }, {
                    info { it }
                    view?.showEmptyProviderList()
                })
    }

    override fun loadPublishedProgramList() {
        DataManager.getAllPublishedProgramList()
                .doOnNext {
                    allPrograms = it
                }
                .map { it.copy(true, null, it.Results.filter { it.IsHot }.toMutableList(), true) }
                .subscribe({
                    view?.showPublishedProgramList(it)
                    countProviderProgram()
                }, {
                    info { it }
                    view?.showEmptyPublishedProgramList()
                })
    }

    override fun countProviderProgram() {
        if (allPrograms != null && allProviders != null && !isCountProviderList) {
            allProviders!!.Results.forEach {
                val providerId = it.Id
                it.TotalPrograms = allPrograms!!.Results.filter { it.ProviderId ==  providerId }.size
            }
            view?.updatePromotionCount(allProviders!!)
            isCountProviderList = true
        }
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
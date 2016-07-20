package com.socket9.pointube.screens.home

import com.socket9.pointube.manager.DataManager
import com.socket9.pointube.utils.LoginState
import com.socket9.pointube.utils.SharedPref
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.info
import org.jetbrains.anko.uiThread

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

    override fun doLogin() {
        view?.goLogin()
    }

    override fun onCreate() {
        info { "Home : OnCreate" }
        loadProviderList()
        if(LoginState.isLogin()){
            view?.showLoggedInState()
            info { SharedPref.loadLoginResult().toString() }
        }else{
            view?.showUnLoggedInState()
        }
    }

    override fun onDestroy() {
        view = null
    }
}
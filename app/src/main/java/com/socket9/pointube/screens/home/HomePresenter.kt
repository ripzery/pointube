package com.socket9.pointube.screens.home

import com.socket9.pointube.manager.HttpManager
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

/**
 * Created by Euro (ripzery@gmail.com) on 7/16/2016 AD.
 */
class HomePresenter(var view: HomeContract.View) : HomeContract.Presenter, AnkoLogger {

    /* Variable zone */

    init {

    }

    /**  Override UserActionsListener Interface zone **/
    override fun loadProviderList() {
        HttpManager.getAllBrands()
                .subscribe({
                    view.showProviderList(it)
                }, {
                    info { it }
                    view.showEmptyProviderList()
                })
    }

    override fun doLogin() {
        view.showLogin()
    }
}
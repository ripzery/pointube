package com.socket9.pointube.screens.home

import com.socket9.pointube.manager.DataManager
import com.socket9.pointube.repository.brands.BrandRepo
import com.socket9.pointube.screens.brand.BrandModel
import com.socket9.pointube.utils.LoginStateUtil
import com.socket9.pointube.utils.SharedPrefUtil
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

/**
 * Created by Euro (ripzery@gmail.com) on 7/16/2016 AD.
 */
class HomePresenter(var view: HomeContract.View?) : HomeContract.Presenter, AnkoLogger {
    /* Variable zone */
    private val mLoginResult: LoginModel.Response.LoginResult? by lazy { SharedPrefUtil.loadLoginResult() }

    /**  Override UserActionsListener Interface zone **/
    override fun loadProviderList() {
        if (mLoginResult == null) { /* Don't get point when user doesn't logged in */
            DataManager.getAllProvider()
                    .subscribe({
                        view?.showProviderList(it.Results)
                    }, {
                        it.printStackTrace()
                        view?.showEmptyProviderList()
                    })
        } else { /* Get point with provider when user is logged in */
            var memberBrand: BrandModel.Response.GetMemberBrand? = null
            DataManager.getAllBrandMember(BrandModel.Request.GetMemberBrand(mLoginResult!!.id.toString(), mLoginResult!!.token!!))
                    .doOnNext { memberBrand = it }
                    .flatMap { DataManager.getAllProvider() }
                    .map {
                        it.Results.forEach {
                            val providerItem = it
                            val memberBrandItem = memberBrand!!.Results.find { it.Id == providerItem.Id }
                            if (memberBrandItem != null) {
                                providerItem.Points = memberBrandItem.Points.toString()
                            }
                        }
                        it
                    }
                    .subscribe({
                        view?.showProviderList(it.Results)
                    }, {
                        it.printStackTrace()
                        view?.showEmptyProviderList()
                    })

        }
    }

    override fun clickBrand(brand: BrandRepo) {
        view?.showProgramListByBrand(brand.Id, brand.Name)
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
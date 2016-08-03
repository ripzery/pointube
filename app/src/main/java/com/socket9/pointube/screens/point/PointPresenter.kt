package com.socket9.pointube.screens.point

import com.socket9.pointube.manager.DataManager
import com.socket9.pointube.screens.brand.BrandModel
import com.socket9.pointube.screens.home.LoginModel
import com.socket9.pointube.utils.SharedPrefUtil
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

/**
 * Created by ripzery on 8/3/16.
 */
class PointPresenter(var view: PointContract.View?) : AnkoLogger, PointContract.Presenter {
    private val mLoginResult: LoginModel.Response.LoginResult by lazy { SharedPrefUtil.loadLoginResult()!! }

    override fun loadUser() {
        view?.initUser(mLoginResult)
    }

    override fun loadBrands() {
        view?.showLoading()
        DataManager.getAllBrandSelectedMember(BrandModel.Request.GetMemberSelectBrand(mLoginResult.id.toString(), mLoginResult.token.toString(), true))
                .subscribe({
                    view?.hideLoading()

                    view?.showBrands(it.Brands)

                }, {
                    view?.hideLoading()

                    info { it }

                })
    }

    override fun onCreate() {

    }

    override fun onDestroy() {
        view = null
    }

}
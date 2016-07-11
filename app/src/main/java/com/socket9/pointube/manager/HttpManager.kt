package com.socket9.pointube.manager

import com.socket9.pointube.screens.home.HomeModel
import com.socket9.pointube.screens.home.LoginModel
import com.socket9.pointube.utils.RetrofitUtils
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Euro (ripzery@gmail.com) on 7/11/2016 AD.
 */
object HttpManager {
    fun getAllBrands(): Observable<HomeModel.AllBrands> {
        return RetrofitUtils.getInstance().getAllProvider()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun login(email: String, password: String): Observable<LoginModel.Login> {
        return RetrofitUtils.getInstance().login(email, password)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
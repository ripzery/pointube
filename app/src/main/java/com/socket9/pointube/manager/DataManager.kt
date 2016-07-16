package com.socket9.pointube.manager

import com.socket9.pointube.screens.home.HomeModel
import com.socket9.pointube.screens.home.LoginModel
import com.socket9.pointube.screens.register.RegisterModel
import com.socket9.pointube.utils.RetrofitUtils
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Euro (ripzery@gmail.com) on 7/11/2016 AD.
 */
object DataManager : AnkoLogger {
    /* For get all brands */
    fun getAllProvider(): Observable<HomeModel.AllBrands> {
        return Observable.concat(DiskProviderManager.getAllProvider(), NetworkProviderManager.getAllProvider())
                .first { it.Results.size > 0 }
                .doOnNext { info { it.IsDisk } }
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    /* For login */
    fun login(email: String, password: String): Observable<LoginModel.Login> {
        return RetrofitUtils.getInstance().login(email, password)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    /* For create member */
    fun register(firstName: String,
                 lastName: String,
                 citizenId: String,
                 passport: String,
                 mobile: String?,
                 email: String,
                 password: String,
                 gender: String,
                 address: String,
                 birthday: String,
                 firstNameEn: String,
                 lastNameEN: String): Observable<RegisterModel.Response.Register> {
        return RetrofitUtils.getInstance().register(RegisterModel.Request.Register(firstName, lastName,
                firstNameEn,
                lastNameEN,
                citizenId,
                passport,
                mobile,
                email,
                password,
                gender,
                address,
                birthday))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}
package com.socket9.pointube.manager

import com.socket9.pointube.screens.home.HomeModel
import com.socket9.pointube.screens.home.LoginModel
import com.socket9.pointube.screens.register.RegisterModel
import com.socket9.pointube.utils.SharedPrefUtil
import org.jetbrains.anko.AnkoLogger
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Euro (ripzery@gmail.com) on 7/11/2016 AD.
 */
object DataManager : AnkoLogger {
    /*
    * Cache
    * For get all brands
    * */
    fun getAllProvider(): Observable<HomeModel.AllBrands> {
        return Observable.concat(DiskProviderManager.getAllProvider(), NetworkProviderManager.getAllProvider())
                .first { it.Results.size > 0 }
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { DiskProviderManager.getAllProvider() }
    }

    /*
    * Cache
    * For get all published program
    * */
    fun getAllPublishedProgramList(): Observable<HomeModel.PublishedProgramListRepo> {
        return Observable.concat(DiskProviderManager.getPublishedProgramList(), NetworkProviderManager.getPublishedProgramList())
                .first { it.Results.size > 0 }
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { DiskProviderManager.getPublishedProgramList() }
    }

    /* For login */
    fun login(email: String, password: String): Observable<LoginModel.Login> {
        return NetworkProviderManager.login(email, password)
                .doOnNext {
                    if (it.result.id != 0) SharedPrefUtil.saveLoginResult(it.result)
                }
    }

    /* For create member */
    fun register(model: RegisterModel.Request.Register): Observable<RegisterModel.Response.Register> {
        return NetworkProviderManager.register(model)
    }

    fun saveMobileNo(id: Int, phoneNumber: String): Observable<RegisterModel.Response.SaveMobileNo> {
        return NetworkProviderManager.saveMobileNo(id, phoneNumber)
    }

    fun verifyPhoneNumber(id: Int, otp: String): Observable<RegisterModel.Response.VerifyPhoneNumber> {
        return NetworkProviderManager.verifyPhoneNumber(id, otp)
    }

}
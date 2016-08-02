package com.socket9.pointube.manager

import com.socket9.pointube.screens.brand.BrandModel
import com.socket9.pointube.screens.home.HomeModel
import com.socket9.pointube.screens.home.LoginModel
import com.socket9.pointube.screens.register.RegisterModel
import com.socket9.pointube.screens.setting.SettingModel
import com.socket9.pointube.utils.DataExtendingUtil
import com.socket9.pointube.utils.RealmUtil
import com.socket9.pointube.utils.RetrofitUtils
import io.realm.RealmModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Euro (ripzery@gmail.com) on 7/16/2016 AD.
 */
object NetworkProviderManager : AnkoLogger {
    fun getAllProvider(): Observable<HomeModel.AllBrands> {
        return RetrofitUtils.getInstance().getAllProvider()
                .doOnNext { saveToDisk(it.Results) }
                .flatMap { DataManager.getAllPublishedProgramList() }
                .doOnNext { DataExtendingUtil.countProviderProgram() }
                .flatMap { DiskProviderManager.getAllProvider() }
    }

    fun getPublishedProgramList(): Observable<HomeModel.PublishedProgramListRepo> {
        return RetrofitUtils.getInstance().getPublishProgramList()
                .doOnNext { saveToDisk(it.Results) }
                .flatMap { DiskProviderManager.getPublishedProgramList() }
                .doOnNext { DataExtendingUtil.countProviderProgram() }
    }

    fun login(model: LoginModel.Request.Login): Observable<LoginModel.Response.Login> {
        return RetrofitUtils.getInstance().login(model)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun logout(memberId: Int): Observable<LoginModel.Response.Login> {
        return RetrofitUtils.getInstance().logout(memberId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun register(model: RegisterModel.Request.Register): Observable<RegisterModel.Response.Register> {
        return RetrofitUtils.getInstance().register(model)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun updateProfile(model: SettingModel.Request.UpdateProfile): Observable<SettingModel.Response.UpdateProfile> {
        return RetrofitUtils.getInstance().updateProfile(model)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun saveMobileNo(id: Int, phoneNumber: String): Observable<RegisterModel.Response.SaveMobileNo> {
        return RetrofitUtils.getInstance().saveMobileNo(RegisterModel.Request.SaveMobileNo(id, phoneNumber))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun verifyPhoneNumber(id: Int, otp: String): Observable<RegisterModel.Response.VerifyPhoneNumber> {
        return RetrofitUtils.getInstance().verifyPhoneNumber(RegisterModel.Request.VerifyPhoneNumber(id, otp))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getAllBrandMember(memberBrand: BrandModel.Request.GetMemberBrand): Observable<BrandModel.Response.GetMemberBrand> {
        return RetrofitUtils.getInstance().getMemberBrand(memberBrand)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun saveSelectedBrand(brandModel: BrandModel.Request.SaveBrand): Observable<BrandModel.Response.SaveBrand> {
        return RetrofitUtils.getInstance().saveBrand(brandModel)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    private fun <T : RealmModel> saveToDisk(networkData: MutableList<T>?) {
        if (networkData != null && networkData.size > 0) {
            RealmUtil.write {
                it.copyToRealmOrUpdate(networkData)
            }
            info { networkData[0].javaClass.simpleName + " is saved successful" }
        }
    }
}
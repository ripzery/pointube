package com.socket9.pointube.manager

import com.socket9.pointube.repository.brands.BrandRepo
import com.socket9.pointube.repository.brands.GetMemberBrand
import com.socket9.pointube.repository.brands.GetMemberSelectBrand
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
                .doOnNext { setChildCoverPath(it.Results) }
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

    fun changePassword(model: LoginModel.Request.ChangePassword): Observable<LoginModel.Response.ChangePassword> {
        return RetrofitUtils.getInstance().changePassword(model)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun forgotPassword(email: String): Observable<LoginModel.Response.ForgotPassword> {
        return RetrofitUtils.getInstance().forgotPassword(email)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun forgotPasswordCheckPin(checkUserWithPin: LoginModel.Request.CheckUserWithPin): Observable<LoginModel.Response.CheckUserWithPin> {
        return RetrofitUtils.getInstance().forgotPasswordCheckPin(checkUserWithPin)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun forgotPasswordNewPassword(newPassword: LoginModel.Request.ResetPassword) : Observable<LoginModel.Response.ResetPassword> {
        return RetrofitUtils.getInstance().resetPassword(newPassword)
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

    fun getAllBrandMember(memberBrand: BrandModel.Request.GetMemberBrand): Observable<GetMemberBrand> {
        return RetrofitUtils.getInstance().getMemberBrand(memberBrand)
                .doOnNext { saveToDisk(it) }
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { DiskProviderManager.getAllBrandMember() }
    }

    fun getAllBrandSelectMember(memberBrand: BrandModel.Request.GetMemberSelectBrand): Observable<GetMemberSelectBrand> {
        return RetrofitUtils.getInstance().getMemberSelectedBrand(memberBrand)
                .doOnNext { saveToDisk(it) }
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { DiskProviderManager.getAllBrandSelectMember() }
    }


    fun saveSelectedBrand(brandModel: BrandModel.Request.SaveBrand): Observable<BrandModel.Response.SaveBrand> {
        return RetrofitUtils.getInstance().saveBrand(brandModel)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    private fun setChildCoverPath(brandList: MutableList<BrandRepo>) {
        brandList.forEach {
            val cover = it.CoverPath
            it.Units.forEach { it.CoverPath = cover }
        }
    }

    private fun <T : RealmModel> saveToDisk(networkData: MutableList<T>?) {
        if (networkData != null && networkData.size > 0) {
            RealmUtil.write {
                it.copyToRealmOrUpdate(networkData)
            }
            info { networkData[0].javaClass.simpleName + " is saved successful" }
        }
    }

    private fun <T : RealmModel> saveToDisk(networkData: T?) {
        if (networkData != null) {
            RealmUtil.write {
                it.copyToRealmOrUpdate(networkData)
            }
            info { networkData.javaClass.simpleName + " is saved successful" }
        }
    }
}
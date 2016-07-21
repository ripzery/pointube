package com.socket9.pointube.manager

import com.socket9.pointube.screens.home.HomeModel
import com.socket9.pointube.utils.DataExtendingUtil
import com.socket9.pointube.utils.RealmUtil
import com.socket9.pointube.utils.RetrofitUtils
import io.realm.Realm
import io.realm.RealmModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import rx.Observable

/**
 * Created by Euro (ripzery@gmail.com) on 7/16/2016 AD.
 */
object NetworkProviderManager : AnkoLogger {
    fun getAllProvider(): Observable<HomeModel.AllBrands> {
        return RetrofitUtils.getInstance().getAllProvider()
                .doOnNext { saveToDisk(it.Results) }
                .flatMap { DiskProviderManager.getAllProvider() }
                .doOnNext { DataExtendingUtil.countProviderProgram() }
    }

    fun getPublishedProgramList(): Observable<HomeModel.PublishedProgramListRepo> {
        return RetrofitUtils.getInstance().getPublishProgramList()
                .doOnNext { saveToDisk(it.Results) }
                .flatMap { DiskProviderManager.getPublishedProgramList() }
                .doOnNext { DataExtendingUtil.countProviderProgram() }
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
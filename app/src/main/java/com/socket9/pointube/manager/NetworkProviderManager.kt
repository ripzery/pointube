package com.socket9.pointube.manager

import com.socket9.pointube.screens.home.HomeModel
import com.socket9.pointube.utils.RetrofitUtils
import io.realm.Realm
import io.realm.RealmModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import rx.Observable

/**
 * Created by Euro (ripzery@gmail.com) on 7/16/2016 AD.
 */
object NetworkProviderManager: AnkoLogger {
    fun getAllProvider(): Observable<HomeModel.AllBrands> {
        return RetrofitUtils.getInstance().getAllProvider().doOnNext {
            saveToDisk(it.Results)
        }
    }

    private fun <T : RealmModel> saveToDisk(networkData: MutableList<T>) {
        if (networkData != null && networkData.size > 0) {
            val realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            realm.copyToRealmOrUpdate(networkData)
            realm.commitTransaction()
            info { realm.path }
            realm.close()
        }
    }
}
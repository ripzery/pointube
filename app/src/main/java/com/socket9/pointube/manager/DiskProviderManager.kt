package com.socket9.pointube.manager

import com.socket9.pointube.repository.brands.BrandRepo
import com.socket9.pointube.screens.home.HomeModel
import io.realm.Realm
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import rx.Observable

/**
 * Created by Euro (ripzery@gmail.com) on 7/16/2016 AD.
 */
object DiskProviderManager: AnkoLogger {

    fun getAllProvider() : Observable<HomeModel.AllBrands>{
        val realm = Realm.getDefaultInstance()
        val allBrands = realm.where(BrandRepo::class.java).findAll()
        return Observable.just(HomeModel.AllBrands(true, allBrands.toMutableList(), true))
    }
}
package com.socket9.pointube.manager

import com.socket9.pointube.repository.brands.BrandRepo
import com.socket9.pointube.repository.programs.PublishedProgramItemRepo
import com.socket9.pointube.screens.home.HomeModel
import com.socket9.pointube.utils.RealmUtil
import io.realm.Realm
import org.jetbrains.anko.AnkoLogger
import rx.Observable

/**
 * Created by Euro (ripzery@gmail.com) on 7/16/2016 AD.
 */
object DiskProviderManager : AnkoLogger {
    fun getAllProvider(): Observable<HomeModel.AllBrands> {
        val allBrands = RealmUtil.readAll(BrandRepo::class.java)
        return Observable.just(HomeModel.AllBrands(true, allBrands.toMutableList(), true))
    }

    fun getPublishedProgramList(): Observable<HomeModel.PublishedProgramListRepo> {
        val allPublishedProgramList = RealmUtil.readAll(PublishedProgramItemRepo::class.java)
        return Observable.just(HomeModel.PublishedProgramListRepo(true, null, allPublishedProgramList.toMutableList(), true))
    }
}
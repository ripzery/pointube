package com.socket9.pointube.manager

import com.socket9.pointube.repository.brands.BrandRepo
import com.socket9.pointube.repository.brands.GetMemberBrand
import com.socket9.pointube.repository.brands.GetMemberSelectBrand
import com.socket9.pointube.repository.programs.PublishedProgramItemRepo
import com.socket9.pointube.screens.home.HomeModel
import com.socket9.pointube.utils.RealmUtil
import io.realm.RealmResults
import org.jetbrains.anko.AnkoLogger
import rx.Observable

/**
 * Created by Euro (ripzery@gmail.com) on 7/16/2016 AD.
 */
object DiskProviderManager : AnkoLogger {
    /* Brands */
    fun getAllProvider(): Observable<HomeModel.AllBrands> {
        val allBrands = RealmUtil.readAll(BrandRepo::class.java)
        return Observable.just(HomeModel.AllBrands(true, allBrands.toMutableList(), true))
    }

    fun getAllBrandMember(): Observable<GetMemberBrand> {
        val allBrands = RealmUtil.readAll(GetMemberBrand::class.java)
        return Observable.just(allBrands.first())
    }

    fun getAllBrandSelectMember(): Observable<GetMemberSelectBrand>{
        val allBrands = RealmUtil.readAll(GetMemberSelectBrand::class.java)
        return Observable.just(allBrands.first())
    }

    /* Programs */
    fun getPublishedProgramList(): Observable<HomeModel.PublishedProgramListRepo> {
        val allPublishedProgramList = RealmUtil.readAll(PublishedProgramItemRepo::class.java)
        return Observable.just(HomeModel.PublishedProgramListRepo(true, null, allPublishedProgramList.toMutableList(), true))
    }

    fun getPublishedProgramListByProgramType(programType: Int = 0): Observable<MutableList<PublishedProgramItemRepo>> {
        val allPublishedProgramList = RealmUtil.readAll(PublishedProgramItemRepo::class.java)
        if (programType < 11)
            return Observable.just(allPublishedProgramList.filter { it.ProgramType == programType }.toMutableList())
        else {
            return Observable.just(allPublishedProgramList.filter { it.IsHot }.toMutableList())
        }
    }

    fun getPublishedProgramById(programId: Int): Observable<PublishedProgramItemRepo> {
        val allPublishedProgramList = RealmUtil.readAll(PublishedProgramItemRepo::class.java)
        return Observable.just(allPublishedProgramList.find { it.Id == programId })
    }

    fun getProviderById(id: Int = 0): Observable<BrandRepo> {
        val brand = RealmUtil.readAll(BrandRepo::class.java).find { it.Id == id }
        return Observable.just(brand)
    }

    fun getPublishedProgramByProviderId(providerId: Int): Observable<MutableList<PublishedProgramItemRepo>> {
        val allPublishedProgramList = RealmUtil.readAll(PublishedProgramItemRepo::class.java)
        return Observable.just(allPublishedProgramList.filter { it.ProviderId == providerId }.toMutableList())
    }

}
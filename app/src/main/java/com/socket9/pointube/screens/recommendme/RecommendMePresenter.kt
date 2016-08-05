package com.socket9.pointube.screens.recommendme

import com.socket9.pointube.manager.DataManager
import com.socket9.pointube.repository.programs.PublishedProgramItemRepo
import com.socket9.pointube.screens.brand.BrandModel
import com.socket9.pointube.screens.home.LoginModel
import com.socket9.pointube.utils.SharedPrefUtil
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

/**
 * Created by ripzery on 8/5/16.
 */

class RecommendMePresenter(var view: RecommendMeContract.View?) : RecommendMeContract.Presenter, AnkoLogger {
    private val mLoginResult: LoginModel.Response.LoginResult? by lazy { SharedPrefUtil.loadLoginResult() }

    override fun loadRecommendMe(brandId: Int) {
        view?.showLoading()
        var allPublishedProgram: MutableList<PublishedProgramItemRepo> = mutableListOf()
        DataManager.getPublishedProgramByProviderId(brandId)
                .doOnNext { allPublishedProgram = it }
                .flatMap { DataManager.getAllBrandMember(BrandModel.Request.GetMemberBrand(mLoginResult!!.id.toString(), mLoginResult!!.token!!)) }
                .map {
                    val allBrands = it.Results
                    allPublishedProgram.filter { it.Point <= allBrands.find { it.Name.equals("The 1 Card") }!!.Points }
                }
                .subscribe({
                    view?.hideLoading()
                    view?.showRecommendMe(allPublishedProgram)
                }, {
                    view?.hideLoading()
                    info { it }
                })
    }

    override fun loadBrand(brandId: Int) {
        view?.showLoading()
        DataManager.getProviderById(brandId)
                .subscribe({
                    view?.hideLoading()
                    view?.showBrandInfo(it)
                }, {
                    view?.hideLoading()
                    info { it }
                })
    }

    override fun clickProgram(programId: Int) {
        view?.showProgramDetail(programId)
    }

    override fun onCreate() {

    }

    override fun onDestroy() {
        view = null
    }

}
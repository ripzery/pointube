package com.socket9.pointube.screens.recommendme

import com.socket9.pointube.manager.DataManager
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

/**
 * Created by ripzery on 8/5/16.
 */

class RecommendMePresenter(var view: RecommendMeContract.View?) : RecommendMeContract.Presenter, AnkoLogger {
    override fun loadRecommendMe(brandId: Int) {
//        DataManager
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
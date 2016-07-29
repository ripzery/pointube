package com.socket9.pointube.screens.promotion.detail

import com.socket9.pointube.manager.DataManager
import com.socket9.pointube.repository.programs.PublishedProgramItemRepo
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

/**
 * Created by ripzery on 7/28/16.
 */
class PromotionDetailPresenter(var view: PromotionDetailContract.View?) : PromotionDetailContract.Presenter, AnkoLogger {

    override fun getDetail(programId: Int) {
        var publishedProgramModel: PublishedProgramItemRepo? = null
        DataManager.getPublishedProgramById(programId)
                .doOnNext { publishedProgramModel = it }
                .flatMap { DataManager.getProviderById(it.ProviderId) }
                .subscribe({
                    view?.showDetail(publishedProgramModel!!, it.LogoPath)
                }, {
                    info("Show detail error $it")
                })
    }

    override fun back() {
        view?.goBack()
    }

    override fun onCreate() {

    }

    override fun onDestroy() {
        view = null
    }

}
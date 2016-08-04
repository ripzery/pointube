package com.socket9.pointube.screens.promotion.list

import com.socket9.pointube.manager.DataManager
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.warn

/**
 * Created by ripzery on 8/4/16.
 */
class ProgramListPresenter(var view: ProgramListContract.View?) : AnkoLogger, ProgramListContract.Presenter {
    override fun loadCover(brandId: Int) {
        DataManager.getProviderById(brandId)
                .subscribe({
                    view?.showCover(it.CoverPath)
                }, {
                    warn { it }
                })
    }

    override fun loadProgramList(brandId: Int) {
        view?.showLoading()
        DataManager.getPublishedProgramByProviderId(brandId)
                .subscribe({
                    view?.hideLoading()
                    view?.showProgramList(it)
                }, {
                    view?.hideLoading()
                    warn { it }
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
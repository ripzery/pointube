package com.socket9.pointube.screens.promotion.list.any

import com.socket9.pointube.manager.DataManager
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.warn

/**
 * Created by ripzery on 8/4/16.
 */
class ProgramListPresenter(var view: ProgramListContract.View?) : AnkoLogger, ProgramListContract.Presenter {
    override fun loadProgramList(brandId: Int, unitId: Int) {
        view?.showLoading()
        info { "$brandId,$unitId" }
        DataManager.getPublishedProgramByNotNull(brandId, unitId)
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
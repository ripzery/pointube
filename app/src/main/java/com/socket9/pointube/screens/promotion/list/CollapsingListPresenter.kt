package com.socket9.pointube.screens.promotion.list

import com.socket9.pointube.manager.DataManager
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.warn

/**
 * Created by ripzery on 8/9/16.
 */
class CollapsingListPresenter(var view: CollapsingListContract.View?) : CollapsingListContract.Presenter, AnkoLogger {
    override fun loadCover(brandId: Int) {
        DataManager.getProviderById(brandId)
                .subscribe({
                    view?.showCover(it.CoverPath)
                }, {
                    warn { it }
                })
    }

    override fun onCreate() {

    }

    override fun onDestroy() {

    }

}
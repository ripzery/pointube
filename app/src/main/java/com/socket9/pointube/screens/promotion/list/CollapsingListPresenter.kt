package com.socket9.pointube.screens.promotion.list

import com.socket9.pointube.manager.DataManager
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.warn

/**
 * Created by ripzery on 8/9/16.
 */
class CollapsingListPresenter(var view: CollapsingListContract.View?) : CollapsingListContract.Presenter, AnkoLogger {
    override fun selectPromotionList(brandName: String) {
        DataManager.getProviderByName(brandName)
                .flatMap { DataManager.getBrandUnitByName(brandName) }
                .subscribe({
                    if (it.Name.equals("Royal Orchid Plus")) {
                        view?.showThaiAirwayPromotionList()
                    } else {
                        view?.showDefaultPromotionList()
                    }
                }, {
                    warn { it }
                })
    }

    override fun loadCover(name: String) {
        DataManager.getProviderByName(name)
                .flatMap { DataManager.getBrandUnitByName(name) }
                .subscribe({
                    info { it }
                    view?.showCover(it.CoverPath)
                }, {
                    warn { it }
                })
    }

    override fun onCreate() {

    }

    override fun onDestroy() {
        view = null
    }

}
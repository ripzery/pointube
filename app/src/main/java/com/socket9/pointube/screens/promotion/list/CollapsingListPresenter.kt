package com.socket9.pointube.screens.promotion.list

import com.socket9.pointube.manager.DataManager
import com.socket9.pointube.repository.brands.BrandRepo
import com.socket9.pointube.repository.brands.BrandUnitRepo
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.warn
import rx.Observable

/**
 * Created by ripzery on 8/9/16.
 */
class CollapsingListPresenter(var view: CollapsingListContract.View?) : CollapsingListContract.Presenter, AnkoLogger {
    override fun selectPromotionList(brandName: String) {
        DataManager.getProviderByName(brandName)
                .flatMap {
                    if (it == null) DataManager.getBrandUnitByName(brandName)
                    else Observable.just(it)
                }
                .subscribe({
                    val name: String
                    try {
                        name = (it as BrandRepo).Name
                    } catch (ex: Exception) {
                        name = (it as BrandUnitRepo).Name
                    }

                    if (name.equals("Royal Orchid Plus")) {
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
                .flatMap {
                    if (it == null) DataManager.getBrandUnitByName(name)
                    else Observable.just(it)
                }
                .subscribe({
                    val path: String
                    try {
                        path = (it as BrandRepo).CoverPath
                    } catch (ex: Exception) {
                        path = (it as BrandUnitRepo).CoverPath
                    }

                    view?.showCover(path)
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
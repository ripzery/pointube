package com.socket9.pointube.screens.promotion.list

import com.socket9.pointube.manager.DataManager
import com.socket9.pointube.repository.brands.BrandRepo
import com.socket9.pointube.repository.brands.BrandUnitRepo
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.warn

/**
 * Created by ripzery on 8/9/16.
 */
class CollapsingListPresenter(var view: CollapsingListContract.View?) : CollapsingListContract.Presenter, AnkoLogger {
    override fun selectPromotionList(brandId: Int, unitId: Int) {
        DataManager.getProviderBrandUnitByNotNull(brandId, unitId)
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

    override fun loadCover(brandId: Int, unitId: Int) {
        DataManager.getProviderBrandUnitByNotNull(brandId, unitId)
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
package com.socket9.pointube.screens

import com.socket9.pointube.manager.DataManager
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.warn

/**
 * Created by ripzery on 8/15/16.
 */
class FilteredBrandPresenter(var view: FilteredBrandContract.View?) : FilteredBrandContract.Presenter, AnkoLogger {
    override fun loadAllBrands() {
        DataManager.getAllProvider()
                .subscribe({
                    view?.onLoadAllBrands(it.Results)
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
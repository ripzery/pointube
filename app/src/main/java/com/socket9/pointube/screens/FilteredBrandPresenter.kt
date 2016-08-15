package com.socket9.pointube.screens

/**
 * Created by ripzery on 8/15/16.
 */
class FilteredBrandPresenter(var view: FilteredBrandContract.View?) : FilteredBrandContract.Presenter{
    override fun loadAllBrands() {
//        view?.onLoadAllBrands()

    }

    override fun onCreate() {

    }

    override fun onDestroy() {
        view = null
    }

}
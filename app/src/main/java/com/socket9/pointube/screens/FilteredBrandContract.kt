package com.socket9.pointube.screens

import com.socket9.pointube.base.BasePresenter
import com.socket9.pointube.repository.brands.BrandRepo

/**
 * Created by ripzery on 8/15/16.
 */
interface FilteredBrandContract {
    interface View {
        fun onLoadAllBrands(allBrands: MutableList<BrandRepo>)
    }

    interface Presenter : BasePresenter {
        fun loadAllBrands()
    }
}

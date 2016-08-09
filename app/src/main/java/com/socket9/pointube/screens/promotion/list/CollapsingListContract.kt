package com.socket9.pointube.screens.promotion.list

import com.socket9.pointube.base.BasePresenter

/**
 * Created by ripzery on 8/9/16.
 */
interface CollapsingListContract {
    interface View {
        fun showCover(path: String)

        fun showDefaultPromotionList()

        fun showThaiAirwayPromotionList()
    }

    interface Presenter : BasePresenter {
        fun loadCover(brandId: Int)

        fun selectPromotionList(brandId: Int)
    }
}
package com.socket9.pointube.screens.promotion.detail

import com.socket9.pointube.base.BasePresenter
import com.socket9.pointube.repository.programs.PublishedProgramItemRepo

/**
 * Created by ripzery on 7/28/16.
 */
interface PromotionDetailContract : BasePresenter {
    interface View {
        fun showDetail(model: PublishedProgramItemRepo)


    }

    interface Presenter {

    }
}
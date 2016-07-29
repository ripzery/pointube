package com.socket9.pointube.screens.promotion.main.program_type_item

import com.socket9.pointube.base.BasePresenter
import com.socket9.pointube.repository.programs.PublishedProgramItemRepo

/**
 * Created by ripzery on 7/26/16.
 */
interface PromotionProgramTypeContract {
    interface View {
        fun showAllProgram(list: MutableList<PublishedProgramItemRepo>)

        fun showProgramDetail(id: Int)

        fun showBackgroundColor(color: Int)

        fun showProgramEmpty()
    }

    interface Presenter : BasePresenter {
        fun loadAllProgramByType(programType: Int = 0)

        fun setBackgroundColor(programType: Int)

        fun clickPromotion(id: Int)
    }
}
package com.socket9.pointube.screens.promotion.list.any

import com.socket9.pointube.base.BasePresenter
import com.socket9.pointube.repository.programs.PublishedProgramItemRepo

/**
 * Created by ripzery on 8/4/16.
 */
interface ProgramListContract {
    interface View {
        fun showProgramList(newList: MutableList<PublishedProgramItemRepo>)

        fun showLoading()

        fun hideLoading()

        fun showProgramDetail(programId: Int)
    }

    interface Presenter : BasePresenter {
        fun loadProgramList(brandId: Int)

        fun clickProgram(programId: Int)
    }
}
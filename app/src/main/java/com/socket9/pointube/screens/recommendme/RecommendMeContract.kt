package com.socket9.pointube.screens.recommendme

import com.socket9.pointube.base.BasePresenter
import com.socket9.pointube.repository.brands.GetMemberBrandResult
import com.socket9.pointube.repository.programs.PublishedProgramItemRepo

/**
 * Created by ripzery on 8/5/16.
 */
interface RecommendMeContract {
    interface View {
        fun showRecommendMe(list: MutableList<PublishedProgramItemRepo>)

        fun showBrandInfo(model: GetMemberBrandResult)

        fun showProgramDetail(programId: Int)

        fun showLoading()

        fun hideLoading()

        fun showEmptyView()
    }

    interface Presenter : BasePresenter {
        fun loadRecommendMe(brandId: Int)

        fun clickProgram(programId: Int)
    }
}
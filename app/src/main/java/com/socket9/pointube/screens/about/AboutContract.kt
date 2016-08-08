package com.socket9.pointube.screens.about

import com.socket9.pointube.base.BasePresenter

/**
 * Created by ripzery on 8/8/16.
 */
interface AboutContract {
    interface View {
        fun showAboutDetail(position: Int)

        fun showAboutList(list: MutableList<AboutItem>)
    }

    interface Presenter : BasePresenter {
        fun clickAboutDetail(position: Int)

        fun loadAboutList()
    }
}
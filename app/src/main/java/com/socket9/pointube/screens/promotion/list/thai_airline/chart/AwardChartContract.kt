package com.socket9.pointube.screens.promotion.list.thai_airline.chart

import com.socket9.pointube.base.BasePresenter

/**
 * Created by ripzery on 8/11/16.
 */
interface AwardChartContract {
    interface View {
        fun showChartDetail(position: Int)

        fun showChartList(list: MutableList<AwardChartModel>)
    }

    interface Presenter : BasePresenter {
        fun loadCharts()

        fun clickChart(position: Int)
    }
}
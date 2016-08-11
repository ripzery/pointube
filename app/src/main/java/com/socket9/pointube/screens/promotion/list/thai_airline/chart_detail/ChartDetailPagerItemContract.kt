package com.socket9.pointube.screens.promotion.list.thai_airline.chart_detail

import com.socket9.pointube.base.BasePresenter
import com.socket9.pointube.screens.promotion.list.thai_airline.chart.AwardPriceTrip

/**
 * Created by ripzery on 8/11/16.
 */
interface ChartDetailPagerItemContract {
    interface View {
        fun showChartList(model: AwardPriceTrip, title: String, subtitle: String, description: String)
    }

    interface Presenter : BasePresenter {
        fun loadChartDetail(isRound: Boolean, position: Int)
    }
}
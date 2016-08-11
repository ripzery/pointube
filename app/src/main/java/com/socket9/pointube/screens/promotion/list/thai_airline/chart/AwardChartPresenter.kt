package com.socket9.pointube.screens.promotion.list.thai_airline.chart

import com.socket9.pointube.manager.DiskProviderManager

/**
 * Created by ripzery on 8/11/16.
 */
class AwardChartPresenter(var view: AwardChartContract.View?) : AwardChartContract.Presenter {
    override fun loadCharts() {
        DiskProviderManager.getAirlineAwardChartList()
                .subscribe {
                    view?.showChartList(it)
                }
    }

    override fun clickChart(position: Int) {
        view?.showChartDetail(position)
    }

    override fun onCreate() {

    }

    override fun onDestroy() {
        view = null
    }
}

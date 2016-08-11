package com.socket9.pointube.screens.promotion.list.thai_airline.chart_detail

import com.socket9.pointube.manager.DataManager

/**
 * Created by ripzery on 8/11/16.
 */
class ChartDetailPagerItemPresenter(var view: ChartDetailPagerItemContract.View?) : ChartDetailPagerItemContract.Presenter {
    override fun loadChartDetail(isRound: Boolean, position: Int) {
        DataManager.getAirlineAwardChartList().subscribe {
            view?.showChartList(if (isRound) it[position].priceRound else it[position].priceOneWay, it[position].title, it[position].subtitle, it[position].description)
        }
    }

    override fun onCreate() {

    }

    override fun onDestroy() {
        view = null
    }

}
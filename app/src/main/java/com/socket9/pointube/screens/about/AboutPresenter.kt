package com.socket9.pointube.screens.about

/**
 * Created by ripzery on 8/8/16.
 */
class AboutPresenter(var view: AboutContract.View?) : AboutContract.Presenter {
    override fun clickAboutDetail(position: Int) {
        view?.showAboutDetail(position)
    }

    override fun loadAboutList() {
        /*  TODO: create list and send to view*/
    }

    override fun onCreate() {

    }

    override fun onDestroy() {
        view = null
    }

}
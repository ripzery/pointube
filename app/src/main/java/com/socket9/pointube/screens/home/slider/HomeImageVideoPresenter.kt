package com.socket9.pointube.screens.home.slider

/**
 * Created by ripzery on 7/20/16.
 */
class HomeImageVideoPresenter(var view: HomeImageVideoContract.View?) : HomeImageVideoContract.Presenter {
    override fun load(isVideo: Boolean, path: String) {
        if(isVideo) view?.showVideo(path) else view?.showImage(path)
    }

    override fun clicked() {
        view?.showTouch("Touch, touch, touch!")
    }

    override fun onCreate() {

    }

    override fun onDestroy() {
        view = null
    }

}
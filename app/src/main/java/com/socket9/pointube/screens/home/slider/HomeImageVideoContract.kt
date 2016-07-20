package com.socket9.pointube.screens.home.slider

import com.socket9.pointube.base.BasePresenter

/**
 * Created by ripzery on 7/20/16.
 */
interface HomeImageVideoContract{
    interface View {
        fun showImage(path: String)

        fun showVideo(path: String)

        fun showTouch(msg: String)
    }

    interface Presenter : BasePresenter{
        fun load(isVideo: Boolean, path: String)

        fun clicked()
    }
}
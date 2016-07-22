package com.socket9.pointube.screens.register.otp

import org.jetbrains.anko.AnkoLogger

/**
 * Created by ripzery on 7/22/16.
 */
class OtpPresenter(var view: OtpContract.View?) : AnkoLogger, OtpContract.Presenter {
    override fun verifyOtp(otp: String) {

    }

    override fun register() {

    }

    override fun onCreate() {

    }

    override fun onDestroy() {
        view = null
    }

}
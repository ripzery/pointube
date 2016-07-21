package com.socket9.pointube.screens.register.phone

import org.jetbrains.anko.AnkoLogger

/**
 * Created by ripzery on 7/22/16.
 */

class PhonePresenter(var view: PhoneContract.View?) : PhoneContract.Presenter, AnkoLogger {
    private var mPhoneNumber: String = ""

    override fun onNumberChange(phoneNumber: String) {
        mPhoneNumber = phoneNumber
        if (phoneNumber.length == 10) {
            view?.enableNext()
        } else {
            view?.disableNext()
        }
    }

    override fun next() {
        view?.goNext()
    }

    override fun onCreate() {

    }

    override fun onDestroy() {
        view = null
    }

}
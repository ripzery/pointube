package com.socket9.pointube.screens.register.otp

import com.socket9.pointube.base.BasePresenter

/**
 * Created by ripzery on 7/22/16.
 */

interface OtpContract {
    interface View {
        fun enableNext()

        fun disableNext()

        fun goNext()

    }

    interface Presenter : BasePresenter{
        fun verifyPhoneNumber(otp: String)

        fun register()
    }
}
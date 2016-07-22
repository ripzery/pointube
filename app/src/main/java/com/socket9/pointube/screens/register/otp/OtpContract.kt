package com.socket9.pointube.screens.register.otp

import com.socket9.pointube.base.BasePresenter

/**
 * Created by ripzery on 7/22/16.
 */

interface OtpContract {
    interface View {
        fun enableNext()

        fun disableNext()

        fun showOtpError(msg: String)

        fun showOtpSuccess()

        fun goNext()

    }

    interface Presenter : BasePresenter{

        fun onTypeOtp(otp: String)

        fun verifyPhoneNumber(memberId: Int)
    }
}
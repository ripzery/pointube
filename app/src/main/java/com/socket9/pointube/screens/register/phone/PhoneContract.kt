package com.socket9.pointube.screens.register.phone

import com.socket9.pointube.base.BasePresenter

/**
 * Created by ripzery on 7/22/16.
 */
interface PhoneContract {
    interface View {
        fun enableNext()

        fun disableNext()

        fun goNext(memberId: Int)

        fun showSavePhoneNumberError(msg: String)

        fun showSavePhoneNumberSuccess()

    }

    interface Presenter : BasePresenter {
        fun onNumberChange(number: String)

        fun savePhoneNumber(memberId: Int, phoneNumber: String)
    }
}
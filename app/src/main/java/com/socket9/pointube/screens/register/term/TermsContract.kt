package com.socket9.pointube.screens.register.term

import com.socket9.pointube.base.BasePresenter

/**
 * Created by ripzery on 7/22/16.
 */
interface TermsContract {
    interface View {
        fun enableNext()

        fun disableNext()

        fun goNext(memberId: Int)
    }

    interface Presenter : BasePresenter{

        fun checkTerms(isChecked: Boolean)

        fun next(memberId: Int)

    }
}
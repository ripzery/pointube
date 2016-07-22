package com.socket9.pointube.screens.register.form

import com.socket9.pointube.base.BasePresenter
import com.socket9.pointube.screens.register.RegisterModel
import rx.Observable

/**
 * Created by ripzery on 7/21/16.
 */
interface RegisterFormContract {
    interface View {
        fun enableNext()

        fun disableNext()

        fun goNext(id: Int)

        fun validateRepeatPassword()

        fun showRegisterError(msg: String)

        fun showRegisterSuccess()
    }

    interface Presenter : BasePresenter {
        fun validateAll(emailObs: Observable<CharSequence>,
                        pwObs: Observable<CharSequence>,
                        rpwObs: Observable<CharSequence>,
                        fnEnObs: Observable<CharSequence>,
                        lnEnObs: Observable<CharSequence>,
                        fnThObs: Observable<CharSequence>,
                        lnThObs: Observable<CharSequence>,
                        citiObs: Observable<CharSequence>,
                        ppObs: Observable<CharSequence>,
                        dobObs: Observable<CharSequence>,
                        natObs: Observable<CharSequence>
        )

        fun setNationalities(isThai: Boolean)

        fun setGender(isMale: Boolean)

        fun setDob(dob: String)

        fun next()

        fun register()
    }
}
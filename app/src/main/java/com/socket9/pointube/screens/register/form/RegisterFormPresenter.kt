package com.socket9.pointube.screens.register.form

import com.socket9.pointube.screens.register.RegisterModel
import com.socket9.pointube.utils.ValidatorUtil
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import rx.Observable

/**
 * Created by ripzery on 7/21/16.
 */
class RegisterFormPresenter(var view: RegisterFormContract.View?) : AnkoLogger, RegisterFormContract.Presenter {
    private var isThai: Boolean = true
    private var isMale: Boolean = true
    private var textDob: String = ""
    private var registerRequest: RegisterModel.Request.Register? = null

    override fun validateAll(emailObs: Observable<CharSequence>, pwObs: Observable<CharSequence>, rpwObs: Observable<CharSequence>, fnEnObs: Observable<CharSequence>, lnEnObs: Observable<CharSequence>, fnThObs: Observable<CharSequence>, lnThObs: Observable<CharSequence>, citiObs: Observable<CharSequence>, ppObs: Observable<CharSequence>) {
        /* Check all combination */
        Observable.combineLatest(emailObs, pwObs, rpwObs, fnEnObs, lnEnObs, fnThObs, lnThObs, citiObs, ppObs,
                { t1, t2, t3, t4, t5, t6, t7, t8, t9 ->
                    buildRegisterModel(t1, t2, t4, t5, t6, t7, t8, t9)

                    val isNationalityRequiredValid = if (isThai)
                        ValidatorUtil.provideCitizenIdValidator().isValid(t8, t8.isEmpty())
                    else
                        ValidatorUtil.providePassportValidator().isValid(t9, t9.isEmpty())

                    ValidatorUtil.provideEmailValidator().isValid(t1, t1.isEmpty()) &&
                            ValidatorUtil.providePasswordValidator().isValid(t2, t2.isEmpty()) &&
                            ValidatorUtil.provideRepeatPasswordValidator(t2).isValid(t3, t3.isEmpty()) &&
                            ValidatorUtil.provideFirstNameEnValidator().isValid(t4, t4.isEmpty()) &&
                            ValidatorUtil.provideLastNameEnValidator().isValid(t5, t5.isEmpty()) &&
                            ValidatorUtil.provideFirstNameThValidator().isValid(t6, t6.isEmpty()) &&
                            ValidatorUtil.provideLastNameThValidator().isValid(t7, t7.isEmpty()) &&
                            isNationalityRequiredValid


                })
                .subscribe {
                    if (it) view?.enableNext() else view?.disableNext()
                }

        pwObs.filter { it.length > 0 }.subscribe { view?.validateRepeatPassword() }
    }

    private fun buildRegisterModel(t1: CharSequence, t2: CharSequence, t4: CharSequence, t5: CharSequence, t6: CharSequence, t7: CharSequence, t8: CharSequence, t9: CharSequence) {
        registerRequest = RegisterModel.Request.Register(
                t6.toString(),
                t7.toString(),
                t4.toString(),
                t5.toString(),
                t8.toString(),
                t9.toString(),
                null,
                t1.toString(),
                t2.toString(),
                if (isMale) "1" else "2",
                null,
                textDob
        )
    }

    override fun setDob(dob: String) {
        textDob = dob
    }

    override fun setNationalities(isThai: Boolean) {
        this.isThai = isThai
    }

    override fun setGender(isMale: Boolean) {
        this.isMale = isMale
    }

    override fun next() {
        info { " next " }

        /* TODO : Go agreement page and save user object */
    }

    override fun onCreate() {

    }

    override fun onDestroy() {
        view = null
    }
}
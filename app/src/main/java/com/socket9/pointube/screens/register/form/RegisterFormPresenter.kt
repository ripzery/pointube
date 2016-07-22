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
    private var mIsThai: Boolean = true
    private var mIsMale: Boolean = true
    private var mTextDob: String = ""
    private var mRegisterRequest: RegisterModel.Request.Register? = null

    override fun validateAll(emailObs: Observable<CharSequence>, pwObs: Observable<CharSequence>, rpwObs: Observable<CharSequence>, fnEnObs: Observable<CharSequence>, lnEnObs: Observable<CharSequence>,
                             fnThObs: Observable<CharSequence>, lnThObs: Observable<CharSequence>, citiObs: Observable<CharSequence>, ppObs: Observable<CharSequence>, dobObs: Observable<CharSequence>,
                             natObs: Observable<CharSequence>) {
        /* Check all combination */
        val listObservable: MutableList<Observable<CharSequence>> = mutableListOf(emailObs, pwObs, rpwObs, fnEnObs, lnEnObs, fnThObs, lnThObs, citiObs, ppObs, dobObs, natObs)

        Observable.combineLatest(listObservable, {
            info { "combineLatest is working now" }
            val list: MutableList<CharSequence> = mutableListOf()
            it.forEach {
                list.add(it as CharSequence)
            }

            info { "$mIsThai, ${list[9]}" }

            /* build register model on-the-fly */
            buildRegisterModel(list[0], list[1], list[3], list[4], list[5], list[6], list[7], list[8])

            val mIsCitizenIdValid = ValidatorUtil.provideCitizenIdValidator().isValid(list[7], list[7].isEmpty())
            val mIsPassportValid = ValidatorUtil.providePassportValidator().isValid(list[8], list[8].isEmpty())
            val isNationalityRequiredValid = if (mIsThai) mIsCitizenIdValid else mIsPassportValid


            ValidatorUtil.provideEmailValidator().isValid(list[0], list[0].isEmpty()) &&
                    ValidatorUtil.providePasswordValidator().isValid(list[1], list[1].isEmpty()) &&
                    ValidatorUtil.provideRepeatPasswordValidator(list[1]).isValid(list[2], list[2].isEmpty()) &&
                    ValidatorUtil.provideFirstNameEnValidator().isValid(list[3], list[3].isEmpty()) &&
                    ValidatorUtil.provideLastNameEnValidator().isValid(list[4], list[4].isEmpty()) &&
                    ValidatorUtil.provideFirstNameThValidator().isValid(list[5], list[5].isEmpty()) &&
                    ValidatorUtil.provideLastNameThValidator().isValid(list[6], list[6].isEmpty()) &&
                    !list[9].isEmpty() &&
                    isNationalityRequiredValid

        }).subscribe {
            if (it) view?.enableNext() else view?.disableNext()
        }

        pwObs.filter { it.length > 0 }.subscribe { view?.validateRepeatPassword() }
    }

    private fun buildRegisterModel(t1: CharSequence, t2: CharSequence, t4: CharSequence, t5: CharSequence, t6: CharSequence, t7: CharSequence, t8: CharSequence, t9: CharSequence) {
        mRegisterRequest = RegisterModel.Request.Register(
                t6.toString(),
                t7.toString(),
                t4.toString(),
                t5.toString(),
                t8.toString(),
                t9.toString(),
                null,
                t1.toString(),
                t2.toString(),
                if (mIsMale) "1" else "2",
                null,
                mTextDob
        )
    }

    override fun setDob(dob: String) {
        mTextDob = dob
    }

    override fun setNationalities(isThai: Boolean) {
        this.mIsThai = isThai
    }

    override fun setGender(isMale: Boolean) {
        this.mIsMale = isMale
    }

    override fun next() {
        info { mRegisterRequest?.toString() }
        view?.goNext()
        /* TODO : Go agreement page and save user object */
    }

    override fun onCreate() {

    }

    override fun onDestroy() {
        view = null
    }
}
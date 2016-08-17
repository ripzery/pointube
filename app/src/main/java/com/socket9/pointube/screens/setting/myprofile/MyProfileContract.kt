package com.socket9.pointube.screens.setting.myprofile

import com.socket9.pointube.base.BasePresenter
import com.socket9.pointube.screens.home.LoginModel
import rx.Observable
import java.util.*

/**
 * Created by ripzery on 8/2/16.
 */
interface MyProfileContract {
    interface View {
        fun enableSave()

        fun disableSave()

        fun showChangePassword()

        fun showUpdateError(msg: String)

        fun showChangePasswordError(msg: String)

        fun showChangePasswordSuccess()

        fun showUpdateSuccess()

        fun showLoading()

        fun hideLoading()

        fun initLoginData(loginData: LoginModel.Response.LoginResult)
    }

    interface Presenter : BasePresenter {
        fun loadCurrentUser()

        fun validateAll(fnEnObs: Observable<CharSequence>,
                        lnEnObs: Observable<CharSequence>,
                        fnThObs: Observable<CharSequence>,
                        lnThObs: Observable<CharSequence>,
                        citiObs: Observable<CharSequence>,
                        ppObs: Observable<CharSequence>,
                        dobObs: Observable<CharSequence>
        )

        fun setGender(isMale: Boolean)

        fun setDob(dob: Date)

        fun next()

        fun save()

        fun clickChangePassword()

        fun changePassword(oldPassword:String, newPassword: String, ConfirmPassword: String)
    }
}
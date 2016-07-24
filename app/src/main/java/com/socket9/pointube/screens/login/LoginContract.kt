package com.socket9.pointube.screens.login

import com.socket9.pointube.base.BasePresenter

/**
 * Created by Euro (ripzery@gmail.com) on 7/16/2016 AD.
 */
interface LoginContract {
    interface View {
        fun showProgressDialog()

        fun hideProgressDialog()

        fun showLoginSuccess(msg: String)

        fun showLoginError(msg: String)

        fun showSignUp()

        fun showForgetPasswordDialog()
    }

    interface Presenter : BasePresenter {
        fun doLogin(email:String, password: String)

        fun doSignUp()

        fun doForgetPassword(email: String)

    }
}
package com.socket9.pointube.screens.login

/**
 * Created by Euro (ripzery@gmail.com) on 7/16/2016 AD.
 */
interface LoginContract {
    interface View {
        fun showProgressDialog()

        fun showLoginSuccess(msg: String)

        fun showLoginError(msg: String)

        fun showSignUp()

        fun showForgetPasswordDialog()
    }

    interface Presenter {
        fun doLogin(email:String, password: String)

        fun doSignUp()

        fun doForgetPassword(email: String)

    }
}
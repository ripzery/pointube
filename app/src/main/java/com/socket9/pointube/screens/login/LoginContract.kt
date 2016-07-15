package com.socket9.pointube.screens.login

/**
 * Created by Euro (ripzery@gmail.com) on 7/16/2016 AD.
 */
interface LoginContract {
    interface View {

    }

    interface Presenter {
        fun doLogin()

        fun doSignUp()

        fun doForgetPassword()

    }
}
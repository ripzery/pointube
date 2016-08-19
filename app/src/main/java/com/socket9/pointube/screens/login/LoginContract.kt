package com.socket9.pointube.screens.login

import com.socket9.pointube.base.BasePresenter

/**
 * Created by Euro (ripzery@gmail.com) on 7/16/2016 AD.
 */
interface LoginContract {
    interface View {
        fun showProgressDialog(title: String)

        fun hideProgressDialog()

        fun showLoginSuccess(msg: String)

        fun showLoginError(msg: String)

        fun showPinValidate()

        fun showForgotError(msg: String)

        fun showValidateOtpError(msg: String)

        fun showNewPasswordDialog()

        fun showResetPasswordError(msg: String)

        fun showResetPasswordComplete()

        fun showSignUp()

        fun showForgetPasswordDialog()

        fun enableValidateOtp()

        fun disableValidateOtp()
    }

    interface Presenter : BasePresenter {
        fun doLogin(email: String, password: String)

        fun doSignUp()

        fun doForgetPassword(email: String)

        fun onTypeOtp(otp: String)

        fun clickForgetPassword()

        fun validateForgotPasswordOtp()

        fun resetPassword(newPassword: String, confirmPassword: String)
    }
}
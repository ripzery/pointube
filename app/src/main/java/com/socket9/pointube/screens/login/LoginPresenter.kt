package com.socket9.pointube.screens.login

import com.socket9.pointube.manager.DataManager
import com.socket9.pointube.screens.home.LoginModel
import com.socket9.pointube.utils.SharedPrefUtil
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.warn

/**
 * Created by Euro (ripzery@gmail.com) on 7/16/2016 AD.
 */
class LoginPresenter(var view: LoginContract.View?) : AnkoLogger, LoginContract.Presenter {
    private var mOtp: String? = null
    private var mEmail: String? = null
    private var mMemberId: Int = 0

    override fun onTypeOtp(otp: String) {
        this.mOtp = otp
        if (otp.length < 4) {
            view?.disableValidateOtp()
        } else {
            view?.enableValidateOtp()
        }
    }

    override fun validateForgotPasswordOtp() {
        view?.showProgressDialog("Validate forgot password...")
        DataManager.forgotPasswordCheckPin(LoginModel.Request.CheckUserWithPin(mEmail!!, mOtp!!))
                .subscribe({
                    view?.hideProgressDialog()
                    if (it.IsSuccess) {
                        mMemberId = it.result!!.id
                        view?.showNewPasswordDialog()
                    } else {
                        view?.showValidateOtpError(it.Message ?: "Internet connection problem. Please try again.")
                    }
                }, {
                    view?.hideProgressDialog()
                    view?.showValidateOtpError(it.message ?: "Internet connection problem. Please try again")
                })
    }

    override fun resetPassword(newPassword: String, confirmPassword: String) {
        view?.showProgressDialog("Resetting password...")
        DataManager.forgotPasswordNewPassword(LoginModel.Request.ResetPassword(mMemberId, newPassword, confirmPassword))
                .subscribe({
                    if (it.IsSuccess) {
                        view?.showResetPasswordComplete()
                    } else {
                        view?.showResetPasswordError(it.Message!!)
                    }
                }, {
                    warn { it }
                    view?.showResetPasswordError(it.message ?: "Internet connection problem. Please try again.")
                })
    }

    override fun doLogin(email: String, password: String) {
        view?.showProgressDialog("Logging in...")
        DataManager.login(LoginModel.Request.Login(email, password))
                .subscribe({
                    info { it }
                    view?.hideProgressDialog()
                    if (it.result != null && it.result.id != 0) {
                        view?.showLoginSuccess("Login success")
                    } else {
                        view?.showLoginError(it.message!!)
                    }
                }, {
                    view?.hideProgressDialog()
                    warn { it }
                    view?.showLoginError(it.message ?: "Internet connection problem. Please try again.")
                })
    }

    override fun doSignUp() {
        view?.showSignUp()
    }

    override fun doForgetPassword(email: String) {
        mEmail = email
        view?.showProgressDialog("Request Otp...")
        DataManager.forgotPassword(email)
                .subscribe({
                    view?.hideProgressDialog()
                    if (it.IsSuccess) {
                        view?.showPinValidate()
                    } else {
                        view?.showForgotError(it.Message!!)
                    }
                }, {
                    view?.hideProgressDialog()
                    view?.showForgotError(it.message ?: "Internet connection problem")
                })
    }

    override fun clickForgetPassword() {
        view?.showForgetPasswordDialog()
    }

    override fun onCreate() {
        /* do something */
    }

    override fun onDestroy() {
        // unregister async callback
        /* remove view referenced */
        view = null
    }
}
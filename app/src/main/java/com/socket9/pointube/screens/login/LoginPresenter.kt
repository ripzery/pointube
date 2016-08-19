package com.socket9.pointube.screens.login

import com.socket9.pointube.manager.DataManager
import com.socket9.pointube.screens.home.LoginModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.warn

/**
 * Created by Euro (ripzery@gmail.com) on 7/16/2016 AD.
 */
class LoginPresenter(var view: LoginContract.View?) : AnkoLogger, LoginContract.Presenter {
    private var otp: String? = null

    override fun onTypeOtp(otp: String) {
        this.otp = otp
        if (otp.length < 4) {
            view?.disableValidateOtp()
        } else {
            view?.enableValidateOtp()
        }
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
                    view?.showLoginError(it.message!!)
                })
    }

    override fun doSignUp() {
        view?.showSignUp()
    }

    override fun doForgetPassword(email: String) {
        view?.showProgressDialog("Request otp...")
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
                    view?.showForgotError("Internet connection problem")
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
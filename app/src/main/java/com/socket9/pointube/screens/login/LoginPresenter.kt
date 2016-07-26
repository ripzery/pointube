package com.socket9.pointube.screens.login

import com.socket9.pointube.manager.DataManager
import com.socket9.pointube.screens.home.LoginModel
import com.socket9.pointube.utils.LoginStateUtil
import com.socket9.pointube.utils.SharedPrefUtil
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.warn

/**
 * Created by Euro (ripzery@gmail.com) on 7/16/2016 AD.
 */
class LoginPresenter(var view: LoginContract.View?) : AnkoLogger, LoginContract.Presenter {
    override fun doLogin(email: String, password: String) {
        view?.showProgressDialog()
        DataManager.login(LoginModel.Request.Login(email, password))
                .subscribe({
                    info { it }
                    view?.hideProgressDialog()
                    if (it.result.id != 0) {
                        view?.showLoginSuccess("Login success")
                    } else {
                        view?.showLoginError(it.result.message!!)
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
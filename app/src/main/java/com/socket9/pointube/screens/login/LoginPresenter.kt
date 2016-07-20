package com.socket9.pointube.screens.login

import com.socket9.pointube.manager.DataManager
import com.socket9.pointube.utils.LoginState
import com.socket9.pointube.utils.SharedPref
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.warn

/**
 * Created by Euro (ripzery@gmail.com) on 7/16/2016 AD.
 */
class LoginPresenter(var view: LoginContract.View?) : AnkoLogger, LoginContract.Presenter {
    override fun doLogin(email: String, password: String) {
        view?.showProgressDialog()
        DataManager.login(email, password)
                .subscribe({
                    info { it }
                    view?.showProgressDialog()
                    if (it.result.id != 0) {
                        SharedPref.saveLoginResult(it.result)
                        view?.showLoginSuccess("Login success")
                    } else {
                        view?.showLoginError(it.result.message!!)
                    }
                }, {
                    view?.showProgressDialog()
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
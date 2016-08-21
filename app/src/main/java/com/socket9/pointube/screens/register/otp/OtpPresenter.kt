package com.socket9.pointube.screens.register.otp

import com.socket9.pointube.R
import com.socket9.pointube.manager.DataManager
import com.socket9.pointube.utils.ContextUtil
import org.jetbrains.anko.AnkoLogger

/**
 * Created by ripzery on 7/22/16.
 */
class OtpPresenter(var view: OtpContract.View?) : AnkoLogger, OtpContract.Presenter {

    private var otp: String = ""

    override fun onTypeOtp(otp: String) {
        this.otp = otp
        if (otp.length == 6) {
            view?.enableNext()
        } else {
            view?.disableNext()
        }
    }

    override fun verifyPhoneNumber(memberId: Int) {
        view?.showLoading()
        DataManager.verifyPhoneNumber(memberId, otp)
                .subscribe({
                    view?.hideLoading()
                    if (it.IsSuccess && it.Result.Id > 0) {
                        view?.showOtpSuccess()
                        view?.goNext()
                    } else {
                        view?.showOtpError(it.Message!!)
                    }
                }, {
                    try {
                        view?.hideLoading()
                        view?.showOtpError(ContextUtil.context!!.getString(R.string.error_msg_internet_connection))
                        it.printStackTrace()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                })
    }

    override fun onCreate() {

    }

    override fun onDestroy() {
        view = null
    }

}
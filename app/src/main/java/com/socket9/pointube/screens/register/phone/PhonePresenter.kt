package com.socket9.pointube.screens.register.phone

import com.socket9.pointube.manager.DataManager
import org.jetbrains.anko.AnkoLogger

/**
 * Created by ripzery on 7/22/16.
 */

class PhonePresenter(var view: PhoneContract.View?) : PhoneContract.Presenter, AnkoLogger {

    private var mPhoneNumber: String = ""

    override fun onNumberChange(phoneNumber: String) {
        mPhoneNumber = phoneNumber
        if (phoneNumber.length == 10) {
            view?.enableNext()
        } else {
            view?.disableNext()
        }
    }

    override fun savePhoneNumber(memberId: Int, phoneNumber: String) {
        /* Todo call api */
        DataManager.saveMobileNo(memberId, phoneNumber)
            .subscribe ({
                if(it.IsSuccess){
                    view?.showSavePhoneNumberSuccess()
                    view?.goNext(it.Result.Id)
                }else{
                    view?.showSavePhoneNumberError(it.Message!!)
                }
            },{
                it.printStackTrace()
                view?.showSavePhoneNumberError("An error occured, please try again")
            })
    }

    override fun onCreate() {

    }

    override fun onDestroy() {
        view = null
    }

}
package com.socket9.pointube.screens.register.term

/**
 * Created by ripzery on 7/22/16.
 */
class TermsPresenter(var view: TermsContract.View?) : TermsContract.Presenter {
    override fun checkTerms(isChecked: Boolean) {
        if(isChecked) view?.enableNext() else view?.disableNext()
    }

    override fun next() {
        view?.goNext()
    }

    override fun onCreate() {

    }

    override fun onDestroy() {
        view = null
    }

}
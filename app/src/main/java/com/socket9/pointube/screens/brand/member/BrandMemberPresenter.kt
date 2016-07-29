package com.socket9.pointube.screens.brand.member

import com.socket9.pointube.manager.DataManager
import com.socket9.pointube.screens.brand.BrandModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import rx.Observable

/**
 * Created by ripzery on 7/25/16.
 */
class BrandMemberPresenter(var view: BrandMemberContract.View?) : BrandMemberContract.Presenter, AnkoLogger {
    private var mAllBrandMember: BrandModel.Response.GetMemberBrand? = null

    override fun loadAllBrands(memberId: String, token: String) {
        view?.showLoading()
        info { "$memberId : $token" }
        Observable.concat(Observable.just(mAllBrandMember), DataManager.getAllBrandMember(BrandModel.Request.GetMemberBrand(memberId, token)))
                .first { it != null }
                .subscribe({
                    view?.hideLoading()
                    info { it }
                    if (it!!.IsSuccess) {
                        mAllBrandMember = it
                        view?.showQualifiedBrand(mAllBrandMember!!.Results)
                    } else {
                        view?.showEmptyBrands()
                    }
                }, {
                    info { it }
                    view?.hideLoading()
                    view?.showLoadingError()
                })
    }

    override fun selectAllBrand(isSelect: Boolean) {
        mAllBrandMember!!.Results.forEach {
            it.isChecked = isSelect
        }
        if (isSelect) view?.highlightAllBrands(mAllBrandMember!!.Results)
        else view?.unHighlightAllBrands(mAllBrandMember!!.Results)
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
package com.socket9.pointube.screens.brand.non_member

import com.socket9.pointube.manager.DataManager
import com.socket9.pointube.repository.brands.BrandRepo
import com.socket9.pointube.screens.brand.BrandModel

/**
 * Created by ripzery on 8/1/16.
 */
class BrandNonMemberPresenter(var view: BrandNonMemberContract.View?) : BrandNonMemberContract.Presenter {
    private var mAllBrands: MutableList<BrandRepo>? = null

    override fun loadAllBrands(exceptProvider: MutableList<Int>) {
        DataManager.getAllProvider()
                .subscribe({
                    if (it.Results.size > 0) {
                        mAllBrands = it.Results
                        view?.showQualifiedBrand(it.Results.filter { !exceptProvider.contains(it.Id) }.toMutableList())
                    } else {
                        view?.showEmptyBrands()
                    }
                }, {
                    view?.showLoadingError()
                })
    }

    override fun selectAllBrand(isSelect: Boolean) {
        mAllBrands!!.forEach {
            it.isChecked = isSelect
        }
        if (isSelect) view?.highlightAllBrands(mAllBrands!!)
        else view?.unHighlightAllBrands(mAllBrands!!)
    }

    override fun save(memberId: String, token: String, brandRepoList: MutableList<BrandRepo>) {
        view?.showLoading()
        val selectedBrand: MutableList<BrandModel.Request.Brand> = brandRepoList.map { BrandModel.Request.Brand(it.Id, it.isChecked) }.toMutableList()
        DataManager.saveSelectedBrand(BrandModel.Request.SaveBrand(memberId, token, selectedBrand))
                .subscribe({
                    view?.hideLoading()
                    if (it.IsSuccess) {
                        view?.showSaveSuccess()
                        view?.goNext()
                    } else {
                        view?.showSaveFailed()
                    }
                }, {
                    view?.hideLoading()
                    view?.showSaveFailed()
                })
    }

    override fun onCreate() {

    }

    override fun onDestroy() {
        view = null
    }

}
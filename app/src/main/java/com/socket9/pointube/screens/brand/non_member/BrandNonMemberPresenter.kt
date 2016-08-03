package com.socket9.pointube.screens.brand.non_member

import com.socket9.pointube.manager.DataManager
import com.socket9.pointube.repository.brands.BrandRepo
import com.socket9.pointube.screens.brand.BrandModel

/**
 * Created by ripzery on 8/1/16.
 */
class BrandNonMemberPresenter(var view: BrandNonMemberContract.View?) : BrandNonMemberContract.Presenter {
    private var mAllBrands: MutableList<BrandRepo>? = null
    private var mExceptProvider: MutableList<Int> = mutableListOf()
    private var mSelectedQualifiedBrand: MutableList<BrandModel.Request.Brand>? = null
    override fun loadAllBrands(exceptProviderId: MutableList<Int>, allSelectedQualifiedProviderId: MutableList<Int>) {
        mExceptProvider = exceptProviderId
        DataManager.getAllProvider()
                .subscribe({
                    if (it.Results.size > 0) {
                        mAllBrands = it.Results

                        /* Set all selected qualified brand */
                        mSelectedQualifiedBrand = it.Results.filter { allSelectedQualifiedProviderId.contains(it.Id) }.map { BrandModel.Request.Brand(it.Id, true) }.toMutableList()

                        /* Remove qualified brand in brand member from the list before show*/
                        view?.showQualifiedBrand(it.Results.filter { !exceptProviderId.contains(it.Id) }.toMutableList())
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
//        val selectedBrand: MutableList<BrandModel.Request.Brand> = brandRepoList.map { BrandModel.Request.Brand(it.Id, it.isChecked) }.toMutableList()

        /* use isMemberBrand false because we don't want brands in this page show up immediately */
        val selectedBrand: MutableList<BrandModel.Request.Brand> = brandRepoList.filter { it.isChecked }.map { BrandModel.Request.Brand(it.Id, false) }.toMutableList()

        /* add all selected brand from brand member */
        selectedBrand.addAll(mSelectedQualifiedBrand!!)

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
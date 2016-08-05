package com.socket9.pointube.screens.brand.member

import com.socket9.pointube.manager.DataManager
import com.socket9.pointube.repository.brands.GetMemberBrand
import com.socket9.pointube.repository.brands.GetMemberBrandResult
import com.socket9.pointube.repository.brands.getIdBySelected
import com.socket9.pointube.screens.brand.BrandModel
import com.socket9.pointube.screens.home.LoginModel
import com.socket9.pointube.utils.RealmUtil
import com.socket9.pointube.utils.SharedPrefUtil
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import rx.Observable

/**
 * Created by ripzery on 7/25/16.
 */
class BrandMemberPresenter(var view: BrandMemberContract.View?) : BrandMemberContract.Presenter, AnkoLogger {
    private var mAllBrandMember: GetMemberBrand? = null
    private val mLoginResult: LoginModel.Response.LoginResult by lazy { SharedPrefUtil.loadLoginResult()!! }

    override fun loadAllBrands(memberId: String, token: String, isEdit: Boolean) {
        if (isEdit) loadAllBrandsEdit(memberId, token)
        else loadAllBrandsFirstTime(memberId, token)
    }

    private fun loadAllBrandsFirstTime(memberId: String, token: String) {
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

    private fun loadAllBrandsEdit(memberId: String, token: String) {
        view?.showLoading()

        /* initialize member selected brand */
        var memberSelectedBrand: MutableList<GetMemberBrandResult> = mutableListOf()

        /* get "all selected brand member" then check if they are contained in "all brand member"
         * if yes -> checked
         * if no -> leave unchecked
         * */
        DataManager.getAllBrandSelectedMember(BrandModel.Request.GetMemberSelectBrand(memberId, token, true))
                .doOnNext { memberSelectedBrand = it.Brands }
                .flatMap { Observable.concat(Observable.just(mAllBrandMember), DataManager.getAllBrandMember(BrandModel.Request.GetMemberBrand(memberId, token))) }
                .first { it != null }
                .subscribe({
                    view?.hideLoading()
                    info { it }
                    if (it!!.IsSuccess) {
                        mAllBrandMember = it
                        /* set isChecked by finding each one from memberSelectedBrand */
                        mAllBrandMember!!.Results.forEach {
                            val brand = it
                            it.isChecked = memberSelectedBrand.find { it.Id == brand.Id } != null
                        }

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
        val selectedBrand: MutableList<Int> = mAllBrandMember!!.getIdBySelected()
        val allBrands: MutableList<Int> = mAllBrandMember!!.Results.map { it.Id }.toMutableList()
        view?.goNext(selectedBrand, allBrands)
    }

    /* use for edit brand member later */
    override fun saveBrand() {
        view?.showLoading()
//        val selectedBrand: MutableList<BrandModel.Request.Brand> = brandRepoList.map { BrandModel.Request.Brand(it.Id, it.isChecked) }.toMutableList()

        /* use isMemberBrand false because we don't want brands in this page show up immediately */
        val selectedBrand: MutableList<BrandModel.Request.Brand> = mAllBrandMember!!.Results.filter { it.isChecked }.map { BrandModel.Request.Brand(it.Id, true) }.toMutableList()

        DataManager.saveSelectedBrand(BrandModel.Request.SaveBrand(mLoginResult.id.toString(), mLoginResult.token!!, selectedBrand))
                .subscribe({
                    view?.hideLoading()
                    if (it.IsSuccess) {
                        view?.showSaveSuccess()
                        /* save update brand to disk */
                        RealmUtil.write { mAllBrandMember }
                    } else {
                        view?.showSaveFailed(it.Message!!)
                    }
                }, {
                    view?.hideLoading()
                    view?.showSaveFailed("Internet connection problem")
                })
    }


    override fun onCreate() {

    }

    override fun onDestroy() {
        view = null
    }

}
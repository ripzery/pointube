package com.socket9.pointube.screens.promotion.main

import android.content.Context
import com.socket9.pointube.R
import com.socket9.pointube.manager.DataManager
import com.socket9.pointube.screens.brand.BrandModel
import com.socket9.pointube.screens.home.LoginModel
import com.socket9.pointube.utils.ContextUtil
import com.socket9.pointube.utils.SharedPrefUtil
import org.jetbrains.anko.AnkoLogger

/**
 * Created by ripzery on 7/26/16.
 */
class PromotionPresenter(var view: PromotionContract.View?) : PromotionContract.Presenter, AnkoLogger {
    private val INDEX_HOTDEAL = 0
    private val INDEX_DINING = 1
    private val INDEX_SHOPPING = 2
    private val INDEX_TRAVEL = 3
    private val INDEX_ENTERTAINMENT = 4
    private val INDEX_HEALTH = 5
    private val INDEX_BEAUTY = 6
    private val INDEX_EDUCATION = 7
    private val INDEX_READING = 8
    private val INDEX_HOME_LIVING = 9
    private val INDEX_IT_GADGET = 10
    private val INDEX_OTHER = 11
    lateinit private var context: Context
    private val mLoginResult: LoginModel.Response.LoginResult? by lazy { SharedPrefUtil.loadLoginResult() }
    private var mIsSubscribed: Boolean = false
    override fun prepareViewPager() {
        view?.setupViewPager()
    }

    override fun prepareTabBar() {
        val iconList: MutableList<Int> = mutableListOf()
        var idDrawable: Int = 0

        for (i in INDEX_HOTDEAL..INDEX_OTHER) {
            when (i) {
                INDEX_HOTDEAL -> {
                    idDrawable = R.drawable.ic_hot_deal
                }
                INDEX_DINING -> {
                    idDrawable = R.drawable.ic_dining
                }
                INDEX_SHOPPING -> {
                    idDrawable = R.drawable.ic_shopping
                }
                INDEX_TRAVEL -> {
                    idDrawable = R.drawable.ic_travel
                }
                INDEX_ENTERTAINMENT -> {
                    idDrawable = R.drawable.ic_entertainment
                }
                INDEX_HEALTH -> {
                    idDrawable = R.drawable.ic_health
                }
                INDEX_BEAUTY -> {
                    idDrawable = R.drawable.ic_beauty
                }
                INDEX_EDUCATION -> {
                    idDrawable = R.drawable.ic_education
                }
                INDEX_READING -> {
                    idDrawable = R.drawable.ic_reading
                }
                INDEX_HOME_LIVING -> {
                    idDrawable = R.drawable.ic_home_living
                }
                INDEX_IT_GADGET -> {
                    idDrawable = R.drawable.ic_it_gadget
                }
                INDEX_OTHER -> {
                    idDrawable = R.drawable.ic_other
                }
            }
            iconList.add(idDrawable)
        }

        view?.showNavigationTabBar(iconList)
    }

    override fun clickRecommendMe() {
        if(mIsSubscribed) view?.showRecommendMe() else view?.showErrorMsgRecommendMe(context.getString(R.string.promotion_list_text_recommend_me_error))
    }

    override fun onCreate() {
        context = ContextUtil.context!!

        DataManager.getAllBrandSelectedMember(BrandModel.Request.GetMemberSelectBrand(mLoginResult!!.id.toString(), mLoginResult!!.token.toString(), true))
                .subscribe({
                    if (it.Brands.size > 0) {
                        mIsSubscribed = true
                    }
                })
    }

    override fun onDestroy() {
        view = null
    }

}
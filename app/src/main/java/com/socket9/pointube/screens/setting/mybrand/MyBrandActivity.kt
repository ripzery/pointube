package com.socket9.pointube.screens.setting.mybrand

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.socket9.pointube.R
import com.socket9.pointube.extensions.replaceFragment
import com.socket9.pointube.extensions.setupToolbar
import com.socket9.pointube.screens.brand.BrandModel
import com.socket9.pointube.screens.brand.member.BrandMemberFragment
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class MyBrandActivity : AppCompatActivity(), BrandMemberFragment.BrandMemberListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_brand)
        initInstance()
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    private fun initInstance() {
        setupToolbar("My Brands", isShowBackButton = true)
        replaceFragment(fragment = BrandMemberFragment.newInstance(true))
    }

    /* Override View Interface */
    override fun goBackFromBrandMember() {
        finish()
    }

    override fun goNextFromBrandMember(selectedBrand: MutableList<Int>, qualifiedBrandIdList: MutableList<Int>) {
        finish()
    }

}

package com.socket9.pointube.screens.brand

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.socket9.pointube.R
import com.socket9.pointube.extensions.replaceFragment
import com.socket9.pointube.extensions.setupToolbar
import com.socket9.pointube.screens.brand.member.BrandMemberFragment
import com.socket9.pointube.screens.brand.non_member.BrandNonMemberFragment

class SelectBrandActivity : AppCompatActivity(), BrandNonMemberFragment.BrandNonMemberListener, BrandMemberFragment.BrandMemberListener {

    private var mCurrentFragment = FRAGMENT_BRAND_MEMBER

    companion object {
        val FRAGMENT_BRAND_MEMBER = 1
        val FRAGMENT_BRAND_NON_MEMBER = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_brand)
        initInstance()
    }

    private fun initInstance() {
        setupToolbar("Registered Brands", isShowBackButton = false)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.contentContainer, BrandMemberFragment.newInstance(false))
                .commit()
    }

    override fun onBackPressed() {
        when(mCurrentFragment){
            FRAGMENT_BRAND_MEMBER -> goBackFromBrandMember()
            FRAGMENT_BRAND_NON_MEMBER -> goBackFromBrandNonMember()
        }
    }

    override fun goBackFromBrandNonMember() {
        setupToolbar("Registered Brands", isShowBackButton = false)
        mCurrentFragment = 1
        supportFragmentManager.popBackStack()
    }

    override fun goNextFromBrandNonMember() {
        mCurrentFragment = 2
        setResult(Activity.RESULT_OK)
        finish()
    }

    override fun goBackFromBrandMember() {
        mCurrentFragment = 1
        setResult(Activity.RESULT_CANCELED)
        finish()
    }

    override fun goNextFromBrandMember(selectedBrand: MutableList<Int>, qualifiedBrandIdList: MutableList<Int>) {
        setupToolbar("Recommended Brands")
        mCurrentFragment = 2
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.translate_enter_from_right, R.anim.translate_exit_to_left, R.anim.translate_enter_from_left, R.anim.translate_exit_to_right)
                .replace(R.id.contentContainer, BrandNonMemberFragment.newInstance(selectedBrand, qualifiedBrandIdList))
                .addToBackStack("brand_member")
                .commit()
    }

}

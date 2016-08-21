package com.socket9.pointube.extensions

import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.socket9.pointube.R
import com.socket9.pointube.utils.ContextUtil
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * Created by Euro (ripzery@gmail.com) on 7/11/2016 AD.
 */

fun AppCompatActivity.replaceFragmentWithAnimation(container: Int = R.id.contentContainer, fragment: Fragment, enterTransition: Int = R.anim.slide_enter_from_bottom, exitTransition: Int = R.anim.slide_exit_to_bottom) {
    this.supportFragmentManager.beginTransaction().setCustomAnimations(enterTransition, exitTransition).replace(container, fragment).commit()
}

fun AppCompatActivity.replaceFragment(container: Int = R.id.contentContainer, fragment: Fragment) {
    this.supportFragmentManager.beginTransaction().replace(container, fragment).commit()
}

fun AppCompatActivity.setupToolbar(title: String? = getString(R.string.app_name), showHamburger: Boolean = false, isShowBackButton: Boolean = true, isShowIcon: Boolean = false) {
    setSupportActionBar(toolbar)
    tvToolbarTitle.text = title
    supportActionBar?.title = ""

    if (isShowIcon) {
        ivLogo.setImageDrawable(ContextCompat.getDrawable(ContextUtil.context, R.drawable.icon))
        ivLogo.visibility = View.VISIBLE
    } else {
        ivLogo.visibility = View.GONE
    }

    if (showHamburger) {

    } else if (isShowBackButton) {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    } else if (!isShowBackButton) {
        supportActionBar?.setDisplayShowHomeEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
}
package com.socket9.pointube.extensions

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.socket9.pointube.R
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * Created by Euro (ripzery@gmail.com) on 7/11/2016 AD.
 */

fun AppCompatActivity.replaceFragment(container: Int = R.id.contentContainer, fragment: Fragment) {
    this.supportFragmentManager.beginTransaction().replace(container, fragment).commit()
}

fun AppCompatActivity.setupToolbar(title: String? = "Pointube", showHamburger: Boolean = false, isShowBackButton:Boolean = true) {
    setSupportActionBar(toolbar)
    tvToolbarTitle.text = title
    supportActionBar?.title = ""
    if (showHamburger) {

    } else if(isShowBackButton){
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
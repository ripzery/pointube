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

fun AppCompatActivity.setupToolbar(title: String? = "Pointube", showHamburger: Boolean = false) {
    setSupportActionBar(toolbar)
    tvToolbarTitle.text = title
    if (showHamburger) {

    } else {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
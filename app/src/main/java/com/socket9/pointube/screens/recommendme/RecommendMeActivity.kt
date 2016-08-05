package com.socket9.pointube.screens.recommendme

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.socket9.pointube.extensions.replaceFragment

/**
 * Created by ripzery on 8/5/16.
 */
class RecommendMeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initInstance()
    }

    private fun initInstance() {
        replaceFragment()
    }
}
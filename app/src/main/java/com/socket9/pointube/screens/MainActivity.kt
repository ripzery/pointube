package com.socket9.pointube.screens

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.socket9.pointube.R
import com.socket9.pointube.extensions.replaceFragment
import com.socket9.pointube.screens.home.HomeFragment
import org.jetbrains.anko.AnkoLogger

class MainActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initInstance()
    }

    private fun initInstance() {
        replaceFragment(fragment = HomeFragment.newInstance("HomeFragment"))
    }
}

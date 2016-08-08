package com.socket9.pointube.screens.about.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.socket9.pointube.R
import com.socket9.pointube.extensions.replaceFragment

class AboutDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        initInstance()
    }

    private fun initInstance() {
        replaceFragment(fragment = AboutDetailFragment.newInstance(intent.getIntExtra("id", 1)))
    }
}

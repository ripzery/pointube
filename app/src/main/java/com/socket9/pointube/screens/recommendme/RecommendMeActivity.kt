package com.socket9.pointube.screens.recommendme

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.socket9.pointube.R
import com.socket9.pointube.extensions.replaceFragment

class RecommendMeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommend_me)
        initInstance()
    }

    private fun initInstance() {
        replaceFragment(fragment = RecommendMeFragment.newInstance(""))
    }
}

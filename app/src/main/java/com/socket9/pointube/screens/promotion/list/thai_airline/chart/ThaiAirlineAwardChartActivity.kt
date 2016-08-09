package com.socket9.pointube.screens.promotion.list.thai_airline.chart

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.socket9.pointube.R
import com.socket9.pointube.extensions.replaceFragment

class ThaiAirlineAwardChartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thai_airline_award)
        initInstance()
    }

    private fun initInstance() {
        replaceFragment(fragment = AwardChartFragment.newInstance(""))
    }
}

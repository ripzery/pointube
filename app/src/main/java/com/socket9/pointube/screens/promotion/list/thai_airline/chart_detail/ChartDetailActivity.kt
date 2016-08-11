package com.socket9.pointube.screens.promotion.list.thai_airline.chart_detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.socket9.pointube.R
import com.socket9.pointube.extensions.replaceFragment
import com.socket9.pointube.extensions.setupToolbar

class ChartDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart_detail)
        initInstance()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return true
    }

    private fun initInstance() {
        setupToolbar("Chart detail")
        replaceFragment(fragment = ChartDetailFragment.newInstance(intent.getIntExtra("id", 1)))
    }
}

package com.socket9.pointube.screens.about.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.socket9.pointube.R
import com.socket9.pointube.extensions.replaceFragment
import com.socket9.pointube.extensions.setupToolbar

class AboutDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        setupToolbar("About Detail", isShowBackButton = true)
        initInstance()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            android.R.id.home -> finish()
        }

        return true
    }

    private fun initInstance() {
        replaceFragment(fragment = AboutDetailFragment.newInstance(intent.getIntExtra("id", 1)))
    }
}

package com.socket9.pointube.screens.promotion.list

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.socket9.pointube.R
import com.socket9.pointube.extensions.replaceFragment

class ProgramListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_program_list)
        initInstance()
    }

    private fun initInstance() {
        replaceFragment(fragment = ProgramListFragment.newInstance(intent.getIntExtra("brandId", 1), intent.getStringExtra("brandTitle")))
    }
}

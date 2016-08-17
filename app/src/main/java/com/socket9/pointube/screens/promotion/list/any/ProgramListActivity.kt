package com.socket9.pointube.screens.promotion.list.any

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.socket9.pointube.R
import com.socket9.pointube.extensions.replaceFragment
import com.socket9.pointube.screens.promotion.list.CollapsingListFragment
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class ProgramListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_program_list)
        initInstance()
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    private fun initInstance() {
//        replaceFragment(fragment = ProgramListFragment.newInstance(intent.getIntExtra("brandId", 1), intent.getStringExtra("brandTitle")))
        replaceFragment(fragment = CollapsingListFragment.newInstance(intent.getIntExtra("brandId", 0), intent.getStringExtra("brandTitle"), intent.getIntExtra("unitId", 0)))
    }
}

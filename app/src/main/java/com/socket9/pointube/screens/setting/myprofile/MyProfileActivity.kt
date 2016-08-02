package com.socket9.pointube.screens.setting.myprofile

import MyProfileFragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.socket9.pointube.R
import com.socket9.pointube.extensions.setupToolbar

class MyProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)
        initInstance()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            android.R.id.home -> finish()
        }

        return true
    }

    private fun initInstance() {
        setupToolbar("Edit Profile", false, true)

        supportFragmentManager.beginTransaction().replace(R.id.contentContainer, MyProfileFragment.newInstance("")).commit()
    }
}

package com.socket9.pointube.screens.register

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.socket9.pointube.R
import com.socket9.pointube.extensions.replaceFragment
import com.socket9.pointube.extensions.setupToolbar
import com.socket9.pointube.screens.register.form.RegisterFormFragment

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setupToolbar("Register")
        initInstance()
    }

    private fun initInstance() {
        replaceFragment(fragment = RegisterFormFragment.newInstance("Register Form"))
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> {
                setResult(Activity.RESULT_CANCELED)
                finish()
            }
        }
        return true
    }
}

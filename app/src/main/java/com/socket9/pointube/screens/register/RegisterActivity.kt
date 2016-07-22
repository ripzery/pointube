package com.socket9.pointube.screens.register

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.socket9.pointube.R
import com.socket9.pointube.extensions.replaceFragment
import com.socket9.pointube.extensions.setupToolbar
import com.socket9.pointube.screens.register.form.RegisterFormFragment
import com.socket9.pointube.screens.register.phone.PhoneFragment

class RegisterActivity : AppCompatActivity(), RegisterFormFragment.RegisterFormListener, TermsFragment.TermsListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initInstance()
    }

    private fun initInstance() {
        supportFragmentManager.beginTransaction().replace(R.id.contentContainer, RegisterFormFragment.newInstance(""), "register_form").addToBackStack("register_form").commit()
    }

    override fun goPhoneFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.contentContainer, PhoneFragment.newInstance(""), "phone").addToBackStack("phone").commit()
    }

    override fun goTermsFragment(id: Int) {
        supportFragmentManager.beginTransaction().replace(R.id.contentContainer, TermsFragment.newInstance(id), "terms").addToBackStack("terms").commit()
    }

}

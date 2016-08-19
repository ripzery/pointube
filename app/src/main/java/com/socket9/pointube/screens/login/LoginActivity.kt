package com.socket9.pointube.screens.login

import android.app.Activity
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import com.socket9.pointube.R
import com.socket9.pointube.extensions.replaceFragmentWithAnimation
import com.socket9.pointube.extensions.setupToolbar
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupToolbar("Login", false, true, false)
        initInstance()

    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            android.R.id.home ->{
                setResult(Activity.RESULT_CANCELED)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        setResult(Activity.RESULT_CANCELED)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun initInstance() {
        replaceFragmentWithAnimation(fragment = LoginFragment.newInstance("Login"))
    }
}

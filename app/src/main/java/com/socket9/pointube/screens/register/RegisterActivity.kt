package com.socket9.pointube.screens.register

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.socket9.pointube.R
import com.socket9.pointube.extensions.replaceFragment
import com.socket9.pointube.extensions.setupToolbar
import com.socket9.pointube.screens.register.form.RegisterFormFragment
import com.socket9.pointube.screens.register.otp.OtpFragment
import com.socket9.pointube.screens.register.phone.PhoneFragment

class RegisterActivity : AppCompatActivity(), RegisterFormFragment.RegisterFormListener, TermsFragment.TermsListener, PhoneFragment.PhoneListener, OtpFragment.OtpListener {

    private var mCurrentFragment: Int = FRAGMENT_REGISTER_FORM

    companion object {
        val FRAGMENT_REGISTER_FORM = 1
        val FRAGMENT_TERMS = 2
        val FRAGMENT_TEL_NUMBER= 3
        val FRAGMENT_OTP = 4
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initInstance()
    }

    private fun initInstance() {
        setupToolbar("Register")
        replaceFragment(fragment =  RegisterFormFragment.newInstance())
    }

    override fun onBackPressed() {
        when(mCurrentFragment){
            FRAGMENT_REGISTER_FORM -> goBackFromRegisterForm()
            FRAGMENT_TERMS -> goBackFromTerms()
            FRAGMENT_TEL_NUMBER -> goBackFromOtp()
            FRAGMENT_OTP -> goBackFromOtp()
        }
    }

    override fun goNextFromRegisterForm(memberId: Int) {
        setupToolbar("Terms and condition", isShowBackButton = false)
        replaceFragment(fragment =  TermsFragment.newInstance(memberId))
        mCurrentFragment = FRAGMENT_TERMS
    }

    override fun goNextFromTerms(memberId: Int) {
        setupToolbar("Verify phone number", isShowBackButton = false)
        replaceFragment(fragment = PhoneFragment.newInstance(memberId))
//        supportFragmentManager.beginTransaction()
//                .setCustomAnimations(R.anim.slide_enter_from_bottom, R.anim.slide_exit_to_bottom)
//                .replace(R.id.contentContainer, PhoneFragment.newInstance(memberId))
//                .commit()
        mCurrentFragment = FRAGMENT_TEL_NUMBER
    }

    override fun goNextFromPhone(memberId: Int) {
        /*TODO : Go to OTP*/
        setupToolbar("Enter OTP")
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.translate_enter_from_right, R.anim.translate_exit_to_left, R.anim.translate_enter_from_left, R.anim.translate_exit_to_right)
                .replace(R.id.contentContainer, OtpFragment.newInstance(memberId), "phone").addToBackStack("phone").commit()
        mCurrentFragment = FRAGMENT_OTP
    }

    override fun goNextFromOtp() {
        setResult(Activity.RESULT_OK)
        finish()
    }

    override fun goBackFromRegisterForm() {
        setResult(Activity.RESULT_CANCELED)
        finish()
    }

    override fun goBackFromTerms() {
        setResult(Activity.RESULT_OK)
        finish()
    }

    override fun goBackFromPhone() {
        setResult(Activity.RESULT_OK)
        finish()
    }

    override fun goBackFromOtp() {
        setupToolbar("Verify phone number", isShowBackButton = false)
        supportFragmentManager.popBackStack()
        mCurrentFragment = FRAGMENT_TEL_NUMBER
    }
}

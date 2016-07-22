package com.socket9.pointube.screens.register.otp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.socket9.pointube.R
import kotlinx.android.synthetic.main.fragment_otp.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.support.v4.toast

/**
 * Created by Euro (ripzery@gmail.com) on 3/10/16 AD.
 */
class OtpFragment : Fragment(), AnkoLogger, OtpContract.View {

    /** Variable zone **/
    var memberId: Int = 0
    lateinit var mOtpListener: OtpFragment.OtpListener
    lateinit var mOtpPresenter: OtpContract.Presenter

    /** Static method zone **/
    companion object {
        val ARG_1 = "ARG_1"

        fun newInstance(memberId: Int): OtpFragment {
            var bundle: Bundle = Bundle()
            bundle.putInt(ARG_1, memberId)
            val otpFragment: OtpFragment = OtpFragment()
            otpFragment.arguments = bundle
            return otpFragment
        }

    }

    /** Activity method zone  **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mOtpListener = activity as OtpListener

        if (savedInstanceState == null) {
            /* if newly created */
            memberId = arguments.getInt(ARG_1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater!!.inflate(R.layout.fragment_otp, container, false)

        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        initInstance()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                mOtpListener.goBackFromOtp()
            }
        }

        return true
    }

    /* Override View Interface */

    override fun enableNext() {
        btnVerifyOtp.isEnabled = true
    }

    override fun disableNext() {
        btnVerifyOtp.isEnabled = false
    }

    override fun goNext() {
        mOtpListener.goNextFromOtp()
    }

    override fun showOtpError(msg: String) {
        toast(msg)
    }

    override fun showOtpSuccess() {
        toast("Verify OTP successfully")
    }

    /** Method zone **/

    private fun initInstance() {
        mOtpPresenter = OtpPresenter(this)

        otpInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                mOtpPresenter.onTypeOtp(p0!!.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })

        btnVerifyOtp.setOnClickListener { mOtpPresenter.verifyPhoneNumber(memberId) }

    }

    interface OtpListener {
        fun goNextFromOtp()

        fun goBackFromOtp()
    }
}
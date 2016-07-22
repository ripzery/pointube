package com.socket9.pointube.screens.register.otp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.socket9.pointube.R
import kotlinx.android.synthetic.main.fragment_register_form.*
import org.jetbrains.anko.AnkoLogger

/**
 * Created by Euro (ripzery@gmail.com) on 3/10/16 AD.
 */
class OtpFragment : Fragment(), AnkoLogger, OtpContract.View {

    /** Variable zone **/
    lateinit var param1: String
    lateinit var mOtpPresenter:OtpContract.Presenter

    /** Static method zone **/
    companion object {
        val ARG_1 = "ARG_1"

        fun newInstance(param1: String): OtpFragment {
            var bundle: Bundle = Bundle()
            bundle.putString(ARG_1, param1)
            val otpFragment: OtpFragment = OtpFragment()
            otpFragment.arguments = bundle
            return otpFragment
        }

    }

    /** Activity method zone  **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            /* if newly created */
            param1 = arguments.getString(ARG_1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater!!.inflate(R.layout.fragment_otp, container, false)

        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInstance()
    }

    /* Override View Interface */

    override fun enableNext() {
        btnNext.isEnabled = true
    }

    override fun disableNext() {
        btnNext.isEnabled = false
    }

    override fun goNext() {

    }

    /** Method zone **/

    private fun initInstance() {
        mOtpPresenter = OtpPresenter(this)

    }
}
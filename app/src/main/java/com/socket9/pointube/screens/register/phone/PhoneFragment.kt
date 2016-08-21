package com.socket9.pointube.screens.register.phone

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding.view.enabled
import com.socket9.pointube.R
import com.socket9.pointube.extensions.hideLoadingDialog
import com.socket9.pointube.extensions.setupToolbar
import com.socket9.pointube.extensions.showLoadingDialog
import kotlinx.android.synthetic.main.fragment_phone.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.support.v4.toast

/**
 * Created by Euro (ripzery@gmail.com) on 3/10/16 AD.
 */
class PhoneFragment : Fragment(), AnkoLogger, PhoneContract.View {
    /** Variable zone **/
    var memberId: Int = 0
    lateinit private var mPhoneListener: PhoneListener
    private val mPhonePresenter: PhoneContract.Presenter by lazy { PhonePresenter(this) }


    /** Static method zone **/
    companion object {
        val ARG_1 = "ARG_1"

        fun newInstance(memberId: Int): PhoneFragment {
            var bundle: Bundle = Bundle()
            bundle.putInt(ARG_1, memberId)
            val phoneFragment: PhoneFragment = PhoneFragment()
            phoneFragment.arguments = bundle
            return phoneFragment
        }

    }

    /** Activity method zone  **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPhoneListener = activity as PhoneListener
        if (savedInstanceState == null) {
            /* if newly created */
            memberId = arguments.getInt(ARG_1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater!!.inflate(R.layout.fragment_phone, container, false)

        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInstance()
    }

    /* Override View Interface zone */
    override fun enableNext() {
        btnNext.isEnabled = true
    }

    override fun disableNext() {
        btnNext.isEnabled = false
    }

    override fun goNext(memberId: Int) {
        mPhoneListener.goNextFromPhone(memberId)
    }

    override fun showSavePhoneNumberSuccess() {
        toast(getString(R.string.register_phone_toast_save_success))
    }

    override fun showSavePhoneNumberError(msg: String) {
        toast(msg)
    }

    override fun showLoading() {
        showLoadingDialog(getString(R.string.dialog_default_progress_loading_title), getString(R.string.register_phone_dialog_progress_saving_phone))
    }

    override fun hideLoading() {
        hideLoadingDialog()
    }

    /** Method zone **/

    private fun initInstance() {
        telInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                mPhonePresenter.onNumberChange(p0!!.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })

        btnNext.setOnClickListener { mPhonePresenter.savePhoneNumber(memberId, telInput.text.toString()) }
    }

    interface PhoneListener{
        fun goNextFromPhone(memberId: Int)

        fun goBackFromPhone()
    }
}
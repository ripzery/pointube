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
import com.socket9.pointube.extensions.setupToolbar
import kotlinx.android.synthetic.main.fragment_phone.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

/**
 * Created by Euro (ripzery@gmail.com) on 3/10/16 AD.
 */
class PhoneFragment : Fragment(), AnkoLogger, PhoneContract.View {
    /** Variable zone **/
    lateinit var param1: String
    private val mPhonePresenter: PhoneContract.Presenter by lazy { PhonePresenter(this) }


    /** Static method zone **/
    companion object {
        val ARG_1 = "ARG_1"

        fun newInstance(param1: String): PhoneFragment {
            var bundle: Bundle = Bundle()
            bundle.putString(ARG_1, param1)
            val phoneFragment: PhoneFragment = PhoneFragment()
            phoneFragment.arguments = bundle
            return phoneFragment
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
        val rootView: View = inflater!!.inflate(R.layout.fragment_phone, container, false)

        return rootView
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            android.R.id.home -> {
                activity.supportFragmentManager.popBackStack()
            }
        }

        return true
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInstance()

        setHasOptionsMenu(true)
    }

    /* Override View Interface zone */
    override fun enableNext() {
        btnNext.isEnabled = true
    }

    override fun disableNext() {
        btnNext.isEnabled = false
    }

    override fun goNext() {
        info { "go next" }
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

        btnNext.setOnClickListener { mPhonePresenter.next() }

        (activity as AppCompatActivity).setupToolbar("Step 3 of 4 - Register")

    }
}
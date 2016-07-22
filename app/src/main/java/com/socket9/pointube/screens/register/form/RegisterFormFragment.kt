package com.socket9.pointube.screens.register.form

import android.app.Activity
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding.widget.RxTextView
import com.socket9.pointube.R
import com.socket9.pointube.extensions.setupToolbar
import com.socket9.pointube.screens.register.ToggleViewGroup
import com.socket9.pointube.utils.ValidatorUtil
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import kotlinx.android.synthetic.main.fragment_register_form.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.info
import org.jetbrains.anko.support.v4.toast
import rx.Observable
import java.util.*

/**
 * Created by Euro (ripzery@gmail.com) on 3/10/16 AD.
 */
class RegisterFormFragment : Fragment(), AnkoLogger, DatePickerDialog.OnDateSetListener, RegisterFormContract.View {
    /** Variable zone **/
    lateinit var param1: String
    lateinit private var mRegisterFormPresenter: RegisterFormContract.Presenter
    private val mEmailChangeObservable: Observable<CharSequence> by lazy { RxTextView.textChanges(metEmail) }
    private val mPasswordChangeObservable: Observable<CharSequence> by lazy { RxTextView.textChanges(metPassword) }
    private val mPasswordRepeatChangeObservable: Observable<CharSequence> by lazy { RxTextView.textChanges(metRepeatPassword) }
    private val mFirstNameEnObservable: Observable<CharSequence> by lazy { RxTextView.textChanges(metFirstName) }
    private val mLastNameEnObservable: Observable<CharSequence> by lazy { RxTextView.textChanges(metLastName) }
    private val mFirstNameThObservable: Observable<CharSequence> by lazy { RxTextView.textChanges(metFirstNameTH) }
    private val mLastNameThObservable: Observable<CharSequence> by lazy { RxTextView.textChanges(metLastNameTH) }
    private val mCitizenIdObservable: Observable<CharSequence> by lazy { RxTextView.textChanges(metCitizenID) }
    private val mPassportObservable: Observable<CharSequence> by lazy { RxTextView.textChanges(metPassport) }
    private val mNationalityObservable: Observable<CharSequence> by lazy { toggleNationality.getToggleObservable() }
    private val mDateOfBirthObservable: Observable<CharSequence> by lazy { RxTextView.textChanges(tvDob) }

    /** Static method zone **/
    companion object {
        val ARG_1 = "ARG_1"

        fun newInstance(param1: String): RegisterFormFragment {
            var bundle: Bundle = Bundle()
            bundle.putString(ARG_1, param1)
            val registerFormFragment: RegisterFormFragment = RegisterFormFragment()
            registerFormFragment.arguments = bundle
            return registerFormFragment
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
        val rootView: View = inflater!!.inflate(R.layout.fragment_register_form, container, false)

        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        info { "Form create" }
        initInstance()
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                activity.setResult(Activity.RESULT_CANCELED)
                activity.finish()
            }
        }

        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        info { "Form destroy" }
        mRegisterFormPresenter.onDestroy()
    }

    /* View interface zone */

    override fun enableNext() {
        btnNext.isEnabled = true
    }

    override fun disableNext() {
        btnNext.isEnabled = false
    }

    override fun validateRepeatPassword() {
        metRepeatPassword.validate()
    }

    override fun goNext(id: Int) {
        (activity as RegisterFormListener).goTermsFragment(id)
    }

    override fun showRegisterSuccess() {
        toast("Register successful")
        mRegisterFormPresenter.next()
    }

    override fun showRegisterError(msg: String) {
        toast(msg)
    }

    /** Method zone **/

    private fun initInstance() {
        (activity as AppCompatActivity).setupToolbar("Step 1 of 3 - Register")

        mRegisterFormPresenter = RegisterFormPresenter(this)
        mRegisterFormPresenter.onCreate()

        initEditText()
        /* Set Listener */
        tvDob.setOnClickListener {
            val now = Calendar.getInstance()
            val dpd = DatePickerDialog.newInstance(
                    this,
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH))

            dpd.show(activity.fragmentManager, "Datepickerdialog")
        }
        toggleNationality.setOnToggleListener(object : ToggleViewGroup.OnToggleListener {
            override fun onToggleLeft(isLeft: Boolean) {
                layoutCitizenID.visibility = if (isLeft) View.VISIBLE else View.GONE
                layoutPassport.visibility = if (isLeft) View.GONE else View.VISIBLE
                mRegisterFormPresenter.setNationalities(isLeft)
            }
        })

        toggleGender.setOnToggleListener(object : ToggleViewGroup.OnToggleListener {
            override fun onToggleLeft(isLeft: Boolean) {
                mRegisterFormPresenter.setGender(isLeft)
            }
        })

        btnNext.setOnClickListener { mRegisterFormPresenter.register() }

        removeValidator()
        initValidator()

        mRegisterFormPresenter.validateAll(
                RxTextView.textChanges(metEmail),
                RxTextView.textChanges(metPassword),
                RxTextView.textChanges(metRepeatPassword),
                RxTextView.textChanges(metFirstName),
                RxTextView.textChanges(metLastName),
                RxTextView.textChanges(metFirstNameTH),
                RxTextView.textChanges(metLastNameTH),
                RxTextView.textChanges(metCitizenID),
                RxTextView.textChanges(metPassport),
                RxTextView.textChanges(tvDob),
                toggleNationality.getToggleObservable()
        )
    }

    private fun initValidator() {
        metEmail.addValidator(ValidatorUtil.provideEmailValidator())
        metPassword.addValidator(ValidatorUtil.providePasswordValidator())
        metRepeatPassword.addValidator(ValidatorUtil.provideRepeatPasswordValidator(metPassword.text))
        metFirstName.addValidator(ValidatorUtil.provideFirstNameEnValidator())
        metLastName.addValidator(ValidatorUtil.provideLastNameEnValidator())
        metFirstNameTH.addValidator(ValidatorUtil.provideFirstNameThValidator())
        metLastNameTH.addValidator(ValidatorUtil.provideLastNameThValidator())
        metCitizenID.addValidator(ValidatorUtil.provideCitizenIdValidator())
        metPassport.addValidator(ValidatorUtil.providePassportValidator())
    }

    private fun removeValidator() {
        info { metRepeatPassword.hasValidators() }
        metRepeatPassword.clearValidators()
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        tvDob.text = "$dayOfMonth/$monthOfYear/$year"
        mRegisterFormPresenter.setDob(tvDob.text.toString())
    }

    private fun initEditText() {
        metPassword.typeface = Typeface.DEFAULT
        metPassword.transformationMethod = PasswordTransformationMethod()

        metRepeatPassword.typeface = Typeface.DEFAULT
        metRepeatPassword.transformationMethod = PasswordTransformationMethod()
    }

    interface RegisterFormListener {
        fun goTermsFragment(id: Int)
    }
}
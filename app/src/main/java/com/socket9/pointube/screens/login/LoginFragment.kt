package com.socket9.pointube.screens.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.afollestad.materialdialogs.DialogAction
import com.afollestad.materialdialogs.MaterialDialog
import com.rengwuxian.materialedittext.MaterialEditText
import com.socket9.pointube.R
import com.socket9.pointube.extensions.hideLoadingDialog
import com.socket9.pointube.extensions.showLoadingDialog
import com.socket9.pointube.screens.MainActivity
import com.socket9.pointube.screens.register.RegisterActivity
import com.socket9.pointube.utils.DialogUtil
import kotlinx.android.synthetic.main.fragment_login.*
import me.philio.pinentry.PinEntryView
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.support.v4.toast
import rx_activity_result.RxActivityResult

/**
 * Created by Euro (ripzery@gmail.com) on 3/10/16 AD.
 */
class LoginFragment : Fragment(), AnkoLogger, LoginContract.View {
    /** Variable zone **/
    lateinit var param1: String
    private val mLoginPresenter: LoginContract.Presenter by lazy { LoginPresenter(this) }
    private var mForgotDialogOtp: MaterialDialog? = null
    private var mForgotDialogReset: MaterialDialog? = null

    /** Static method zone **/
    companion object {
        val ARG_1 = "ARG_1"

        fun newInstance(param1: String): LoginFragment {
            var bundle: Bundle = Bundle()
            bundle.putString(ARG_1, param1)
            val loginFragment: LoginFragment = LoginFragment()
            loginFragment.arguments = bundle
            return loginFragment
        }

    }

    /** Override View Interface zone **/
    override fun showLoginSuccess(msg: String) {
        toast(msg)
        activity.setResult(Activity.RESULT_OK, Intent().putExtra("fragment", MainActivity.FRAGMENT_POINT))
        activity.finish()
    }

    override fun showLoginError(msg: String) {
        toast(msg)
    }

    override fun showSignUp() {
        val intent = Intent(activity, RegisterActivity::class.java)
        RxActivityResult.on(this).startIntent(intent).subscribe { result ->
            val data = result.data()
            val resultCode = result.resultCode()
            if (resultCode == Activity.RESULT_OK) {
                info("Register complete")
            } else {
                info("Cancel Register")
            }
        }
    }

    override fun showForgetPasswordDialog() {
        val forgetDialog = DialogUtil.getForgotPasswordDialog(context, "Forgot Password") {
            mLoginPresenter.doForgetPassword(it)
        }

        forgetDialog?.show()
    }

    override fun showPinValidate() {
        mForgotDialogOtp = DialogUtil.getForgotPasswordOtpDialog(context, "Validate Pin", "Validate", "Cancel") {
            mLoginPresenter.validateForgotPasswordOtp()
        }

        mForgotDialogOtp?.show()

        val otp = mForgotDialogOtp!!.customView!!.findViewById(R.id.otpInput) as PinEntryView
        mLoginPresenter.onTypeOtp("")
        otp.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                mLoginPresenter.onTypeOtp(p0!!.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })
    }

    override fun disableValidateOtp() {
        mForgotDialogOtp?.getActionButton(DialogAction.POSITIVE)?.isEnabled = false
    }

    override fun enableValidateOtp() {
        mForgotDialogOtp?.getActionButton(DialogAction.POSITIVE)?.isEnabled = true
    }

    override fun showResetPasswordError(msg: String) {
        toast(msg)
    }

    override fun showForgotError(msg: String) {
        toast(msg)
    }

    override fun showValidateOtpError(msg: String) {
        toast(msg)
    }

    override fun showNewPasswordDialog() {
        mForgotDialogReset = DialogUtil.getForgotPasswordResetDialog(context, "Reset Password", "Reset", "Cancel") {
            val newPassword = it.findViewById(R.id.metNewPassword) as MaterialEditText
            val confirmPassword = it.findViewById(R.id.metRepeatPassword) as MaterialEditText
            mLoginPresenter.resetPassword(newPassword.text.toString(), confirmPassword.text.toString())
        }

        mForgotDialogReset?.show()
    }

    override fun showResetPasswordComplete() {
        toast("Reset password complete.")
    }

    override fun showProgressDialog(title: String) {
        showLoadingDialog("Please wait", title)
    }

    override fun hideProgressDialog() {
        hideLoadingDialog()
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
        val rootView: View = inflater!!.inflate(R.layout.fragment_login, container, false)

        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInstance()
        mLoginPresenter.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        mLoginPresenter.onDestroy()
    }

    /** Method zone **/

    private fun initInstance() {
        btnLogin.setOnClickListener {
            mLoginPresenter.doLogin(metUsername.text.toString(), metPassword.text.toString())
        }

        tvForgetPassword.setOnClickListener {
            mLoginPresenter.clickForgetPassword()
        }

        tvSignUp.setOnClickListener {
            mLoginPresenter.doSignUp()
        }
    }

}
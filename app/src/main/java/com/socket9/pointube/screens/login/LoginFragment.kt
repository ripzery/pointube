package com.socket9.pointube.screens.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.socket9.pointube.R
import com.socket9.pointube.extensions.hideLoadingDialog
import com.socket9.pointube.extensions.showLoadingDialog
import com.socket9.pointube.screens.MainActivity
import com.socket9.pointube.screens.register.RegisterActivity
import com.socket9.pointube.utils.DialogUtil
import kotlinx.android.synthetic.main.fragment_login.*
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
            info { it }
        }

        forgetDialog?.show()
    }

    override fun showPinValidate() {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showForgotError(msg: String) {
        toast(msg)
    }

    override fun showProgressDialog() {
        showLoadingDialog("Please wait", "Logging In...")
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
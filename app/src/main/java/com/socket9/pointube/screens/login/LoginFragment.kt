package com.socket9.pointube.screens.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.socket9.pointube.R
import com.socket9.pointube.screens.MainActivity
import kotlinx.android.synthetic.main.fragment_login.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.support.v4.toast

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
        toast("SignUp")
    }

    override fun showForgetPasswordDialog() {
        toast("ShowForgetDialog")
    }

    override fun showProgressDialog() {

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
            mLoginPresenter.doForgetPassword("something")
        }

        tvSignUp.setOnClickListener {
            mLoginPresenter.doSignUp()
        }
    }
}
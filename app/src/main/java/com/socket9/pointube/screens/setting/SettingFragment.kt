package com.socket9.pointube.screens.setting

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.socket9.pointube.R
import com.socket9.pointube.extensions.hideLoadingDialog
import com.socket9.pointube.extensions.showLoadingDialog
import com.socket9.pointube.screens.setting.mybrand.MyBrandActivity
import com.socket9.pointube.screens.setting.myprofile.MyProfileActivity
import kotlinx.android.synthetic.main.fragment_setting.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * Created by Euro (ripzery@gmail.com) on 3/10/16 AD.
 */
class SettingFragment : Fragment(), SettingContract.View {
    /** Variable zone **/
    lateinit var param1: String
    lateinit var mActivity: SettingListener
    lateinit var mSettingPresenter: SettingContract.Presenter


    /** Static method zone **/
    companion object {
        val ARG_1 = "ARG_1"

        fun newInstance(param1: String): SettingFragment {
            var bundle: Bundle = Bundle()
            bundle.putString(ARG_1, param1)
            val settingFragment: SettingFragment = SettingFragment()
            settingFragment.arguments = bundle
            return settingFragment
        }

    }

    /** Activity method zone  **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            /* if newly created */
            param1 = arguments.getString(ARG_1)
            mActivity = activity as SettingListener
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater!!.inflate(R.layout.fragment_setting, container, false)

        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mSettingPresenter = SettingPresenter(this)
        mSettingPresenter.onCreate()
        initInstance()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mSettingPresenter.onDestroy()
    }

    /* Override View Interface */
    override fun showMyBrand() {
        startActivity<MyBrandActivity>()
    }

    override fun showMyProfile() {
        startActivity<MyProfileActivity>()
    }

    override fun showLoading() {
        showLoadingDialog("Please wait", "Logging out...")
    }

    override fun hideLoading() {
        hideLoadingDialog()
    }

    override fun showLogout() {
        mActivity.onLogout()
    }

    /** Method zone **/

    private fun initInstance() {
        btnLogout.setOnClickListener { mSettingPresenter.clickLogout() }

        layoutMyBrand.setOnClickListener { mSettingPresenter.clickMyBrand() }

        layoutMyProfile.setOnClickListener { mSettingPresenter.clickMyProfile() }
    }

    interface SettingListener {
        fun onLogout()
    }
}
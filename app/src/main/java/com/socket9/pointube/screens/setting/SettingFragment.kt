package com.socket9.pointube.screens.setting

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.socket9.pointube.R
import com.socket9.pointube.screens.setting.mybrand.MyBrandActivity
import com.socket9.pointube.screens.setting.myprofile.MyProfileActivity
import org.jetbrains.anko.support.v4.startActivity

/**
 * Created by Euro (ripzery@gmail.com) on 3/10/16 AD.
 */
class SettingFragment : Fragment(), SettingContract.View {
    /** Variable zone **/
    lateinit var param1: String


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

    /* Override view inter face */
    override fun showMyBrand() {
        startActivity<MyBrandActivity>()
    }

    override fun showMyProfile() {
        startActivity<MyProfileActivity>()
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun goToHomeAndShowLogin() {

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
        val rootView: View = inflater!!.inflate(R.layout.fragment_setting, container, false)

        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInstance()
    }

    /** Method zone **/

    private fun initInstance() {

    }
}
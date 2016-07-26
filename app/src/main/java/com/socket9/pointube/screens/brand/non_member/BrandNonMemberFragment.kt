package com.socket9.pointube.screens.brand.non_member

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.socket9.pointube.R
import com.socket9.pointube.screens.brand.member.BrandMemberFragment

/**
 * Created by Euro (ripzery@gmail.com) on 3/10/16 AD.
 */
class BrandNonMemberFragment : Fragment() {

    /** Variable zone **/
    lateinit var param1: String
    lateinit var mActivityListener: BrandNonMemberListener

    /** Static method zone **/
    companion object {
        val ARG_1 = "ARG_1"

        fun newInstance(param1: String): BrandNonMemberFragment {
            var bundle: Bundle = Bundle()
            bundle.putString(ARG_1, param1)
            val brandNonMemberFragment: BrandNonMemberFragment = BrandNonMemberFragment()
            brandNonMemberFragment.arguments = bundle
            return brandNonMemberFragment
        }

    }

    /** Activity method zone  **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityListener = activity as BrandNonMemberListener
        if (savedInstanceState == null) {
            /* if newly created */
            param1 = arguments.getString(ARG_1)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            android.R.id.home -> mActivityListener.goBackFromBrandNonMember()
        }
        return true
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater!!.inflate(R.layout.fragment_brand_non_member, container, false)

        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInstance()
        setHasOptionsMenu(true)
    }

    /** Method zone **/

    private fun initInstance() {

    }

    interface BrandNonMemberListener {
        fun goBackFromBrandNonMember()

        fun goNextFromBrandNonMember()
    }
}
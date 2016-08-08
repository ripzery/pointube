package com.socket9.pointube.screens.about.detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.socket9.pointube.R
import org.jetbrains.anko.AnkoLogger

/**
 * Created by ripzery on 7/20/16.
 */

class AboutDetailFragment : Fragment(), AnkoLogger {

    /** Variable zone **/
    var mAboutId: Int = 0


    /** Static method zone **/
    companion object {
        val ARG_1 = "ARG_1"

        fun newInstance(aboutId: Int): AboutDetailFragment {
            val bundle: Bundle = Bundle()
            bundle.putInt(ARG_1, aboutId)
            val aboutDetailFragment: AboutDetailFragment = AboutDetailFragment()
            aboutDetailFragment.arguments = bundle
            return aboutDetailFragment
        }

    }

    /** Activity method zone  **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            /* if newly created */
            mAboutId = arguments.getInt(ARG_1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater!!.inflate(R.layout.fragment_about_detail, container, false)

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
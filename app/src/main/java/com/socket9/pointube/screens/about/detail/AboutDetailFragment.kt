package com.socket9.pointube.screens.about.detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.socket9.pointube.R
import kotlinx.android.synthetic.main.fragment_about_detail.*
import org.jetbrains.anko.AnkoLogger

/**
 * Created by ripzery on 7/20/16.
 */

class AboutDetailFragment : Fragment(), AnkoLogger {

    /** Variable zone **/
    var mAboutId: Int = 0
    private val WHAT_IS_POINTUBE = 1
    private val WHO_USE_POINTUBE = 2
    private val HOW_TO_USE_POINTUBE = 3
    private val FAQ = 4
    private val CONTACT_US = 5
    private val PRIVACY_POLICY = 6
    private val TERMS_OF_USE = 7


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
        loadWebView()
    }

    private fun loadWebView() {
        val htmlFile: String
        htmlFile = when (mAboutId) {
            WHAT_IS_POINTUBE -> "about-whatis.html"
            WHO_USE_POINTUBE -> "about-whoshould.html"
            HOW_TO_USE_POINTUBE -> "about-howtouse.html"
            FAQ -> "about-faq-question.html"
            CONTACT_US -> "about-contact.html"
            PRIVACY_POLICY -> "about-privacy.html"
            TERMS_OF_USE -> "about-terms.html"
            else -> ""
        }
        webView.loadUrl("file:///android_asset/en/$htmlFile")
    }
}
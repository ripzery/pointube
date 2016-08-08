package com.socket9.pointube.screens.about

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.socket9.pointube.R
import kotlinx.android.synthetic.main.itemview_about.view.*

/**
 * Created by Euro (ripzery@gmail.com) on 3/10/16 AD.
 */
class AboutFragment : Fragment() {

    /** Variable zone **/
    lateinit var param1: String


    /** Static method zone **/
    companion object {
        val ARG_1 = "ARG_1"

        fun newInstance(param1: String): AboutFragment {
            var bundle: Bundle = Bundle()
            bundle.putString(ARG_1, param1)
            val aboutFragment: AboutFragment = AboutFragment()
            aboutFragment.arguments = bundle
            return aboutFragment
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
        val rootView: View = inflater!!.inflate(R.layout.fragment_about, container, false)

        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInstance()
    }

    /** Method zone **/

    private fun initInstance() {

    }

    inner class AboutAdapter(list: MutableList<AboutItem>) {


        inner class AboutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            init {
                itemView.aboutViewGroup.setOnClickListener {
                    
                }
            }

            fun setModel(model: AboutItem) {
                itemView.aboutViewGroup.setModel(model)
            }

        }
    }
}
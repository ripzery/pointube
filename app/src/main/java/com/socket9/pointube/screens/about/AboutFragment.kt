package com.socket9.pointube.screens.about

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.socket9.pointube.R
import kotlinx.android.synthetic.main.fragment_brand_member.*
import kotlinx.android.synthetic.main.itemview_about.view.*

/**
 * Created by Euro (ripzery@gmail.com) on 3/10/16 AD.
 */
class AboutFragment : Fragment() {

    /** Variable zone **/
    lateinit var param1: String
    lateinit private var mLayoutManager: RecyclerView.LayoutManager
    lateinit private var mAboutAdapter: AboutAdapter

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
        /* Init recycler view */
        mAboutAdapter = AboutAdapter(mutableListOf()) {
            /* TODO: handle click here */
        }
        mLayoutManager = GridLayoutManager(context, 3)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.adapter = mAboutAdapter
    }

    inner class AboutAdapter(var list: MutableList<AboutItem>, val listener: (Int) -> Unit) : RecyclerView.Adapter<AboutAdapter.AboutViewHolder>() {
        override fun onBindViewHolder(holder: AboutViewHolder?, position: Int) {
            holder!!.setModel(list[position])
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AboutViewHolder {
            throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        fun updateList(newList: MutableList<AboutItem>) {
            list = newList
            notifyDataSetChanged()
        }

        inner class AboutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            init {
                itemView.aboutViewGroup.setOnClickListener {
                    listener(adapterPosition)
                }
            }

            fun setModel(model: AboutItem) {
                itemView.aboutViewGroup.setModel(model)
            }

        }
    }
}
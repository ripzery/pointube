package com.socket9.pointube.screens.promotion.list

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.socket9.pointube.R
import com.socket9.pointube.extensions.replaceFragment
import kotlinx.android.synthetic.main.fragment_promotion_detail.*
import org.jetbrains.anko.AnkoLogger

/**
 * Created by ripzery on 7/20/16.
 */

class CollapsingListFragment : Fragment(), AnkoLogger, CollapsingListContract.View {
    /** Variable zone **/
    private var mBrandId: Int = 0
    private var mBrandTitle: String = ""
    lateinit private var mCollapsingListPresenter: CollapsingListContract.Presenter
    lateinit var param1: String
    lateinit private var mActivity: AppCompatActivity

    /** Static method zone **/
    companion object {
        val ARG_1 = "ARG_1"
        val ARG_2 = "ARG_2"

        fun newInstance(brandId: Int, brandTitle: String): CollapsingListFragment {
            val bundle: Bundle = Bundle()
            bundle.putInt(ProgramListFragment.ARG_1, brandId)
            bundle.putString(ProgramListFragment.ARG_2, brandTitle)
            val collapsingListFragment: CollapsingListFragment = CollapsingListFragment()
            collapsingListFragment.arguments = bundle
            return collapsingListFragment
        }

    }

    /** Activity method zone  **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            /* if newly created */
            mBrandId = arguments.getInt(ProgramListFragment.ARG_1)
            mBrandTitle = arguments.getString(ProgramListFragment.ARG_2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater!!.inflate(R.layout.fragment_collapsing_list, container, false)
        setHasOptionsMenu(true)
        return rootView
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> mActivity.finish()
        }
        return true
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mCollapsingListPresenter = CollapsingListPresenter(this)
        mCollapsingListPresenter.onCreate()
        initInstance()
    }

    override fun showCover(path: String) {
        Glide.with(this).load(path).into(ivCover)
    }

    /** Method zone **/

    private fun initInstance() {
        /* Set toolbar title */
        collapsing_toolbar.title = mBrandTitle

        /* Load brand cover url */
        mCollapsingListPresenter.loadCover(mBrandId)

        /* Setup toolbar*/
        mActivity = activity as AppCompatActivity
        mActivity.setSupportActionBar(toolbar)
        mActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        replaceFragment(fragment = ProgramListFragment.newInstance(mBrandId, mBrandTitle))
    }
}
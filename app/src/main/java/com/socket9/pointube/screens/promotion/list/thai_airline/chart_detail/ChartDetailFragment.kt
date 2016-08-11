package com.socket9.pointube.screens.promotion.list.thai_airline.chart_detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.socket9.pointube.R
import kotlinx.android.synthetic.main.fragment_chart_detail.*

/**
 * Created by ripzery on 7/20/16.
 */

class ChartDetailFragment : Fragment() {

    /** Variable zone **/
    var mPosition: Int = 0


    /** Static method zone **/
    companion object {
        val ARG_1 = "ARG_1"

        fun newInstance(position: Int): ChartDetailFragment {
            val bundle: Bundle = Bundle()
            bundle.putInt(ARG_1, position)
            val templateFragment: ChartDetailFragment = ChartDetailFragment()
            templateFragment.arguments = bundle
            return templateFragment
        }

    }

    /** Activity method zone  **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            /* if newly created */
            mPosition = arguments.getInt(ARG_1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater!!.inflate(R.layout.fragment_chart_detail, container, false)

        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInstance()
    }

    /** Method zone **/

    private fun initInstance() {

        viewPager.adapter = ChartDetailFragmentPagerAdapter(mPosition, childFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

    inner class ChartDetailFragmentPagerAdapter(val chartPosition: Int, fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        val TOTAL_PAGE = 2
        val PAGE_TITLES = listOf("Round Trip", "One way Trip")

        override fun getItem(position: Int): Fragment {
            return ChartDetailPagerItemFragment.newInstance(chartPosition, position == 0)
        }

        override fun getCount(): Int {
            return TOTAL_PAGE
        }

        override fun getPageTitle(position: Int): CharSequence {
            return PAGE_TITLES[position]
        }
    }
}
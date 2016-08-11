package com.socket9.pointube.screens.promotion.list.thai_airline.chart_detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.socket9.pointube.R

/**
 * Created by ripzery on 7/20/16.
 */

class ChartDetailPagerItemFragment : Fragment() {

    /** Variable zone **/
    private var mIsRound: Boolean = false
    private var mPosition: Int = 0

    /** Static method zone **/
    companion object {
        val ARG_1 = "ARG_1"
        val ARG_2 = "ARG_2"

        fun newInstance(position: Int, isRound: Boolean): ChartDetailPagerItemFragment {
            val bundle: Bundle = Bundle()
            bundle.putInt(ARG_1, position)
            bundle.putBoolean(ARG_2, isRound)
            val chartDetailPagerItem: ChartDetailPagerItemFragment = ChartDetailPagerItemFragment()
            chartDetailPagerItem.arguments = bundle
            return chartDetailPagerItem
        }

    }

    /** Activity method zone  **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            /* if newly created */
            mPosition = arguments.getInt(ARG_1)
            mIsRound = arguments.getBoolean(ARG_2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater!!.inflate(R.layout.fragment_chart_detail_pager_item, container, false)

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
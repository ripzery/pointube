package com.socket9.pointube.screens.promotion.list.thai_airline.chart

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.socket9.pointube.R
import kotlinx.android.synthetic.main.fragment_brand_member.*
import kotlinx.android.synthetic.main.itemview_award_chart.view.*
import org.jetbrains.anko.AnkoLogger

/**
 * Created by ripzery on 7/20/16.
 */

class AwardChartFragment : Fragment(), AnkoLogger, AwardChartContract.View {
    /** Variable zone **/
    lateinit var param1: String
    lateinit var mAwardChartAdapter: AwardChartAdapter
    lateinit var mAwardChartPresenter: AwardChartContract.Presenter
//    lateinit var mAward


    /** Static method zone **/
    companion object {
        val ARG_1 = "ARG_1"

        fun newInstance(param1: String): AwardChartFragment {
            val bundle: Bundle = Bundle()
            bundle.putString(ARG_1, param1)
            val awardChartFragment: AwardChartFragment = AwardChartFragment()
            awardChartFragment.arguments = bundle
            return awardChartFragment
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
        val rootView: View = inflater!!.inflate(R.layout.fragment_award_chart, container, false)

        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAwardChartPresenter = AwardChartPresenter(this)
        mAwardChartPresenter.onCreate()
        initInstance()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mAwardChartPresenter.onDestroy()
    }

    /*  Implement View Interface zone*/

    override fun showChartDetail(position: Int) {

    }

    override fun showChartList(list: MutableList<AwardChartModel>) {
        mAwardChartAdapter.updateList(list)
    }

    /** Method zone **/

    private fun initInstance() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        mAwardChartAdapter = AwardChartAdapter(mutableListOf())
        recyclerView.adapter = mAwardChartAdapter

        /* Load mockup chart */
        mAwardChartPresenter.loadCharts()
    }

    inner class AwardChartAdapter(var list: MutableList<AwardChartModel>) : RecyclerView.Adapter<AwardChartAdapter.AwardChartViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AwardChartViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.itemview_award_chart, parent, false)
            return AwardChartViewHolder(view)
        }

        override fun onBindViewHolder(holder: AwardChartViewHolder?, position: Int) {
            holder!!.setModel(list[position])
        }

        override fun getItemCount(): Int {
            return list.size
        }

        fun updateList(newList: MutableList<AwardChartModel>) {
            list = newList
            notifyDataSetChanged()
        }

        inner class AwardChartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            private val mAwardChartViewGroup: AwardChartViewGroup by lazy { itemView.awardChartViewGroup }

            init {

            }

            fun setModel(model: AwardChartModel) {
                mAwardChartViewGroup.setModel(model)
            }
        }
    }
}
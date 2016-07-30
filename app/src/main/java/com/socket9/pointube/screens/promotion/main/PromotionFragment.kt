package com.socket9.pointube.screens.promotion.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.socket9.pointube.R
import com.socket9.pointube.customviews.TintableImageView
import com.socket9.pointube.utils.ContextUtil
import kotlinx.android.synthetic.main.fragment_promotion.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find

/**
 * Created by Euro (ripzery@gmail.com) on 3/10/16 AD.
 */
class PromotionFragment : Fragment(), AnkoLogger, PromotionContract.View {
    /** Variable zone **/
    lateinit var param1: String
    lateinit private var mPromotionPresenter: PromotionContract.Presenter
    lateinit private var mPromotionPagerAdapter: PromotionPagerAdapter

    /** Static method zone **/
    companion object {
        val ARG_1 = "ARG_1"

        fun newInstance(param1: String): PromotionFragment {
            val bundle: Bundle = Bundle()
            bundle.putString(ARG_1, param1)
            val promotionFragment: PromotionFragment = PromotionFragment()
            promotionFragment.arguments = bundle
            return promotionFragment
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
        val rootView: View = inflater!!.inflate(R.layout.fragment_promotion, container, false)

        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPromotionPresenter = PromotionPresenter(this)
        initInstance()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPromotionPresenter.onDestroy()
    }

    override fun showNavigationTabBar(iconList: MutableList<Int>) {
//        for(index in 0..11){
        tabLayout.setCustomTabView({ viewGroup, i, pagerAdapter ->
            val context = ContextUtil.context
            val view: View = LayoutInflater.from(context).inflate(R.layout.layout_custom_tab_layout, viewGroup, false)
            val ivIcon = view.find<TintableImageView>(R.id.ivIcon)
            val layoutBackground = view.find<FrameLayout>(R.id.layoutBackground)
            ivIcon.setImageDrawable(ContextCompat.getDrawable(context, iconList[i]))
            layoutBackground.background.level = PromotionPagerAdapter.PAIR_POSITION_TO_TYPE.find { i == it.first }!!.second

            view
        })
//        }
        tabLayout.setViewPager(viewpager)

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun setupViewPager() {
        mPromotionPagerAdapter = PromotionPagerAdapter(fragmentManager)
        viewpager.adapter = mPromotionPagerAdapter
    }

    /** Method zone **/

    private fun initInstance() {
        mPromotionPresenter.onCreate()
        mPromotionPresenter.prepareViewPager()
        mPromotionPresenter.prepareTabBar()
    }
}
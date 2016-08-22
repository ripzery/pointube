package com.socket9.pointube.screens.promotion.detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.socket9.pointube.R
import com.socket9.pointube.extensions.farFrom
import com.socket9.pointube.extensions.plainText
import com.socket9.pointube.repository.programs.PublishedProgramItemRepo
import com.socket9.pointube.screens.promotion.viewgroups.PromotionItemViewGroup
import kotlinx.android.synthetic.main.fragment_promotion_detail.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.collections.forEachWithIndex
import org.jetbrains.anko.info
import java.util.*

/**
 * Created by Euro (ripzery@gmail.com) on 3/10/16 AD.
 */
class PromotionDetailFragment : Fragment(), AnkoLogger, PromotionDetailContract.View {
    /** Variable zone **/
    lateinit private var mActivity: AppCompatActivity
    private var mPromotionId: Int = 0
    lateinit private var mPromotionDetailPresenter: PromotionDetailContract.Presenter


    /** Static method zone **/
    companion object {
        val ARG_1 = "ARG_1"

        fun newInstance(promotionId: Int): PromotionDetailFragment {
            val bundle: Bundle = Bundle()
            bundle.putInt(ARG_1, promotionId)
            val promotionDetailFragment: PromotionDetailFragment = PromotionDetailFragment()
            promotionDetailFragment.arguments = bundle
            return promotionDetailFragment
        }
    }

    /* Override View interface */
    override fun showDetail(model: PublishedProgramItemRepo, logoPath: String) {
        with(model) {
            info { MasterPath }
            Glide.with(this@PromotionDetailFragment).load(MasterPath).into(ivCover)
            Glide.with(this@PromotionDetailFragment).load(logoPath).into(civLogo)

            tvTitle.text = Title
            promotionPriceSale.setModel(this)

//            mActivity.supportActionBar!!.title = Title
            collapsing_toolbar.title = Title

            /* Calculate day left */
            if (PublishPeriod != null) {
                val dayBetween = Date().farFrom(PublishPeriod!!.EndDate)
                val isShow = dayBetween < PromotionItemViewGroup.SHOW_DAY_LEFT_THRESHOLD
                tvDayLeft.visibility = if (isShow) View.VISIBLE else View.GONE
                setDayLeft(dayBetween)
            }

            tvDescription.text = Description.plainText()
            tvPoint.text = "$Point $UnitOfPoint"
            tvPeriod.text = PeriodStr

            var channels = ""
            Channels.forEachWithIndex { i, publishedChannel ->
                if (i < Channels.size - 1)
                    channels += ("${publishedChannel.Name}, ")
                else
                    channels += ("${publishedChannel.Name}")
            }

            tvChannel.text = channels
            tvHowTo.text = HowTo
            tvTerm.text = TermAndCondition
        }
    }

    override fun goBack() {
        mActivity.finish()
//        activity.overridePendingTransition(R.anim.translate_enter_from_left, R.anim.translate_exit_to_right);
    }

    /** Activity method zone  **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            /* if newly created */
            mPromotionId = arguments.getInt(ARG_1)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> mPromotionDetailPresenter.back()
        }
        return true
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater!!.inflate(R.layout.fragment_promotion_detail, container, false)

        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPromotionDetailPresenter = PromotionDetailPresenter(this)
        mPromotionDetailPresenter.onCreate()
        initInstance()
        setHasOptionsMenu(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPromotionDetailPresenter.onDestroy()
    }

    /** Method zone **/

    private fun initInstance() {
        mActivity = activity as AppCompatActivity
        mActivity.setSupportActionBar(toolbar)
        mActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mPromotionDetailPresenter.getDetail(mPromotionId)
    }

    private fun setDayLeft(dayLeft: Long) {
        when {
            dayLeft == 1L -> tvDayLeft.text = getString(R.string.promotion_detail_text_1_day)
            dayLeft > 1L -> tvDayLeft.text = "$dayLeft ${getString(R.string.promotion_detail_text_more_than_1_day)}"
            dayLeft == 0L -> tvDayLeft.text = getString(R.string.promotion_detail_text_less_than_1_day)
        }
    }
}
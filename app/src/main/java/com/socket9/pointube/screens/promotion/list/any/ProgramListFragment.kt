package com.socket9.pointube.screens.promotion.list.any

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.socket9.pointube.R
import com.socket9.pointube.extensions.hideLoadingDialog
import com.socket9.pointube.extensions.showLoadingDialog
import com.socket9.pointube.repository.programs.PublishedProgramItemRepo
import com.socket9.pointube.screens.login.LoginActivity
import com.socket9.pointube.screens.promotion.detail.PromotionDetailActivity
import com.socket9.pointube.utils.SharedPrefUtil
import kotlinx.android.synthetic.main.fragment_program_list.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.support.v4.startActivity

/**
 * Created by ripzery on 7/20/16.
 */

class ProgramListFragment : Fragment(), ProgramListContract.View, AnkoLogger {
    /** Variable zone **/
    private var mBrandId: Int = 0
    private var mUnitId: Int = 0
    lateinit private var mProgramListPresenter: ProgramListContract.Presenter
    lateinit private var mProgramListAdapter: ProgramListAdapter
    lateinit private var mActivity: AppCompatActivity

    /** Static method zone **/
    companion object {
        val ARG_1 = "ARG_1"
        val ARG_2 = "ARG_2"

        fun newInstance(brandId: Int, unitId: Int): ProgramListFragment {
            val bundle: Bundle = Bundle()
            bundle.putInt(ARG_1, brandId)
            bundle.putInt(ARG_2, unitId)
            val programListFragment: ProgramListFragment = ProgramListFragment()
            programListFragment.arguments = bundle
            return programListFragment
        }

    }

    /** Activity method zone  **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            /* if newly created */
            mBrandId = arguments.getInt(ARG_1)
            mUnitId = arguments.getInt(ARG_2)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> mActivity.finish()
        }
        return true
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater!!.inflate(R.layout.fragment_program_list, container, false)
        setHasOptionsMenu(true)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mProgramListPresenter = ProgramListPresenter(this)
        mProgramListPresenter.onCreate()
        initInstance()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mProgramListPresenter.onDestroy()
    }

    /* Override View Interface zone */
    override fun showProgramList(newList: MutableList<PublishedProgramItemRepo>) {
        mProgramListAdapter.updateProgramList(newList)
        tvComingSoon.visibility = View.GONE
    }

    override fun showLoading() {
        showLoadingDialog(getString(R.string.dialog_default_progress_loading_title), getString(R.string.promotion_list_loading_program))
    }

    override fun hideLoading() {
        hideLoadingDialog()
    }

    override fun showProgramDetail(programId: Int) {
        startActivity<PromotionDetailActivity>("id" to programId)
    }

    override fun showLogin() {
        startActivity<LoginActivity>()
    }

    override fun showEmptyProgram() {
        tvComingSoon.visibility = View.VISIBLE
    }
    /** Method zone **/

    private fun initInstance() {
        /* Set recycler view adapter */
        recyclerView.layoutManager = LinearLayoutManager(context)
        mProgramListAdapter = ProgramListAdapter(mutableListOf(), object : ProgramListListener {
            override fun onItemClick(programId: Int) {
                mProgramListPresenter.clickProgram(programId)
            }
        })
        recyclerView.adapter = mProgramListAdapter

        /* Load program list by brand */
        mProgramListPresenter.loadProgramList(mBrandId, mUnitId)

        /* Load brand cover url */
//        mProgramListPresenter.loadCover(mBrandId)
        initLoginSpannable()

    }

    private fun initLoginSpannable(){
        if(SharedPrefUtil.loadLoginResult() == null){
            val spannableString = SpannableString(getString(R.string.program_list_text_login_to_see_more))
            val clickableSpan: ClickableSpan = object : ClickableSpan(){
                override fun onClick(view: View?) {
                    mProgramListPresenter.clickLogin()
                }

                override fun updateDrawState(ds: TextPaint?) {
                    super.updateDrawState(ds)
                    ds!!.isUnderlineText = true
                }
            }

            spannableString.setSpan(clickableSpan, 7, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            tvSeeMore.text = spannableString
            tvSeeMore.movementMethod = LinkMovementMethod.getInstance()
            tvSeeMore.highlightColor = ContextCompat.getColor(context, R.color.colorGreen)
            tvSeeMore.visibility = View.VISIBLE
        }else{
            tvSeeMore.visibility = View.GONE
        }
    }

    inner class ProgramListAdapter(var list: MutableList<PublishedProgramItemRepo>, val listener: ProgramListListener) : RecyclerView.Adapter<ProgramListAdapter.ProgramListViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ProgramListViewHolder {
            val view = LayoutInflater.from(parent!!.context).inflate(R.layout.itemview_promotion_list, parent, false)
            return ProgramListViewHolder(view)
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: ProgramListViewHolder?, position: Int) {
            holder!!.setModel(list[position])
        }

        fun updateProgramList(newList: MutableList<PublishedProgramItemRepo>) {
            this.list = newList
            notifyDataSetChanged()
        }

        inner class ProgramListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val mPromotionListViewGroup: PromotionListViewGroup by lazy { itemView.findViewById(R.id.promotionList) as PromotionListViewGroup }

            init {
                mPromotionListViewGroup.setOnClickListener { listener.onItemClick(list[adapterPosition].Id) }
            }

            fun setModel(model: PublishedProgramItemRepo) {
                mPromotionListViewGroup.setModel(model)
            }
        }


    }

    interface ProgramListListener {
        fun onItemClick(programId: Int)
    }
}
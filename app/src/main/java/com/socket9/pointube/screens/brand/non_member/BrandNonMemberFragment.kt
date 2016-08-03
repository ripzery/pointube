package com.socket9.pointube.screens.brand.non_member

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.socket9.pointube.R
import com.socket9.pointube.extensions.hideLoadingDialog
import com.socket9.pointube.extensions.showLoadingDialog
import com.socket9.pointube.repository.brands.BrandRepo
import com.socket9.pointube.screens.brand.BrandViewGroup
import com.socket9.pointube.screens.home.LoginModel
import com.socket9.pointube.utils.SharedPrefUtil
import kotlinx.android.synthetic.main.fragment_brand_non_member.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.support.v4.toast
import java.util.*

/**
 * Created by Euro (ripzery@gmail.com) on 3/10/16 AD.
 */
class BrandNonMemberFragment : Fragment(), AnkoLogger, BrandNonMemberContract.View {
    /** Variable zone **/
    lateinit var mSelectedBrands: ArrayList<Int>
    lateinit var mQualifiedBrandIdList: ArrayList<Int>
    lateinit var mActivityListener: BrandNonMemberListener
    lateinit var mBrandNonMemberPresenter: BrandNonMemberContract.Presenter
    lateinit var mBrandNonMemberAdapter: BrandNonMemberAdapter

    private val mLoginModel: LoginModel.Response.LoginResult by lazy { SharedPrefUtil.loadLoginResult()!! }

    /** Static method zone **/
    companion object {
        val ARG_1 = "ARG_1"
        val ARG_2 = "ARG_2"

        fun newInstance(selectedBrand: MutableList<Int>, qualifiedBrandIdList: MutableList<Int>): BrandNonMemberFragment {
            val bundle: Bundle = Bundle()
            bundle.putIntegerArrayList(ARG_1, ArrayList(selectedBrand))
            bundle.putIntegerArrayList(ARG_2, ArrayList(qualifiedBrandIdList))
            val brandNonMemberFragment: BrandNonMemberFragment = BrandNonMemberFragment()
            brandNonMemberFragment.arguments = bundle
            return brandNonMemberFragment
        }

    }

    /** Activity method zone  **/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityListener = activity as BrandNonMemberListener
        if (savedInstanceState == null) {
            /* if newly created */
            mSelectedBrands = arguments.getIntegerArrayList(ARG_1)
            mQualifiedBrandIdList = arguments.getIntegerArrayList(ARG_2)
            info { mSelectedBrands }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> mActivityListener.goBackFromBrandNonMember()
        }
        return true
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater!!.inflate(R.layout.fragment_brand_non_member, container, false)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBrandNonMemberPresenter = BrandNonMemberPresenter(this)
        mBrandNonMemberPresenter.onCreate()
        initInstance()
        setHasOptionsMenu(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBrandNonMemberPresenter.onDestroy()
    }

    /** Override View Interface **/
    override fun highlightAllBrands(allBrands: MutableList<BrandRepo>) {
        mBrandNonMemberAdapter.updateList(allBrands)
    }

    override fun unHighlightAllBrands(allBrands: MutableList<BrandRepo>) {
        mBrandNonMemberAdapter.updateList(allBrands)
    }

    override fun showQualifiedBrand(allBrands: MutableList<BrandRepo>) {
        mBrandNonMemberAdapter.updateList(allBrands)
    }

    override fun showLoading() {
        /* TODO: Show loading */
        showLoadingDialog("Please wait", "Saving brand..", true)
    }

    override fun hideLoading() {
        hideLoadingDialog()
    }

    override fun showSaveSuccess() {
        toast("Save brand successful")
    }

    override fun showSaveFailed() {
        toast("Save brand failed")
    }

    override fun showEmptyBrands() {
        toast("Empty brands")
    }

    override fun showLoadingError() {
        toast("Error on loading")
    }

    override fun goNext() {
        mActivityListener.goNextFromBrandNonMember()
    }

    /** Method zone **/

    private fun initInstance() {
        /* Setup recycler view */
        mBrandNonMemberAdapter = BrandNonMemberAdapter(mutableListOf())
        recyclerView.adapter = mBrandNonMemberAdapter
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        btnStart.setOnClickListener { mBrandNonMemberPresenter.save(mLoginModel.id.toString(), mLoginModel.token!!, mBrandNonMemberAdapter.getBrandList()) }

        btnSelectAll.setOnClickListener {
            it.isSelected = !it.isSelected
            btnSelectAll.text = if (it.isSelected) "Unselect all" else "Select all"
            mBrandNonMemberPresenter.selectAllBrand(it.isSelected)
        }

        tvUsername.text = "${mLoginModel.firstNameEN} ${mLoginModel.lastNameEN}"

        mBrandNonMemberPresenter.loadAllBrands(mQualifiedBrandIdList, mSelectedBrands)
    }

    /* Inner class */
    inner class BrandNonMemberAdapter(var list: MutableList<BrandRepo>) : RecyclerView.Adapter<BrandNonMemberAdapter.BrandNonMemberViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BrandNonMemberViewHolder {
            val view: View = LayoutInflater.from(parent!!.context).inflate(R.layout.itemview_select_brand, parent, false)
            return BrandNonMemberViewHolder(view)
        }

        override fun onBindViewHolder(holder: BrandNonMemberViewHolder?, position: Int) {
            holder!!.setModel(list[position])
        }

        override fun getItemCount(): Int {
            return list.size
        }

        fun updateList(list: MutableList<BrandRepo>) {
            this.list = list
            notifyDataSetChanged()
        }

        fun getBrandList(): MutableList<BrandRepo> {
            return list
        }

        inner class BrandNonMemberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val mBrandMember: BrandViewGroup by lazy { itemView.findViewById(R.id.brandViewGroup) as BrandViewGroup }

            init {
                mBrandMember.getCheckedObservable()
                        .subscribe {
                            list[adapterPosition].isChecked = it
                        }
            }

            fun setModel(brand: BrandRepo) {
                mBrandMember.setModel(brand)
            }

        }
    }

    interface BrandNonMemberListener {
        fun goBackFromBrandNonMember()

        fun goNextFromBrandNonMember()
    }
}
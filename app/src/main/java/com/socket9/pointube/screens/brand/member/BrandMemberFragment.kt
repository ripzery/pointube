package com.socket9.pointube.screens.brand.member

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
import com.socket9.pointube.repository.brands.GetMemberBrandResult
import com.socket9.pointube.screens.brand.BrandModel
import com.socket9.pointube.screens.brand.BrandViewGroup
import com.socket9.pointube.screens.home.LoginModel
import com.socket9.pointube.utils.SharedPrefUtil
import kotlinx.android.synthetic.main.fragment_brand_member.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.support.v4.toast

/**
 * Created by Euro (ripzery@gmail.com) on 3/10/16 AD.
 */
class BrandMemberFragment : Fragment(), BrandMemberContract.View, AnkoLogger {
    /** Variable zone **/
    private lateinit var mActivityListener: BrandMemberListener
    private lateinit var mBrandMemberPresenter: BrandMemberContract.Presenter
    private lateinit var mBrandMemberAdapter: BrandMemberAdapter
    private val mLoginModel: LoginModel.Response.LoginResult by lazy { SharedPrefUtil.loadLoginResult()!! }
    private var mIsSelectAllSelected: Boolean = false
    private var mIsEdit: Boolean = false

    /** Static method zone **/
    companion object {
        val ARG_1 = "ARG_1"

        fun newInstance(isEdit: Boolean = false): BrandMemberFragment {
            val bundle: Bundle = Bundle()
            bundle.putBoolean(ARG_1, isEdit)
            val brandMemberFragment: BrandMemberFragment = BrandMemberFragment()
            brandMemberFragment.arguments = bundle
            return brandMemberFragment
        }

    }

    /** Activity method zone  **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityListener = activity as BrandMemberListener
        if (savedInstanceState == null) {
            /* if newly created */
            mIsEdit = arguments.getBoolean(ARG_1)
            retainInstance = true
            mBrandMemberPresenter = BrandMemberPresenter(this)
            mBrandMemberPresenter.onCreate()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> mActivityListener.goBackFromBrandMember()
        }
        return true
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater!!.inflate(R.layout.fragment_brand_member, container, false)

        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initInstance()
        setHasOptionsMenu(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        mBrandMemberPresenter.onDestroy()
    }

    /* Override View Interface zone */
    override fun highlightAllBrands(qualifiedList: MutableList<GetMemberBrandResult>) {
        mBrandMemberAdapter.updateList(qualifiedList)
    }

    override fun unHighlightAllBrands(qualifiedList: MutableList<GetMemberBrandResult>) {
        mBrandMemberAdapter.updateList(qualifiedList)
    }

    override fun showQualifiedBrand(qualifiedList: MutableList<GetMemberBrandResult>) {
        mBrandMemberAdapter.updateList(qualifiedList)
    }

    override fun showLoading() {
        showLoadingDialog("Please wait", "Loading qualified brand...")
    }

    override fun hideLoading() {
        hideLoadingDialog()
    }

    override fun showEmptyBrands() {
//        toast("Empty qualified brand")
    }

    override fun showLoadingError() {
        toast("Loading error")
    }

    override fun showSaveFailed(msg: String) {
        toast(msg)
    }

    override fun showSaveSuccess() {
        toast("Save successful")
        mActivityListener.goNextFromBrandMember(mutableListOf(), mutableListOf())
    }

    override fun goNext(selectedBrand: MutableList<Int>, qualifiedBrandIdList: MutableList<Int>) {
        /* If in select brand first time*/
        if (!mIsEdit) {
            mActivityListener.goNextFromBrandMember(selectedBrand, qualifiedBrandIdList)
        } else { /* If edit brand member later */
            mBrandMemberPresenter.saveBrand()
        }
    }

    /** Method zone **/

    private fun initInstance() {
        mBrandMemberAdapter = BrandMemberAdapter(mutableListOf())
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = mBrandMemberAdapter

        if(!mIsEdit)
            btnNext.setOnClickListener { mBrandMemberPresenter.next() }
        else
            btnNext.setOnClickListener { mBrandMemberPresenter.saveBrand() }

        /* Initial btn select all */
        btnSelectAll.isSelected = mIsSelectAllSelected
        btnSelectAll.text = if (mIsSelectAllSelected) "Unselect all" else "Select all"
        btnSelectAll.setOnClickListener {
            it.isSelected = !it.isSelected
            mIsSelectAllSelected = it.isSelected
            btnSelectAll.text = if (it.isSelected) "Unselect all" else "Select all"
            mBrandMemberPresenter.selectAllBrand(it.isSelected)
        }

        tvUsername.text = "${mLoginModel.firstNameEN} ${mLoginModel.lastNameEN}"

        btnNext.text = if (mIsEdit) "Save" else "Next"

        mBrandMemberPresenter.loadAllBrands(mLoginModel.id.toString(), mLoginModel.token!!, mIsEdit)
    }

    /* Inner class */
    inner class BrandMemberAdapter(var list: MutableList<GetMemberBrandResult>) : RecyclerView.Adapter<BrandMemberAdapter.BrandMemberViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BrandMemberViewHolder {
            val view: View = LayoutInflater.from(parent!!.context).inflate(R.layout.itemview_select_brand, parent, false)
            return BrandMemberViewHolder(view)
        }

        override fun onBindViewHolder(holder: BrandMemberViewHolder?, position: Int) {
            holder!!.setModel(list[position])
        }

        override fun getItemCount(): Int {
            return list.size
        }

        fun updateList(list: MutableList<GetMemberBrandResult>) {
            this.list = list
            notifyDataSetChanged()
        }

        fun getBrandList(): MutableList<GetMemberBrandResult> {
            return list
        }

        inner class BrandMemberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val mBrandMember: BrandViewGroup by lazy { itemView.findViewById(R.id.brandViewGroup) as BrandViewGroup }

            init {
                mBrandMember.getCheckedObservable()
                        .subscribe {
                            list[adapterPosition].isChecked = it
                            info { it }
                        }
            }

            fun setModel(brand: GetMemberBrandResult) {
                mBrandMember.setModel(brand)
            }

        }
    }

    interface BrandMemberListener {
        fun goBackFromBrandMember()

        fun goNextFromBrandMember(selectedBrand: MutableList<Int>, qualifiedBrandIdList: MutableList<Int>)
    }
}

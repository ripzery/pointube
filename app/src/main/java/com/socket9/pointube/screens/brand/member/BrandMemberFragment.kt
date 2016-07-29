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
import com.socket9.pointube.screens.brand.BrandModel
import com.socket9.pointube.screens.brand.BrandViewGroup
import com.socket9.pointube.screens.home.LoginModel
import com.socket9.pointube.utils.SharedPrefUtil
import kotlinx.android.synthetic.main.fragment_brand_member.*
import org.jetbrains.anko.support.v4.toast

/**
 * Created by Euro (ripzery@gmail.com) on 3/10/16 AD.
 */
class BrandMemberFragment : Fragment(), BrandMemberContract.View {
    /** Variable zone **/
    private lateinit var mActivityListener: BrandMemberListener
    private lateinit var mBrandMemberPresenter: BrandMemberContract.Presenter
    private lateinit var mBrandMemberAdapter: BrandMemberAdapter
    private val mLoginModel: LoginModel.Response.LoginResult by lazy { SharedPrefUtil.loadLoginResult()!! }
    private var param1:String = ""

    /** Static method zone **/
    companion object {
        val ARG_1 = "ARG_1"

        fun newInstance(param1: String): BrandMemberFragment {
            var bundle: Bundle = Bundle()
            bundle.putString(ARG_1, param1)
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
            param1 = arguments.getString(ARG_1)
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
    override fun highlightAllBrands(qualifiedList: MutableList<BrandModel.Response.GetMemberBrandResult>) {
        mBrandMemberAdapter.updateList(qualifiedList)
    }

    override fun unHighlightAllBrands(qualifiedList: MutableList<BrandModel.Response.GetMemberBrandResult>) {
        mBrandMemberAdapter.updateList(qualifiedList)
    }

    override fun showQualifiedBrand(qualifiedList: MutableList<BrandModel.Response.GetMemberBrandResult>) {
        qualifiedList.add(qualifiedList[0])
        qualifiedList.add(qualifiedList[0])
        qualifiedList.add(qualifiedList[0])
        qualifiedList.add(qualifiedList[0])
        qualifiedList.add(qualifiedList[0])
        qualifiedList.add(qualifiedList[0])
        mBrandMemberAdapter.updateList(qualifiedList)
    }

    override fun showLoading() {
        showLoadingDialog("Please wait", "Loading qualified brand...")
    }

    override fun hideLoading() {
        hideLoadingDialog()
    }

    override fun showEmptyBrands() {
        toast("Empty qualified brand")
    }

    override fun showLoadingError() {
        toast("Loading error")
    }

    override fun goNext() {
        mActivityListener.goNextFromBrandMember()
    }

    /** Method zone **/

    private fun initInstance() {
        mBrandMemberAdapter = BrandMemberAdapter(mutableListOf())
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = mBrandMemberAdapter

        btnNext.setOnClickListener { mBrandMemberPresenter.next() }

        btnSelectAll.setOnClickListener {
            it.isSelected = !it.isSelected
            btnSelectAll.text = if(it.isSelected) "Unselect all" else "Select all"
            mBrandMemberPresenter.selectAllBrand(it.isSelected)
        }

        tvUsername.text = "${mLoginModel.firstNameEN} ${mLoginModel.lastNameEN}"

        mBrandMemberPresenter.loadAllBrands(mLoginModel.id.toString(), mLoginModel.token!!)
    }

    /* Inner class */
    inner class BrandMemberAdapter(var list: MutableList<BrandModel.Response.GetMemberBrandResult>) : RecyclerView.Adapter<BrandMemberAdapter.BrandMemberViewHolder>() {
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

        fun updateList(list: MutableList<BrandModel.Response.GetMemberBrandResult>) {
            this.list = list
            notifyDataSetChanged()
        }

        fun getBrandList(): MutableList<BrandModel.Response.GetMemberBrandResult> {
            return list
        }

        inner class BrandMemberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val mBrandMember: BrandViewGroup by lazy { itemView.findViewById(R.id.brandViewGroup) as BrandViewGroup }

            init {
                mBrandMember.getCheckedObservable()
                        .subscribe {
                            list[adapterPosition].isChecked = it
                        }
            }

            fun setModel(brand: BrandModel.Response.GetMemberBrandResult) {
                mBrandMember.setModel(brand)
            }

        }
    }

    interface BrandMemberListener {
        fun goBackFromBrandMember()

        fun goNextFromBrandMember()
    }
}

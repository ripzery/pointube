package com.socket9.pointube.screens.home

import HomeImageVideoFragment
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.socket9.pointube.R
import com.socket9.pointube.repository.brands.BrandRepo
import com.socket9.pointube.repository.programs.PublishedProgramItemRepo
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.support.v4.toast

/**
 * Created by Euro (ripzery@gmail.com) on 3/10/16 AD.
 */
class HomeFragment : Fragment(), HomeContract.View, AnkoLogger {
    /** Variable zone **/
    lateinit var param1: String
    private val mLinearLayoutManager: LinearLayoutManager by lazy { LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false) }
    private val mProviderListAdapter: BrandUnitAdapter by lazy { BrandUnitAdapter(mutableListOf()) }
    private val mImageVideoPagerAdapter: ImageVideoPagerAdapter by lazy { ImageVideoPagerAdapter(childFragmentManager, mutableListOf()) }
    private val mHomePresenter: HomeContract.Presenter by lazy { HomePresenter(this) }
    private val mMainActivity: OnLoginListener by lazy { activity as OnLoginListener }

    /** Static method zone **/
    companion object {
        val ARG_1 = "ARG_1"

        fun newInstance(param1: String): HomeFragment {
            var bundle: Bundle = Bundle()
            bundle.putString(ARG_1, param1)
            val homeFragment: HomeFragment = HomeFragment()
            homeFragment.arguments = bundle
            return homeFragment
        }

    }

    /** Activity method zone  **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            /* if newly created */
            param1 = arguments.getString(ARG_1)
            activity
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater!!.inflate(R.layout.fragment_home, container, false)

        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInstance()
        info { "Create Home" }
        mHomePresenter.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        Realm.getDefaultInstance().close()
        info { "Destroy Home" }
        mHomePresenter.onDestroy()
    }

    /** Override View Interface zone **/

    override fun showProviderList(allBrands: MutableList<BrandRepo>) {
        mProviderListAdapter.updateProviderList(allBrands)
    }

    override fun showEmptyProviderList() {

    }

    override fun showPublishedProgramList(allPublishedProgram: MutableList<PublishedProgramItemRepo>) {
        mImageVideoPagerAdapter.updateList(allPublishedProgram)
    }

    override fun showEmptyPublishedProgramList() {
        toast("Empty Published Program List")
    }

    override fun showProgressBar() {

    }

    override fun goLogin() {
        mMainActivity.onLogin()
    }

    override fun goSignUp() {
        mMainActivity.onSignUp()
    }

    override fun showLoggedInState() {
        btnLogin.visibility = View.GONE
        layoutNewUser.visibility = View.GONE
    }

    override fun showUnLoggedInState() {
        btnLogin.visibility = View.VISIBLE
        layoutNewUser.visibility = View.VISIBLE
    }

    override fun updatePromotionCount(newList: MutableList<BrandRepo>) {
        info { newList }
        mProviderListAdapter.updateProviderList(newList)
    }

    /** Method zone **/

    private fun initInstance() {
        recyclerView.adapter = mProviderListAdapter
        recyclerView.layoutManager = mLinearLayoutManager

        viewpager.adapter = mImageVideoPagerAdapter
        indicator.setViewPager(viewpager)
        mImageVideoPagerAdapter.registerDataSetObserver(indicator.dataSetObserver)

        btnLogin.setOnClickListener {
            mHomePresenter.doLogin()
        }

        tvSignUp.setOnClickListener {
            mHomePresenter.doSignUp()
        }
    }

    /** Inner class zone **/

    inner class BrandUnitAdapter(var list: MutableList<BrandRepo>) : RecyclerView.Adapter<BrandUnitAdapter.BrandUnitViewHolder>() {
        override fun onBindViewHolder(holder: BrandUnitViewHolder?, position: Int) {
            holder!!.setModel(list[position])
            holder.setBadgeCount(list[position].TotalPrograms)
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BrandUnitViewHolder {
            val v: View = LayoutInflater.from(parent!!.context).inflate(R.layout.itemview_home_brand, parent, false)
            return BrandUnitViewHolder(v)
        }

        fun updateProviderList(providerList: MutableList<BrandRepo>) {
            list = providerList
            notifyDataSetChanged()
        }

        inner class BrandUnitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            private val homeBrandViewGroup: HomePartnerViewGroup by lazy {
                itemView?.findViewById(R.id.homePartnerViewGroup) as HomePartnerViewGroup
            }

            fun setModel(brand: BrandRepo) {
                homeBrandViewGroup.setModel(brand)
            }

            fun setBadgeCount(count: Int) {
                homeBrandViewGroup.setBadgeCount(count)
            }
        }
    }

    inner class ImageVideoPagerAdapter(fm: FragmentManager, var list: MutableList<PublishedProgramItemRepo>) : FragmentStatePagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            /* TODO: Return correctly fragment */
            return HomeImageVideoFragment.newInstance(list[position].MasterPath, false)
        }

        override fun getCount(): Int {
            /* TODO: Return correctly count */
            return list.size
        }

        fun updateList(list: MutableList<PublishedProgramItemRepo>) {
            this.list = list
            notifyDataSetChanged()
        }
    }

    /* Interface zone */
    interface OnLoginListener {
        fun onLogin()
        fun onSignUp()
    }

}
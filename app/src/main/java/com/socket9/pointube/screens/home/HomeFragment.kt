package com.socket9.pointube.screens.home

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.socket9.pointube.R
import com.socket9.pointube.manager.DataManager
import com.socket9.pointube.repository.brands.BrandRepo
import com.socket9.pointube.screens.login.LoginActivity
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import rx_activity_result.RxActivityResult

/**
 * Created by Euro (ripzery@gmail.com) on 3/10/16 AD.
 */
class HomeFragment : Fragment(), HomeContract.View, AnkoLogger {

    /** Variable zone **/
    lateinit var param1: String
    private val mLinearLayoutManager: LinearLayoutManager by lazy { LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false) }
    private val mProviderListAdapter: BrandUnitAdapter by lazy { BrandUnitAdapter(HomeModel.AllBrands(false, mutableListOf())) }
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
        mHomePresenter.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        Realm.getDefaultInstance().close()
        mHomePresenter.onDestroy()
    }

    /** Override View Interface zone **/

    override fun showProviderList(allBrands: HomeModel.AllBrands) {
        mProviderListAdapter.updateProviderList(allBrands)
    }

    override fun showProgressBar() {

    }

    override fun showEmptyProviderList() {

    }

    override fun goLogin() {
        mMainActivity.onLogin()
    }

    override fun showLoggedInState() {
        btnLogin.visibility = View.GONE
        layoutNewUser.visibility = View.GONE
    }

    override fun showUnLoggedInState() {
        btnLogin.visibility = View.VISIBLE
        layoutNewUser.visibility = View.VISIBLE
    }

    /** Method zone **/

    private fun initInstance() {
        recyclerView.adapter = mProviderListAdapter
        recyclerView.layoutManager = mLinearLayoutManager
        btnLogin.setOnClickListener {
            mHomePresenter.doLogin()
        }
    }


    /** Inner class zone **/

    inner class BrandUnitAdapter(var list: HomeModel.AllBrands) : RecyclerView.Adapter<BrandUnitAdapter.BrandUnitViewHolder>() {
        override fun onBindViewHolder(holder: BrandUnitViewHolder?, position: Int) {
            holder!!.setModel(list.Results[position])
        }

        override fun getItemCount(): Int {
            return list.Results.size
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BrandUnitViewHolder {
            val v: View = LayoutInflater.from(parent!!.context).inflate(R.layout.itemview_home_brand, parent, false)
            return BrandUnitViewHolder(v)
        }

        fun updateProviderList(providerList: HomeModel.AllBrands) {
            list = providerList
            notifyDataSetChanged()
        }

        inner class BrandUnitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            lateinit private var homeBrandViewGroup: HomePartnerViewGroup

            init {
                homeBrandViewGroup = itemView?.findViewById(R.id.homePartnerViewGroup) as HomePartnerViewGroup
            }

            fun setModel(brand: BrandRepo) {
                homeBrandViewGroup.setModel(brand)
            }

        }
    }

    /* Interface zone */
    interface OnLoginListener {
        fun onLogin()
    }

}
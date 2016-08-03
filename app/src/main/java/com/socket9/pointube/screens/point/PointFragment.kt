package com.socket9.pointube.screens.point

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.socket9.pointube.R
import com.socket9.pointube.extensions.hideLoadingDialog
import com.socket9.pointube.extensions.showLoadingDialog
import com.socket9.pointube.screens.brand.BrandModel
import com.socket9.pointube.screens.brand.BrandViewGroup
import com.socket9.pointube.screens.home.LoginModel
import kotlinx.android.synthetic.main.fragment_point.*
import org.jetbrains.anko.AnkoLogger
import java.text.SimpleDateFormat

/**
 * Created by Euro (ripzery@gmail.com) on 3/10/16 AD.
 */
class PointFragment : Fragment(), AnkoLogger, PointContract.View {


    /** Variable zone **/
    lateinit var param1: String
    lateinit var mPointPresenter: PointContract.Presenter
    lateinit var mPointAdapter: PointAdapter

    /** Static method zone **/
    companion object {
        val ARG_1 = "ARG_1"

        fun newInstance(param1: String): PointFragment {
            var bundle: Bundle = Bundle()
            bundle.putString(ARG_1, param1)
            val templateFragment: PointFragment = PointFragment()
            templateFragment.arguments = bundle
            return templateFragment
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
        val rootView: View = inflater!!.inflate(R.layout.fragment_point, container, false)

        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPointPresenter = PointPresenter(this)
        mPointPresenter.onCreate()
        initInstance()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPointPresenter.onDestroy()
    }

    /* Implement View Interface zone */
    override fun showBrands(allBrands: MutableList<BrandModel.Response.GetMemberBrandResult>) {
        mPointAdapter.updateList(allBrands)
    }

    override fun showLoading() {
        showLoadingDialog("Please wait", "Loading selected brand..")
    }

    override fun hideLoading() {
        hideLoadingDialog()
    }

    override fun initUser(model: LoginModel.Response.LoginResult) {
        tvUsername.text = "${model.firstNameEN} ${model.lastNameEN}"
        tvDate.text = SimpleDateFormat("dd/MM/yyyy").format(model.birthday)
    }

    /** Method zone **/

    private fun initInstance() {

        /* Init recycler view */
        recyclerView.layoutManager = LinearLayoutManager(context)
        mPointAdapter = PointAdapter(mutableListOf(), object : PointListener {
            override fun onBrandClick(id: Int) {
                /* TODO: Do something when user tapped brand point  */
            }
        })
        recyclerView.adapter = mPointAdapter

        mPointPresenter.loadUser()
        mPointPresenter.loadBrands()
    }

    /* Inner class zone */
    inner class PointAdapter(var list: MutableList<BrandModel.Response.GetMemberBrandResult>, var listener: PointListener) : RecyclerView.Adapter<PointAdapter.PointViewHolder>() {
        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: PointViewHolder?, position: Int) {
            holder!!.setModel(list[position])
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PointViewHolder {
            val view = LayoutInflater.from(parent!!.context).inflate(R.layout.itemview_select_brand, parent, false)
            return PointViewHolder(view)
        }

        fun updateList(newList: MutableList<BrandModel.Response.GetMemberBrandResult>) {
            list = newList
            notifyDataSetChanged()
        }

        inner class PointViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val mBrandViewGroup: BrandViewGroup by lazy { itemView.findViewById(R.id.brandViewGroup) as BrandViewGroup }

            init {
                mBrandViewGroup.setOnClickListener {
                    listener.onBrandClick(list[adapterPosition].Id)
                }
            }

            fun setModel(model: BrandModel.Response.GetMemberBrandResult) {
                mBrandViewGroup.setModel(model, true) /* true for showPoint instead of checkBox */
            }

        }
    }

    interface PointListener {
        fun onBrandClick(id: Int)
    }
}
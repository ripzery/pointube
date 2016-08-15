package com.socket9.pointube.screens.promotion.main

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder
import com.socket9.pointube.R
import com.socket9.pointube.repository.brands.BrandRepo
import com.socket9.pointube.screens.brand.BrandModel
import com.socket9.pointube.utils.ContextUtil
import kotlinx.android.synthetic.main.itemview_filtered_brand.*
import org.jetbrains.anko.find

/**
 * Created by ripzery on 8/15/16.
 */
class ExpandableListAdapter(val context: Context = ContextUtil.context!!, parentListItem: MutableList<ParentListItem>) : ExpandableRecyclerAdapter<ExpandableListAdapter.BrandParentViewHolder, ExpandableListAdapter.BrandChildViewHolder>(parentListItem) {
    override fun onCreateParentViewHolder(parentViewGroup: ViewGroup?): BrandParentViewHolder {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindChildViewHolder(childViewHolder: BrandChildViewHolder?, position: Int, childListItem: Any?) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateChildViewHolder(childViewGroup: ViewGroup?): BrandChildViewHolder {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindParentViewHolder(parentViewHolder: BrandParentViewHolder?, position: Int, parentListItem: ParentListItem?) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    inner class BrandParentViewHolder(itemView: View) : ParentViewHolder(itemView) {
        private val mParentViewGroup = itemView.find<FilteredBrandViewGroup>(R.id.filteredBrandViewGroup)

        init {

        }

        fun setModel(model: BrandRepo){
            mParentViewGroup.setModel(model)
        }
    }

    inner class BrandChildViewHolder(itemView: View) : ChildViewHolder(itemView) {
        private val mChildViewGroup = itemView.find<FilteredBrandViewGroup>(R.id.filteredBrandViewGroup)

        init {

        }

        fun setModel(model: BrandRepo){
            mChildViewGroup.setModel(model)
        }

    }
}
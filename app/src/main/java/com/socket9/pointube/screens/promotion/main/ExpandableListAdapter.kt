package com.socket9.pointube.screens.promotion.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder
import com.socket9.pointube.R
import com.socket9.pointube.repository.brands.BrandRepo
import com.socket9.pointube.repository.brands.BrandUnitRepo
import com.socket9.pointube.utils.ContextUtil
import org.jetbrains.anko.find

/**
 * Created by ripzery on 8/15/16.
 */
class ExpandableListAdapter(val context: Context = ContextUtil.context!!, var parentListItem: MutableList<BrandRepo>) : ExpandableRecyclerAdapter<ExpandableListAdapter.BrandParentViewHolder, ExpandableListAdapter.BrandChildViewHolder>(parentListItem) {
    override fun onCreateParentViewHolder(parentViewGroup: ViewGroup?): BrandParentViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.itemview_filtered_brand, parentViewGroup, false)
        return BrandParentViewHolder(view)
    }

    override fun onBindChildViewHolder(childViewHolder: BrandChildViewHolder?, position: Int, childListItem: Any?) {
        childViewHolder?.setModel(childListItem as BrandUnitRepo)
    }

    override fun onCreateChildViewHolder(childViewGroup: ViewGroup?): BrandChildViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.itemview_filtered_brand, childViewGroup, false)
        return BrandChildViewHolder(view)
    }

    override fun onBindParentViewHolder(parentViewHolder: BrandParentViewHolder?, position: Int, parentListItem: ParentListItem?) {
        parentViewHolder?.setModel(parentListItem!! as BrandRepo)
    }

    fun updateList(allBrands: MutableList<BrandRepo>) {
        parentListItem = allBrands
        notifyDataSetChanged()
    }

    inner class BrandParentViewHolder(itemView: View) : ParentViewHolder(itemView) {
        private val mParentViewGroup = itemView.find<FilteredBrandViewGroup>(R.id.filteredBrandViewGroup)

        init {
            mParentViewGroup.setExpandClickListener { if (it) expandView() else collapseView() }
            mParentViewGroup.setItemClickListener { /* TODO: Implement onItemClicked */ }
        }

        fun setModel(model: BrandRepo) {
            mParentViewGroup.setModel(model)
        }

        override fun shouldItemViewClickToggleExpansion(): Boolean {
            return true
        }
    }

    inner class BrandChildViewHolder(itemView: View) : ChildViewHolder(itemView) {
        private val mChildViewGroup = itemView.find<FilteredBrandViewGroup>(R.id.filteredBrandViewGroup)

        init {
            mChildViewGroup.setItemClickListener { /* TODO: Implement onItemClicked */ }
        }

        fun setModel(model: BrandUnitRepo) {
            mChildViewGroup.setModel(model)
        }

    }
}
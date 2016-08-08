package com.socket9.pointube.screens.about

import android.content.Context
import com.socket9.pointube.R
import com.socket9.pointube.utils.ContextUtil

/**
 * Created by ripzery on 8/8/16.
 */
class AboutPresenter(var view: AboutContract.View?) : AboutContract.Presenter {
    private val mContext: Context by lazy { ContextUtil.context!! }

    private val mAboutItemList: MutableList<AboutItem> by lazy {
        mutableListOf(
                AboutItem(R.drawable.ic_library_books_black_24dp, mContext.getString(R.string.fragment_about_what_is_pointube)),
                AboutItem(R.drawable.ic_library_books_black_24dp, mContext.getString(R.string.fragment_about_what_is_pointube)),
                AboutItem(R.drawable.ic_library_books_black_24dp, mContext.getString(R.string.fragment_about_what_is_pointube)),
                AboutItem(R.drawable.ic_help_outline_black_24dp, mContext.getString(R.string.fragment_about_what_is_pointube)),
                AboutItem(R.drawable.ic_call_black_24dp, mContext.getString(R.string.fragment_about_what_is_pointube)),
                AboutItem(R.drawable.ic_, mContext.getString(R.string.fragment_about_what_is_pointube)),
                AboutItem(R.drawable.ic_library_books_black_24dp, mContext.getString(R.string.fragment_about_what_is_pointube)),

        )
    }

    override fun clickAboutDetail(position: Int) {
        view?.showAboutDetail(position)
    }

    override fun loadAboutList() {
        /*  TODO: create list and send to view*/
    }

    override fun onCreate() {

    }

    override fun onDestroy() {
        view = null
    }

}
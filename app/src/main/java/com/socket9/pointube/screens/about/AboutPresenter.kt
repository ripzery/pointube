package com.socket9.pointube.screens.about

import android.content.Context
import com.socket9.pointube.R
import com.socket9.pointube.utils.ContextUtil

/**
 * Created by ripzery on 8/8/16.
 */
class AboutPresenter(var view: AboutContract.View?) : AboutContract.Presenter {
    private val mContext: Context by lazy { ContextUtil.context!! }

    /* Define about menu */
    private val mAboutItemList: MutableList<AboutItem> by lazy {
        mutableListOf(
                AboutItem(R.drawable.ic_library_books_black_24dp, mContext.getString(R.string.fragment_about_what_is_pointube)),
                AboutItem(R.drawable.ic_library_books_black_24dp, mContext.getString(R.string.fragment_about_who_should_use)),
                AboutItem(R.drawable.ic_library_books_black_24dp, mContext.getString(R.string.fragment_about_how_to_use)),
                AboutItem(R.drawable.ic_help_outline_black_24dp, mContext.getString(R.string.fragment_about_faq)),
                AboutItem(R.drawable.ic_call_black_24dp, mContext.getString(R.string.fragment_about_contact_us)),
                AboutItem(R.drawable.ic_lock_black_24dp, mContext.getString(R.string.fragment_about_privacy)),
                AboutItem(R.drawable.ic_library_books_black_24dp, mContext.getString(R.string.fragment_about_terms_of_use))
        )
    }

    companion object {
        val MENU_WHAT_IS_POINTUBE = 1
        val MENU_WHO_SHOULD_USE = 2
        val MENU_HOW_TO_USE = 3
        val MENU_FAQ = 4
        val MENU_CONTACT_US = 5
        val MENU_PRIVACY = 6
        val MENU_TERMS = 7
    }

    override fun clickAboutDetail(position: Int) {
        view?.showAboutDetail(position + 1)
    }

    override fun loadAboutList() {
        view?.showAboutList(mAboutItemList)
    }

    override fun onCreate() {

    }

    override fun onDestroy() {
        view = null
    }

}
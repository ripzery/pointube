package com.socket9.pointube.screens.home

import android.annotation.TargetApi
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.socket9.pointube.R
import com.socket9.pointube.repository.brands.BrandRepo
import com.socket9.pointube.utils.SharedPrefUtil
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.viewgroup_member_brand.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find

class HomePartnerViewGroup : FrameLayout, AnkoLogger {

    /** Variable zone **/
    lateinit private var viewContainer: View
    lateinit private var civLogo: CircleImageView
    lateinit private var tvBadgeCount: TextView
    lateinit private var mTvPoint: TextView
    private val mLoginResult: LoginModel.Response.LoginResult by lazy { SharedPrefUtil.loadLoginResult()!! }

    /** Override method zone **/
    constructor(context: Context) : super(context) {
        initInflate()
        initInstances()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initInflate()
        initInstances()
        initWithAttrs(attrs, 0, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initInflate()
        initInstances()
        initWithAttrs(attrs, defStyleAttr, 0)
    }

    @TargetApi(21)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        initInflate()
        initInstances()
        initWithAttrs(attrs, defStyleAttr, defStyleRes)
    }


    private fun initInflate() {
        viewContainer = inflate(context, R.layout.viewgroup_home_partner, this)
    }

    private fun initInstances() {
        // findViewById here
        civLogo = viewContainer.find(R.id.civLogo)
        tvBadgeCount = viewContainer.find(R.id.tvBadgeCount)
        mTvPoint = viewContainer.find(R.id.tvPoint)
    }

    private fun initWithAttrs(attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) {
        /*
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.StyleableName,
                defStyleAttr, defStyleRes);

        try {

        } finally {
            a.recycle();
        }
        */
    }

    /** Method zone **/

    fun setModel(brand: BrandRepo) {
        setBrandLogo(brand.LogoPath)
        mTvPoint.visibility = if (!brand.Points.isEmpty()) View.VISIBLE else View.INVISIBLE
        setPoint(brand.Points)
    }

    fun setBadgeCount(count: Int) {
        tvBadgeCount.text = "$count"
    }

    fun setPoint(point: String) {
        if (!point.isEmpty()) {
            val formattedPoint = String.format("%,d", point.toInt())
            mTvPoint.text = "$formattedPoint Points"
        }
    }

    fun setBrandLogo(logo: String) {
        Glide.with(context).load(logo.replace("192.168.100.252:8099", "service.pointube.com")).into(civLogo)
    }
}

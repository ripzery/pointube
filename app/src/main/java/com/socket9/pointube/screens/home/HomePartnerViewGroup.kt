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
import de.hdodenhof.circleimageview.CircleImageView
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find
import org.jetbrains.anko.info

class HomePartnerViewGroup : FrameLayout,AnkoLogger {

    /** Variable zone **/
    lateinit private var viewContainer: View
    lateinit private var civLogo: CircleImageView
    lateinit private var tvBadgeCount: TextView

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
        civLogo = find(R.id.civLogo)
        tvBadgeCount = find(R.id.tvBadgeCount)

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
    }

    fun setBadgeCount(count: Int){
        tvBadgeCount.text = "$count"
    }

    fun setBrandLogo(logo: String){
        Glide.with(context).load(logo.replace("192.168.100.252:8099", "service.pointube.com")).into(civLogo)
    }
}

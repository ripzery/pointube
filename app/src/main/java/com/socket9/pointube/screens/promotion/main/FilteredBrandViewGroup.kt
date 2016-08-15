package com.socket9.pointube.screens.promotion.main

import android.annotation.TargetApi
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.socket9.pointube.R
import com.socket9.pointube.repository.brands.BrandRepo
import com.socket9.pointube.repository.brands.BrandUnitRepo
import de.hdodenhof.circleimageview.CircleImageView
import org.jetbrains.anko.find

class FilteredBrandViewGroup : FrameLayout {

    /** Variable zone **/
    lateinit private var viewContainer: View
    lateinit private var civLogo: CircleImageView
    lateinit private var tvBrandName: TextView
    lateinit private var ivExpand: ImageView

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
        viewContainer = inflate(context, R.layout.viewgroup_filtered_brand, this)
    }

    private fun initInstances() {
        // findViewById here
        civLogo = find(R.id.civLogo)
        tvBrandName = find(R.id.tvBrandName)
        ivExpand = find(R.id.ivExpand)
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

    fun setModel(model: BrandRepo) {
        with(model) {
            Glide.with(context).load(this.LogoPath).into(civLogo)
            tvBrandName.text = Name
            ivExpand.visibility = if (Units.size > 0) View.VISIBLE else View.GONE
        }
    }

    fun setModel(model: BrandUnitRepo) {
        with(model) {
            Glide.with(context).load(this.LogoPath).into(civLogo)
            tvBrandName.text = Name
            ivExpand.visibility = View.GONE
        }
    }
}

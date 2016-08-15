package com.socket9.pointube.screens.promotion.main

import android.annotation.TargetApi
import android.content.Context
import android.support.v4.content.ContextCompat
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

    private var mIsExpand: Boolean = false
    private var mExpandListener: (Boolean) -> Unit = {}
    private var mBrandClickListener: (Int, String) -> Unit = {a,b -> }
    private var mBrandModel: BrandRepo? = null
    private var mBrandUnitModel: BrandUnitRepo? = null

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

        ivExpand.setOnClickListener {
            mIsExpand = !mIsExpand
            mExpandListener(mIsExpand)
            ivExpand.setImageDrawable(ContextCompat.getDrawable(context, if (mIsExpand) R.drawable.ic_keyboard_arrow_up_white_24dp else R.drawable.ic_keyboard_arrow_down_white_24dp))
        }

        viewContainer.setOnClickListener {
            if (mBrandModel == null) {
                mBrandClickListener(mBrandUnitModel!!.ProviderId, mBrandModel!!.Name)
            } else {
                mBrandClickListener(mBrandModel!!.Id, mBrandModel!!.Name)
            }
        }
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
        mBrandModel = model
        with(model) {
            Glide.with(context).load(this.LogoPath).into(civLogo)
            tvBrandName.text = Name
            ivExpand.visibility = if (Units.size > 0) View.VISIBLE else View.GONE
        }
    }

    fun setModel(model: BrandUnitRepo) {
        mBrandUnitModel = model
        with(model) {
            Glide.with(context).load(this.LogoPath).into(civLogo)
            tvBrandName.text = Name
            ivExpand.visibility = View.GONE
        }

        viewContainer.setBackgroundResource(R.color.colorWhite200)
    }

    fun setExpandClickListener(listener: (Boolean) -> Unit) {
        mExpandListener = listener
    }

    fun setItemClickListener(listener: (Int, String) -> Unit) {
        mBrandClickListener = listener
    }
}

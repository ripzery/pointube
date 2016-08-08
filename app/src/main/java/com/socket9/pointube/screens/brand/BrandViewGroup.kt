package com.socket9.pointube.screens.brand

import android.annotation.TargetApi
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.socket9.pointube.R
import com.socket9.pointube.repository.brands.BrandRepo
import com.socket9.pointube.repository.brands.GetMemberBrandResult
import kotlinx.android.synthetic.main.viewgroup_member_brand.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.subjects.PublishSubject
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * Created by ripzery on 7/21/16.
 */
class BrandViewGroup : FrameLayout, AnkoLogger {

    /** Variable zone **/
    lateinit private var viewContainer: View
    private var mObserveChecked: (Boolean) -> Unit = { info { "Waiting for subscription checked: $it" } }
    private var mIsChecked: Boolean by Delegates.observable(false) { meta: KProperty<*>, oldValue: Boolean, newValue: Boolean ->
        with(newValue) {
            cbSelect.isChecked = this
            viewContainer.isSelected = this
            mObserveChecked(this)
        }
    }

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
        viewContainer = inflate(context, R.layout.viewgroup_member_brand, this)
    }

    private fun initInstances() {
        viewContainer.setOnClickListener {
            mIsChecked = !mIsChecked
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
    fun setModel(model: GetMemberBrandResult, isShowPoint: Boolean = false) {
        Glide.with(context).load(model.LogoPath).into(civBrandLogo)
        tvBrandName.text = model.Name

        viewContainer.cbSelect.visibility = if (isShowPoint) View.GONE else View.VISIBLE
        viewContainer.tvPoint.visibility = if (isShowPoint) View.VISIBLE else View.GONE

        if (!isShowPoint) {
            mIsChecked = model.isChecked
        } else {
            val formattedPoint = String.format("%,d", model.Points.toInt())
            viewContainer.tvPoint.text = formattedPoint
        }
    }

    fun setModel(model: BrandRepo) {
        with(model) {
            Glide.with(context).load(LogoPath).into(viewContainer.civBrandLogo)
            viewContainer.tvBrandName.text = Name
            mIsChecked = isChecked
        }
    }

    fun observeChecked(code: (Boolean) -> Unit) {
        mObserveChecked = code
    }
}
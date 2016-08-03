package com.socket9.pointube.screens.brand

import android.annotation.TargetApi
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.socket9.pointube.R
import com.socket9.pointube.repository.brands.BrandRepo
import kotlinx.android.synthetic.main.viewgroup_member_brand.view.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.subjects.PublishSubject

/**
 * Created by ripzery on 7/21/16.
 */
class BrandViewGroup : FrameLayout {

    /** Variable zone **/
    lateinit private var viewContainer: View
    private var mCheckedObservable: PublishSubject<Boolean> = PublishSubject.create()

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
            cbSelect.isChecked = !cbSelect.isChecked
            it.isSelected = cbSelect.isChecked
            mCheckedObservable.onNext(it.isSelected)
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
    fun setModel(model: BrandModel.Response.GetMemberBrandResult, isShowPoint: Boolean = false) {
        Glide.with(context).load(model.LogoPath).into(civBrandLogo)
        tvBrandName.text = model.Name

        cbSelect.visibility = if (isShowPoint) View.GONE else View.VISIBLE
        tvPoint.visibility = if (isShowPoint) View.VISIBLE else View.GONE

        if (!isShowPoint) {
            cbSelect.isChecked = model.isChecked
        } else {
            tvPoint.text = model.Points.toString()
        }
    }

    fun setModel(model: BrandRepo) {
        Glide.with(context).load(model.LogoPath).into(civBrandLogo)
        tvBrandName.text = model.Name
        cbSelect.isChecked = model.isChecked
    }

    fun setChecked(checked: Boolean) {
        cbSelect.isChecked = checked
    }

    fun getChecked(): Boolean {
        return cbSelect.isChecked
    }

    fun getCheckedObservable(): Observable<Boolean> {
        return mCheckedObservable
                .subscribeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

}
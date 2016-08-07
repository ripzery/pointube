package com.socket9.pointube.screens.promotion.detail

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.socket9.pointube.R
import com.socket9.pointube.extensions.replaceFragmentWithAnimation
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class PromotionDetailActivity : AppCompatActivity() {

    /* Variable */
    private var mPromotionId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promotion_detail)
        initInstance()
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    private fun initInstance() {
        mPromotionId = intent.getIntExtra("id", 0)
        replaceFragmentWithAnimation(fragment = PromotionDetailFragment.newInstance(mPromotionId))
    }
}

package com.socket9.pointube

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.support.v7.app.AppCompatDelegate
import com.socket9.pointube.utils.ContextUtil
import com.socket9.pointube.utils.SharedPrefUtil
import io.realm.Realm
import io.realm.RealmConfiguration
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import rx_activity_result.RxActivityResult
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import java.util.*

/**
 * Created by Euro (ripzery@gmail.com) on 7/16/2016 AD.
 */
class App : Application(), AnkoLogger {
    private val locale: Locale? = null

    override fun onCreate() {
        super.onCreate()
        val realmConfig = RealmConfiguration.Builder(applicationContext)
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(realmConfig)
        info { "App is loaded" }
        ContextUtil.context = applicationContext
        SharedPrefUtil.sharePref = getSharedPreferences(SharedPrefUtil.NAME, Context.MODE_PRIVATE)
        RxActivityResult.register(this)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
//        CalligraphyConfig.initDefault(CalligraphyConfig.Builder().setDefaultFontPath("fonts/QuarkLight.ttf").setFontAttrId(R.attr.fontPath).build())

        val locale = Locale(if (SharedPrefUtil.isEnglish()) "en" else "th")
        Locale.setDefault(locale)
        baseContext.resources.configuration.locale = locale
        baseContext.resources.updateConfiguration(baseContext.resources.configuration, baseContext.resources.displayMetrics);
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (locale != null) {
            newConfig.locale = locale
            Locale.setDefault(locale)
            baseContext.resources.updateConfiguration(newConfig, baseContext.resources.displayMetrics)
        }
    }
}
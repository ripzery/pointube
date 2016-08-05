package com.socket9.pointube

import android.app.Application
import android.content.Context
import android.support.v7.app.AppCompatDelegate
import com.socket9.pointube.utils.ContextUtil
import com.socket9.pointube.utils.SharedPrefUtil
import io.realm.Realm
import io.realm.RealmConfiguration
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import rx_activity_result.RxActivityResult

/**
 * Created by Euro (ripzery@gmail.com) on 7/16/2016 AD.
 */
class App : Application(), AnkoLogger {
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
    }
}
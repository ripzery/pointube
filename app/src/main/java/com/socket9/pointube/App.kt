package com.socket9.pointube

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.socket9.pointube.utils.Contextor
import com.socket9.pointube.utils.SharedPref
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
        Contextor.context = applicationContext
        SharedPref.sharePref = getSharedPreferences(SharedPref.NAME, Context.MODE_PRIVATE)
        RxActivityResult.register(this)
    }
}
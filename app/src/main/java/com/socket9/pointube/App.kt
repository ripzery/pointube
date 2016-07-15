package com.socket9.pointube

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by Euro (ripzery@gmail.com) on 7/16/2016 AD.
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val realmConfig = RealmConfiguration.Builder(applicationContext.externalCacheDir).build()
        Realm.setDefaultConfiguration(realmConfig)
    }
}
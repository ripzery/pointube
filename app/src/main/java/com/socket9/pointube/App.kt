package com.socket9.pointube

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.socket9.pointube.utils.SharedPref
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by Euro (ripzery@gmail.com) on 7/16/2016 AD.
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val realmConfig = RealmConfiguration.Builder(applicationContext).build()
        Realm.setDefaultConfiguration(realmConfig)
        SharedPref.sharePref = getSharedPreferences(SharedPref.NAME, Context.MODE_PRIVATE)
    }
}
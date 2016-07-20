package com.socket9.pointube.utils

import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by ripzery on 7/20/16.
 */
object RealmUtil {
    fun getInstance() : Realm {
        return Realm.getDefaultInstance()
    }
}
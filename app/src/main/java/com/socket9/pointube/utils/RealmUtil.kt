package com.socket9.pointube.utils

import com.socket9.pointube.repository.brands.GetMemberBrand
import com.socket9.pointube.repository.brands.GetMemberSelectBrand
import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmResults

/**
 * Created by ripzery on 7/20/16.
 */
object RealmUtil {
    fun getInstance(): Realm {
        return Realm.getDefaultInstance()
    }

    fun write(code: (Realm) -> Unit) {
        getInstance().beginTransaction()
        code(getInstance())
        getInstance().commitTransaction()
        getInstance().close()
    }

    fun deleteMemberBrand() {
        getInstance().beginTransaction()
        getInstance().delete(GetMemberBrand::class.java)
        getInstance().delete(GetMemberSelectBrand::class.java)
        getInstance().commitTransaction()
        getInstance().close()
    }

    fun <T : RealmObject> readAll(table: Class<T>): RealmResults<T> {
        return getInstance().where(table).findAll()
    }
}
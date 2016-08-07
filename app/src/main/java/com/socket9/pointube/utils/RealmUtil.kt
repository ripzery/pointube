package com.socket9.pointube.utils

import com.socket9.pointube.repository.brands.GetMemberBrand
import com.socket9.pointube.repository.brands.GetMemberBrandResult
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
        with(getInstance()) {
            beginTransaction()
            code(this)
            commitTransaction()
            close()
        }
    }

    fun deleteMemberBrand() {
        with(getInstance()) {
            beginTransaction()
            delete(GetMemberBrand::class.java)
            delete(GetMemberSelectBrand::class.java)
            delete(GetMemberBrandResult::class.java)
            commitTransaction()
            close()
        }
    }

    fun <T : RealmObject> readAll(table: Class<T>): RealmResults<T> {
        return getInstance().where(table).findAll()
    }
}
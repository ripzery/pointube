package com.socket9.pointube.repository.brands

import com.socket9.pointube.screens.brand.BrandModel
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.Ignore

/**
 * Created by ripzery on 8/5/16.
 */
open class GetMemberBrand(
        open var IsSuccess: Boolean,
        open var Message: String?,
        open var Results: RealmList<GetMemberBrandResult> = RealmList()
) : RealmObject() {}

open class GetMemberSelectBrand(
        open var IsSuccess: Boolean,
        open var Message: String?,
        open var Results: RealmList<GetMemberBrandResult> = RealmList()
) : RealmObject() {}

open class GetMemberBrandResult(
        open var Id: Int,
        open var Name: String,
        open var LogoPath: String,
        open var Points: Int,
        open var Code: String,
        open var Message: String?,
        open var IsBrandMember: Boolean,
        @Ignore open var isChecked: Boolean = false
) : RealmObject() {}

fun GetMemberBrand.getIdBySelected(): MutableList<Int> = this.Results.filter { it.isChecked }.map { it.Id }.toMutableList()

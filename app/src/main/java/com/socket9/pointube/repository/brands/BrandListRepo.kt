package com.socket9.pointube.repository.brands

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.Ignore
import io.realm.annotations.PrimaryKey

/**
 * Created by Euro (ripzery@gmail.com) on 7/16/2016 AD.
 */
open class BrandRepo(
        @PrimaryKey open var Id: Int = 0,
        open var Name: String = "",
        open var LogoPath: String = "",
        open var CoverPath: String = "",
        open var TotalPrograms: Int = 1,
        open var Units : RealmList<BrandUnitRepo> = RealmList(),
        @Ignore open var Points: String = "",
        @Ignore open var isChecked: Boolean = false

) : RealmObject(), ParentListItem /* For support expandable list */ {

    override fun getChildItemList(): MutableList<BrandUnitRepo>? {
        return Units
    }

    override fun isInitiallyExpanded(): Boolean {
        return false
    }

}

open class BrandUnitRepo(
        @PrimaryKey open var Id: Int = 0,
        open var ProviderId: Int = 0,
        open var Name: String ="",
        open var LogoPath: String=""
) : RealmObject() {

}
package com.socket9.pointube.repository.brands

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by Euro (ripzery@gmail.com) on 7/16/2016 AD.
 */
open class BrandRepo(
        @PrimaryKey open var Id: Int = 0,
        open var Name: String = "",
        open var LogoPath: String = "",
        open var CoverPath: String = "",
        open var Units : RealmList<BrandUnitRepo> = RealmList()

) : RealmObject() {

}

open class BrandUnitRepo(
        @PrimaryKey open var Id: Int = 0,
        open var ProviderId: Int = 0,
        open var Name: String ="",
        open var LogoPath: String=""
) : RealmObject() {

}
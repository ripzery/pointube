package com.socket9.pointube.repository.brands

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by Euro (ripzery@gmail.com) on 7/16/2016 AD.
 */
open class BrandUnitRepo(
        @PrimaryKey open var Id: Int = 0,
        open var ProviderId: Int = 0,
        open var Name: String ="",
        open var LogoPath: String=""
) : RealmObject() {

}
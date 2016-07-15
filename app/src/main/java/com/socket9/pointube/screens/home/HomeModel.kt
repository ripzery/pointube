package com.socket9.pointube.screens.home

import com.socket9.pointube.repository.brands.BrandRepo
import com.socket9.pointube.repository.brands.BrandUnitRepo

/**
 * Created by Euro (ripzery@gmail.com) on 7/11/2016 AD.
 */
object HomeModel {
    data class AllBrands(val IsSuccess: Boolean, val Results: MutableList<BrandRepo>,val IsDisk: Boolean = false)

    data class Brand(val Name: String, val IsActive: Boolean, val LogoPath: String, val CoverPath: String, val IsAirline: Boolean, val Units:
    MutableList<BrandUnitRepo>, val Points: Int, val Code: String, val UnitOfPoint: String, val ListCount: Int, val Message: String?, val Id: Int)

    data class BrandUnit(val Name: String, val IsActive: Boolean, val IsDeleted: Boolean, val LogoPath: String, val Code: String, val ProviderId:
    Int, val Id: Int)
}
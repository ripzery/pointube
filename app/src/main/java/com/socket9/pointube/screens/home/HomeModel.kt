package com.socket9.pointube.screens.home

/**
 * Created by Euro (ripzery@gmail.com) on 7/11/2016 AD.
 */
object HomeModel {
    data class AllBrands(val Success: Boolean, val Results: MutableList<Brand>)

    data class Brand(val Name: String, val IsActive: Boolean, val LogoPath: String, val CoverPath: String, val IsAirline: Boolean, val Units:
    MutableList<BrandUnit>, val Points: Int, val Code: String, val UnitOfPoint: String, val ListCount: Int, val Message: String?, val Id: Int)

    data class BrandUnit(val Name: String, val IsActive: Boolean, val IsDeleted: Boolean, val LogoPath: String, val Code: String, val ProviderId:
    Int, val Id: Int)
}
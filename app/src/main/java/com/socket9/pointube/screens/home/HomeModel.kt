package com.socket9.pointube.screens.home

/**
 * Created by Euro (ripzery@gmail.com) on 7/11/2016 AD.
 */
object HomeModel {
    data class AllBrands(val success: Boolean, val result: MutableList<Brand>)

    data class Brand(val name: String, val isActive: Boolean, val logoPath: String, val coverPath: String, val isAirline: Boolean, val brandUnits:
    MutableList<BrandUnit>, val points: Int, val code: String, val unitOfPoint: String, val listCount: Int, val message: String?, val id: Int)

    data class BrandUnit(val name: String, val isActive: Boolean, val isDeleted: Boolean, val logoPath: String, val code: String, val providerId: Int, val id: Int)
}
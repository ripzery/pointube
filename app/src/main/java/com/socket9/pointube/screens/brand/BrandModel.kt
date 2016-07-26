package com.socket9.pointube.screens.brand

/**
 * Created by Euro (ripzery@gmail.com) on 7/15/2016 AD.
 */
object BrandModel {

    object Request {
        data class GetMemberBrand(val MemberId: String, val Token: String)

        data class SaveBrand(val MemberId: String, val Token: String, val Brands: MutableList<Brand>)

        data class Brand(val ProviderId: Int, val IsBrandMember: Boolean)

        data class GetMemberSelectBrand(val MemberId: String, val Token: String, val IsBrandMember: Boolean)
    }

    object Response {
        data class GetMemberBrand(val IsSuccess: Boolean, val Message: String?, val Results: MutableList<GetMemberBrandResult>)

        data class GetMemberBrandResult(val Id: Int, val Name: String, val LogoPath: String, val Point: Int, val Code: String, val Message: String?, val IsBrandMember: Boolean, var isChecked: Boolean = false)

        data class SaveBrand(val IsSuccess: Boolean, val Message: String?)

        data class GetMemberSelectBrand(val IsSuccess: Boolean, val Message: String?, val Brands: GetMemberBrandResult)
    }

}

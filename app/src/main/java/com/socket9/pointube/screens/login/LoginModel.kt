package com.socket9.pointube.screens.home

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by Euro (ripzery@gmail.com) on 7/11/2016 AD.
 */

object LoginModel {

    object Request {
        data class Login(val Email: String, val Password: String)
    }

    object Response {
        data class Login(@SerializedName(value = "IsSuccess", alternate = arrayOf("isSuccess")) val isSuccess: Boolean,
                         @SerializedName("Message", alternate = kotlin.arrayOf("message")) val message: String?,
                         @SerializedName("result", alternate = kotlin.arrayOf("Result")) val result: LoginResult?)

        data class LoginResult(
                @SerializedName("isSuccess") val isSuccess: Boolean = false,
                @SerializedName("message") val message: String? = "",
                @SerializedName("id") val id: Int = 0,
                @SerializedName("code") val code: String?,
                @SerializedName("firstName") val firstName: String?,
                @SerializedName("lastName") val lastName: String?,
                @SerializedName("firstNameEN") val firstNameEN: String?,
                @SerializedName("lastNameEN") val lastNameEN: String?,
                @SerializedName("citizenID") val citizenID: String?,
                @SerializedName("passport") val passport: String?,
                @SerializedName("mobile") val mobile: String?,
                @SerializedName("email") val email: String?,
                @SerializedName("gender") val gender: String?,
                @SerializedName("address") val address: String?,
                @SerializedName("birthday") val birthday: Date?,
                @SerializedName("token") val token: String?
        ) {
            fun toJson(): String {
                return Gson().toJson(this)
            }
        }
    }

}
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

        data class ChangePassword(val MemberId: String, val Token: String, val OldPassword: String, val NewPassword: String, val ConfirmPassword: String)

        data class CheckUserWithPin(val Email: String, val Pin: String)

        data class ResetPassword(val MemberId: Int, val NewPassword: String, val ConfirmPassword: String)
    }

    object Response {
        data class Login(@SerializedName(value = "IsSuccess", alternate = arrayOf("isSuccess")) val isSuccess: Boolean,
                         @SerializedName("Message", alternate = kotlin.arrayOf("message")) val message: String?,
                         @SerializedName("result", alternate = kotlin.arrayOf("Result")) val result: LoginResult?)

        data class LoginResult(
                @SerializedName("isSuccess", alternate = kotlin.arrayOf("IsSuccess")) val isSuccess: Boolean = false,
                @SerializedName("message", alternate = kotlin.arrayOf("Message")) val message: String? = "",
                @SerializedName("id", alternate = kotlin.arrayOf("Id")) val id: Int = 0,
                @SerializedName("code", alternate = kotlin.arrayOf("Code")) val code: String?,
                @SerializedName("firstName", alternate = kotlin.arrayOf("FirstName")) val firstName: String?,
                @SerializedName("lastName", alternate = kotlin.arrayOf("LastName")) val lastName: String?,
                @SerializedName("firstNameEN", alternate = kotlin.arrayOf("FirstNameEN")) val firstNameEN: String?,
                @SerializedName("lastNameEN", alternate = kotlin.arrayOf("LastNameEN")) val lastNameEN: String?,
                @SerializedName("citizenID", alternate = kotlin.arrayOf("CitizenID")) val citizenID: String?,
                @SerializedName("passport", alternate = kotlin.arrayOf("Passport")) val passport: String?,
                @SerializedName("mobile", alternate = kotlin.arrayOf("Mobile")) val mobile: String?,
                @SerializedName("email", alternate = kotlin.arrayOf("Email")) val email: String?,
                @SerializedName("gender", alternate = kotlin.arrayOf("Gender")) val gender: String?,
                @SerializedName("address", alternate = kotlin.arrayOf("Address")) val address: String?,
                @SerializedName("birthday", alternate = kotlin.arrayOf("Birthday")) val birthday: Date?,
                @SerializedName("token", alternate = kotlin.arrayOf("Token")) val token: String?
        ) {
            fun toJson(): String {
                return Gson().toJson(this)
            }
        }

        data class ChangePassword(val IsSuccess: Boolean, val Message: String?)

        data class ForgotPassword(val IsSuccess: Boolean, val Message: String?)

        data class CheckUserWithPin(val IsSuccess: Boolean, val Message: String?, val result: LoginResult?)

        data class ResetPassword(val IsSuccess: Boolean, val Message: String?)
    }

}
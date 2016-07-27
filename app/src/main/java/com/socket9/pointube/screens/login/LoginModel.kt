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

    object Response{
        data class Login(@SerializedName("Success") val success: Boolean,@SerializedName("Result") val result: LoginResult)

        data class LoginResult(
                @SerializedName("Message") val message: String?,
                @SerializedName("Id") val id: Int = 0,
                @SerializedName("FirstName") val firstName: String?,
                @SerializedName("LastName") val lastName: String?,
                @SerializedName("FirstNameEN") val firstNameEN: String?,
                @SerializedName("LastNameEN") val lastNameEN: String?,
                @SerializedName("CitizenID") val citizenID: String?,
                @SerializedName("Passport") val passport: String?,
                @SerializedName("Mobile") val mobile: String?,
                @SerializedName("Email") val email: String?,
                @SerializedName("Gender") val gender: String?,
                @SerializedName("Address") val address: String?,
                @SerializedName("Birthday") val birthday: Date?,
                @SerializedName("Token") val token: String?
        ){
            fun toJson(): String{
                return Gson().toJson(this)
            }
        }
    }

}
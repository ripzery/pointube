package com.socket9.pointube.screens.home

import com.google.gson.Gson
import java.util.*

/**
 * Created by Euro (ripzery@gmail.com) on 7/11/2016 AD.
 */

object LoginModel {
    data class Login(val success: Boolean, val result: LoginResult)

    data class LoginResult(
            val message: String?,
            val id: Int = 0,
            val firstName: String?,
            val lastName: String?,
            val firstNameEN: String?,
            val lastNameEN: String?,
            val citizenID: String?,
            val passport: String?,
            val mobile: String?,
            val email: String?,
            val gender: String?,
            val address: String?,
            val birthday: Date?,
            val token: String?
    ){
        fun toJson(): String{
            return Gson().toJson(this)
        }
    }
}
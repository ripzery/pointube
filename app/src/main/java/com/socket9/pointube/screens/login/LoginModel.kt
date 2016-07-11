package com.socket9.pointube.screens.home

import java.util.*

/**
 * Created by Euro (ripzery@gmail.com) on 7/11/2016 AD.
 */

object LoginModel {
    data class Login(val success: Boolean, val result: LoginResult)

    data class LoginResult(val isSuccess: Boolean,
                           val message: String?,
                           val id: Int?,
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
                           val token: String?)
}
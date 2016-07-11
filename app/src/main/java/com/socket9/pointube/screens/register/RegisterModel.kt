package com.socket9.pointube.screens.register

/**
 * Created by Euro (ripzery@gmail.com) on 7/11/2016 AD.
 */

object RegisterModel {
    data class Register(val IsSuccess: Boolean, val Message: String, val Member: RegisterMember?, val Id: Int = 0)

    data class RegisterMember(val FirstName: String,
                              val LastName: String,
                              val FirstNameEN: String,
                              val LastNameEN: String,
                              val CitizenID: String,
                              val Passport: String)
}
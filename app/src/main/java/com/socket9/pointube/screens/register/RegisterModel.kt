package com.socket9.pointube.screens.register

/**
 * Created by Euro (ripzery@gmail.com) on 7/11/2016 AD.
 */

object RegisterModel {

    object Request {
        data class Register(val FirstName: String,
                                   val LastName: String,
                                   val FirstNameEN: String,
                                   val LastNameEN: String,
                                   val CitizenID: String,
                                   val Passport: String?,
                                   val Mobile: String?,
                                   val Email: String,
                                   val Password: String,
                                   val Gender: String,
                                   val Address: String,
                                   val Birthday: String
        )

        data class SaveMobileNo(val Id: Int, val Mobile: String)

        data class VerifyPhoneNumber(val Id: Int, val OTP: String)

        data class GenOTP(val Mobile: String)
    }

    object Response {
        data class Register(val IsSuccess: Boolean, val Message: String, val Member: RegisterMember?, val Id: Int = 0)

        data class RegisterMember(val FirstName: String,
                                  val LastName: String,
                                  val FirstNameEN: String,
                                  val LastNameEN: String,
                                  val CitizenID: String,
                                  val Passport: String)

        data class SaveMobileNo(val IsSuccess: Boolean, val Message: String?, val Result : SaveMobileNoResult)

        data class SaveMobileNoResult(val Id: Int, val Mobile: String, val OTP: String)

        data class GenOTP(val IsSuccess: Boolean, val Message: String?, val Result : GenOTPResult)

        data class GenOTPResult(val Id: Int,val Mobile: String, val OTP: String)

        data class VerifyPhoneNumber(val IsSuccess: Boolean, val Message: String?, val Result : VerifyPhoneNumberResult)

        data class VerifyPhoneNumberResult(val Id: Int,val Mobile: String, val OTP: String)

    }

}
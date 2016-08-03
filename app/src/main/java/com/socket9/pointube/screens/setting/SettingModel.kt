package com.socket9.pointube.screens.setting

import java.util.*

/**
 * Created by ripzery on 8/2/16.
 */
object SettingModel {
    object Request {
        data class UpdateProfile(val Id : Int,
                                 val Email: String,
                                 val FirstName: String,
                                 val LastName: String,
                                 val FirstNameEN: String,
                                 val LastNameEN: String,
                                 val CitizenID: String?,
                                 val Passport: String?,
                                 val Birthday: String,
                                 val Code: String,
                                 var Gender: String)
    }

    object Response {
        data class UpdateProfile(val IsSuccess: Boolean, val Message: String, val Result: ProfileMember?)

        data class ProfileMember(val Email: String,
                                  val FirstName: String,
                                  val LastName: String,
                                  val FirstNameEN: String,
                                  val LastNameEN: String,
                                  val CitizenID: String,
                                  val Passport: String,
                                  val Dob: Date)
    }
}
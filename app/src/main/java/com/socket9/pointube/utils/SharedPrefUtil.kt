package com.socket9.pointube.utils

import android.content.SharedPreferences
import com.google.gson.Gson
import com.socket9.pointube.screens.home.LoginModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.warn

/**
 * Created by ripzery on 7/20/16.
 */
object SharedPrefUtil : AnkoLogger {
    val NAME = "pointube.sp"
    var sharePref: SharedPreferences? = null
    val KEY_LOGIN_RESULT = "login_result"

    fun saveLoginResult(loginResult: LoginModel.Response.LoginResult) {
        if (loginResult.id > 0) {
            /* login result is valid */
            sharePref?.edit()?.putString(KEY_LOGIN_RESULT, loginResult.toJson())?.apply()
            info { "Save login result to share preference successfully" }
        } else {
            /* login result is invalid */
            warn { "Invalid loginResult" }
        }
    }

    fun loadLoginResult(): LoginModel.Response.LoginResult? {
        return Gson().fromJson(sharePref?.getString(KEY_LOGIN_RESULT, null), LoginModel.Response.LoginResult::class.java)
    }

    fun clearLogin(){
        sharePref?.edit()?.remove(KEY_LOGIN_RESULT)?.apply()
    }
}
package com.socket9.pointube.utils

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

/**
 * Created by ripzery on 7/20/16.
 */
object LoginState : AnkoLogger {
    fun isLogin(): Boolean{
        return SharedPref.loadLoginResult() != null
    }
}
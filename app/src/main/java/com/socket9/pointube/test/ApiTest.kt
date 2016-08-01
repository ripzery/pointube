package com.socket9.pointube.test

import com.socket9.pointube.manager.DataManager
import com.socket9.pointube.screens.brand.BrandModel
import com.socket9.pointube.screens.home.LoginModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.warn

/**
 * Created by Euro (ripzery@gmail.com) on 7/11/2016 AD.
 */
object ApiTest : AnkoLogger {

    /* Doesn't work */
    fun register() {
//        DataManager.register("Phuchut", "Sirimong", "1419900326966", "TH12345678", "0875567582", "ripzery222@gmail.com", "12345", "1",
//                "Socket9", "1992-06-27T19:30:22", "Phuchit", "Sirimongkolsathien")
//                .subscribe({
//                    info { it }
//                }, {
//                    info { it }
//                })
    }

    fun login() {
//        DataManager.login(LoginModel.Request.Login("euro03@google.com", "1234"))
//                .subscribe({
//                    info { it }
//                }, {
//                    info { it }
//                })
    }

    fun getAllBrands() {
        DataManager.getAllProvider()
                .subscribe({
                    info { it }
                }, {
                    info { it }
                })
    }

    /* Login then get all member brand */
    fun getAllMemberBrand() {
        DataManager.login(LoginModel.Request.Login("euro3@google.com", "1234"))
                .doOnNext { info { it } }
                .flatMap { DataManager.getAllBrandMember(BrandModel.Request.GetMemberBrand(it.result.id.toString(), it.result.token!!)) }
                .subscribe({
                    info { it }
                }, {
                    warn { it }
                })
    }
}
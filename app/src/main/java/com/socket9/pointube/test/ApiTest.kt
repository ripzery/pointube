package com.socket9.pointube.test

import com.socket9.pointube.manager.HttpManager
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

/**
 * Created by Euro (ripzery@gmail.com) on 7/11/2016 AD.
 */
object ApiTest: AnkoLogger {

    /* Doesn't work */
    fun register() {
        HttpManager.register("Phuchut", "Sirimong", "1419900326966", "TH12345678", "0875567582", "ripzery222@gmail.com", "12345", "1",
                "Socket9", "1992-06-27T19:30:22", "Phuchit", "Sirimongkolsathien")
                .subscribe ({
                    info { it }
                },{
                    info {it}
                })
    }

    fun login(){
        HttpManager.login("mon01@mon.com", "1234")
            .subscribe ({
                info { it }
            },{
                info {it}
            })
    }

    fun getAllBrands(){
        HttpManager.getAllBrands()
            .subscribe ({
                info { it }
            },{
                info {it}
            })
    }
}
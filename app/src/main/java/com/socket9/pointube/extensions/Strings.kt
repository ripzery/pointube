package com.socket9.pointube.extensions

/**
 * Created by ripzery on 7/28/16.
 */
fun String.plainText() : String{
    return replace("<.*?>".toRegex(), "").replace("\n","").replace("&nbsp;","")
}

fun String.thousandSeparator() : String{
    return String.format("%,d", this.toInt())
}
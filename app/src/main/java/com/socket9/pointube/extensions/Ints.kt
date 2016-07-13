package com.socket9.pointube.extensions

import android.content.res.Resources

/**
 * Created by Euro (ripzery@gmail.com) on 7/13/2016 AD.
 */


fun Int.toDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()

fun Int.toPx(): Float = (this * Resources.getSystem().displayMetrics.density)
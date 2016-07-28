package com.socket9.pointube.extensions

import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by ripzery on 7/28/16.
 */
fun Date.farFrom(day: Date): Long{
    return TimeUnit.MILLISECONDS.toDays(day.time - time)
}


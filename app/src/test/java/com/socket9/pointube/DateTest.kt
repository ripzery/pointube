package com.socket9.pointube

import com.socket9.pointube.extensions.farFrom
import org.junit.Test
import java.util.*
import kotlin.test.assertTrue

/**
 * Created by Euro (ripzery@gmail.com) on 7/11/2016 AD.
 */

class DateTest {
    @Test fun testDateFarFromFutureDay(){
        val today = Date()
        val tomorrow = Date()
        tomorrow.date = tomorrow.date + 2
        assertTrue(2.toLong() == today.farFrom(tomorrow))
    }

    @Test fun testDateFarFromPastDay(){
        val today = Date()
        val yesterday = Date()
        yesterday.date = yesterday.date - 2
        assertTrue(-2.toLong() == today.farFrom(yesterday))
    }

    @Test fun testDateFarFromToday(){
        val today = Date()
        val yesterday = Date()
        assertTrue(0.toLong() == today.farFrom(yesterday))
    }

    @Test fun testDateFarFromFutureMonth(){
        val today = Date()
        val tomorrow = Date()
        tomorrow.month = tomorrow.month + 1
        System.out.print(today.farFrom(tomorrow))
        assertTrue(31.toLong() == today.farFrom(tomorrow))
    }

    @Test fun testDateFarFromPastMonth(){
        val today = Date()
        val tomorrow = Date()
        tomorrow.month = tomorrow.month -1
        System.out.print(today.farFrom(tomorrow))
        assertTrue(-30.toLong() == today.farFrom(tomorrow))
    }
}
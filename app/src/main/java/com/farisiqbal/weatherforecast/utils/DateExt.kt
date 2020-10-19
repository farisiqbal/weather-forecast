package com.farisiqbal.weatherforecast.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar
import java.util.Locale
import java.util.TimeZone
import kotlin.math.abs

/**
 * Created by farisiqbal on 30/05/2020
 */
private val localeID = Locale("en", "id")

// Example: "2020-06-23" (default format by API)
val dateFormatDash = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", localeID)
// Example: "02 Feb 2017, 7PM"
val dateHourFormat = SimpleDateFormat("dd MMM yyyy, haa", localeID)
// Example : "9AM"
val hourFormat = SimpleDateFormat("haa", localeID)

fun Date?.formatDate(formatter: DateFormat): String? = this?.let { formatter.format(it) }

fun Date?.getDaysDifference(now: Date = Calendar.getInstance().time): Long {
    if (this == null) return 0

    val nowCalendar = GregorianCalendar(TimeZone.getDefault()).apply {
        time = now
        set(Calendar.HOUR, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
        set(Calendar.HOUR_OF_DAY, 0)
    }
    val specifiedCalendar = GregorianCalendar(TimeZone.getDefault()).also {
        it.time = this
        it.set(Calendar.HOUR, 0)
        it.set(Calendar.MINUTE, 0)
        it.set(Calendar.SECOND, 0)
        it.set(Calendar.MILLISECOND, 0)
        it.set(Calendar.HOUR_OF_DAY, 0)
    }

    val millisDiff = abs(nowCalendar.timeInMillis - specifiedCalendar.timeInMillis)
    val dayInMilliseconds = 24 * 60 * 60 * 1000
    return millisDiff / dayInMilliseconds
}
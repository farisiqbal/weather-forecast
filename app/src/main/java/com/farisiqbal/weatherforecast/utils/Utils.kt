package com.farisiqbal.weatherforecast.utils

import com.farisiqbal.weatherforecast.data.api.response.Main
import com.farisiqbal.weatherforecast.data.api.response.WeatherForecast
import java.util.Date

// data extensions
fun Main.getTemperatureRangeText(): String = "${tempMin.toDegree()}/${tempMax.toDegree()}"

fun WeatherForecast.getWeatherDateText(): String? {
    val forecastDate = dateFormatDash.parse(dtTxt)
    return when (forecastDate.getDaysDifference()) {
        0L -> "Today"
        1L -> "Tomorrow"
        else -> forecastDate.formatDate()
    }
}

fun Double.toDegree(): String = "${this.toInt()}°"
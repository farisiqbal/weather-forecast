package com.farisiqbal.weatherforecast.utils

import com.farisiqbal.weatherforecast.data.api.response.Main
import com.farisiqbal.weatherforecast.data.api.response.WeatherForecast

// data extensions
fun Main.getTemperatureRangeText(): String {
    return if (tempMin.toInt() == tempMax.toInt()) {
        tempMin.toDegree()
    } else {
        "${tempMin.toDegree()} to ${tempMax.toDegree()}"
    }
}

fun WeatherForecast.getWeatherDateText(): String? {
    val date = dateFormatDash.parse(dtTxt)
    val hour = date.formatDate(hourFormat)
    return when (date.getDaysDifference()) {
        0L -> "Today, $hour"
        1L -> "Tomorrow, $hour"
        else -> date.formatDate(dateHourFormat)
    }
}

// convert 3 hourly data to daily data. take one of each day
fun List<WeatherForecast>.getDailyData(): List<WeatherForecast> {
    return this.distinctBy { it.dtTxt.split(" ").first() }
}

fun Double.toDegree(): String = "${this.toInt()}°"
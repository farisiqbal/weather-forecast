package com.farisiqbal.weatherforecast.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.farisiqbal.weatherforecast.data.api.response.Main
import com.farisiqbal.weatherforecast.data.api.response.Weather

fun ImageView.setImageUrl(context: Context, imgUrl: String?) {
    if (imgUrl.isNullOrBlank()) return

    Glide.with(context)
        .load(imgUrl)
        .fitCenter()
        .into(this)
}

fun ImageView.setWeatherIconUrl(context: Context, iconCode: String?) =
    setImageUrl(context, "http://openweathermap.org/img/wn/$iconCode@2x.png")

fun Main.getTemperatureRangeText(): String = "${tempMin.toDegree()}/${tempMax.toDegree()}"

fun Weather.getWeatherDateText(): String {
    return "" // todo : today, tomorrow, 27 Feb 2020
}

fun Double.toDegree(): String = "$this°"